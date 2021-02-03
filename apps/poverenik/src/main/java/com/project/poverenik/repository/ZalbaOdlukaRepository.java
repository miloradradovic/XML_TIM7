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

    public final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS + "\" xmlns:zoc=\"" + TARGET_NAMESPACE + "\"" + " xmlns:re=\"" + "http://reusability" +  "\">" +
            "<xu:update select=\"%1$s\">%2$s</xu:update>"
            + "</xu:modifications>";
    public final  String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns:zoc=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
            + "</xu:modifications>";


    public boolean create(ZalbaOdluka zalbaOdluka) throws XMLDBException {
        return existManager.store(collectionUri, zalbaOdluka.getZalbaOdlukaBody().getId(), zalbaOdluka);
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
        String xpath =  String.format("/zalba_odluka/zalba_odluka_body[@id='%s' or @id[.='%s'] or @id[text()='%s']]/ancestor::zalba_odluka ", id, id, id);
        ResourceSet s = existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
        return existManager.update(collectionUri, id, xpath, patch, UPDATE);
    }
    
    public ResourceSet getMaxId() throws XMLDBException  {
    	String xpath = "/zalba_odluka/zalba_odluka_body[@id = max(/zalba_odluka/zalba_odluka_body/@id)]/ancestor::zalba_odluka";
    	return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet getAllByUser(String email) throws XMLDBException {///re:osoba[@id = '%s']/ancestor::zalba_odluka
        String xpath = String.format("/zalba_odluka/zalba_odluka_body/child::zalilac/*[1]/*[1][@id = '%s']/ancestor::zalba_odluka", email);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet getAllByObradaOrNeobradjena() throws XMLDBException {
        String xpath = String.format("/zalba_odluka/zalba_odluka_body/child::status[.='%s' or .='%s']/ancestor::zalba_odluka", "neobradjena", "u obradi");
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet getOdbijene() throws XMLDBException {
        String xpath = String.format("/zalba_odluka/zalba_odluka_body/child::status[.='%s']/ancestor::zalba_odluka", "odbijena");
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet getPrihvacene() throws XMLDBException {
        String xpath = String.format("/zalba_odluka/zalba_odluka_body/child::status[.='%s']/ancestor::zalba_odluka", "prihvacena");
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet getPonistene() throws XMLDBException {
        String xpath = String.format("/zalba_odluka/zalba_odluka_body/child::status[.='%s']/ancestor::zalba_odluka", "ponistena");
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }
    
    public ResourceSet searchText(String text) throws XMLDBException {
    	String xpath = String.format(
				"/zalba_odluka/zalba_odluka_body[sadrzaj/*[local-name()='osnova_za_zalbu'][contains(.,'%s')] or protiv_resenja_zakljucka/*[local-name()='naziv_organa_koji_je_doneo_odluku'][contains(.,'%s')]]/ancestor::zalba_odluka",
				text, text);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }
}
