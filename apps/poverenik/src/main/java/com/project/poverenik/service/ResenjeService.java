package com.project.poverenik.service;

import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.mappers.ResenjeMapper;
import com.project.poverenik.model.resenje.Resenje;
import com.project.poverenik.model.util.lists.ResenjeList;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
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
import javax.xml.namespace.QName;
import javax.xml.ws.Action;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResenjeService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ResenjeRepository resenjeRepository;

    @Autowired
    ZalbaCutanjeService zalbaCutanjeService;

    @Autowired
    ZalbaOdlukaService zalbaOdlukaService;

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


    public String create(Resenje resenjeDTO) throws XMLDBException, JAXBException {
        if (jaxB.validate(resenjeDTO.getClass(), resenjeDTO)){
            String id = String.valueOf(Integer.parseInt(getMaxId())+1);


            String zalba = resenjeDTO.getResenjeBody().getOtherAttributes().get(new QName("idZalbe")).split("/")[0];
            String idZalbe = resenjeDTO.getResenjeBody().getOtherAttributes().get(new QName("idZalbe")).split("/")[1];
            String email = "";
            if(zalba.equals("cutanje")){
                ZalbaCutanje zalbaCutanje = zalbaCutanjeService.getOne(idZalbe);
                email = zalbaCutanje.getZalbaCutanjeBody().getPodaciOPodnosiocu().getOsoba().getOtherAttributes().get(new QName("id"));
            }else{
                ZalbaOdluka zalbaOdluka = zalbaOdlukaService.getOne(idZalbe);
                email = zalbaOdluka.getZalbaOdlukaBody().getZalilac().getTipLica().getOsoba().getOtherAttributes().get(new QName("id"));
            }




            Resenje resenje = ResenjeMapper.mapFromDTO(resenjeDTO, id, email);
        	
        	if(jaxB.validate(resenje.getClass(), resenje)){
                return resenjeRepository.create(resenje);
            }else {
                return null;
            }
        }
        return null;
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

    public ResenjeList getByUser(String email) throws XMLDBException, JAXBException {
        List<Resenje> resenjeList = new ArrayList<>();

        ResourceSet resourceSet = resenjeRepository.getAllByUser(email);
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
}
