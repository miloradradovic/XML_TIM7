package com.project.organ_vlasti.service;

import com.project.organ_vlasti.database.ExistManager;
import com.project.organ_vlasti.jaxb.JaxB;
import com.project.organ_vlasti.mappers.ZahtevMapper;
import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import com.project.organ_vlasti.model.util.lists.ObavestenjeList;
import com.project.organ_vlasti.model.util.lists.ZahtevList;
import com.project.organ_vlasti.model.zahtev.Zahtev;
import com.project.organ_vlasti.rdf_utils.AuthenticationUtilities;
import com.project.organ_vlasti.rdf_utils.FileUtil;
import com.project.organ_vlasti.rdf_utils.AuthenticationUtilities.ConnectionProperties;
import com.project.organ_vlasti.repository.ZahtevRepository;
import com.project.organ_vlasti.transformer.Transformator;

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
import java.util.List;

@Service
public class ZahtevService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Autowired
    private ExistManager existManager;
    
    private String getMaxId() throws XMLDBException, JAXBException {
        ResourceSet max = zahtevRepository.getMaxId();
        ResourceIterator resourceIterator = max.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return "0";
            JAXBContext context = JAXBContext.newInstance(Zahtev.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Zahtev zahteveMax = (Zahtev) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return zahteveMax.getZahtevBody().getId();
        }
        return "0";
    }
     

    public boolean create(Zahtev zahtevDTO, String userEmail) throws XMLDBException, JAXBException {
        if(jaxB.validate(zahtevDTO.getClass(), zahtevDTO)){
        	String id = String.valueOf(Integer.parseInt(getMaxId())+1);
        	
        	Zahtev zahtev = ZahtevMapper.mapFromDTO(zahtevDTO, id, userEmail);
        	if(jaxB.validate(zahtev.getClass(), zahtev)) {
                return zahtevRepository.create(zahtev);
        	}{
        		return false;
        	}

        }else{
            return false;
        }
    }

    public ZahtevList getAll() throws XMLDBException, JAXBException {
        List<Zahtev> zahtevList = new ArrayList<>();

        ResourceSet resourceSet = zahtevRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(Zahtev.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            zahtevList.add(zahtev);
        }
        return new ZahtevList(zahtevList);
    }

    public Zahtev getOne(String id) throws JAXBException, XMLDBException {
        XMLResource xmlResource = zahtevRepository.getOne(id);

        if(xmlResource == null)
            return null;

        Zahtev zahtev = null;

        JAXBContext context = JAXBContext.newInstance(Zahtev.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        zahtev = (Zahtev) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return zahtev;
    }

    public boolean delete(String id) throws XMLDBException {
        return zahtevRepository.delete(id);
    }

    public boolean update(Zahtev zahtev, String status) throws XMLDBException {
        zahtev.getZahtevBody().getStatus().setValue(status);
        return zahtevRepository.create(zahtev);
        //String patch = jaxB.marshall(zahtev.getClass(), zahtev);
        ////u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        //patch = patch.substring(patch.lastIndexOf("<zcir:ciljani_organ_vlasti>"), patch.indexOf("</zcir:fusnote>") + "</zcir:fusnote>".length());

    }

    public boolean generateDocuments(String broj){
        final String OUTPUT_PDF = "src/main/resources/generated_files/documents/zahtev" + broj + ".pdf";
        final String OUTPUT_HTML = "src/main/resources/generated_files/documents/zahtev" + broj + ".html";
        final String XSL_FO = "src/main/resources/generated_files/xsl-fo/zahtev_fo.xsl";


        System.out.println("[INFO] " + Transformator.class.getSimpleName());


        try {
            Transformator transformator = new Transformator();
            Zahtev xml = getOne(broj);
            transformator.generateHTML(existManager.getOutputStream(xml),
                    "src/main/resources/generated_files/xslt/zahtev.xsl", OUTPUT_HTML);
            transformator.generatePDF(XSL_FO, existManager.getOutputStream(xml), OUTPUT_PDF);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

		/*pdfTransformer.generateHTML(existManager.getOutputStream(), XSL_FILE);
		pdfTransformer.generatePDF(OUTPUT_FILE);
*/
        System.out.println("[INFO] File \"" + OUTPUT_HTML + "\" generated successfully.");
        System.out.println("[INFO] End.");
        return true;
    }


    public Long getPodnetiZahtevi() throws XMLDBException {
        ResourceSet resourceSet = zahtevRepository.getAll();
        return resourceSet.getSize();
    }

    public Long getOdbijeniZahtevi() throws XMLDBException {
        ResourceSet resourceSet = zahtevRepository.getOdbijeniZahtevi();
        return resourceSet.getSize();
    }

    public Long getPrihvaceniZahtevi() throws XMLDBException {
        ResourceSet resourceSet = zahtevRepository.getPrihvaceniZahtevi();
        return resourceSet.getSize();
    }

    public ZahtevList getAllNeobradjen() throws XMLDBException, JAXBException {
        List<Zahtev> zahtevList = new ArrayList<>();

        ResourceSet resourceSet = zahtevRepository.getAllNeobradjen();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(Zahtev.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            zahtevList.add(zahtev);
        }
        return new ZahtevList(zahtevList);
    }

    public ZahtevList getAllByUser(String email) throws XMLDBException, JAXBException {
        List<Zahtev> zahtevList = new ArrayList<>();

        ResourceSet resourceSet = zahtevRepository.getAllByUser(email);
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(Zahtev.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            zahtevList.add(zahtev);
        }
        return new ZahtevList(zahtevList);
    }
    
    public ZahtevList searchMetadata(String datumAfter, String datumBefore, String mesto, String organ_vlasti,
			String userEmail) throws IOException, JAXBException, XMLDBException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		if (datumAfter.equals("")) {
			datumAfter = "1000-01-01";
		}
		if (datumBefore.equals("")) {
			datumBefore = "9999-12-31";
		}

		String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_search_metadata_zahtev.rq",
				StandardCharsets.UTF_8);
		System.out.println(sparqlQueryTemplate);
		
		String user = "";
		if (userEmail.equals("")) {
			user = "?podnosilac";
		} else {
			user = "<http://users/"+userEmail+">";
		}
		
		String sparqlQuery = String.format(sparqlQueryTemplate, user, datumAfter, datumBefore, mesto, organ_vlasti);
		System.out.println(sparqlQuery);

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		ResultSet results = query.execSelect();

		RDFNode id;

		ZahtevList zcList = null;

		List<Zahtev> listZC = new ArrayList<>();

		while (results.hasNext()) {

			QuerySolution querySolution = results.next();

			id = querySolution.get("zahtev");
			String idStr = id.toString().split("zahtevi/")[1];
			Zahtev z = getOne(idStr);
			listZC.add(z);
		}

		zcList = new ZahtevList(listZC);
		System.out.println();

		query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		results = query.execSelect();

		// ResultSetFormatter.outputAsXML(System.out, results);
		ResultSetFormatter.out(System.out, results);

		query.close();

		System.out.println("[INFO] End.");

		return zcList;

	}

	public ZahtevList searchText(String text) throws IOException, JAXBException, XMLDBException {
		List<Zahtev> zahtevList = new ArrayList<>();

		ResourceSet resourceSet = zahtevRepository.searchText(text);
		ResourceIterator resourceIterator = resourceSet.getIterator();

		while (resourceIterator.hasMoreResources()) {
			XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
			if (xmlResource == null)
				return null;
			JAXBContext context = JAXBContext.newInstance(Zahtev.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
			zahtevList.add(zahtev);
		}
		return new ZahtevList(zahtevList);

	}

    public String downloadResenjePDF(String broj){
        String path = "src/main/resources/generated_files/documents/zahtev" + broj + ".pdf";
        boolean obavestenje = generateDocuments(broj);
        if(obavestenje){
            return path;
        }
        return "";
    }


    public String downloadResenjeXHTML(String broj){
        String path = "src/main/resources/generated_files/documents/zahtev" + broj + ".html";
        boolean obavestenje = generateDocuments(broj);
        if(obavestenje){
            return path;
        }
        return "";
    }
}
