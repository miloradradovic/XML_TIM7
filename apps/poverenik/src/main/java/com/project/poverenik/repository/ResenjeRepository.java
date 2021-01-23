package com.project.poverenik.repository;

import com.project.poverenik.database.ExistManager;
import com.project.poverenik.model.resenje.Resenje;
import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

@Repository
public class ResenjeRepository {

    @Autowired
    public ExistManager existManager;

    private final String collectionUri = "db/proverenik/xml/resenje";

    private final String TARGET_NAMESPACE = "http://resenje";
    /*
        public final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
                + "\" xmlns:zc=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
                + "</xu:modifications>";
      */
    public final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS + "\" xmlns=\"" + TARGET_NAMESPACE + "\">" +
            "<xu:update select=\"%1$s\">%2$s</xu:update>"
            + "</xu:modifications>";
    public final  String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
            + "</xu:modifications>";


    public boolean create(Resenje resenje) throws XMLDBException {
        return existManager.store(collectionUri, resenje.getBroj(), resenje);
    }

    public ResourceSet getAll() throws XMLDBException {
        return existManager.retrieve(collectionUri, "/resenje", TARGET_NAMESPACE);
    }

    public XMLResource getOne(String broj) throws XMLDBException {
        return existManager.load(collectionUri, broj);
    }

    public boolean delete(String broj) throws XMLDBException {
        return existManager.remove(collectionUri, broj);
    }

    public boolean update(String broj, String patch) throws XMLDBException {
        String xpath =  String.format("/resenje[@broj='%s']", broj);
        return existManager.update(collectionUri, broj, xpath, patch, UPDATE);
    }
}
