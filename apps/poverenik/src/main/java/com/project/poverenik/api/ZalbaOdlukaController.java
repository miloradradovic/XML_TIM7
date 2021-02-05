package com.project.poverenik.api;

import com.project.poverenik.model.util.lists.ZalbaOdlukaList;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import com.project.poverenik.service.ZalbaOdlukaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin(origins = "https://localhost:4201")
@RestController
@RequestMapping(value = "/zalba-odluka", produces = MediaType.APPLICATION_XML_VALUE)
public class ZalbaOdlukaController {

    @Autowired
    ZalbaOdlukaService zalbaOdlukaService;

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/search-metadata", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZalbaOdlukaList> searchMetadata(@RequestParam("datumAfter") String datumAfter,
                                                          @RequestParam("datumBefore") String datumBefore,
                                                          @RequestParam("status") String status,
                                                          @RequestParam("organ_vlasti") String organ_vlasti,
                                                          @RequestParam("mesto") String mesto,
                                                          @RequestParam("userEmail") String userEmail,
                                                          @RequestParam("zahtevId") String zahtevId) throws XMLDBException, JAXBException, IOException {

        ZalbaOdlukaList zalbaOdlukaList = zalbaOdlukaService.searchMetadata(
                datumAfter, datumBefore, status, organ_vlasti, mesto, userEmail, zahtevId);
      
        if (zalbaOdlukaList != null) {
            return new ResponseEntity<>(zalbaOdlukaList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/search-text", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZalbaOdlukaList> searchText(@RequestParam("input") String input) throws XMLDBException, JAXBException, IOException {

        ZalbaOdlukaList zalbaOdlukaList = zalbaOdlukaService.searchText(input);
        if (zalbaOdlukaList != null) {
            return new ResponseEntity<>(zalbaOdlukaList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createZalbaOdluka(@RequestBody ZalbaOdluka zalbaOdluka) throws XMLDBException, NumberFormatException, JAXBException {
        if (zalbaOdlukaService.create(zalbaOdluka)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_POVERENIK')")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZalbaOdlukaList> getZalbaOdlukaList() throws XMLDBException, JAXBException {
        ZalbaOdlukaList zalbaOdlukaList = zalbaOdlukaService.getAll();

        if (zalbaOdlukaList != null)
            return new ResponseEntity<>(zalbaOdlukaList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/by-user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZalbaOdlukaList> getZalbaOdlukaListByUser() throws XMLDBException, JAXBException {
        ZalbaOdlukaList zalbaOdlukaList = zalbaOdlukaService.getByUser();

        if (zalbaOdlukaList != null)
            return new ResponseEntity<>(zalbaOdlukaList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/by-status", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalbaOdlukaListObradaOrNeobradjena() throws XMLDBException, JAXBException {
        ZalbaOdlukaList zalbaOdlukaList = zalbaOdlukaService.getByObradaOrNeobradjena();
        if (zalbaOdlukaList != null)
            return new ResponseEntity<>(zalbaOdlukaList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalbaOdluka(@PathVariable String id) throws XMLDBException, JAXBException {
        ZalbaOdluka zalbaOdluka = zalbaOdlukaService.getOne(id);
        if (zalbaOdluka != null)
            return new ResponseEntity<>(zalbaOdluka, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/toPdf/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadResenjePDF(@PathVariable String broj) {
        String path = zalbaOdlukaService.downloadResenjePDF(broj);
        if (!path.equals("")) {
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

    @RequestMapping(value = "/toXhtml/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadResenjeXHTML(@PathVariable String broj) {
        String path = zalbaOdlukaService.downloadResenjeXHTML(broj);
        if (!path.equals("")) {
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
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
<<<<<<< HEAD
    
    //odluka-1
    @RequestMapping(value = "/toRdf/{idZalbe}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadZalbaCutanjeRdf(@PathVariable String idZalbe) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {

        String path = zalbaOdlukaService.generateRdf(idZalbe);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //odluka-1
    @RequestMapping(value = "/toJson/{idZalbe}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadZalbaCutanjeJson(@PathVariable String idZalbe) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {

        String path = zalbaOdlukaService.generateJson(idZalbe);
        return new ResponseEntity<>(HttpStatus.OK);
    }
=======

>>>>>>> 73d72a09413557e7541e3dacf99cecafd792a252
}
