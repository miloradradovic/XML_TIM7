package com.project.organ_vlasti.repository;

import com.project.organ_vlasti.database.ExistManager;
import com.project.organ_vlasti.model.zahtev.Zahtev;
import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

@Repository
public class ZahtevRepository {

    @Autowired
    public ExistManager existManager;

    private final String collectionUri = "db/organ_vlasti/xml/zahtev";

    private final String TARGET_NAMESPACE = "http://www.zahtevcir";

    public final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS + "\" xmlns:zcir=\"" + TARGET_NAMESPACE + "\"" + " xmlns:re=\"" + "http://reusability" +  "\">" +
            "<xu:update select=\"%1$s\">%2$s</xu:update>"
            + "</xu:modifications>";
    public final  String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns:zcir=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
            + "</xu:modifications>";


    public boolean create(Zahtev zahtev) throws XMLDBException {
        return existManager.store(collectionUri, zahtev.getZahtevBody().getId(), zahtev);
    }

    public ResourceSet getAll() throws XMLDBException {
        return existManager.retrieve(collectionUri, "/zahtev", TARGET_NAMESPACE);
    }

    public XMLResource getOne(String id) throws XMLDBException {
        return existManager.load(collectionUri, id);
    }

    public boolean delete(String id) throws XMLDBException {
        return existManager.remove(collectionUri, id);
    }

    public boolean update(String id, String patch) throws XMLDBException {
        String xpath =  String.format("/zahtev/zahtev_body[@id='%s']/ancestor::zahtev", id);
        return existManager.update(collectionUri, id, xpath, patch, UPDATE);
    }
    
    public ResourceSet getMaxId() throws XMLDBException  {
    	String xpath = "/zahtev/zahtev_body[@id = max(/zahtev/zahtev_body/@id)]/ancestor::zahtev";
    	return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet getAllNeobradjen() throws XMLDBException {
        String xpath = String.format("/zahtev/zahtev_body/status[.= '%s']/ancestor::zahtev", "neobradjen");
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet getAllByUser(String email) throws XMLDBException {
        String xpath = String.format("/zahtev/zahtev_body/child::informacije_o_traziocu/*[1]/*[1][@id = '%s']/ancestor::zahtev", email);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }
}
