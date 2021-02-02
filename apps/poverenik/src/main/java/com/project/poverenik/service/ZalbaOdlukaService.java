package com.project.poverenik.service;

import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.mappers.ZalbaOdlukaMapper;
import com.project.poverenik.model.util.lists.ZalbaCutanjeList;
import com.project.poverenik.model.util.lists.ZalbaOdlukaList;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import com.project.poverenik.rdf_utils.AuthenticationUtilities;
import com.project.poverenik.rdf_utils.FileUtil;
import com.project.poverenik.rdf_utils.AuthenticationUtilities.ConnectionProperties;
import com.project.poverenik.repository.ZalbaOdlukaRepository;

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
import java.util.List;
import java.util.Map;

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
            return zalbaOdlukaMax.getZalbaOdlukaBody().getId();
        }
        return "0";
    }

    public boolean create(ZalbaOdluka zalbaOdlukaDTO, String userEmail) throws XMLDBException, NumberFormatException, JAXBException {
        if (jaxB.validate(zalbaOdlukaDTO.getClass(), zalbaOdlukaDTO)){
        	
        	String id = String.valueOf(Integer.parseInt(getMaxId())+1);
        	
        	ZalbaOdluka zalbaOdluka = ZalbaOdlukaMapper.mapFromDTO(zalbaOdlukaDTO, id, userEmail);
        	
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
        return zalbaOdlukaRepository.update(zalbaOdluka.getZalbaOdlukaBody().getId(), patch);
    }
    
    public ZalbaOdlukaList searchMetadata(String datumAfter, String datumBefore, String status, String organ_vlasti,
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

		ZalbaOdlukaList zcList = null;

		while (results.hasNext()) {

			QuerySolution querySolution = results.next();
			List<ZalbaOdluka> listZC = new ArrayList<>();

			id = querySolution.get("zalba");
			if (id.toString().contains("odluka")) {
				String idStr = id.toString().split("zalbe/odluka/")[1];
				ZalbaOdluka z = getOne(idStr);
				listZC.add(z);
			}

			zcList = new ZalbaOdlukaList(listZC);
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

    public ZalbaOdlukaList getByUser(String email) throws XMLDBException, JAXBException {
        List<ZalbaOdluka> zalbaOdlukaList = new ArrayList<>();

        ResourceSet resourceSet = zalbaOdlukaRepository.getAllByUser(email);
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

    public ZalbaOdlukaList getByObradaOrNeobradjena() throws XMLDBException, JAXBException {
        List<ZalbaOdluka> zalbaOdlukaList = new ArrayList<>();

        ResourceSet resourceSet = zalbaOdlukaRepository.getAllByObradaOrNeobradjena();
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
}
