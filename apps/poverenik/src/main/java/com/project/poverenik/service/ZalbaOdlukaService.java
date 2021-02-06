package com.project.poverenik.service;

import com.project.poverenik.database.ExistManager;
import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.mappers.ZalbaOdlukaMapper;
import com.project.poverenik.model.user.User;
import com.project.poverenik.model.util.lists.ZalbaOdlukaList;
import com.project.poverenik.model.zalba_odluka.Tzalba;
import com.project.poverenik.model.zahtev.client.getZahtevResponse;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import com.project.poverenik.rdf_utils.AuthenticationUtilities;
import com.project.poverenik.rdf_utils.AuthenticationUtilities.ConnectionProperties;
import com.project.poverenik.rdf_utils.FileUtil;
import com.project.poverenik.rdf_utils.MetadataExtractor;
import com.project.poverenik.rdf_utils.SparqlUtil;
import com.project.poverenik.repository.ZalbaOdlukaRepository;
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
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.TransformerException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ZalbaOdlukaService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ZalbaOdlukaRepository zalbaOdlukaRepository;

    @Autowired
    private ExistManager existManager;

    @Autowired
    private ZahtevService zahtevService;

    private String getMaxId() throws XMLDBException, JAXBException {
        ResourceSet max = zalbaOdlukaRepository.getMaxId();
        ResourceIterator resourceIterator = max.getIterator();

        if (!resourceIterator.hasMoreResources()) {
            return "0";
        }
        XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
        if (xmlResource == null)
            return "0";
        JAXBContext context = JAXBContext.newInstance(ZalbaOdluka.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ZalbaOdluka zalbaOdlukaMax = (ZalbaOdluka) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
        return zalbaOdlukaMax.getZalbaOdlukaBody().getId();

    }

    public Tzalba getByZahtevId(String zahtevId) throws Exception {

        ResourceSet resourceSet = zalbaOdlukaRepository.getOneByZahtev(zahtevId);
        ResourceIterator resourceIterator = resourceSet.getIterator();

        if (!resourceIterator.hasMoreResources()) {
            throw new Exception("");
        }
        XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
        if (xmlResource == null)
            throw new Exception("");
        JAXBContext context = JAXBContext.newInstance(ZalbaOdluka.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ZalbaOdluka zalbaOdluka = (ZalbaOdluka) unmarshaller.unmarshal(xmlResource.getContentAsDOM());


        return zalbaOdluka.getZalbaOdlukaBody();
    }

    public boolean create(ZalbaOdluka zalbaOdlukaDTO) throws XMLDBException, NumberFormatException, JAXBException {
        if (jaxB.validate(zalbaOdlukaDTO.getClass(), zalbaOdlukaDTO)) {

            if (!canMakeZalba(zalbaOdlukaDTO.getZalbaOdlukaBody().getZahtev().getValue())) {
                return false;
            }

            String id = String.valueOf(Integer.parseInt(getMaxId()) + 1);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();

            ZalbaOdluka zalbaOdluka = ZalbaOdlukaMapper.mapFromDTO(zalbaOdlukaDTO, id, user.getEmail());

            if (jaxB.validate(zalbaOdluka.getClass(), zalbaOdluka)) {
                return zalbaOdlukaRepository.create(zalbaOdluka, false);
            }
        }
        return false;
    }

    public boolean canMakeZalba(String idZahteva) {
        getZahtevResponse zahtevResponse = zahtevService.getZahtev(idZahteva);
        if (zahtevResponse == null)
            return false;
        else if(zahtevResponse.getZahtev().getStatus().getValue().equals("odbijen")){
            return true;
        }
        return false;
    }

    public ZalbaOdlukaList getAll() throws XMLDBException, JAXBException {
        List<ZalbaOdluka> zalbaOdlukaList = new ArrayList<>();

        ResourceSet resourceSet = zalbaOdlukaRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()) {
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if (xmlResource == null)
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

        if (xmlResource == null)
            return null;

        ZalbaOdluka zalbaOdluka;

        JAXBContext context = JAXBContext.newInstance(ZalbaOdluka.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        zalbaOdluka = (ZalbaOdluka) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return zalbaOdluka;
    }

    public boolean delete(String id) throws XMLDBException {
        return zalbaOdlukaRepository.delete(id);
    }

    public boolean update(ZalbaOdluka zalbaOdluka, String status) throws XMLDBException {
    	try {
			updateStatusFuseki(zalbaOdluka.getZalbaOdlukaBody().getId(), zalbaOdluka.getZalbaOdlukaBody().getStatus().getValue(), status);
		} catch (Exception e) {
			e.printStackTrace();
		}
        zalbaOdluka.getZalbaOdlukaBody().getStatus().setValue(status);
        return zalbaOdlukaRepository.create(zalbaOdluka, true);

        //String patch = jaxB.marshall(zalbaOdluka.getClass(), zalbaOdluka);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        //patch = patch.substring(patch.lastIndexOf("<zoc:zalba_odluka_body"), patch.indexOf("</zoc:zalba_odluka_body>") + "</zoc:zalba_odluka_body>".length());
    }

    public ZalbaOdlukaList searchMetadata(String datumAfter, String datumBefore, String status, String organ_vlasti,
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

        String sparqlQuery = String.format(sparqlQueryTemplate, zahtev, user, datumAfter, datumBefore, status, organ_vlasti, mesto);
        System.out.println(sparqlQuery);

        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

        ResultSet results = query.execSelect();

        RDFNode id;

        ZalbaOdlukaList zcList;

        List<ZalbaOdluka> listZC = new ArrayList<>();

        while (results.hasNext()) {

            QuerySolution querySolution = results.next();

            id = querySolution.get("zalba");
            System.out.println(id);
            if (id.toString().contains("odluka")) {
                String idStr = id.toString().split("zalbe/odluka/")[1];
                ZalbaOdluka z = getOne(idStr);
                listZC.add(z);
                System.out.println(z);
            }
        }

        zcList = new ZalbaOdlukaList(listZC);
        System.out.println();

        query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

        results = query.execSelect();

        // ResultSetFormatter.outputAsXML(System.out, results);
        ResultSetFormatter.out(System.out, results);

        query.close();

        System.out.println("[INFO] End.");

        return zcList;

    }

    public String downloadResenjePDF(String broj) {
        String path = "src/main/resources/generated_files/documents/zalbaodluka" + broj + ".pdf";
        boolean obavestenje = generateDocuments(broj);
        if (obavestenje) {
            return path;
        }
        return "";
    }


    public String downloadResenjeXHTML(String broj) {
        String path = "src/main/resources/generated_files/documents/zalbaodluka" + broj + ".html";
        boolean obavestenje = generateDocuments(broj);
        if (obavestenje) {
            return path;
        }
        return "";
    }

    public boolean generateDocuments(String broj) {
        final String OUTPUT_PDF = "src/main/resources/generated_files/documents/zalbaodluka" + broj + ".pdf";
        final String OUTPUT_HTML = "src/main/resources/generated_files/documents/zalbaodluka" + broj + ".html";
        final String XSL_FO = "src/main/resources/generated_files/xsl-fo/zalbaodluka_fo.xsl";
        final String XSL = "src/main/resources/generated_files/xslt/zalbaodluka.xsl";

        System.out.println("[INFO] " + Transformator.class.getSimpleName());

        try {
            Transformator transformator = new Transformator();
            ZalbaOdluka xml = getOne(broj);
            transformator.generateHTML(existManager.getOutputStream(xml),
                    XSL, OUTPUT_HTML);

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

    public ZalbaOdlukaList getByUser() throws XMLDBException, JAXBException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<ZalbaOdluka> zalbaOdlukaList = new ArrayList<>();

        ResourceSet resourceSet = zalbaOdlukaRepository.getAllByUser(user.getEmail());
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()) {
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if (xmlResource == null)
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

        while (resourceIterator.hasMoreResources()) {
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if (xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(ZalbaOdluka.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ZalbaOdluka zalbaOdluka = (ZalbaOdluka) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            zalbaOdlukaList.add(zalbaOdluka);
        }
        return new ZalbaOdlukaList(zalbaOdlukaList);
    }

    public ZalbaOdlukaList searchText(String text) throws JAXBException, XMLDBException {
        List<ZalbaOdluka> zalbaOdlukaList = new ArrayList<>();

        ResourceSet resourceSet = zalbaOdlukaRepository.searchText(text);
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()) {
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if (xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(ZalbaOdluka.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ZalbaOdluka zalbaOdluka = (ZalbaOdluka) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            zalbaOdlukaList.add(zalbaOdluka);
        }
        return new ZalbaOdlukaList(zalbaOdlukaList);

    }

    public Long getPodnete() throws XMLDBException {
        ResourceSet resourceSet = zalbaOdlukaRepository.getAll();
        return resourceSet.getSize();
    }

    public Long getPonistene() throws XMLDBException {
        ResourceSet resourceSet = zalbaOdlukaRepository.getPonistene();
        return resourceSet.getSize();
    }

    public Long getPrihvacene() throws XMLDBException {
        ResourceSet resourceSet = zalbaOdlukaRepository.getPrihvacene();
        return resourceSet.getSize();
    }

    public Long getOdbijene() throws XMLDBException {
        ResourceSet resourceSet = zalbaOdlukaRepository.getOdbijene();
        return resourceSet.getSize();
    }
    
	public String generateRdf(String idZalbe) throws XMLDBException, TransformerException, SAXException, IOException, JAXBException {
		ZalbaOdluka xml = getOne(idZalbe.split("-")[1]);
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

		String zalba = "<http://zalbe/odluka/" + idZalbe.split("-")[1] + ">";
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
	
	public void updateStatusFuseki(String idZalbe, String oldValue, String newValue) throws IOException, JAXBException, XMLDBException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/update_zalba.rq",
				StandardCharsets.UTF_8);
		System.out.println(sparqlQueryTemplate);

		String zalba = "<http://zalbe/odluka/" + idZalbe + ">";
 
		String sparqlQuery = String.format(sparqlQueryTemplate, zalba, oldValue, zalba, newValue);
		System.out.println(sparqlQuery);

		UpdateRequest request = UpdateFactory.create(sparqlQuery) ;
        UpdateProcessor proc = UpdateExecutionFactory.createRemote(request, conn.updateEndpoint);
        proc.execute();

	}
}
