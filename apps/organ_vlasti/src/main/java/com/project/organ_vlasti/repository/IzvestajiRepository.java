package com.project.organ_vlasti.repository;

import com.project.organ_vlasti.database.ExistManager;
import com.project.organ_vlasti.model.izvestaji.Izvestaj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

@Repository
public class IzvestajiRepository {

    @Autowired
    public ExistManager existManager;

    private final String collectionUri = "db/organ_vlasti/xml/izvestaj";

    private final String TARGET_NAMESPACE = "http://izvestaji";


    public boolean create(Izvestaj izvestaj) throws XMLDBException {
        return existManager.store(collectionUri, izvestaj.getIzvestajBody().getId(), izvestaj);
    }

    public ResourceSet getAll() throws XMLDBException {
        return existManager.retrieve(collectionUri, "/izvestaj", TARGET_NAMESPACE);
    }

    public XMLResource getOne(String id) throws XMLDBException {
        return existManager.load(collectionUri, id);
    }

    public boolean delete(String id) throws XMLDBException {
        return existManager.remove(collectionUri, id);
    }

    public ResourceSet getMaxId() throws XMLDBException  {
        String xpath = "/izvestaj/izvestaj_body[@id = max(/izvestaj/izvestaj_body/@id)]/ancestor::izvestaj";
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public boolean update(Izvestaj izvestaj) throws XMLDBException {

        return existManager.store(collectionUri, izvestaj.getIzvestajBody().getId(), izvestaj);
    }
}
