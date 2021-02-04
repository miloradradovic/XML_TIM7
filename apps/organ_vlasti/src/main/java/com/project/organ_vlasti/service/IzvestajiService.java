package com.project.organ_vlasti.service;

import com.project.organ_vlasti.client.IzvestajiClient;
import com.project.organ_vlasti.model.izvestaji.Izvestaj;
import com.project.organ_vlasti.model.izvestaji.ObjectFactory;
import com.project.organ_vlasti.model.izvestaji.Tbody;
import com.project.organ_vlasti.model.izvestaji.client.getPodaci;
import com.project.organ_vlasti.model.izvestaji.client.getPodaciResponse;
import com.project.organ_vlasti.model.izvestaji.database.client.podnesiIzvestaj;
import com.project.organ_vlasti.model.util.lists.IzvestajList;
import com.project.organ_vlasti.model.util.lists.ZahtevList;
import com.project.organ_vlasti.model.zahtev.Zahtev;
import com.project.organ_vlasti.rdf_utils.AuthenticationUtilities;
import com.project.organ_vlasti.rdf_utils.FileUtil;
import com.project.organ_vlasti.rdf_utils.AuthenticationUtilities.ConnectionProperties;
import com.project.organ_vlasti.repository.IzvestajiRepository;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import javax.xml.namespace.QName;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
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

    public boolean generate() throws XMLDBException, JAXBException, DatatypeConfigurationException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.izvestaji.client");

        IzvestajiClient izvestajiClient = new IzvestajiClient();
        izvestajiClient.setDefaultUri("http://localhost:8085/ws");
        izvestajiClient.setMarshaller(marshaller);
        izvestajiClient.setUnmarshaller(marshaller);

        getPodaci getPodaciRequest = new getPodaci();
        getPodaciResponse response = izvestajiClient.getPodaci(getPodaciRequest);

        Izvestaj izvestaj = compose(response.getResponse());
        String id = create(izvestaj);
        if(id != null){
            if(sendToPoverenik(id)){
                izvestaj.getIzvestajBody().setId(id);
                return  true;
            }
        }

        return false;
    }

    private boolean sendToPoverenik(String id){

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.izvestaji.database.client");

        IzvestajiClient izvestajiClient = new IzvestajiClient();
        izvestajiClient.setDefaultUri("http://localhost:8085/ws");
        izvestajiClient.setMarshaller(marshaller);
        izvestajiClient.setUnmarshaller(marshaller);

        podnesiIzvestaj podnesiIzvestajRequest = new podnesiIzvestaj();
        podnesiIzvestajRequest.setIzvestajRef(id);
        return izvestajiClient.sendIzvestajRef(podnesiIzvestajRequest);

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
        
        izvestaj.getIzvestajBody().setZahteviPodneti(new Tbody.ZahteviPodneti()); 
        izvestaj.getIzvestajBody().getZahteviPodneti().setValue(BigInteger.valueOf(zahtevService.getPodnetiZahtevi()));
        izvestaj.getIzvestajBody().getZahteviPodneti().getOtherAttributes().put(new QName("property"), "pred:zahtevi_podneti");
        izvestaj.getIzvestajBody().getZahteviPodneti().getOtherAttributes().put(new QName("datatype"), "xs:integer");
        
        izvestaj.getIzvestajBody().setZahteviOdbijeni(new Tbody.ZahteviOdbijeni()); 
        izvestaj.getIzvestajBody().getZahteviOdbijeni().setValue(BigInteger.valueOf(zahtevService.getOdbijeniZahtevi()));
        izvestaj.getIzvestajBody().getZahteviOdbijeni().getOtherAttributes().put(new QName("property"), "pred:zahtevi_odbijeni");
        izvestaj.getIzvestajBody().getZahteviOdbijeni().getOtherAttributes().put(new QName("datatype"), "xs:integer");
        
        izvestaj.getIzvestajBody().setZahteviPrihvaceni(new Tbody.ZahteviPrihvaceni()); 
        izvestaj.getIzvestajBody().getZahteviPrihvaceni().setValue(BigInteger.valueOf(zahtevService.getPrihvaceniZahtevi()));
        izvestaj.getIzvestajBody().getZahteviPrihvaceni().getOtherAttributes().put(new QName("property"), "pred:zahtevi_prihvaceni");
        izvestaj.getIzvestajBody().getZahteviPrihvaceni().getOtherAttributes().put(new QName("datatype"), "xs:integer");

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        XMLGregorianCalendar dateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateString);

        izvestaj.getIzvestajBody().setDatum(dateXML);
        izvestaj.getIzvestajBody().setId(id);

        izvestaj.getIzvestajBody().setZalbeCutanjeOdbijeno(zalbe.getZalbeCutanjeOdbijeno());
        izvestaj.getIzvestajBody().getZalbeCutanjeOdbijeno().getOtherAttributes().put(new QName("property"), "pred:zalbe_cutanje_odbijeno");
        izvestaj.getIzvestajBody().getZalbeCutanjeOdbijeno().getOtherAttributes().put(new QName("datatype"), "xs:integer");
        
        izvestaj.getIzvestajBody().setZalbeCutanjePodneti(zalbe.getZalbeCutanjePodneti());
        izvestaj.getIzvestajBody().getZalbeCutanjePodneti().getOtherAttributes().put(new QName("property"), "pred:zalbe_cutanje_podneti");
        izvestaj.getIzvestajBody().getZalbeCutanjePodneti().getOtherAttributes().put(new QName("datatype"), "xs:integer");
        
        izvestaj.getIzvestajBody().setZalbeCutanjePonisteno(zalbe.getZalbeCutanjePonisteno());
        izvestaj.getIzvestajBody().getZalbeCutanjePonisteno().getOtherAttributes().put(new QName("property"), "pred:zalbe_cutanje_ponisteno");
        izvestaj.getIzvestajBody().getZalbeCutanjePonisteno().getOtherAttributes().put(new QName("datatype"), "xs:integer");
        
        izvestaj.getIzvestajBody().setZalbeCutanjePrihvaceno(zalbe.getZalbeCutanjePrihvaceno());
        izvestaj.getIzvestajBody().getZalbeCutanjePrihvaceno().getOtherAttributes().put(new QName("property"), "pred:zalbe_cutanje_prihvaceno");
        izvestaj.getIzvestajBody().getZalbeCutanjePrihvaceno().getOtherAttributes().put(new QName("datatype"), "xs:integer");
        

        izvestaj.getIzvestajBody().setZalbeOdlukeOdbijeno(zalbe.getZalbeOdlukeOdbijeno());
        izvestaj.getIzvestajBody().getZalbeOdlukeOdbijeno().getOtherAttributes().put(new QName("property"), "pred:zalbe_odluke_odbijeno");
        izvestaj.getIzvestajBody().getZalbeOdlukeOdbijeno().getOtherAttributes().put(new QName("datatype"), "xs:integer");
        
        izvestaj.getIzvestajBody().setZalbeOdlukePodneti(zalbe.getZalbeOdlukePodneti());
        izvestaj.getIzvestajBody().getZalbeOdlukePodneti().getOtherAttributes().put(new QName("property"), "pred:zalbe_odluke_podneti");
        izvestaj.getIzvestajBody().getZalbeOdlukePodneti().getOtherAttributes().put(new QName("datatype"), "xs:integer");
        
        izvestaj.getIzvestajBody().setZalbeOdlukePonisteno(zalbe.getZalbeOdlukePonisteno());
        izvestaj.getIzvestajBody().getZalbeOdlukePonisteno().getOtherAttributes().put(new QName("property"), "pred:zalbe_odluke_ponisteno");
        izvestaj.getIzvestajBody().getZalbeOdlukePonisteno().getOtherAttributes().put(new QName("datatype"), "xs:integer");
        
        izvestaj.getIzvestajBody().setZalbeOdlukePrihvaceno(zalbe.getZalbeOdlukePrihvaceno());
        izvestaj.getIzvestajBody().getZalbeOdlukePrihvaceno().getOtherAttributes().put(new QName("property"), "pred:zalbe_odluke_prihvaceno");
        izvestaj.getIzvestajBody().getZalbeOdlukePrihvaceno().getOtherAttributes().put(new QName("datatype"), "xs:integer");
        
        izvestaj.getIzvestajBody().getOtherAttributes().put(new QName("about"), "http://izvestaji/" + izvestaj.getIzvestajBody().getId());
        izvestaj.getIzvestajBody().getOtherAttributes().put(new QName("vocab"),"http://examples/predicate/");
        izvestaj.getIzvestajBody().getOtherAttributes().put(new QName("property"),"pred:datum");
        izvestaj.getIzvestajBody().getOtherAttributes().put(new QName("datatype"),"xs:date");
        izvestaj.getIzvestajBody().getOtherAttributes().put(new QName("content"), izvestaj.getIzvestajBody().getDatum().toString());

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
    
    
    public IzvestajList searchMetadata(String datumAfter, String datumBefore) throws IOException, JAXBException, XMLDBException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		if (datumAfter.equals("")) {
			datumAfter = "1000-01-01T00:00:00";
		}
		if (datumBefore.equals("")) {
			datumBefore = "9999-12-31T00:00:00";
		}

		String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_search_metadata_izvestaj.rq",
				StandardCharsets.UTF_8);
		System.out.println(sparqlQueryTemplate);

		String sparqlQuery = String.format(sparqlQueryTemplate, datumAfter, datumBefore);
		System.out.println(sparqlQuery);

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		ResultSet results = query.execSelect();

		RDFNode id;

		IzvestajList zcList = null;

		List<Izvestaj> listZC = new ArrayList<>();

		while (results.hasNext()) {

			QuerySolution querySolution = results.next();

			id = querySolution.get("izvestaj");
			String idStr = id.toString().split("izvestaji/")[1];
			Izvestaj z = getOne(idStr);
			listZC.add(z);
		}

		zcList = new IzvestajList(listZC);
		System.out.println();

		query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		results = query.execSelect();

		// ResultSetFormatter.outputAsXML(System.out, results);
		ResultSetFormatter.out(System.out, results);

		query.close();

		System.out.println("[INFO] End.");

		return zcList;

	}

}
