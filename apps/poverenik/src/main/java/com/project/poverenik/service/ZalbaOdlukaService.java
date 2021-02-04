package com.project.poverenik.service;

import com.project.poverenik.database.ExistManager;
import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.mappers.ZalbaOdlukaMapper;
import com.project.poverenik.model.util.lists.ZalbaOdlukaList;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import com.project.poverenik.rdf_utils.AuthenticationUtilities;
import com.project.poverenik.rdf_utils.AuthenticationUtilities.ConnectionProperties;
import com.project.poverenik.rdf_utils.FileUtil;
import com.project.poverenik.repository.ZalbaOdlukaRepository;
import com.project.poverenik.transformer.Transformator;
import org.apache.jena.query.*;
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
public class ZalbaOdlukaService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ZalbaOdlukaRepository zalbaOdlukaRepository;

    @Autowired
    private ExistManager existManager;

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

    public boolean create(ZalbaOdluka zalbaOdlukaDTO, String userEmail) throws XMLDBException, NumberFormatException, JAXBException {
        if (jaxB.validate(zalbaOdlukaDTO.getClass(), zalbaOdlukaDTO)) {

            String id = String.valueOf(Integer.parseInt(getMaxId()) + 1);

            ZalbaOdluka zalbaOdluka = ZalbaOdlukaMapper.mapFromDTO(zalbaOdlukaDTO, id, userEmail);

            if (jaxB.validate(zalbaOdluka.getClass(), zalbaOdluka)) {
                return zalbaOdlukaRepository.create(zalbaOdluka);
            } else {
                return false;
            }
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
        zalbaOdluka.getZalbaOdlukaBody().getStatus().setValue(status);
        return zalbaOdlukaRepository.create(zalbaOdluka);

        //String patch = jaxB.marshall(zalbaOdluka.getClass(), zalbaOdluka);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        //patch = patch.substring(patch.lastIndexOf("<zoc:zalba_odluka_body"), patch.indexOf("</zoc:zalba_odluka_body>") + "</zoc:zalba_odluka_body>".length());
    }

    public ZalbaOdlukaList searchMetadata(String datumAfter, String datumBefore, String status, String organ_vlasti,
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

        ZalbaOdlukaList zcList;

        List<ZalbaOdluka> listZC = new ArrayList<>();

        while (results.hasNext()) {

            QuerySolution querySolution = results.next();

            id = querySolution.get("zalba");
            if (id.toString().contains("odluka")) {
                String idStr = id.toString().split("zalbe/odluka/")[1];
                ZalbaOdluka z = getOne(idStr);
                listZC.add(z);
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

    public String downloadResenjePDF(String broj){
        String path = "src/main/resources/generated_files/documents/zalbaodluka" + broj + ".pdf";
        boolean obavestenje = generateDocuments(broj);
        if(obavestenje){
            return path;
        }
        return "";
    }


    public String downloadResenjeXHTML(String broj){
        String path = "src/main/resources/generated_files/documents/zalbaodluka" + broj + ".html";
        boolean obavestenje = generateDocuments(broj);
        if(obavestenje){
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

    public ZalbaOdlukaList getByUser(String email) throws XMLDBException, JAXBException {
        List<ZalbaOdluka> zalbaOdlukaList = new ArrayList<>();

        ResourceSet resourceSet = zalbaOdlukaRepository.getAllByUser(email);
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
}
