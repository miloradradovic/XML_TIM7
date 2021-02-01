package com.project.poverenik.service;

import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.mappers.ZalbaCutanjeMapper;
import com.project.poverenik.model.util.lists.ZalbaCutanjeList;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.repository.ZalbaCutanjeRepository;

import org.apache.commons.text.StringSubstitutor;
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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.project.poverenik.rdf_utils.AuthenticationUtilities;
import com.project.poverenik.rdf_utils.AuthenticationUtilities.ConnectionProperties;
import com.project.poverenik.rdf_utils.FileUtil;

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
    
    public ZalbaCutanjeList searchMetadata(String datumAfter, String datumBefore, String status, String organ_vlasti,
			String mesto) throws IOException, JAXBException, XMLDBException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		if (datumAfter.equals("")) {
			datumAfter = "1000-01-01";
		}
		if (datumBefore.equals("")) {
			datumAfter = "9999-12-31";
		}

		System.out.println(datumAfter + datumBefore + status + organ_vlasti + mesto);
		Map<String, String> params = new HashMap<String, String>();
		params.put("datumAfter", datumAfter);
		params.put("datumBefore", datumBefore);
		params.put("status", status);
		params.put("organ_vlasti", organ_vlasti);
		params.put("mesto", mesto);
		
		String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_search_metadata_zalbe.rq",
				StandardCharsets.UTF_8);
		System.out.println(sparqlQueryTemplate);

		String sparqlQuery = String.format(sparqlQueryTemplate, datumAfter, datumBefore, status, organ_vlasti, mesto);
		System.out.println(sparqlQuery);

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		ResultSet results = query.execSelect();

		RDFNode id;

		ZalbaCutanjeList zcList = null;

		while (results.hasNext()) {

			QuerySolution querySolution = results.next();
			List<ZalbaCutanje> listZC = new ArrayList<>();

			id = querySolution.get("zalba_cutanje");
			String idStr = id.toString().split("zalbe/cutanje/")[1];
			ZalbaCutanje z = getOne(idStr);
			listZC.add(z);

			zcList = new ZalbaCutanjeList(listZC);
			System.out.println();
		}

		query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		results = query.execSelect();

		// ResultSetFormatter.outputAsXML(System.out, results);
		ResultSetFormatter.out(System.out, results);

		query.close();

		System.out.println("[INFO] End.");

		return zcList;

	}

	public ZalbaCutanjeList searchText(String text) throws IOException, JAXBException, XMLDBException {
		List<ZalbaCutanje> zalbaCutanjeList = new ArrayList<>();

		ResourceSet resourceSet = zalbaCutanjeRepository.searchText(text);
		ResourceIterator resourceIterator = resourceSet.getIterator();

		while (resourceIterator.hasMoreResources()) {
			XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
			if (xmlResource == null)
				return null;
			JAXBContext context = JAXBContext.newInstance(ZalbaCutanje.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			ZalbaCutanje zalbaCutanje = (ZalbaCutanje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
			zalbaCutanjeList.add(zalbaCutanje);
		}
		return new ZalbaCutanjeList(zalbaCutanjeList);

	}

	public ZalbaCutanjeList getZalbeByUser(String userEmail) throws IOException, JAXBException, XMLDBException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		Map<String, String> params = new HashMap<String, String>();
		params.put("user", "<http://users/" + userEmail + ">");

		String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_zalbe_gradjanina.rq",
				StandardCharsets.UTF_8);
		System.out.println(sparqlQueryTemplate);
		// String sparqlQuery = StringSubstitutor.replace(sparqlQueryTemplate, params,
		// "{{", "}}");
		String sparqlQuery = String.format(sparqlQueryTemplate, userEmail);
		System.out.println(sparqlQuery);

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		// Query the SPARQL endpoint, iterate over the result set...
		System.out.println("[INFO] Showing the results for SPARQL query using the result handler.\n");
		ResultSet results = query.execSelect();

		RDFNode id;

		ZalbaCutanjeList zcList = null;

		while (results.hasNext()) {

			// A single answer from a SELECT query
			QuerySolution querySolution = results.next();
			List<ZalbaCutanje> listZC = new ArrayList<>();

			id = querySolution.get("zalba_cutanje");
			String idStr = id.toString().split("zalbe/cutanje/")[1];
			ZalbaCutanje z = getOne(idStr);
			listZC.add(z);

			zcList = new ZalbaCutanjeList(listZC);
			System.out.println();
		}

		// Issuing the same query once again...

		// Create a QueryExecution that will access a SPARQL service over HTTP
		query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		// Query the collection, dump output response as XML
		System.out.println("[INFO] Showing the results for SPARQL query in native SPARQL XML format.\n");
		results = query.execSelect();

		// ResultSetFormatter.outputAsXML(System.out, results);
		ResultSetFormatter.out(System.out, results);

		query.close();

		System.out.println("[INFO] End.");

		return zcList;

	}
    
   
}
