package com.project.organ_vlasti.repository;

import com.project.organ_vlasti.database.ExistManager;
import com.project.organ_vlasti.model.resenje.database.ResenjeRef;
import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

@Repository
public class ResenjeRefRepository {

    @Autowired
    public ExistManager existManager;

    private final String collectionUri = "db/organ_vlasti/xml/resenje_ref";

    private final String TARGET_NAMESPACE = "http://resenje";


    public boolean create(ResenjeRef resenjeRef) throws XMLDBException {
        return existManager.store(collectionUri, resenjeRef.getBody().getBroj(), resenjeRef);
    }

    public ResourceSet getAllByProcitano(String procitano) throws XMLDBException {
        String xpath =  String.format("/resenje_ref/body[@procitano ='%s']/ancestor::resenje_ref", procitano);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }
    
    public ResourceSet getOneStatusByBroj(String broj, String status) throws XMLDBException {
        String xpath =  String.format("/resenje_ref/body[.='%s' and @procitano ='%s']/ancestor::resenje_ref", broj, status);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public ResourceSet getOneByBroj(String broj) throws XMLDBException {
        String xpath =  String.format("/resenje_ref/body[.='%s']/ancestor::resenje_ref", broj);
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public XMLResource getOne(String id) throws XMLDBException {
        return existManager.load(collectionUri, id);
    }

    public boolean delete(String id) throws XMLDBException {
        return existManager.remove(collectionUri, id);
    }

    public ResourceSet getMaxId() throws XMLDBException  {
        String xpath = "/resenje_ref/body[@broj = max(/resenje_ref/body/@broj)]/ancestor::resenje_ref";
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }

    public boolean update(ResenjeRef resenjeRef) throws XMLDBException {

        return existManager.store(collectionUri, resenjeRef.getBody().getBroj(), resenjeRef);
    }
}
