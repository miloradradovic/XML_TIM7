package com.project.organ_vlasti.repository;

import com.project.organ_vlasti.database.ExistManager;
import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

@Repository
public class ObavestenjeRepository {

    @Autowired
    public ExistManager existManager;

    private final String collectionUri = "db/organ_vlasti/xml/obavestenje";

    private final String TARGET_NAMESPACE = "http://www.obavestenje";

    public final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS + "\" xmlns:oba=\"" + TARGET_NAMESPACE + "\"" + " xmlns:re=\"" + "http://reusability" + "\">" +
            "<xu:update select=\"%1$s\">%2$s</xu:update>"
            + "</xu:modifications>";
    public final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns:oba=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
            + "</xu:modifications>";


    public String create(Obavestenje obavestenje) throws XMLDBException {
        if (existManager.store(collectionUri, obavestenje.getObavestenjeBody().getBroj(), obavestenje, false)) {
            return obavestenje.getObavestenjeBody().getId();
        }
        return null;
    }

    public ResourceSet getAll() throws XMLDBException {
        return existManager.retrieve(collectionUri, "/obavestenje", TARGET_NAMESPACE);
    }

    public XMLResource getOne(String broj) throws XMLDBException {
        return existManager.load(collectionUri, broj);
    }

    public boolean delete(String broj) throws XMLDBException {
        return existManager.remove(collectionUri, broj);
    }

    public boolean update(String broj, String patch) throws XMLDBException {
        String xpath = String.format("/obavestenje/obavestenje_body[@broj='%s']/ancestor::obavestenje", broj);
        return existManager.update(collectionUri, broj, xpath, patch, UPDATE);
    }

    public ResourceSet getMaxId() throws XMLDBException {
        String xpath = "/obavestenje/obavestenje_body[@id = max(/obavestenje/obavestenje_body/@id)]/ancestor::obavestenje";
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet searchText(String text) throws XMLDBException {
        String xpath = String.format(
                "/obavestenje/obavestenje_body[child::tekst_zahteva/*[local-name()='opis_trazene_informacije'][contains(.,'%s')]]/ancestor::obavestenje",
                text, text);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet getAllByUser(String email) throws XMLDBException {
        String xpath = String.format("/obavestenje/obavestenje_body/child::informacije_o_podnosiocu/*[1]/*[1][@id = '%s']/ancestor::obavestenje", email);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet getObavestenjeByZahtev(String idZahteva) throws XMLDBException {
        String xpath = String.format("/obavestenje/obavestenje_body[@idZahteva='%s']/ancestor::obavestenje", idZahteva);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }
}
