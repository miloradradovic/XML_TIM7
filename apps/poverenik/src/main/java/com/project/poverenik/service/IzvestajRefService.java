package com.project.poverenik.service;

import com.project.poverenik.model.izvestaj.database.IzvestajRef;
import com.project.poverenik.model.util.lists.IzvestajRefList;
import com.project.poverenik.repository.IzvestajRefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;

@Service
public class IzvestajRefService {


    @Autowired
    private IzvestajRefRepository izvestajRefRepository;

    private String getMaxId() throws XMLDBException, JAXBException {
        ResourceSet max = izvestajRefRepository.getMaxId();
        ResourceIterator resourceIterator = max.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return "0000";
            JAXBContext context = JAXBContext.newInstance(IzvestajRef.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            IzvestajRef izvestajRefMax = (IzvestajRef) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return izvestajRefMax.getBody().getId();
        }
        return "0000";
    }

    public boolean create(IzvestajRef izvestajRef) throws XMLDBException, JAXBException {

        String id = String.valueOf(Integer.parseInt(getMaxId())+1);
        izvestajRef.getBody().setId(id);

        return izvestajRefRepository.create(izvestajRef);

    }

    public IzvestajRefList getAll(String procitano) throws XMLDBException, JAXBException {
        List<IzvestajRef> resenjeRefs = new ArrayList<>();

        ResourceSet resourceSet = izvestajRefRepository.getAllByProcitano(procitano);
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(IzvestajRef.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            IzvestajRef izvestajRef = (IzvestajRef) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            resenjeRefs.add(izvestajRef);
        }
        return new IzvestajRefList(resenjeRefs);
    }

    public IzvestajRef getOne(String id) throws JAXBException, XMLDBException {
        XMLResource xmlResource = izvestajRefRepository.getOne(id);

        if(xmlResource == null)
            return null;

        IzvestajRef izvestajRef = null;

        JAXBContext context = JAXBContext.newInstance(IzvestajRef.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        izvestajRef = (IzvestajRef) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return izvestajRef;
    }

    public IzvestajRef getOneByBroj(String broj) throws XMLDBException, JAXBException {

        ResourceSet resourceSet = izvestajRefRepository.getOneByBroj(broj);
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(IzvestajRef.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            IzvestajRef izvestajRef = (IzvestajRef) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return  izvestajRef;
        }
        return null;
    }

    public boolean delete(String id) throws XMLDBException {
        return izvestajRefRepository.delete(id);
    }

    public boolean update(IzvestajRef resenjeRef) throws XMLDBException {
        return izvestajRefRepository.update(resenjeRef);
    }
}
