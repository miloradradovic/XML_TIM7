package com.project.organ_vlasti.service;

import com.project.organ_vlasti.jaxb.JaxB;
import com.project.organ_vlasti.mappers.ObavestenjeMapper;
import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import com.project.organ_vlasti.model.util.lists.ObavestenjeList;
import com.project.organ_vlasti.repository.ObavestenjeRepository;

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
public class ObavestenjeService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ObavestenjeRepository obavestenjeRepository;
    
    private String getMaxId() throws XMLDBException, JAXBException {
        ResourceSet max = obavestenjeRepository.getMaxId();
        ResourceIterator resourceIterator = max.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return "0000";
            JAXBContext context = JAXBContext.newInstance(Obavestenje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Obavestenje obavestenjeMax = (Obavestenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return obavestenjeMax.getId();
        }
        return "0000";
    }

    public boolean create(Obavestenje obavestenjeDTO) throws XMLDBException, JAXBException {
        
        if (jaxB.validate(obavestenjeDTO.getClass(), obavestenjeDTO)){
        	String id = String.valueOf(Integer.parseInt(getMaxId())+1);
            Obavestenje obavestenje = ObavestenjeMapper.mapFromDTO(obavestenjeDTO, id);

            if(jaxB.validate(obavestenje.getClass(), obavestenje)){
            	 return obavestenjeRepository.create(obavestenje);
            }else {
                return false;
            }
        }
        return false;
         
    }

    public ObavestenjeList getAll() throws XMLDBException, JAXBException {
        List<Obavestenje> obavestenjeList = new ArrayList<>();

        ResourceSet resourceSet = obavestenjeRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(Obavestenje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Obavestenje obavestenje = (Obavestenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            obavestenjeList.add(obavestenje);
        }
        return new ObavestenjeList(obavestenjeList);
    }

    public Obavestenje getOne(String broj) throws JAXBException, XMLDBException {
        XMLResource xmlResource = obavestenjeRepository.getOne(broj);

        if(xmlResource == null)
            return null;

        Obavestenje obavestenje = null;

        JAXBContext context = JAXBContext.newInstance(Obavestenje.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        obavestenje = (Obavestenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return obavestenje;
    }

    public boolean delete(String broj) throws XMLDBException {
        return obavestenjeRepository.delete(broj);
    }

    public boolean update(Obavestenje obavestenje) throws JAXBException, XMLDBException {
        String patch = jaxB.marshall(obavestenje.getClass(), obavestenje);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        patch = patch.substring(patch.lastIndexOf("<oba:naziv_organa property=\"pred:organ_vlasti\" datatype=\"xs:string\" sediste=\"\">"), patch.indexOf("</oba:opcija_dostave>") + "</oba:opcija_dostave>".length());
        return obavestenjeRepository.update(obavestenje.getBroj(), patch);
    }
}
