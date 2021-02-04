package com.project.poverenik.repository;

import com.project.poverenik.database.ExistManager;
import com.project.poverenik.model.izvestaj.database.IzvestajRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

@Repository
public class IzvestajRefRepository {

    @Autowired
    public ExistManager existManager;

    private final String collectionUri = "db/poverenik/xml/izvestaj_ref";

    private final String TARGET_NAMESPACE = "http://izvestaji";


    public boolean create(IzvestajRef izvestajRef) throws XMLDBException {
        return existManager.store(collectionUri, izvestajRef.getBody().getId(), izvestajRef);
    }

    public ResourceSet getAllByProcitano(String procitano) throws XMLDBException {
        String xpath = String.format("/izvestaj_ref/body[@procitano ='%s']/ancestor::izvestaj_ref", procitano);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet getOneByBroj(String broj) throws XMLDBException {
        String xpath = String.format("/izvestaj_ref/body[.='%s']/ancestor::izvestaj_ref", broj);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }
    
    public ResourceSet getOneStatusByBroj(String broj, String status) throws XMLDBException {
        String xpath = String.format("/izvestaj_ref/body[.='%s' and @procitano ='%s']/ancestor::izvestaj_ref", broj);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public XMLResource getOne(String id) throws XMLDBException {
        return existManager.load(collectionUri, id);
    }

    public boolean delete(String id) throws XMLDBException {
        return existManager.remove(collectionUri, id);
    }

    public ResourceSet getMaxId() throws XMLDBException {
        String xpath = "/izvestaj_ref/body[@id = max(/izvestaj_ref/body/@id)]/ancestor::izvestaj_ref";
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public boolean update(IzvestajRef izvestajRef) throws XMLDBException {

        return existManager.store(collectionUri, izvestajRef.getBody().getId(), izvestajRef);
    }
}
