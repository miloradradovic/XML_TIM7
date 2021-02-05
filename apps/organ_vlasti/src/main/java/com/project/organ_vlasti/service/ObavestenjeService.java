package com.project.organ_vlasti.service;

import com.project.organ_vlasti.client.EmailClient;
import com.project.organ_vlasti.database.ExistManager;
import com.project.organ_vlasti.jaxb.JaxB;
import com.project.organ_vlasti.mappers.ObavestenjeMapper;
import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import com.project.organ_vlasti.model.user.User;
import com.project.organ_vlasti.model.util.email.Tbody;
import com.project.organ_vlasti.model.util.email.client.sendAttach;
import com.project.organ_vlasti.model.util.lists.ObavestenjeList;
import com.project.organ_vlasti.model.zahtev.Zahtev;
import com.project.organ_vlasti.rdf_utils.AuthenticationUtilities;
import com.project.organ_vlasti.rdf_utils.AuthenticationUtilities.ConnectionProperties;
import com.project.organ_vlasti.rdf_utils.FileUtil;
import com.project.organ_vlasti.rdf_utils.MetadataExtractor;
import com.project.organ_vlasti.rdf_utils.SparqlUtil;
import com.project.organ_vlasti.repository.ObavestenjeRepository;
import com.project.organ_vlasti.transformer.Transformator;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
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
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObavestenjeService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ObavestenjeRepository obavestenjeRepository;

    @Autowired
    private ExistManager existManager;

    @Autowired
    ZahtevService zahtevService;

    private String getMaxId() throws XMLDBException, JAXBException {
        ResourceSet max = obavestenjeRepository.getMaxId();
        ResourceIterator resourceIterator = max.getIterator();

        if (!resourceIterator.hasMoreResources()) {
            return "0000";
        }
        XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
        if (xmlResource == null)
            return "0000";
        JAXBContext context = JAXBContext.newInstance(Obavestenje.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Obavestenje obavestenjeMax = (Obavestenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
        return obavestenjeMax.getObavestenjeBody().getId();

    }

    public boolean create(Obavestenje obavestenjeDTO) throws XMLDBException, JAXBException {

        if (jaxB.validate(obavestenjeDTO.getClass(), obavestenjeDTO)) {
            String id = String.valueOf(Integer.parseInt(getMaxId()) + 1);

            //email usera koji je podnio zahtjev na koji se odnosi obavjestenje
            String zahtevId = obavestenjeDTO.getObavestenjeBody().getIdZahteva();
            Zahtev zahtev = zahtevService.getOne(zahtevId);
            String userEmail = zahtev.getZahtevBody().getInformacijeOTraziocu().getLice().getOsoba().getOtherAttributes().get(new QName("id"));
            Obavestenje obavestenje = ObavestenjeMapper.mapFromDTO(obavestenjeDTO, id, userEmail);

            if (jaxB.validate(obavestenje.getClass(), obavestenje)) {
                if (obavestenjeRepository.create(obavestenje) != null) {
                    String email = obavestenje.getObavestenjeBody().getInformacijeOPodnosiocu().getLice().getOsoba().getOtherAttributes().get(new QName("id"));
                    zahtevService.update(zahtev, "prihvacen");
                    return sendToUser(id, email);
                }
            }
        }
        return false;
    }

    private boolean sendToUser(String broj, String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.util.email.client");

        EmailClient emailClient = new EmailClient();
        emailClient.setDefaultUri("http://localhost:8095/ws");
        emailClient.setMarshaller(marshaller);
        emailClient.setUnmarshaller(marshaller);

        sendAttach sendAttach = new sendAttach();
        sendAttach.setEmail(new Tbody());
        sendAttach.getEmail().setTo(email);
        sendAttach.getEmail().setContent("Postovani, <br/><br/> dostavljamo Vam obavestenje na Vas zahtev. <br/><br/> Srdacno,  " + user.getName() + " " + user.getLastName());
        sendAttach.getEmail().setSubject("Obavestenje " + broj);

        boolean obavestenje = generateDocuments(broj);
        //TODO - boolean provera


        String pdfName = "obavestenje" + broj + ".pdf";
        sendAttach.getEmail().setFilePdfName(pdfName);
        String htmlName = "obavestenje" + broj + ".html";
        sendAttach.getEmail().setFileHtmlName(htmlName);
        try {
            File filePdf = new File("src/main/resources/generated_files/documents/" + pdfName);
            Path pdfPath = filePdf.toPath();
            byte[] pdfBytes = Files.readAllBytes(pdfPath);

            sendAttach.getEmail().setFilePdf(pdfBytes);

            File fileHtml = new File("src/main/resources/generated_files/documents/" + htmlName);
            Path htmlPath = fileHtml.toPath();
            byte[] htmlBytes = Files.readAllBytes(htmlPath);

            sendAttach.getEmail().setFileHtml(htmlBytes);


            return emailClient.sentAttach(sendAttach);

        } catch (IOException e) {
            e.getStackTrace();
            return false;
        }
    }

    public ObavestenjeList getAll() throws XMLDBException, JAXBException {
        List<Obavestenje> obavestenjeList = new ArrayList<>();

        ResourceSet resourceSet = obavestenjeRepository.getAll();
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

    public Obavestenje getOne(String broj) throws JAXBException, XMLDBException {
        XMLResource xmlResource = obavestenjeRepository.getOne(broj);

        if (xmlResource == null)
            return null;

        Obavestenje obavestenje;

        JAXBContext context = JAXBContext.newInstance(Obavestenje.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        obavestenje = (Obavestenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return obavestenje;
    }

    public Obavestenje getObavestenjeByZahtev(String idZahteva) throws JAXBException, XMLDBException {
        ResourceSet resourceSet = obavestenjeRepository.getObavestenjeByZahtev(idZahteva);

        ResourceIterator resourceIterator = resourceSet.getIterator();

        if (!resourceIterator.hasMoreResources()) {
            return null;
        }
        XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
        if (xmlResource == null)
            return null;
        JAXBContext context = JAXBContext.newInstance(Obavestenje.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Obavestenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

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

    public boolean generateDocuments(String broj) {
        final String OUTPUT_PDF = "src/main/resources/generated_files/documents/obavestenje" + broj + ".pdf";
        final String OUTPUT_HTML = "src/main/resources/generated_files/documents/obavestenje" + broj + ".html";
        final String XSL_FO = "src/main/resources/generated_files/xsl-fo/obavestenje_fo.xsl";
        final String XSL = "src/main/resources/generated_files/xslt/obavestenje.xsl";


        System.out.println("[INFO] " + Transformator.class.getSimpleName());


        try {
            Transformator transformator = new Transformator();
            Obavestenje xml = getOne(broj);
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

    public ObavestenjeList searchMetadata(String datumAfter, String datumBefore, String organ_vlasti,
                                          String userEmail, String zahtev) throws IOException, JAXBException, XMLDBException {
        ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        if (datumAfter.equals("")) {
            datumAfter = "1000-01-01T00:00:00";
        }
        if (datumBefore.equals("")) {
            datumBefore = "9999-12-31T00:00:00";
        }

        String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_search_metadata_obavestenje.rq",
                StandardCharsets.UTF_8);
        System.out.println(sparqlQueryTemplate);

        String user;
        if (userEmail.equals("")) {
            user = "?podnosilac";
        } else {
            user = "<http://users/" + userEmail + ">";
        }
        String responseTo;
        if (zahtev.equals("")) {
            responseTo = "?zahtev";
        } else {
            responseTo = "<http://zahtevi/" + zahtev + ">";
        }

        String sparqlQuery = String.format(sparqlQueryTemplate, user, responseTo, datumAfter, datumBefore, organ_vlasti);
        System.out.println(sparqlQuery);

        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

        ResultSet results = query.execSelect();

        RDFNode id;

        ObavestenjeList zcList;

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

    public ObavestenjeList searchText(String text) throws JAXBException, XMLDBException {
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
    
    public String generateRdf(String id) throws XMLDBException, TransformerException, SAXException, IOException, JAXBException {
		Obavestenje xml = getOne(id);
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
        model.write(new FileOutputStream(new File("src/main/resources/generated_files/metadata/" + "obavestenje-" + id + ".rdf")), SparqlUtil.RDF_XML);
        return "src/main/resources/generated_files/metadata/" + "obavestenje-" + id + ".rdf";
	}
	
	public String generateJson(String id) throws XMLDBException, TransformerException, SAXException, IOException, JAXBException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_obavestenje_json.rq",
				StandardCharsets.UTF_8);
		System.out.println(sparqlQueryTemplate);

		String obavestenje = "<http://obavestenja/" + id + ">";
		String sparqlQuery = String.format(sparqlQueryTemplate, obavestenje, obavestenje, obavestenje, obavestenje);
		System.out.println(sparqlQuery);

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		ResultSet results = query.execSelect();

		query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		results = query.execSelect();
		ResultSetFormatter.outputAsJSON(new FileOutputStream(new File("src/main/resources/generated_files/metadata/" + "obavestenje-" + id + ".json")), results);
		query.close();
		return "src/main/resources/generated_files/metadata/" + "obavestenje-" + id + ".json";
	}
}
