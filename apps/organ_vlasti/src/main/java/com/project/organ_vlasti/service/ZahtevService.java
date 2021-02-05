package com.project.organ_vlasti.service;

import com.project.organ_vlasti.client.EmailClient;
import com.project.organ_vlasti.database.ExistManager;
import com.project.organ_vlasti.jaxb.JaxB;
import com.project.organ_vlasti.mappers.ZahtevMapper;
import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import com.project.organ_vlasti.model.user.User;
import com.project.organ_vlasti.model.util.email.Tbody;
import com.project.organ_vlasti.model.util.email.client.sendPlain;
import com.project.organ_vlasti.model.util.lists.ZahtevList;
import com.project.organ_vlasti.model.zahtev.Zahtev;
import com.project.organ_vlasti.rdf_utils.AuthenticationUtilities;
import com.project.organ_vlasti.rdf_utils.AuthenticationUtilities.ConnectionProperties;
import com.project.organ_vlasti.rdf_utils.FileUtil;
import com.project.organ_vlasti.rdf_utils.MetadataExtractor;
import com.project.organ_vlasti.rdf_utils.SparqlUtil;
import com.project.organ_vlasti.repository.ZahtevRepository;
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

        if (!resourceIterator.hasMoreResources()) {
            return "0";
        }
        XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
        if (xmlResource == null)
            return "0";
        JAXBContext context = JAXBContext.newInstance(Zahtev.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Zahtev zahteveMax = (Zahtev) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
        return zahteveMax.getZahtevBody().getId();

    }


    public boolean create(Zahtev zahtevDTO, String userEmail) throws XMLDBException, JAXBException {
        if (jaxB.validate(zahtevDTO.getClass(), zahtevDTO)) {
            String id = String.valueOf(Integer.parseInt(getMaxId()) + 1);

            Zahtev zahtev = ZahtevMapper.mapFromDTO(zahtevDTO, id, userEmail);
            if (jaxB.validate(zahtev.getClass(), zahtev)) {
                return zahtevRepository.create(zahtev);
            }
        }
        return false;
    }

    public ZahtevList getAll() throws XMLDBException, JAXBException {
        List<Zahtev> zahtevList = new ArrayList<>();

        ResourceSet resourceSet = zahtevRepository.getAll();
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

    public Zahtev getOne(String id) throws JAXBException, XMLDBException {
        XMLResource xmlResource = zahtevRepository.getOne(id);

        if (xmlResource == null)
            return null;

        Zahtev zahtev;

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

    public boolean generateDocuments(String broj) {
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

    public ZahtevList getAllByUser(String email) throws XMLDBException, JAXBException {
        List<Zahtev> zahtevList = new ArrayList<>();

        ResourceSet resourceSet = zahtevRepository.getAllByUser(email);
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

    public ZahtevList searchMetadata(String datumAfter, String datumBefore, String mesto, String organ_vlasti,
                                     String userEmail) throws IOException, JAXBException, XMLDBException {
        ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        if (datumAfter.equals("")) {
            datumAfter = "1000-01-01T00:00:00";
        }
        if (datumBefore.equals("")) {
            datumBefore = "9999-12-31T00:00:00";
        }

        String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_search_metadata_zahtev.rq",
                StandardCharsets.UTF_8);
        System.out.println(sparqlQueryTemplate);

        String user;
        if (userEmail.equals("")) {
            user = "?podnosilac";
        } else {
            user = "<http://users/" + userEmail + ">";
        }

        String sparqlQuery = String.format(sparqlQueryTemplate, user, datumAfter, datumBefore, mesto, organ_vlasti);
        System.out.println(sparqlQuery);

        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

        ResultSet results = query.execSelect();

        RDFNode id;

        ZahtevList zcList;

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

    public ZahtevList searchText(String text) throws JAXBException, XMLDBException {
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

    public boolean odbijZahtev(String info) throws XMLDBException, JAXBException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        //info = "zahtevId:poruka"
        String zahtevId = info.split(":")[0];
        String poruka = info.split(":")[1];

        Zahtev zahtev = getOne(zahtevId);
        String email = zahtev.getZahtevBody().getInformacijeOTraziocu().getLice().getOsoba().getOtherAttributes().get(new QName("id"));

        if (!update(zahtev, "odbijen")) {
            return false;
        }

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.util.email.client");

        EmailClient emailClient = new EmailClient();
        emailClient.setDefaultUri("http://localhost:8095/ws");
        emailClient.setMarshaller(marshaller);
        emailClient.setUnmarshaller(marshaller);

        sendPlain sendPlain = new sendPlain();
        sendPlain.setEmail(new Tbody());
        sendPlain.getEmail().setTo(email);
        sendPlain.getEmail().setContent("Postovani, <br/><br/> " + poruka + " <br/><br/> Srdacno, " + user.getName() + " " + user.getLastName());
        sendPlain.getEmail().setSubject("Zahtev: " + zahtevId + " odbijen");

        return emailClient.sentPlain(sendPlain);
    }

    public String downloadResenjePDF(String broj) {
        String path = "src/main/resources/generated_files/documents/zahtev" + broj + ".pdf";
        boolean obavestenje = generateDocuments(broj);
        if (obavestenje) {
            return path;
        }
        return "";
    }


    public String downloadResenjeXHTML(String broj) {
        String path = "src/main/resources/generated_files/documents/zahtev" + broj + ".html";
        boolean obavestenje = generateDocuments(broj);
        if (obavestenje) {
            return path;
        }
        return "";
    }
    
    public String generateRdf(String id) throws XMLDBException, TransformerException, SAXException, IOException, JAXBException {
		Zahtev xml = getOne(id);
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
        model.write(new FileOutputStream(new File("src/main/resources/generated_files/metadata/" + "zahtev-" + id + ".rdf")), SparqlUtil.RDF_XML);
        return "src/main/resources/generated_files/metadata/" + "zahtev-" + id + ".rdf";
	}
	
	public String generateJson(String id) throws XMLDBException, TransformerException, SAXException, IOException, JAXBException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_zahtev_json.rq",
				StandardCharsets.UTF_8);
		System.out.println(sparqlQueryTemplate);

		String zahtev = "<http://zahtevi/" + id + ">";
		String sparqlQuery = String.format(sparqlQueryTemplate, zahtev, zahtev, zahtev, zahtev);
		System.out.println(sparqlQuery);

		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		ResultSet results = query.execSelect();

		query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		results = query.execSelect();
		ResultSetFormatter.outputAsJSON(new FileOutputStream(new File("src/main/resources/generated_files/metadata/" + "zahtev-" + id + ".json")), results);
		query.close();
		return "src/main/resources/generated_files/metadata/" + "zahtev-" + id + ".json";
	}
}
