package com.project.poverenik.service;

import com.project.poverenik.client.FileClient;
import com.project.poverenik.client.IzvestajClient;
import com.project.poverenik.model.izvestaj.client.getIzvestajById;
import com.project.poverenik.model.izvestaj.client.getIzvestajByIdResponse;
import com.project.poverenik.model.izvestaj.database.IzvestajRef;
import com.project.poverenik.model.izvestaj.database.client.getRefs;
import com.project.poverenik.model.izvestaj.database.client.getRefsResponse;
import com.project.poverenik.model.util.file.Tpath;
import com.project.poverenik.model.util.file.client.*;
import com.project.poverenik.model.util.lists.IzvestajRefList;
import com.project.poverenik.model.util.parametars.ParametarMap;
import com.project.poverenik.model.util.parametars.Tvalue;
import com.project.poverenik.repository.IzvestajRefRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class IzvestajRefService {

    @Autowired
    private IzvestajRefRepository izvestajRefRepository;

    private String getMaxId() throws XMLDBException, JAXBException {
        ResourceSet max = izvestajRefRepository.getMaxId();
        ResourceIterator resourceIterator = max.getIterator();

        if (!resourceIterator.hasMoreResources()) {
            return "0000";
        }
        XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
        if (xmlResource == null)
            return "0000";
        JAXBContext context = JAXBContext.newInstance(IzvestajRef.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        IzvestajRef izvestajRefMax = (IzvestajRef) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
        return izvestajRefMax.getBody().getId();
    }

    public boolean create(IzvestajRef izvestajRef) throws XMLDBException, JAXBException {

        String id = String.valueOf(Integer.parseInt(getMaxId()) + 1);
        izvestajRef.getBody().setId(id);

        return izvestajRefRepository.create(izvestajRef);
    }

    public IzvestajRefList getAll(String procitano) throws XMLDBException, JAXBException {
        List<IzvestajRef> resenjeRefs = new ArrayList<>();

        ResourceSet resourceSet = izvestajRefRepository.getAllByProcitano(procitano);
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()) {
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if (xmlResource == null)
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

        if (xmlResource == null)
            return null;

        IzvestajRef izvestajRef;

        JAXBContext context = JAXBContext.newInstance(IzvestajRef.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        izvestajRef = (IzvestajRef) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return izvestajRef;
    }

    public IzvestajRef getOneByBroj(String broj) throws XMLDBException, JAXBException {

        ResourceSet resourceSet = izvestajRefRepository.getOneByBroj(broj);
        ResourceIterator resourceIterator = resourceSet.getIterator();

        if (!resourceIterator.hasMoreResources()) {
            return null;
        }

        XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
        if (xmlResource == null)
            return null;
        JAXBContext context = JAXBContext.newInstance(IzvestajRef.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (IzvestajRef) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

    }

    public boolean delete(String id) throws XMLDBException {
        return izvestajRefRepository.delete(id);
    }

    public boolean update(IzvestajRef resenjeRef) throws XMLDBException {
        return izvestajRefRepository.update(resenjeRef);
    }

    public IzvestajRefList searchMetadata(String datumAfter, String datumBefore, String status) throws XMLDBException, JAXBException {
        com.project.poverenik.model.util.parametars.ObjectFactory of = new com.project.poverenik.model.util.parametars.ObjectFactory();
        ParametarMap parametarMap = of.createParametarMap();

        Tvalue tvalue = of.createTvalue();
        tvalue.setName("datumAfter");
        tvalue.setValue(datumAfter);
        parametarMap.getValue().add(tvalue);

        Tvalue tvalue1 = of.createTvalue();
        tvalue1.setName("datumBefore");
        tvalue1.setValue(datumBefore);
        parametarMap.getValue().add(tvalue1);

        return sendToPoverenik(parametarMap, status);

    }

    private IzvestajRefList sendToPoverenik(ParametarMap parametarMap, String status) throws XMLDBException, JAXBException {

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.izvestaj.database.client");

        IzvestajClient izvestajClient = new IzvestajClient();
        izvestajClient.setDefaultUri("http://localhost:8090/ws");
        izvestajClient.setMarshaller(marshaller);
        izvestajClient.setUnmarshaller(marshaller);

        getRefs getRefs = new getRefs();
        getRefs.setParametars(parametarMap);

        getRefsResponse refsResponse = izvestajClient.getRefs(getRefs);
        if (refsResponse != null) {
            return getRefs(refsResponse.getResponse().getRef(), status);
        }
        return null;
    }

    public IzvestajRefList getRefs(List<String> refs, String status) throws XMLDBException, JAXBException {
        List<IzvestajRef> izvestajRefs = new ArrayList<>();

        for (String id : refs) {
            ResourceSet resourceSet = izvestajRefRepository.getOneStatusByBroj(id, status);
            ResourceIterator resourceIterator = resourceSet.getIterator();

            while (resourceIterator.hasMoreResources()) {
                XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
                if (xmlResource == null)
                    return null;
                JAXBContext context = JAXBContext.newInstance(IzvestajRef.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                IzvestajRef izvestajRef = (IzvestajRef) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
                izvestajRefs.add(izvestajRef);
            }
        }
        return new IzvestajRefList(izvestajRefs);
    }

    public getIzvestajByIdResponse getIzvestaj(String id) throws XMLDBException, JAXBException {
        // TODO dodati za front xhtml transformacije
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.izvestaj.client");

        IzvestajClient izvestajClient = new IzvestajClient();
        izvestajClient.setDefaultUri("http://localhost:8090/ws");
        izvestajClient.setMarshaller(marshaller);
        izvestajClient.setUnmarshaller(marshaller);


        getIzvestajById getIzvestajById = new getIzvestajById();
        getIzvestajById.setId(id);

        getIzvestajByIdResponse getIzvestajByIdResponse = izvestajClient.getOneResenje(getIzvestajById);
        if (getIzvestajByIdResponse != null) {
            IzvestajRef izvestajRef = getOneByBroj(id);
            if (izvestajRef == null)
                return null;
            izvestajRef.getBody().setProcitano("da");
            update(izvestajRef);
        }
        return getIzvestajByIdResponse;

    }

    public Tpath getRdf(String id) {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.util.file.client");

        FileClient fileClient = new FileClient();
        fileClient.setDefaultUri("http://localhost:8090/ws");
        fileClient.setMarshaller(marshaller);
        fileClient.setUnmarshaller(marshaller);


        sendRdfFile sendRdfFile = new sendRdfFile();
        sendRdfFile.setId(id);

        return fileClient.getRdf(sendRdfFile).getPath();
    }

    public Tpath getJson(String id) {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.util.file.client");

        FileClient fileClient = new FileClient();
        fileClient.setDefaultUri("http://localhost:8090/ws");
        fileClient.setMarshaller(marshaller);
        fileClient.setUnmarshaller(marshaller);


        sendJsonFile sendJsonFile = new sendJsonFile();
        sendJsonFile.setId(id);

        return fileClient.getJson(sendJsonFile).getPath();
    }
}
