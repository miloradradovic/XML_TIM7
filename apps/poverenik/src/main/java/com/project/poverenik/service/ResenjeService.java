package com.project.poverenik.service;

import com.project.poverenik.client.EmailClient;
import com.project.poverenik.client.ResenjeRefClient;
import com.project.poverenik.database.ExistManager;
import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.mappers.ResenjeMapper;
import com.project.poverenik.model.resenje.Resenje;
import com.project.poverenik.model.resenje.database.client.SetResenjeRef;
import com.project.poverenik.model.user.User;
import com.project.poverenik.model.util.email.Tbody;
import com.project.poverenik.model.util.email.client.sendAttach;
import com.project.poverenik.model.util.lists.ResenjeList;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import com.project.poverenik.rdf_utils.AuthenticationUtilities;
import com.project.poverenik.rdf_utils.AuthenticationUtilities.ConnectionProperties;
import com.project.poverenik.rdf_utils.FileUtil;
import com.project.poverenik.repository.ResenjeRepository;
import com.project.poverenik.transformer.Transformator;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResenjeService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ResenjeRepository resenjeRepository;

    @Autowired
    ZalbaCutanjeService zalbaCutanjeService;

    @Autowired
    ZalbaOdlukaService zalbaOdlukaService;

    @Autowired
    private ExistManager existManager;

    private String getMaxId() throws XMLDBException, JAXBException {
        ResourceSet max = resenjeRepository.getMaxId();
        ResourceIterator resourceIterator = max.getIterator();

        if (!resourceIterator.hasMoreResources()) {
            return "0000";
        }
        XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
        if (xmlResource == null)
            return "0000";
        JAXBContext context = JAXBContext.newInstance(Resenje.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Resenje resenjeMax = (Resenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
        return resenjeMax.getResenjeBody().getId();

    }

    public boolean create(Resenje resenjeDTO) throws XMLDBException, JAXBException {
        if (jaxB.validate(resenjeDTO.getClass(), resenjeDTO)) {
            String id = String.valueOf(Integer.parseInt(getMaxId()) + 1);
            String status = resenjeDTO.getResenjeBody().getTipResenja().getValue();

            String zalba = resenjeDTO.getResenjeBody().getOtherAttributes().get(new QName("idZalbe")).split("/")[0];
            String idZalbe = resenjeDTO.getResenjeBody().getOtherAttributes().get(new QName("idZalbe")).split("/")[1];
            String email;
            if (zalba.equals("cutanje")) {
                ZalbaCutanje zalbaCutanje = zalbaCutanjeService.getOne(idZalbe);
                if (zalbaCutanje == null)
                    return false;
                email = zalbaCutanje.getZalbaCutanjeBody().getPodaciOPodnosiocu().getOsoba().getOtherAttributes().get(new QName("id"));
                zalbaCutanjeService.update(zalbaCutanje, status);
            } else {
                ZalbaOdluka zalbaOdluka = zalbaOdlukaService.getOne(idZalbe);
                if (zalbaOdluka == null)
                    return false;
                email = zalbaOdluka.getZalbaOdlukaBody().getZalilac().getTipLica().getOsoba().getOtherAttributes().get(new QName("id"));
                zalbaOdlukaService.update(zalbaOdluka, status);
            }

            Resenje resenje = ResenjeMapper.mapFromDTO(resenjeDTO, id, email);

            if (jaxB.validate(resenje.getClass(), resenje)) {
                String broj = resenjeRepository.create(resenje);
                if (broj != null) {
                    return sendToOrganVlasti(broj) && sendToUser(broj, email);
                }
            }
        }
        return false;
    }

    public ResenjeList getAll() throws XMLDBException, JAXBException {

        List<Resenje> resenjeList = new ArrayList<>();

        ResourceSet resourceSet = resenjeRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()) {
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if (xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(Resenje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Resenje resenje = (Resenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            resenjeList.add(resenje);
        }
        return new ResenjeList(resenjeList);
    }

    public Resenje getOne(String broj) throws JAXBException, XMLDBException {
        XMLResource xmlResource = resenjeRepository.getOne(broj);

        if (xmlResource == null)
            return null;

        Resenje resenje;

        JAXBContext context = JAXBContext.newInstance(Resenje.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        resenje = (Resenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return resenje;
    }

    public boolean delete(String broj) throws XMLDBException {
        return resenjeRepository.delete(broj);
    }

    public boolean update(Resenje resenje) throws JAXBException, XMLDBException {
        String patch = jaxB.marshall(resenje.getClass(), resenje);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        patch = patch.substring(patch.lastIndexOf("<uvodne_informacije>"), patch.indexOf("</poverenik>") + "</poverenik>".length());
        return resenjeRepository.update(resenje.getResenjeBody().getBroj(), patch);
    }

    public Resenje getResenjeByZalba(String idZalbe) throws JAXBException, XMLDBException {
        ResourceSet resourceSet = resenjeRepository.getResenjeByZalba(idZalbe);

        ResourceIterator resourceIterator = resourceSet.getIterator();

        if (!resourceIterator.hasMoreResources()) {
            return null;
        }
        XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
        if (xmlResource == null)
            return null;
        JAXBContext context = JAXBContext.newInstance(Resenje.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Resenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

    }

    public ResenjeList searchMetadata(String poverenikEmail, String trazilacEmail, String idZalbe, String datumAfter, String datumBefore, String tip, String organVlasti, String mesto) throws IOException, JAXBException, XMLDBException {
        ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        if (datumAfter.equals("")) {
            datumAfter = "1000-01-01T00:00:00";
        }
        if (datumBefore.equals("")) {
            datumBefore = "9999-12-31T00:00:00";
        }

        String sparqlQueryTemplate = FileUtil.readFile("src/main/resources/rdf_data/query_search_metadata_resenje.rq",
                StandardCharsets.UTF_8);
        System.out.println(sparqlQueryTemplate);

        String poverenik;
        if (poverenikEmail.equals("")) {
            poverenik = "?poverenik";
        } else {
            poverenik = "<http://users/" + poverenikEmail + ">";
        }
        String trazilac;
        if (trazilacEmail.equals("")) {
            trazilac = "?trazilac";
        } else {
            trazilac = "<http://users/" + trazilacEmail + ">";
        }
        String zalba;
        idZalbe = idZalbe.replace("-", "/");
        if (idZalbe.equals("")) {
            zalba = "?responseTo";
        } else {
            zalba = "<http://zalbe/" + idZalbe + ">";
        }

        String sparqlQuery = String.format(sparqlQueryTemplate, poverenik, trazilac, zalba, datumAfter, datumBefore, tip, organVlasti, mesto);
        System.out.println(sparqlQuery);

        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

        ResultSet results = query.execSelect();

        RDFNode id;

        ResenjeList rList;

        List<Resenje> listR = new ArrayList<>();

        while (results.hasNext()) {

            QuerySolution querySolution = results.next();

            id = querySolution.get("resenje");
            String idStr = id.toString().split("resenja/")[1];
            Resenje r = getOne(idStr);
            listR.add(r);
        }

        rList = new ResenjeList(listR);
        System.out.println();

        query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

        results = query.execSelect();

        // ResultSetFormatter.outputAsXML(System.out, results);
        ResultSetFormatter.out(System.out, results);

        query.close();

        System.out.println("[INFO] End.");

        return rList;

    }

    public ResenjeList getByUser(String email) throws XMLDBException, JAXBException {
        List<Resenje> resenjeList = new ArrayList<>();

        ResourceSet resourceSet = resenjeRepository.getAllByUser(email);
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()) {
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if (xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(Resenje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Resenje resenje = (Resenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            resenjeList.add(resenje);
        }
        return new ResenjeList(resenjeList);
    }

    public boolean generateDocuments(String broj) {
        final String OUTPUT_PDF = "src/main/resources/generated_files/documents/resenje" + broj + ".pdf";
        final String OUTPUT_HTML = "src/main/resources/generated_files/documents/resenje" + broj + ".html";
        final String XSL_FO = "src/main/resources/generated_files/xsl-fo/resenje_fo.xsl";
        final String XSL = "src/main/resources/generated_files/xslt/resenje.xsl";

        System.out.println("[INFO] " + Transformator.class.getSimpleName());

        try {
            Transformator transformator = new Transformator();
            Resenje xml = getOne(broj);
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

    public ResenjeList searchText(String text) throws JAXBException, XMLDBException {
        List<Resenje> resenjeList = new ArrayList<>();

        ResourceSet resourceSet = resenjeRepository.searchText(text);
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()) {
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if (xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(Resenje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Resenje resenje = (Resenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            resenjeList.add(resenje);
        }
        return new ResenjeList(resenjeList);

    }

    private boolean sendToOrganVlasti(String broj) {

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.resenje.database.client");

        ResenjeRefClient resenjeRefClient = new ResenjeRefClient();
        resenjeRefClient.setDefaultUri("http://localhost:8090/ws");
        resenjeRefClient.setMarshaller(marshaller);
        resenjeRefClient.setUnmarshaller(marshaller);

        SetResenjeRef setResenjeRef = new SetResenjeRef();
        setResenjeRef.setResenje_ref(broj);
        return resenjeRefClient.sendResenjeRef(setResenjeRef);

    }

    private boolean sendToUser(String broj, String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.util.email.client");

        EmailClient emailClient = new EmailClient();
        emailClient.setDefaultUri("http://localhost:8095/ws");
        emailClient.setMarshaller(marshaller);
        emailClient.setUnmarshaller(marshaller);

        sendAttach sendAttach = new sendAttach();
        sendAttach.setEmail(new Tbody());
        sendAttach.getEmail().setTo(email);
        sendAttach.getEmail().setContent("Postovani, <br/><br/> Dostavljamo Vam resenje na Vasu zalbu. <br/><br/> Srdacno,  " + user.getName() + " " + user.getLastName());
        sendAttach.getEmail().setSubject("Resenje " + broj);

        generateDocuments(broj);
        //TODO - boolean povezati
        String pdfName = "resenje" + broj + ".pdf";
        sendAttach.getEmail().setFilePdfName(pdfName);
        String htmlName = "resenje" + broj + ".html";
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

    public ResponseEntity<?> downloadResenjePDF(String broj) {
        String path = "src/main/resources/generated_files/documents/resenje" + broj + ".pdf";
        boolean obavestenje = generateDocuments(broj);
        if (obavestenje) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(Paths.get(path)));

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/xml; charset=utf-8");
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resenje" + broj + ".pdf");
                return new ResponseEntity<>(new InputStreamResource(bis), headers, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> downloadResenjeXHTML(String broj) {
        String path = "src/main/resources/generated_files/documents/resenje" + broj + ".html";
        boolean obavestenje = generateDocuments(broj);
        if (obavestenje) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(Paths.get(path)));

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/xml; charset=utf-8");
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resenje" + broj + ".html");
                return new ResponseEntity<>(new InputStreamResource(bis), headers, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(obavestenje, HttpStatus.BAD_REQUEST);
    }

}
