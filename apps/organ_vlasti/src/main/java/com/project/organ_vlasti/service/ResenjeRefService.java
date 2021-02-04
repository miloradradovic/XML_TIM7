package com.project.organ_vlasti.service;

import com.project.organ_vlasti.client.ResenjeClient;
import com.project.organ_vlasti.jaxb.JaxB;
import com.project.organ_vlasti.model.resenje.client.getResenjeByBroj;
import com.project.organ_vlasti.model.resenje.client.getResenjeByBrojResponse;
import com.project.organ_vlasti.model.resenje.database.ResenjeRef;
import com.project.organ_vlasti.model.resenje.database.client.getRefs;
import com.project.organ_vlasti.model.resenje.database.client.getRefsResponse;
import com.project.organ_vlasti.model.user.User;
import com.project.organ_vlasti.model.util.lists.ResenjeRefList;
import com.project.organ_vlasti.model.util.parametars.ParametarMap;
import com.project.organ_vlasti.model.util.parametars.Tvalue;
import com.project.organ_vlasti.repository.ResenjeRefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
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

    public ResenjeRefList getAll(String procitano) throws XMLDBException, JAXBException {
        List<ResenjeRef> resenjeRefs = new ArrayList<>();

        ResourceSet resourceSet = resenjeRefRepository.getAllByProcitano(procitano);
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

    public ResenjeRefList getRefs(List<String> refs, String status) throws XMLDBException, JAXBException {
        List<ResenjeRef> resenjeRefs = new ArrayList<>();

        for (String broj: refs) {
            ResourceSet resourceSet = resenjeRefRepository.getOneStatusByBroj(broj, status);
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
        }
        return new ResenjeRefList(resenjeRefs);
    }

    public getResenjeByBrojResponse getResenje(String broj) throws XMLDBException, JAXBException {
        // TODO dodati za front xhtml transformacije
        //pocetak soap
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.resenje.client");

        ResenjeClient resenjeClient = new ResenjeClient();
        resenjeClient.setDefaultUri("http://localhost:8085/ws");
        resenjeClient.setMarshaller(marshaller);
        resenjeClient.setUnmarshaller(marshaller);


        getResenjeByBroj getResenjeByBroj = new getResenjeByBroj();
        getResenjeByBroj.setBroj(broj);

        getResenjeByBrojResponse getResenjeByBrojResponse = resenjeClient.getOneResenje(getResenjeByBroj);
        //kraj soap
        // ObjectFactory of = new ObjectFactory() com.project.organ_vlasti.model.resenje
        // Resenje r = of.createResenje();
        // r.setResenjeBody(getResenjeByBrojResponse.getResenje());
        if(getResenjeByBrojResponse != null){
            ResenjeRef resenjeRef = getOneByBroj(broj);
            resenjeRef.getBody().setProcitano("da");
            if(update(resenjeRef)){
                return getResenjeByBrojResponse;
            }
            return null;
        }
        return null;
    }

    public ResenjeRefList searchMetadata(String status,
                                         String poverenik,
                                         String trazilac,
                                         String zalba,
                                         String datumAfter,
                                         String datumBefore,
                                         String tip,
                                         String organVlasti,
                                         String mesto) throws XMLDBException, JAXBException {
        com.project.organ_vlasti.model.util.parametars.ObjectFactory of = new com.project.organ_vlasti.model.util.parametars.ObjectFactory();
        ParametarMap parametarMap = of.createParametarMap();

        Tvalue tvalue = of.createTvalue();
        tvalue.setName("poverenik");
        tvalue.setValue(poverenik);
        parametarMap.getValue().add(tvalue);

        Tvalue tvalue1 = of.createTvalue();
        tvalue1.setName("trazilac");
        tvalue1.setValue(trazilac);
        parametarMap.getValue().add(tvalue1);

        Tvalue tvalue2 = of.createTvalue();
        tvalue2.setName("zalba");
        tvalue2.setValue(zalba);
        parametarMap.getValue().add(tvalue2);

        Tvalue tvalue3 = of.createTvalue();
        tvalue3.setName("datumAfter");
        tvalue3.setValue(datumAfter);
        parametarMap.getValue().add(tvalue3);

        Tvalue tvalue4 = of.createTvalue();
        tvalue4.setName("datumBefore");
        tvalue4.setValue(datumBefore);
        parametarMap.getValue().add(tvalue4);

        Tvalue tvalue5 = of.createTvalue();
        tvalue5.setName("tip");
        tvalue5.setValue(tip);
        parametarMap.getValue().add(tvalue5);

        Tvalue tvalue6 = of.createTvalue();
        tvalue6.setName("organVlasti");
        tvalue6.setValue(organVlasti);
        parametarMap.getValue().add(tvalue6);

        Tvalue tvalue7 = of.createTvalue();
        tvalue7.setName("mesto");
        tvalue7.setValue(mesto);
        parametarMap.getValue().add(tvalue7);

        return sendToPoverenik(parametarMap, status);

    }

    public  ResenjeRefList searchText(String status, String input) throws XMLDBException, JAXBException {
        com.project.organ_vlasti.model.util.parametars.ObjectFactory of = new com.project.organ_vlasti.model.util.parametars.ObjectFactory();
        ParametarMap parametarMap = of.createParametarMap();

        Tvalue tvalue = of.createTvalue();
        tvalue.setName("input");
        tvalue.setValue(input);
        parametarMap.getValue().add(tvalue);

        return sendToPoverenik(parametarMap, status);
    }

    private ResenjeRefList sendToPoverenik(ParametarMap parametarMap, String status) throws XMLDBException, JAXBException {

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.resenje.database.client");

        ResenjeClient resenjeClient = new ResenjeClient();
        resenjeClient.setDefaultUri("http://localhost:8085/ws");
        resenjeClient.setMarshaller(marshaller);
        resenjeClient.setUnmarshaller(marshaller);

        getRefs getRefs = new getRefs();
        getRefs.setParametars(parametarMap);

        getRefsResponse refsResponse = resenjeClient.getRefs(getRefs);
        if(refsResponse != null){
            return getRefs(refsResponse.getResponse().getRef(), status);
        }
        return null;

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
        return resenjeRefRepository.update(resenjeRef);
    }
}
