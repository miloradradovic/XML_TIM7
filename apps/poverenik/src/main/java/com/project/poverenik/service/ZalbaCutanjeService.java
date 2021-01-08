package com.project.poverenik.service;

import com.project.poverenik.jaxb.JaxB;
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


    public boolean create(ZalbaCutanje zalbaCutanje) throws XMLDBException {
        if (jaxB.validate(zalbaCutanje.getClass(), zalbaCutanje)){
            return zalbaCutanjeRepository.create(zalbaCutanje);
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
        return zalbaCutanjeRepository.update(zalbaCutanje.getId(), patch);
    }
}
