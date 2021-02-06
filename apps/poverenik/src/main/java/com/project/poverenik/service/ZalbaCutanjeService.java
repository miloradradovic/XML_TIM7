package com.project.poverenik.service;

import com.project.poverenik.database.ExistManager;
import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.mappers.ZalbaCutanjeMapper;
import com.project.poverenik.model.user.User;
import com.project.poverenik.model.util.lists.ZalbaCutanjeList;
import com.project.poverenik.model.zahtev.Tzahtev;
import com.project.poverenik.model.zahtev.client.getZahtevResponse;
import com.project.poverenik.model.zalba_cutanje.Tzalba;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.rdf_utils.AuthenticationUtilities;
import com.project.poverenik.rdf_utils.AuthenticationUtilities.ConnectionProperties;
import com.project.poverenik.rdf_utils.FileUtil;
import com.project.poverenik.rdf_utils.MetadataExtractor;
import com.project.poverenik.rdf_utils.SparqlUtil;
import com.project.poverenik.repository.ZalbaCutanjeRepository;
import com.project.poverenik.transformer.Transformator;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.TransformerException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ZalbaCutanjeService {
	
	@Autowired
    private JaxB jaxB;

    @Autowired
    private ZalbaCutanjeRepository zalbaCutanjeRepository;

    @Autowired
    private ExistManager existManager;

    @Autowired
    private ZahtevService zahtevService;


	private String getMaxId() throws XMLDBException, JAXBException {
		ResourceSet max = zalbaCutanjeRepository.getMaxId();
		ResourceIterator resourceIterator = max.getIterator();

		if (!resourceIterator.hasMoreResources()) {
			return "0";
		}
		XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
		if (xmlResource == null)
			return "0";
		JAXBContext context = JAXBContext.newInstance(ZalbaCutanje.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		ZalbaCutanje zalbaCutanjeMax = (ZalbaCutanje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
		return zalbaCutanjeMax.getZalbaCutanjeBody().getId();
	}

	public boolean create(ZalbaCutanje zalbaCutanjeDTO, String userEmail) throws XMLDBException, JAXBException {
		if (jaxB.validate(zalbaCutanjeDTO.getClass(), zalbaCutanjeDTO)) {
			String id = String.valueOf(Integer.parseInt(getMaxId()) + 1);

			ZalbaCutanje zalbaCutanje = ZalbaCutanjeMapper.mapFromDTO(zalbaCutanjeDTO, id, userEmail);

			if (jaxB.validate(zalbaCutanje.getClass(), zalbaCutanje)) {
				return zalbaCutanjeRepository.create(zalbaCutanje, false);
			}
		}
		return false;
	}

	public ZalbaCutanjeList getAll() throws XMLDBException, JAXBException {
		List<ZalbaCutanje> zalbaCutanjeList = new ArrayList<>();

		ResourceSet resourceSet = zalbaCutanjeRepository.getAll();
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

	public ZalbaCutanje getOne(String id) throws JAXBException, XMLDBException {
		XMLResource xmlResource = zalbaCutanjeRepository.getOne(id);

		if (xmlResource == null)
			return null;

		ZalbaCutanje zalbaCutanje;

		JAXBContext context = JAXBContext.newInstance(ZalbaCutanje.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		zalbaCutanje = (ZalbaCutanje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

		return zalbaCutanje;
	}

	public boolean delete(String id) throws XMLDBException {
		return zalbaCutanjeRepository.delete(id);
	}

	public boolean update(ZalbaCutanje zalbaCutanje, String status) throws XMLDBException {
		try {
			updateStatusFuseki(zalbaCutanje.getZalbaCutanjeBody().getId(), zalbaCutanje.getZalbaCutanjeBody().getStatus().getValue(), status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		zalbaCutanje.getZalbaCutanjeBody().getStatus().setValue(status);
		return zalbaCutanjeRepository.create(zalbaCutanje, true);

		// String patch = jaxB.marshall(zalbaCutanje.getClass(), zalbaCutanje);
		// u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti
		// obrisani
		// patch = patch.substring(patch.lastIndexOf("<zc:zalba_cutanje_body"),
		// patch.indexOf("</zc:zalba_cutanje_body>") +
		// "</zc:zalba_cutanje_body>".length());
	}

	public ZalbaCutanjeList getByUser(String email) throws XMLDBException, JAXBException {
		List<ZalbaCutanje> zalbaCutanjeList = new ArrayList<>();

		ResourceSet resourceSet = zalbaCutanjeRepository.getAllByUser(email);
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

	public ZalbaCutanjeList searchMetadata(String datumAfter, String datumBefore, String status, String organ_vlasti,
			String mesto, String userEmail, String zahtevId) throws IOException, JAXBException, XMLDBException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		if (datumAfter.equals("")) {
			datumAfter = "1000-01-01T00:00:00";
		}
		if (datumBefore.equals("")) {
			datumBefore = "9999-12-31T00:00:00";
		}

		String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_search_metadata_zalbe.rq",
				StandardCharsets.UTF_8);
		System.out.println(sparqlQueryTemplate);

		String user;
		if (userEmail.equals("")) {
			user = "?podnosilac";
		} else {
			user = "<http://users/" + userEmail + ">";
		}
		String zahtev;
		if (zahtevId.equals("")) {
			zahtev = "?zahtev";
		} else {
			zahtev = "<http://zahtevi/" + zahtevId + ">";
		}

		String sparqlQuery = String.format(sparqlQueryTemplate, zahtev, user, datumAfter, datumBefore, status, organ_vlasti,
				mesto);
		System.out.println(sparqlQuery);

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		ResultSet results = query.execSelect();

		RDFNode id;

		ZalbaCutanjeList zcList;

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
		//ResultSetFormatter.outputAsJSON(System.out, results);

		ResultSetFormatter.out(System.out, results);
		
		query.close();

		System.out.println("[INFO] End.");

		return zcList;

	}

	public ZalbaCutanjeList searchText(String text) throws JAXBException, XMLDBException {
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

	public String downloadResenjePDF(String broj) {
		String path = "src/main/resources/generated_files/documents/zalbacutanje" + broj + ".pdf";
		boolean obavestenje = generateDocuments(broj);
		if (obavestenje) {
			return path;
		}
		return "";
	}

	public String downloadResenjeXHTML(String broj) {
		String path = "src/main/resources/generated_files/documents/zalbacutanje" + broj + ".html";
		boolean obavestenje = generateDocuments(broj);
		if (obavestenje) {
			return path;
		}
		return "";
	}

	public boolean generateDocuments(String broj) {
		final String OUTPUT_PDF = "src/main/resources/generated_files/documents/zalbacutanje" + broj + ".pdf";
		final String OUTPUT_HTML = "src/main/resources/generated_files/documents/zalbacutanje" + broj + ".html";
		final String XSL_FO = "src/main/resources/generated_files/xsl-fo/zalbacutanje_fo.xsl";
		final String XSL = "src/main/resources/generated_files/xslt/zalbacutanje.xsl";

		System.out.println("[INFO] " + Transformator.class.getSimpleName());

		try {
			Transformator transformator = new Transformator();
			ZalbaCutanje xml = getOne(broj);
			transformator.generateHTML(existManager.getOutputStream(xml), XSL, OUTPUT_HTML);
			transformator.generatePDF(XSL_FO, existManager.getOutputStream(xml), OUTPUT_PDF);
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

	public String generateRdf(String idZalbe) throws XMLDBException, TransformerException, SAXException, IOException, JAXBException {
		ZalbaCutanje xml = getOne(idZalbe.split("-")[1]);
		if (xml == null) {
			return "";
		}
		MetadataExtractor metadataExtractor = new MetadataExtractor();

        ByteArrayInputStream inStream = new ByteArrayInputStream(existManager.getOutputStream(xml).getBytes());

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        metadataExtractor.extractMetadata(
                inStream,
                outStream);
        ByteArrayInputStream rdfStream = new ByteArrayInputStream(outStream.toByteArray());


        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (rdfStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        String rdf = textBuilder.toString();
        Model model = ModelFactory.createDefaultModel();
        model.read(new StringReader(rdf),
                "TURTLE");
        model.write(new FileOutputStream(new File("src/main/resources/generated_files/metadata/" + idZalbe + ".rdf")), SparqlUtil.RDF_XML);
        return "src/main/resources/generated_files/metadata/" + idZalbe + ".rdf";
	}
	
	public String generateJson(String idZalbe) throws XMLDBException, TransformerException, SAXException, IOException, JAXBException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_zalba_json.rq",
				StandardCharsets.UTF_8);
		System.out.println(sparqlQueryTemplate);

		String zalba = "<http://zalbe/cutanje/" + idZalbe.split("-")[1] + ">";
		String sparqlQuery = String.format(sparqlQueryTemplate, zalba, zalba, zalba, zalba, zalba, zalba);
		System.out.println(sparqlQuery);

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		ResultSet results = query.execSelect();

		query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		results = query.execSelect();
		ResultSetFormatter.outputAsJSON(new FileOutputStream(new File("src/main/resources/generated_files/metadata/" + idZalbe + ".json")), results);
		query.close();
		return "src/main/resources/generated_files/metadata/" + idZalbe + ".json";
	}
    

    public boolean create(ZalbaCutanje zalbaCutanjeDTO) throws XMLDBException, JAXBException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (jaxB.validate(zalbaCutanjeDTO.getClass(), zalbaCutanjeDTO)) {

            if (!canMakeZalba(zalbaCutanjeDTO.getZalbaCutanjeBody().getZahtev().getValue())) {
                return false;
            }
            String id = String.valueOf(Integer.parseInt(getMaxId()) + 1);

            ZalbaCutanje zalbaCutanje = ZalbaCutanjeMapper.mapFromDTO(zalbaCutanjeDTO, id, user.getEmail());

            if (jaxB.validate(zalbaCutanje.getClass(), zalbaCutanje)) {
                return zalbaCutanjeRepository.create(zalbaCutanje, false);
            }
        }
        return false;
    }

    public boolean canMakeZalba(String idZahteva) {
        getZahtevResponse zahtevResponse = zahtevService.getZahtev(idZahteva);
        if (zahtevResponse == null)
            return false;
        else if(zahtevResponse.getZahtev().getStatus().getValue().equals("prihvacen")){
            return false;
        }

        SimpleDateFormat sdfAM = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss a");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        String content = zahtevResponse.getZahtev().getOtherAttributes().get(new QName("content"));
        Date fromString;
        Date dateNow = new Date();

        try{
            fromString = sdf.parse(content);
            dateNow = sdfAM.parse(sdfAM.format(dateNow));
        }catch (Exception e){
            fromString = null;
        }
        long difference_In_Time
                = dateNow.getTime() - fromString.getTime();
        long difference_In_Minutes
                = (difference_In_Time
                / (1000 * 60))
                % 60;
        return difference_In_Minutes >= 2;
    }

    
    public ZalbaCutanjeList getByUser() throws XMLDBException, JAXBException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<ZalbaCutanje> zalbaCutanjeList = new ArrayList<>();

        ResourceSet resourceSet = zalbaCutanjeRepository.getAllByUser(user.getEmail());
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

	public Tzalba getByZahtevId(String zahtevId) throws Exception {

		ResourceSet resourceSet = zalbaCutanjeRepository.getOneByZahtev(zahtevId);
		ResourceIterator resourceIterator = resourceSet.getIterator();

		if (!resourceIterator.hasMoreResources()) {
			throw new Exception("");
		}
			XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
			if (xmlResource == null)
				throw new Exception("");
			JAXBContext context = JAXBContext.newInstance(ZalbaCutanje.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			ZalbaCutanje zalbaCutanje = (ZalbaCutanje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());


		return zalbaCutanje.getZalbaCutanjeBody();
	}

    public ZalbaCutanjeList searchMetadata(String datumAfter, String datumBefore, String status, String organ_vlasti,
                                           String mesto, String userEmail) throws IOException, JAXBException, XMLDBException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		if (datumAfter.equals("")) {
			datumAfter = "1000-01-01T00:00:00";
		}
		if (datumBefore.equals("")) {
			datumBefore = "9999-12-31T00:00:00";
		}

		String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_search_metadata_zalbe.rq",
				StandardCharsets.UTF_8);
		System.out.println(sparqlQueryTemplate);

		String user;
		if (userEmail.equals("")) {
			user = "?podnosilac";
		} else {
			user = "<http://users/" + userEmail + ">";
		}

		String sparqlQuery = String.format(sparqlQueryTemplate, user, datumAfter, datumBefore, status, organ_vlasti, mesto);
		System.out.println(sparqlQuery);

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		ResultSet results = query.execSelect();

		RDFNode id;

		ZalbaCutanjeList zcList;

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

    public void updateStatusFuseki(String idZalbe, String oldValue, String newValue) throws IOException, JAXBException, XMLDBException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/update_zalba.rq",
				StandardCharsets.UTF_8);
		System.out.println(sparqlQueryTemplate);

		String zalba = "<http://zalbe/cutanje/" + idZalbe + ">";
 
		String sparqlQuery = String.format(sparqlQueryTemplate, zalba, oldValue, zalba, newValue);
		System.out.println(sparqlQuery);

		UpdateRequest request = UpdateFactory.create(sparqlQuery) ;
        UpdateProcessor proc = UpdateExecutionFactory.createRemote(request, conn.updateEndpoint);

	}
}
