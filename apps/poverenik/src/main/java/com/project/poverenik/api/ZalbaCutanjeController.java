package com.project.poverenik.api;

import com.project.poverenik.model.user.User;
import com.project.poverenik.model.util.lists.ZalbaCutanjeList;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.service.ZalbaCutanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping(value = "/zalba-cutanje", produces = MediaType.APPLICATION_XML_VALUE)
public class ZalbaCutanjeController {

    @Autowired
    ZalbaCutanjeService zalbaCutanjeService;

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/search-metadata", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZalbaCutanjeList> searchMetadata(@RequestParam("datumAfter") String datumAfter,
                                                           @RequestParam("datumBefore") String datumBefore,
                                                           @RequestParam("status") String status,
                                                           @RequestParam("organ_vlasti") String organ_vlasti,
                                                           @RequestParam("mesto") String mesto,
                                                           @RequestParam("userEmail") String userEmail,
                                                           @RequestParam("zahtevId") String zahtevId) throws XMLDBException, JAXBException, IOException {
        ZalbaCutanjeList zalbaCutanjeList = zalbaCutanjeService.searchMetadata(
                datumAfter, datumBefore, status, organ_vlasti, mesto, userEmail, zahtevId);
        return new ResponseEntity<>(zalbaCutanjeList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/search-text", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZalbaCutanjeList> searchText(@RequestParam("input") String input) throws XMLDBException, JAXBException, IOException {

        ZalbaCutanjeList zalbaCutanjeList = zalbaCutanjeService.searchText(input);
        return new ResponseEntity<>(zalbaCutanjeList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createZalbaCutanje(@RequestBody ZalbaCutanje zalbaCutanje) throws XMLDBException, JAXBException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (zalbaCutanjeService.create(zalbaCutanje, user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_POVERENIK')")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZalbaCutanjeList> getZalbaCutanjeList() throws XMLDBException, JAXBException {
        ZalbaCutanjeList zalbaCutanjeList = zalbaCutanjeService.getAll();

        if (zalbaCutanjeList != null)
            return new ResponseEntity<>(zalbaCutanjeList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalbaCutanje(@PathVariable String id) throws XMLDBException, JAXBException {
        ZalbaCutanje zalbaCutanje = zalbaCutanjeService.getOne(id);
        if (zalbaCutanje != null)
            return new ResponseEntity<>(zalbaCutanje, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/by-user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalbaCutanjeListByUser() throws XMLDBException, JAXBException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ZalbaCutanjeList zalbaCutanjeList = zalbaCutanjeService.getByUser(user.getEmail());
        if (zalbaCutanjeList != null)
            return new ResponseEntity<>(zalbaCutanjeList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/by-status", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalbaCutanjeListObradaOrNeobradjena() throws XMLDBException, JAXBException {
        ZalbaCutanjeList zalbaCutanjeList = zalbaCutanjeService.getByObradaOrNeobradjena();
        if (zalbaCutanjeList != null)
            return new ResponseEntity<>(zalbaCutanjeList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> delete(@PathVariable String id) throws XMLDBException {
        if (zalbaCutanjeService.delete(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> update(@RequestBody ZalbaCutanje zalbaCutanje) throws XMLDBException, JAXBException {
        if (zalbaCutanjeService.update(zalbaCutanje, ""))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/toPdf/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadResenjePDF(@PathVariable String broj) {
        String path = zalbaCutanjeService.downloadResenjePDF(broj);
        if(!path.equals("")){
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
        String path = zalbaCutanjeService.downloadResenjeXHTML(broj);
        if(!path.equals("")){
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
    
    //cutanje-1
    @RequestMapping(value = "/toRdf/{idZalbe}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadZalbaCutanjeRdf(@PathVariable String idZalbe) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {

        String path = zalbaCutanjeService.generateRdf(idZalbe);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //cutanje-1
    @RequestMapping(value = "/toJson/{idZalbe}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadZalbaCutanjeJson(@PathVariable String idZalbe) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {

        String path = zalbaCutanjeService.generateJson(idZalbe);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
