package com.project.poverenik.service;

import com.project.poverenik.database.ExistManager;
import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.mappers.ZalbaCutanjeMapper;
import com.project.poverenik.model.util.lists.ZalbaCutanjeList;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.repository.ZalbaCutanjeRepository;

import com.project.poverenik.transformer.Transformator;
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

    @Autowired
    private ExistManager existManager;

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

    public boolean update(ZalbaCutanje zalbaCutanje, String status) throws JAXBException, XMLDBException {
        zalbaCutanje.getZalbaCutanjeBody().getStatus().setValue(status);
        return zalbaCutanjeRepository.create(zalbaCutanje);

        //String patch = jaxB.marshall(zalbaCutanje.getClass(), zalbaCutanje);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        //patch = patch.substring(patch.lastIndexOf("<zc:zalba_cutanje_body"), patch.indexOf("</zc:zalba_cutanje_body>") + "</zc:zalba_cutanje_body>".length());
        }

    public ZalbaCutanjeList getByUser(String email) throws XMLDBException, JAXBException {
        List<ZalbaCutanje> zalbaCutanjeList = new ArrayList<>();

        ResourceSet resourceSet = zalbaCutanjeRepository.getAllByUser(email);
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

    public ZalbaCutanjeList searchMetadata(String datumAfter, String datumBefore, String status, String organ_vlasti,
			String mesto, String userEmail) throws IOException, JAXBException, XMLDBException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		if (datumAfter.equals("")) {
			datumAfter = "1000-01-01";
		}
		if (datumBefore.equals("")) {
			datumBefore = "9999-12-31";
		}

		String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_search_metadata_zalbe.rq",
				StandardCharsets.UTF_8);
		System.out.println(sparqlQueryTemplate);
		
		String user = "";
		if (userEmail.equals("")) {
			user = "?podnosilac";
		} else {
			user = "<http://users/"+userEmail+">";
		}
		
		String sparqlQuery = String.format(sparqlQueryTemplate, user, datumAfter, datumBefore, status, organ_vlasti, mesto);
		System.out.println(sparqlQuery);

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		ResultSet results = query.execSelect();

		RDFNode id;

		ZalbaCutanjeList zcList = null;

		List<ZalbaCutanje> listZC = new ArrayList<>();

		while (results.hasNext()) {

			QuerySolution querySolution = results.next();

			id = querySolution.get("zalba");
			if (id.toString().contains("cutanje")) {
				String idStr = id.toString().split("zalbe/cutanje/")[1];
				ZalbaCutanje z = getOne(idStr);
				listZC.add(z);
			}
		}

		zcList = new ZalbaCutanjeList(listZC);
		System.out.println();

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

    public boolean generateDocuments(String brojObavestenja){
        final String OUTPUT_PDF = "poverenik/src/main/resources/generated_files/documents/zalbacutanje.pdf";
        final String OUTPUT_HTML = "poverenik/src/main/resources/generated_files/documents/zalbacutanje.html";
        final String XSL_FO = "poverenik/src/main/resources/generated_files/xsl-fo/zalbacutanje_fo.xsl";
        final String XSL = "poverenik/src/main/resources/generated_files/xslt/zalbacutanje.xsl";



        System.out.println("[INFO] " + Transformator.class.getSimpleName());


        try {
            Transformator transformator = new Transformator();
            ZalbaCutanje xml = getOne("1");
            transformator.generateHTML(existManager.getOutputStream(xml),
                    XSL, OUTPUT_HTML);
            transformator.generatePDF(XSL_FO,existManager.getOutputStream(xml), OUTPUT_PDF);
        } catch (XMLDBException | IOException | JAXBException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("[INFO] File \"" + OUTPUT_HTML + "\" generated successfully.");
        System.out.println("[INFO] End.");
        return true;
    }

    public ZalbaCutanjeList getByObradaOrNeobradjena() throws XMLDBException, JAXBException {
        List<ZalbaCutanje> zalbaCutanjeList = new ArrayList<>();

        ResourceSet resourceSet = zalbaCutanjeRepository.getAllByObradaOrNeobradjena();
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

    public Long getPodnete() throws XMLDBException {
        ResourceSet resourceSet = zalbaCutanjeRepository.getAll();
        return resourceSet.getSize();
    }

    public Long getPonistene() throws XMLDBException {
        ResourceSet resourceSet = zalbaCutanjeRepository.getPonistene();
        return resourceSet.getSize();
    }

    public Long getPrihvacene() throws XMLDBException {
        ResourceSet resourceSet = zalbaCutanjeRepository.getPrihvacene();
        return resourceSet.getSize();
    }

    public Long getOdbijene() throws XMLDBException {
        ResourceSet resourceSet = zalbaCutanjeRepository.getOdbijene();
        return resourceSet.getSize();
    }

}
