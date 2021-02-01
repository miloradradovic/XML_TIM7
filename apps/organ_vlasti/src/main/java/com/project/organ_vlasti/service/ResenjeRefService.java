package com.project.organ_vlasti.service;

import com.project.organ_vlasti.jaxb.JaxB;
import com.project.organ_vlasti.model.resenje.database.ResenjeRef;
import com.project.organ_vlasti.model.user.User;
import com.project.organ_vlasti.model.util.lists.ResenjeRefList;
import com.project.organ_vlasti.repository.ResenjeRefRepository;
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
public class ResenjeRefService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ResenjeRefRepository resenjeRefRepository;

    private String getMaxId() throws XMLDBException, JAXBException {
        ResourceSet max = resenjeRefRepository.getMaxId();
        ResourceIterator resourceIterator = max.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return "0000";
            JAXBContext context = JAXBContext.newInstance(ResenjeRef.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ResenjeRef resenjeRefMax = (ResenjeRef) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return resenjeRefMax.getBody().getBroj();
        }
        return "0000";
    }

    public boolean create(ResenjeRef message) throws XMLDBException, JAXBException {

        String id = String.valueOf(Integer.parseInt(getMaxId())+1);
        message.getBody().setBroj(id);

        return resenjeRefRepository.create(message);

    }

    public ResenjeRefList getAll() throws XMLDBException, JAXBException {
        List<ResenjeRef> resenjeRefs = new ArrayList<>();

        ResourceSet resourceSet = resenjeRefRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(ResenjeRef.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ResenjeRef message = (ResenjeRef) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            resenjeRefs.add(message);
        }
        return new ResenjeRefList(resenjeRefs);
    }

    public ResenjeRef getOne(String id) throws JAXBException, XMLDBException {
        XMLResource xmlResource = resenjeRefRepository.getOne(id);

        if(xmlResource == null)
            return null;

        ResenjeRef message = null;

        JAXBContext context = JAXBContext.newInstance(ResenjeRef.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        message = (ResenjeRef) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return message;
    }

    public ResenjeRef getOneByBroj(String broj) throws XMLDBException, JAXBException {

        ResourceSet resourceSet = resenjeRefRepository.getOneByBroj(broj);
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(ResenjeRef.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ResenjeRef resenjeRef = (ResenjeRef) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return  resenjeRef;
        }
        return null;
    }

    public boolean delete(String id) throws XMLDBException {
        return resenjeRefRepository.delete(id);
    }

    public boolean update(ResenjeRef resenjeRef) throws JAXBException, XMLDBException {
        String patch = jaxB.marshall(resenjeRef.getClass(), resenjeRef);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        patch = patch.substring(patch.lastIndexOf("<resenje_ref xmlns=\"http://resenje\">"), patch.indexOf("</resenje_ref>") + "</resenje_ref>".length());
        return resenjeRefRepository.update(resenjeRef.getBody().getBroj(), patch);
    }
}
