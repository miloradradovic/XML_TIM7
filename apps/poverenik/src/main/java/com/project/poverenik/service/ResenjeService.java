package com.project.poverenik.service;

import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.mappers.ResenjeMapper;
import com.project.poverenik.model.resenje.Resenje;
import com.project.poverenik.model.util.lists.ResenjeList;
import com.project.poverenik.repository.ResenjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResenjeService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ResenjeRepository resenjeRepository;

    private String getMaxId() throws XMLDBException, JAXBException {
        ResourceSet max = resenjeRepository.getMaxId();
        ResourceIterator resourceIterator = max.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return "0000";
            JAXBContext context = JAXBContext.newInstance(Resenje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Resenje resenjeMax = (Resenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return resenjeMax.getResenjeBody().getId();
        }
        return "0000";
    }


    public boolean create(Resenje resenjeDTO) throws XMLDBException, JAXBException {
        if (jaxB.validate(resenjeDTO.getClass(), resenjeDTO)){
            String id = String.valueOf(Integer.parseInt(getMaxId())+1);
            Resenje resenje = ResenjeMapper.mapFromDTO(resenjeDTO, id);
        	
        	if(jaxB.validate(resenje.getClass(), resenje)){
                return resenjeRepository.create(resenje);
            }else {
                return false;
            }
        }
        return false;
    }

    public ResenjeList getAll() throws XMLDBException, JAXBException {

        List<Resenje> resenjeList = new ArrayList<>();

        ResourceSet resourceSet = resenjeRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(Resenje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Resenje resenje = (Resenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            resenjeList.add(resenje);
        }
        return new ResenjeList(resenjeList);
    }

    public Resenje getOne(String broj) throws JAXBException, XMLDBException {
        XMLResource xmlResource = resenjeRepository.getOne(broj);

        if(xmlResource == null)
            return null;

        Resenje resenje = null;

        JAXBContext context = JAXBContext.newInstance(Resenje.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        resenje = (Resenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return resenje;
    }

    public boolean delete(String broj) throws XMLDBException {
        return resenjeRepository.delete(broj);
    }

    public boolean update(Resenje resenje) throws JAXBException, XMLDBException {
        String patch = jaxB.marshall(resenje.getClass(), resenje);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        patch = patch.substring(patch.lastIndexOf("<uvodne_informacije>"), patch.indexOf("</poverenik>") + "</poverenik>".length());
        return resenjeRepository.update(resenje.getResenjeBody().getBroj(), patch);
    }
}
