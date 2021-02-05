package com.project.poverenik.database;

import com.project.poverenik.model.resenje.Resenje;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import com.project.poverenik.service.MetadataService;
import org.exist.xmldb.DatabaseImpl;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.modules.XUpdateQueryService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.OutputKeys;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;

@Component
public class ExistManager {

    @Autowired
    private MetadataService metadataService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void createConnection() throws XMLDBException {
        Database db = new DatabaseImpl();
        db.setProperty("create-database", "true");

        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(db);
    }

    public void closeConnection(Collection collection, XMLResource res) {

        if (res != null) {
            try {
                ((EXistResource) res).freeResources();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }

        if (collection != null) {
            try {
                collection.close();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }

    //creates or gets path
    public Collection getOrCreateCollection(String collectionUri, Integer pathOffset) throws XMLDBException {
        //createConnection(); //<- for testing purpose
        Collection collection = DatabaseManager.getCollection(authenticationManager.getUri() + collectionUri, authenticationManager.getUser(), authenticationManager.getPassword());

        if (collection == null) {
            if (collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }
            String[] pathSegments = collectionUri.split("/");

            if (pathSegments.length > 0) {
                StringBuilder newPath = new StringBuilder();

                for (int i = 0; i <= pathOffset; i++) {
                    newPath.append("/").append(pathSegments[i]);

                }

                Collection startColl = DatabaseManager.getCollection(authenticationManager.getUri() + newPath, authenticationManager.getUser(), authenticationManager.getPassword());

                if (startColl == null) {
                    String parentPath = newPath.substring(0, newPath.lastIndexOf("/"));
                    Collection parentCollection = DatabaseManager.getCollection(authenticationManager.getUri() + parentPath, authenticationManager.getUser(), authenticationManager.getPassword());

                    CollectionManagementService service = (CollectionManagementService) parentCollection.getService("CollectionManagementService", "1.0");
                    collection = service.createCollection(pathSegments[pathOffset]);
                    collection.close();
                    parentCollection.close();

                } else {
                    startColl.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathOffset);
        }

        return collection;
    }

    //create new
    public boolean store(String collectionUri, String documentId, Object xml) throws XMLDBException {
        createConnection();
        Collection collection = null;
        XMLResource res = null;
        OutputStream os = new ByteArrayOutputStream();

        try {
            collection = getOrCreateCollection(collectionUri, 0);
            res = (XMLResource) collection.createResource(documentId, XMLResource.RESOURCE_TYPE);

            JAXBContext context = JAXBContext.newInstance(xml.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.bind.xmlHeaders",
                    " <?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            marshaller.setProperty("com.sun.xml.bind.xmlHeaders",
                    "<?xml-stylesheet type=\"text/xsl\" href=\"../xsl/grddl.xsl\"?>");
            marshaller.marshal(xml, os);

            res.setContent(os);
            collection.storeResource(res);

            String id = collectionUri.split("/")[3] + documentId;
            if (xml instanceof Resenje)
                metadataService.extractMetadata("/resenja", os, id);
            else if (xml instanceof ZalbaCutanje)
                metadataService.extractMetadata("/zalbe", os, id);
            else if (xml instanceof ZalbaOdluka)
                metadataService.extractMetadata("/zalbe", os, id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(collection, res);
        }
        return true;
    }

    //export xml to outputStream
    public String getOutputStream(Object xml) {
        StringWriter sw = new StringWriter();

        JAXBContext context;
        try {
            context = JAXBContext.newInstance(xml.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(xml, sw);
        } catch (JAXBException e) {
            System.out.println("getOutputStream threw an error!");
            e.printStackTrace();
        }
        return sw.toString();
    }

    //get one
    public XMLResource load(String collectionUri, String documentId) throws XMLDBException {
        createConnection();
        Collection collection = null;
        XMLResource res;

        try {
            collection = DatabaseManager.getCollection(authenticationManager.getUri() + collectionUri, authenticationManager.getUser(), authenticationManager.getPassword());
            if (collection == null) {
                return null;
            }
            collection.setProperty(OutputKeys.INDENT, "yes");
            res = (XMLResource) collection.getResource(documentId);

            return res;
        } finally {
            if (collection != null) {
                try {
                    collection.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }

    //get all
    public ResourceSet retrieve(String collectionUri, String xPathExp, String TARGET_NAMESPACE) throws XMLDBException {
        createConnection();
        Collection collection = null;
        ResourceSet res;

        try {
            collection = getOrCreateCollection(collectionUri, 0);

            XPathQueryService xPathQueryService = (XPathQueryService) collection.getService("XPathQueryService", "1.0");

            xPathQueryService.setProperty(OutputKeys.INDENT, "yes");
            xPathQueryService.setNamespace("", TARGET_NAMESPACE);

            res = xPathQueryService.query(xPathExp);
        } finally {
            if (collection != null) {
                try {
                    collection.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

        return res;
    }

    //delete
    public boolean remove(String collectionUri, String documentId) throws XMLDBException {
        createConnection();

        Collection collection = null;
        try {
            collection = DatabaseManager.getCollection(authenticationManager.getUri() + collectionUri, authenticationManager.getUser(),
                    authenticationManager.getPassword());
            Resource foundFile = collection.getResource(documentId);

            if (foundFile == null)
                return false;

            collection.removeResource(foundFile);

        } finally {
            // don't forget to cleanup
            if (collection != null) {
                try {
                    collection.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

        return true;
    }

    public boolean update(String collectionUri, String documentId, String contextXPath, String patch, String UPDATE) throws XMLDBException {
        createConnection();
        Collection collection = null;
        try {
            collection = DatabaseManager.getCollection(authenticationManager.getUri() + collectionUri, authenticationManager.getUser(), authenticationManager.getPassword());

            Resource foundFile = collection.getResource(documentId);
            if (foundFile == null)
                return false;

            XUpdateQueryService service = (XUpdateQueryService) collection.getService("XUpdateQueryService", "1.0");
            service.setProperty(OutputKeys.INDENT, "yes");

            service.updateResource(documentId, String.format(UPDATE, contextXPath, patch));
        } finally {
            if (collection != null) {
                try {
                    collection.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
        return true;
    }
}
