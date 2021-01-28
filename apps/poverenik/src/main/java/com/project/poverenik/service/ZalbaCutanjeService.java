package com.project.poverenik.service;

import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.mappers.ZalbaCutanjeMapper;
import com.project.poverenik.model.util.lists.ZalbaCutanjeList;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.repository.ZalbaCutanjeRepository;
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
public class ZalbaCutanjeService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ZalbaCutanjeRepository zalbaCutanjeRepository;

    private String getMaxId() throws XMLDBException, JAXBException {
    	ResourceSet max = zalbaCutanjeRepository.getMaxId();
    	ResourceIterator resourceIterator = max.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return "0";
            JAXBContext context = JAXBContext.newInstance(ZalbaCutanje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ZalbaCutanje zalbaCutanjeMax = (ZalbaCutanje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return zalbaCutanjeMax.getZalbaCutanjeBody().getId();
        }
        return "0";
    }

    public boolean create(ZalbaCutanje zalbaCutanjeDTO, String userEmail) throws XMLDBException, JAXBException {
        if (jaxB.validate(zalbaCutanjeDTO.getClass(), zalbaCutanjeDTO)){
        	String id = String.valueOf(Integer.parseInt(getMaxId())+1);

        	ZalbaCutanje zalbaCutanje = ZalbaCutanjeMapper.mapFromDTO(zalbaCutanjeDTO, id, userEmail);
        	
            if(jaxB.validate(zalbaCutanje.getClass(), zalbaCutanje)){
                return zalbaCutanjeRepository.create(zalbaCutanje);
            }else {
                return false;
            }
        }
        return false;
    }

    public ZalbaCutanjeList getAll() throws XMLDBException, JAXBException {
        List<ZalbaCutanje> zalbaCutanjeList = new ArrayList<>();

        ResourceSet resourceSet = zalbaCutanjeRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(ZalbaCutanje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ZalbaCutanje zalbaCutanje = (ZalbaCutanje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            zalbaCutanjeList.add(zalbaCutanje);
        }
        return new ZalbaCutanjeList(zalbaCutanjeList);
    }

    public ZalbaCutanje getOne(String id) throws JAXBException, XMLDBException {
        XMLResource xmlResource = zalbaCutanjeRepository.getOne(id);

        if(xmlResource == null)
            return null;

        ZalbaCutanje zalbaCutanje = null;

        JAXBContext context = JAXBContext.newInstance(ZalbaCutanje.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        zalbaCutanje = (ZalbaCutanje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return zalbaCutanje;
    }

    public boolean delete(String id) throws XMLDBException {
        return zalbaCutanjeRepository.delete(id);
    }

    public boolean update(ZalbaCutanje zalbaCutanje) throws JAXBException, XMLDBException {
        String patch = jaxB.marshall(zalbaCutanje.getClass(), zalbaCutanje);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        patch = patch.substring(patch.lastIndexOf("<zc:naziv>"), patch.indexOf("</zc:podaci_o_podnosiocu>") + "</zc:podaci_o_podnosiocu>".length());
        return zalbaCutanjeRepository.update(zalbaCutanje.getZalbaCutanjeBody().getId(), patch);
    }
}
