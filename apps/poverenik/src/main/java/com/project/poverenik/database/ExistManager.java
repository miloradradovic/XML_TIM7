package com.project.poverenik.database;

import org.exist.xmldb.DatabaseImpl;
import org.exist.xmldb.EXistResource;
import org.exist.xupdate.XUpdateProcessor;
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
import javax.xml.bind.PropertyException;
import javax.xml.transform.OutputKeys;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

@Component
public class ExistManager {

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final String TARGET_NAMESPACE = "http://user";

    public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
            + "</xu:modifications>";
    public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
            + "</xu:modifications>";


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

        if (collection == null){
            if(collectionUri.startsWith("/")){
                collectionUri = collectionUri.substring(1);
            }
            String[] pathSegments = collectionUri.split("/");

            if(pathSegments.length > 0){
                StringBuilder newPath = new StringBuilder();

                for (int i = 0; i <= pathOffset; i++){
                    newPath.append("/" + pathSegments[i]);

                };

                Collection startColl = DatabaseManager.getCollection(authenticationManager.getUri() + newPath, authenticationManager.getUser(), authenticationManager.getPassword());

                if (startColl == null){
                    String parentPath = newPath.substring(0, newPath.lastIndexOf("/"));
                    Collection parentCollection = DatabaseManager.getCollection(authenticationManager.getUri() + parentPath, authenticationManager.getUser(), authenticationManager.getPassword());

                    CollectionManagementService service = (CollectionManagementService)parentCollection.getService("CollectionManagementService", "1.0");
                    collection = service.createCollection(pathSegments[pathOffset]);
                    collection.close();
                    parentCollection.close();

                }else{
                    startColl.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathOffset);
        }

        return collection;
    }

    //create new
    public void store(String collectionId, String documentId, Object xml) throws XMLDBException {
        createConnection();
        Collection collection = null;
        XMLResource res = null;
        OutputStream os = new ByteArrayOutputStream();

        try {
            collection = getOrCreateCollection(collectionId, 0);
            res = (XMLResource) collection.createResource(documentId, XMLResource.RESOURCE_TYPE);

            JAXBContext context = JAXBContext.newInstance(xml.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(xml, os);

            res.setContent(os);
            collection.storeResource(res);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConnection(collection, res);
        }
    }

    public XMLResource load(String collectionUri, String documentId) throws XMLDBException {
        createConnection();
        Collection collection = null;
        XMLResource res = null;

        try {
            collection = DatabaseManager.getCollection(authenticationManager.getUri() + collectionUri, authenticationManager.getUser(), authenticationManager.getPassword());
            collection.setProperty(OutputKeys.INDENT, "yes");
            res = (XMLResource) collection.getResource(documentId);

            if (res == null)
                System.out.println("[WARNING] Document '" + documentId + "' can not be found!");

            return res;
        }finally {
            if (collection != null) {
                try {
                    collection.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }

    public ResourceSet retrieve(String collectionUri, String xPathExp) throws XMLDBException {
        createConnection();
        Collection collection = null;
        ResourceSet res = null;

        try {
            collection = DatabaseManager.getCollection(authenticationManager.getUri() + collectionUri, authenticationManager.getUser(), authenticationManager.getPassword());

            XPathQueryService xPathQueryService = (XPathQueryService) collection.getService("XPathQueryService", "1.0");

            xPathQueryService.setProperty(OutputKeys.INDENT, "yes");
            xPathQueryService.setNamespace("", TARGET_NAMESPACE);

            res = xPathQueryService.query(xPathExp);
        }finally {
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

    public void remove(String collectionUri, String documentId) throws XMLDBException {
        createConnection();

        Collection collection = null;
        try {
            collection = DatabaseManager.getCollection(authenticationManager.getUri() + collectionUri, authenticationManager.getUser(),
                    authenticationManager.getPassword());
            Resource foundFile = collection.getResource(documentId);

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
    }

    public void update(String collectionUri, String document, String contextXPath, String patch) throws XMLDBException {
        createConnection();
        Collection collection = null;
        try {
            collection = DatabaseManager.getCollection(authenticationManager.getUri() + collectionUri, authenticationManager.getUser(), authenticationManager.getPassword());

            XUpdateQueryService service = (XUpdateQueryService) collection.getService("XUpdateQueryService", "1.0");
            service.setProperty(OutputKeys.INDENT, "yes");

            service.updateResource(document, String.format(UPDATE, contextXPath, patch));
        }finally {
            if (collection != null) {
                try {
                    collection.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }

    public void append(String collectionUri, String document, String contextXPath, String patch) throws XMLDBException {
        createConnection();
        Collection collection = null;
        try {
            collection = DatabaseManager.getCollection(authenticationManager.getUri() + collectionUri, authenticationManager.getUser(), authenticationManager.getPassword());

            XUpdateQueryService service = (XUpdateQueryService) collection.getService("XUpdateQueryService", "1.0");
            service.setProperty(OutputKeys.INDENT, "yes");

            service.updateResource(document, String.format(APPEND, contextXPath, patch));
        }finally {
            if (collection != null)
                collection.close();
        }
    }

    // for adding from file
    public void storeFromFile(String collectionId, String documentId, String filePath) throws XMLDBException {
        createConnection();
        Collection collection = null;
        XMLResource res = null;

        try {
            collection = getOrCreateCollection(collectionId, 0);
            res = (XMLResource) collection.createResource(documentId, XMLResource.RESOURCE_TYPE);
            File f = new File(filePath);

            if(!f.canRead()){
                System.out.println("[ERROR] Cannot read the file: " + filePath);
                return;
            }

            res.setContent(f);
            collection.storeResource(res);
        }finally {
            closeConnection(collection, res);
        }
    }

}
