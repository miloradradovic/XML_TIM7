package com.project.poverenik.repository;

import com.project.poverenik.database.ExistManager;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

@Repository
public class ZalbaCutanjeRepository {

    @Autowired
    public ExistManager existManager;

    private final String collectionUri = "db/poverenik/xml/zalba-cutanje";

    private final String TARGET_NAMESPACE = "http://www.zalbacutanje";
/*
    public final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns:zc=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
            + "</xu:modifications>";
  */
    public final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS + "\" xmlns:zc=\"" + TARGET_NAMESPACE + "\"" + " xmlns:re=\"" + "http://reusability" +  "\">" +
        "<xu:update select=\"%1$s\">%2$s</xu:update>"
        + "</xu:modifications>";
    public final  String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns:zc=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
            + "</xu:modifications>";


    public boolean create(ZalbaCutanje zalbaCutanje) throws XMLDBException {
        return existManager.store(collectionUri, zalbaCutanje.getId(), zalbaCutanje);
    }

    public ResourceSet getAll() throws XMLDBException {
        return existManager.retrieve(collectionUri, "/zalba_cutanje", TARGET_NAMESPACE);
    }

    public XMLResource getOne(String id) throws XMLDBException {
        return existManager.load(collectionUri, id);
    }

    public boolean delete(String id) throws XMLDBException {
        return existManager.remove(collectionUri, id);
    }

    public boolean update(String id, String patch) throws XMLDBException {
        String xpath =  String.format("/zalba_cutanje[@id='%s']", id);
        return existManager.update(collectionUri, id, xpath, patch, UPDATE);
    }
    
    public ResourceSet getMaxId() throws XMLDBException  {
    	String xpath = "/zalba_cutanje[@id = max(/zalba_cutanje/@id)]";
    	return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }
}
