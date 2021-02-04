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

    private final String collectionUri = "db/poverenik/xml/resenje";

    private final String TARGET_NAMESPACE = "http://resenje";
    /*
        public final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
                + "\" xmlns:zc=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
                + "</xu:modifications>";
      */
    public final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS + "\" xmlns=\"" + TARGET_NAMESPACE + "\">" +
            "<xu:update select=\"%1$s\">%2$s</xu:update>"
            + "</xu:modifications>";
    public final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
            + "</xu:modifications>";


    public String create(Resenje resenje) throws XMLDBException {
        if (existManager.store(collectionUri, resenje.getResenjeBody().getBroj(), resenje)) {
            return resenje.getResenjeBody().getBroj();
        }
        return null;
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
        String xpath = String.format("/resenje/resenje_body[@broj='%s']/ancestor::resenje", broj);
        return existManager.update(collectionUri, broj, xpath, patch, UPDATE);
    }


    public ResourceSet getMaxId() throws XMLDBException {
        String xpath = "/resenje/resenje_body[@id = max(/resenje/resenje_body/@id)]/ancestor::resenje";
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet getAllByUser(String email) throws XMLDBException {
        String xpath = String.format("/resenje/resenje_body/uvodne_informacije/child::trazilac[@id = '%s']/ancestor::resenje", email);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet searchText(String text) throws XMLDBException {
        String xpath = String.format(
                "/resenje/resenje_body[child::uvodne_informacije[contains(.,'%s')] "
                        + "or child::podaci_o_resenju[contains(.,'%s')]"
                        + "or child::podaci_o_obrazlozenju[contains(.,'%s')]"
                        + "]/ancestor::resenje",
                text, text, text);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet getResenjeByZalba(String idZalbe) throws XMLDBException {
        String xpath = String.format("/resenje/resenje_body[@idZalbe='%s']/ancestor::resenje", idZalbe);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }
}
