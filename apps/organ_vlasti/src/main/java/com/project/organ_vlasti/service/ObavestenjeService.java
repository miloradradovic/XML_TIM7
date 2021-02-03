package com.project.organ_vlasti.service;

import com.project.organ_vlasti.jaxb.JaxB;
import com.project.organ_vlasti.mappers.ObavestenjeMapper;
import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import com.project.organ_vlasti.model.util.lists.ObavestenjeList;
import com.project.organ_vlasti.model.util.lists.ZahtevList;
import com.project.organ_vlasti.model.zahtev.Zahtev;
import com.project.organ_vlasti.rdf_utils.AuthenticationUtilities;
import com.project.organ_vlasti.rdf_utils.AuthenticationUtilities.ConnectionProperties;
import com.project.organ_vlasti.rdf_utils.FileUtil;
import com.project.organ_vlasti.repository.ObavestenjeRepository;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;
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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObavestenjeService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ObavestenjeRepository obavestenjeRepository;

    @Autowired
    ZahtevService zahtevService;
    
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
            return obavestenjeMax.getObavestenjeBody().getId();
        }
        return "0000";
    }

    public String create(Obavestenje obavestenjeDTO) throws XMLDBException, JAXBException {
        
        if (jaxB.validate(obavestenjeDTO.getClass(), obavestenjeDTO)){
        	String id = String.valueOf(Integer.parseInt(getMaxId())+1);
        	
        	//email usera koji je podnio zahtjev na koji se odnosi obavjestenje
            String zahtevId = obavestenjeDTO.getObavestenjeBody().getIdZahteva();
            Zahtev zahtev = zahtevService.getOne(zahtevId);
        	String userEmail = zahtev.getZahtevBody().getInformacijeOTraziocu().getLice().getOsoba().getOtherAttributes().get(new QName("id"));
            Obavestenje obavestenje = ObavestenjeMapper.mapFromDTO(obavestenjeDTO, id, userEmail);

            if(jaxB.validate(obavestenje.getClass(), obavestenje)){
            	 return obavestenjeRepository.create(obavestenje);
            }else {
                return null;
            }
        }
        return null;
         
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
    
    public Obavestenje getObavestenjeByZahtev(String idZahteva) throws JAXBException, XMLDBException {
        ResourceSet resourceSet = obavestenjeRepository.getObavestenjeByZahtev(idZahteva);

        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(Obavestenje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Obavestenje obavestenje = (Obavestenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return obavestenje;
        }
        return null;
    }

    public boolean delete(String broj) throws XMLDBException {
        return obavestenjeRepository.delete(broj);
    }

    public boolean update(Obavestenje obavestenje) throws JAXBException, XMLDBException {
        String patch = jaxB.marshall(obavestenje.getClass(), obavestenje);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        patch = patch.substring(patch.lastIndexOf("<oba:naziv_organa property=\"pred:organ_vlasti\" datatype=\"xs:string\" sediste=\"\">"), patch.indexOf("</oba:opcija_dostave>") + "</oba:opcija_dostave>".length());
        return obavestenjeRepository.update(obavestenje.getObavestenjeBody().getBroj(), patch);
    }
    
    public ObavestenjeList searchMetadata(String datumAfter, String datumBefore, String organ_vlasti,
			String userEmail, String zahtev) throws IOException, JAXBException, XMLDBException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		if (datumAfter.equals("")) {
			datumAfter = "1000-01-01";
		}
		if (datumBefore.equals("")) {
			datumBefore = "9999-12-31";
		}

		String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_search_metadata_obavestenje.rq",
				StandardCharsets.UTF_8);
		System.out.println(sparqlQueryTemplate);
		
		String user = "";
		if (userEmail.equals("")) {
			user = "?podnosilac";
		} else {
			user = "<http://users/"+userEmail+">";
		}
		String responseTo = "";
		if (zahtev.equals("")) {
			responseTo = "?responseTo";
		} else {
			responseTo = "<http://zahtevi/"+zahtev+">";
		}
		
		String sparqlQuery = String.format(sparqlQueryTemplate, user, responseTo, datumAfter, datumBefore, organ_vlasti);
		System.out.println(sparqlQuery);

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		ResultSet results = query.execSelect();

		RDFNode id;

		ObavestenjeList zcList = null;

		List<Obavestenje> listZC = new ArrayList<>();

		while (results.hasNext()) {

			QuerySolution querySolution = results.next();

			id = querySolution.get("obavestenje");
			String idStr = id.toString().split("obavestenja/")[1];
			Obavestenje z = getOne(idStr);
			listZC.add(z);
		}

		zcList = new ObavestenjeList(listZC);
		System.out.println();

		query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		results = query.execSelect();

		// ResultSetFormatter.outputAsXML(System.out, results);
		ResultSetFormatter.out(System.out, results);

		query.close();

		System.out.println("[INFO] End.");

		return zcList;

	}

	public ObavestenjeList searchText(String text) throws IOException, JAXBException, XMLDBException {
		List<Obavestenje> obavestenjeList = new ArrayList<>();

		ResourceSet resourceSet = obavestenjeRepository.searchText(text);
		ResourceIterator resourceIterator = resourceSet.getIterator();

		while (resourceIterator.hasMoreResources()) {
			XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
			if (xmlResource == null)
				return null;
			JAXBContext context = JAXBContext.newInstance(Obavestenje.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Obavestenje obavestenje = (Obavestenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
			obavestenjeList.add(obavestenje);
		}
		return new ObavestenjeList(obavestenjeList);

	}

    public ObavestenjeList getAllByUser(String email) throws XMLDBException, JAXBException {
        List<Obavestenje> obavestenjeList = new ArrayList<>();

        ResourceSet resourceSet = obavestenjeRepository.getAllByUser(email);
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
}
