package com.project.organ_vlasti.service;

import com.project.organ_vlasti.model.izvestaji.Izvestaj;
import com.project.organ_vlasti.model.izvestaji.ObjectFactory;
import com.project.organ_vlasti.model.izvestaji.Tbody;
import com.project.organ_vlasti.model.util.lists.IzvestajList;
import com.project.organ_vlasti.repository.IzvestajiRepository;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IzvestajiService {

    @Autowired
    private IzvestajiRepository izvestajiRepository;

    @Autowired
    private ZahtevService zahtevService;

    private String getMaxId() throws XMLDBException, JAXBException {
        ResourceSet max = izvestajiRepository.getMaxId();
        ResourceIterator resourceIterator = max.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return "0000";
            JAXBContext context = JAXBContext.newInstance(Izvestaj.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Izvestaj izvestajMax = (Izvestaj) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return izvestajMax.getIzvestajBody().getId();
        }
        return "0000";
    }

    public String create(Izvestaj izvestaj) throws XMLDBException, JAXBException {

        String id = String.valueOf(Integer.parseInt(getMaxId())+1);
        izvestaj.getIzvestajBody().setId(id);

        return izvestajiRepository.create(izvestaj);

    }

    public Izvestaj compose(Tbody zalbe) throws XMLDBException, JAXBException, DatatypeConfigurationException {

        String id = String.valueOf(Integer.parseInt(getMaxId())+1);


        ObjectFactory of = new ObjectFactory();
        Izvestaj izvestaj = of.createIzvestaj();
        izvestaj.setIzvestajBody(of.createTbody());
        izvestaj.getIzvestajBody().setZahteviPodneti(BigInteger.valueOf(zahtevService.getPodnetiZahtevi()));
        izvestaj.getIzvestajBody().setZahteviOdbijeni(BigInteger.valueOf(zahtevService.getOdbijeniZahtevi()));
        izvestaj.getIzvestajBody().setZahteviPrihvaceni(BigInteger.valueOf(zahtevService.getPrihvaceniZahtevi()));

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        XMLGregorianCalendar dateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateString);

        izvestaj.getIzvestajBody().setDatum(dateXML);
        izvestaj.getIzvestajBody().setId(id);

        izvestaj.getIzvestajBody().setZalbeCutanjeOdbijeno(zalbe.getZalbeCutanjeOdbijeno());
        izvestaj.getIzvestajBody().setZalbeCutanjePodneti(zalbe.getZalbeCutanjePodneti());
        izvestaj.getIzvestajBody().setZalbeCutanjePonisteno(zalbe.getZalbeCutanjePonisteno());
        izvestaj.getIzvestajBody().setZalbeCutanjePrihvaceno(zalbe.getZalbeCutanjePrihvaceno());

        izvestaj.getIzvestajBody().setZalbeOdlukeOdbijeno(zalbe.getZalbeOdlukeOdbijeno());
        izvestaj.getIzvestajBody().setZalbeOdlukePodneti(zalbe.getZalbeOdlukePodneti());
        izvestaj.getIzvestajBody().setZalbeOdlukePonisteno(zalbe.getZalbeOdlukePonisteno());
        izvestaj.getIzvestajBody().setZalbeOdlukePrihvaceno(zalbe.getZalbeOdlukePrihvaceno());

        return izvestaj;

    }

    public IzvestajList getAll() throws XMLDBException, JAXBException {
        List<Izvestaj> izvestajList = new ArrayList<>();

        ResourceSet resourceSet = izvestajiRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(Izvestaj.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Izvestaj izvestaj = (Izvestaj) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            izvestajList.add(izvestaj);
        }
        return new IzvestajList(izvestajList);
    }

    public Izvestaj getOne(String id) throws JAXBException, XMLDBException {
        XMLResource xmlResource = izvestajiRepository.getOne(id);

        if(xmlResource == null)
            return null;

        Izvestaj izvestaj = null;

        JAXBContext context = JAXBContext.newInstance(Izvestaj.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        izvestaj = (Izvestaj) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return izvestaj;
    }

    public boolean delete(String id) throws XMLDBException {
        return izvestajiRepository.delete(id);
    }

}
