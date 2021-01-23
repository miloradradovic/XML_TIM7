package com.project.poverenik.service;

import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.mappers.ZalbaCutanjeMapper;
import com.project.poverenik.mappers.ZalbaOdlukaMapper;
import com.project.poverenik.model.util.ComplexTypes.Tadresa;
import com.project.poverenik.model.util.ComplexTypes.Tnapomena;
import com.project.poverenik.model.util.ComplexTypes.TpodaciPovereniku;
import com.project.poverenik.model.util.lists.ZalbaOdlukaList;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import com.project.poverenik.repository.ZalbaOdlukaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZalbaOdlukaService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ZalbaOdlukaRepository zalbaOdlukaRepository;

    private String getMaxId() throws XMLDBException, JAXBException {
    	ResourceSet max = zalbaOdlukaRepository.getMaxId();
    	ResourceIterator resourceIterator = max.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return "0";
            JAXBContext context = JAXBContext.newInstance(ZalbaOdluka.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ZalbaOdluka zalbaOdlukaMax = (ZalbaOdluka) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return zalbaOdlukaMax.getId();
        }
        return "0";
    }

    public boolean create(ZalbaOdluka zalbaOdlukaDTO) throws XMLDBException, NumberFormatException, JAXBException {
        if (jaxB.validate(zalbaOdlukaDTO.getClass(), zalbaOdlukaDTO)){
        	
        	String id = String.valueOf(Integer.parseInt(getMaxId())+1);
        	
        	ZalbaOdluka zalbaOdluka = ZalbaOdlukaMapper.mapFromDTO(zalbaOdlukaDTO, id);
        	
            if(jaxB.validate(zalbaOdluka.getClass(), zalbaOdluka)){
                return zalbaOdlukaRepository.create(zalbaOdluka);
            }else {
                return false;
            }
        	
        	
        }
        return false;
    }

    public ZalbaOdlukaList getAll() throws XMLDBException, JAXBException {
        List<ZalbaOdluka> zalbaOdlukaList = new ArrayList<>();

        ResourceSet resourceSet = zalbaOdlukaRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(ZalbaOdluka.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ZalbaOdluka zalbaOdluka = (ZalbaOdluka) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            zalbaOdlukaList.add(zalbaOdluka);
        }
        return new ZalbaOdlukaList(zalbaOdlukaList);
    }

    public ZalbaOdluka getOne(String id) throws JAXBException, XMLDBException {
        XMLResource xmlResource = zalbaOdlukaRepository.getOne(id);

        if(xmlResource == null)
            return null;

        ZalbaOdluka zalbaOdluka = null;

        JAXBContext context = JAXBContext.newInstance(ZalbaOdluka.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        zalbaOdluka = (ZalbaOdluka) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return zalbaOdluka;
    }

    public boolean delete(String id) throws XMLDBException {
        return zalbaOdlukaRepository.delete(id);
    }

    public boolean update(ZalbaOdluka zalbaOdluka) throws JAXBException, XMLDBException {
        String patch = jaxB.marshall(zalbaOdluka.getClass(), zalbaOdluka);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        patch = patch.substring(patch.lastIndexOf("<zoc:naslov>"), patch.indexOf("</zoc:napomena>") + "</zoc:napomena>".length());
        return zalbaOdlukaRepository.update(zalbaOdluka.getId(), patch);
    }
}
