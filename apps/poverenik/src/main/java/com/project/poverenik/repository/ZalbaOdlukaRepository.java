package com.project.poverenik.repository;

import com.project.poverenik.database.ExistManager;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

@Repository
public class ZalbaOdlukaRepository {

    @Autowired
    public ExistManager existManager;

    private final String collectionUri = "db/poverenik/xml/zalba-odluka";

    private final String TARGET_NAMESPACE = "http://www.zalbanaodlukucir";
    /*
        public final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
                + "\" xmlns:zc=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
                + "</xu:modifications>";
      */
    public final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS + "\" xmlns:zoc=\"" + TARGET_NAMESPACE + "\"" + " xmlns:re=\"" + "http://reusability" +  "\">" +
            "<xu:update select=\"%1$s\">%2$s</xu:update>"
            + "</xu:modifications>";
    public final  String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns:zoc=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
            + "</xu:modifications>";


    public boolean create(ZalbaOdluka zalbaOdluka) throws XMLDBException {
        return existManager.store(collectionUri, zalbaOdluka.getId(), zalbaOdluka);
    }

    public ResourceSet getAll() throws XMLDBException {
        return existManager.retrieve(collectionUri, "/zalba_odluka", TARGET_NAMESPACE);
    }

    public XMLResource getOne(String id) throws XMLDBException {
        return existManager.load(collectionUri, id);
    }

    public boolean delete(String id) throws XMLDBException {
        return existManager.remove(collectionUri, id);
    }

    public boolean update(String id, String patch) throws XMLDBException {
        String xpath =  String.format("/zalba_odluka[@id='%s']", id);
        return existManager.update(collectionUri, id, xpath, patch, UPDATE);
    }
    
    public ResourceSet getMaxId() throws XMLDBException  {
    	String xpath = "/zalba_odluka[@id = max(/zalba_odluka/@id)]";
    	return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }
}
