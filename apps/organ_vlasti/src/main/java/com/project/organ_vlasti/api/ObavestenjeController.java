package com.project.organ_vlasti.api;

import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import com.project.organ_vlasti.model.user.User;
import com.project.organ_vlasti.model.util.lists.ObavestenjeList;
import com.project.organ_vlasti.service.ObavestenjeService;
import com.project.organ_vlasti.service.ZahtevService;
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
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/obavestenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ObavestenjeController {

    @Autowired
    ObavestenjeService obavestenjeService;

    @Autowired
    ZahtevService zahtevService;

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/search-metadata", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ObavestenjeList> searchMetadata(@RequestParam("datumAfter") String datumAfter,
                                                          @RequestParam("datumBefore") String datumBefore,
                                                          @RequestParam("organ_vlasti") String organ_vlasti,
                                                          @RequestParam("userEmail") String userEmail,
                                                          @RequestParam("zahtev") String zahtev) throws XMLDBException, JAXBException, IOException {

        ObavestenjeList obavestenjeList = obavestenjeService.searchMetadata(datumAfter, datumBefore, organ_vlasti, userEmail, zahtev);
        return new ResponseEntity<>(obavestenjeList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/search-text", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ObavestenjeList> searchText(@RequestParam("input") String input) throws XMLDBException, JAXBException {

        ObavestenjeList obavestenjeList = obavestenjeService.searchText(input);
        if(obavestenjeList != null){
            return new ResponseEntity<>(obavestenjeList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createObavestenje(@RequestBody Obavestenje obavestenje) throws XMLDBException, JAXBException {
        if (obavestenjeService.create(obavestenje)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ObavestenjeList> getObavestenjeList() throws XMLDBException, JAXBException {
        ObavestenjeList obavestenjeList = obavestenjeService.getAll();
        if (obavestenjeList != null)
            return new ResponseEntity<>(obavestenjeList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI') || hasRole('ROLE_USER')")
    @RequestMapping(value = "/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getObavestenje(@PathVariable String broj) throws XMLDBException, JAXBException {
        Obavestenje obavestenje = obavestenjeService.getOne(broj);
        if (obavestenje != null)
            return new ResponseEntity<>(obavestenje, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI') || hasRole('ROLE_USER')")
    @RequestMapping(value = "/by-zahtev/{idZahteva}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getObavestenjeByZahtev(@PathVariable String idZahteva) throws XMLDBException, JAXBException {
        Obavestenje obavestenje = obavestenjeService.getObavestenjeByZahtev(idZahteva);
        if (obavestenje != null)
            return new ResponseEntity<>(obavestenje, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/by-user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ObavestenjeList> getObavestenjeListByUser() throws XMLDBException, JAXBException {
        ObavestenjeList obavestenjeList = obavestenjeService.getAllByUser();
        if (obavestenjeList != null)
            return new ResponseEntity<>(obavestenjeList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI') || hasRole('ROLE_USER')")
    @RequestMapping(value = "/toPdf/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadObavestenjePDF(@PathVariable String broj) {
        String path = "src/main/resources/generated_files/documents/obavestenje" + broj + ".html";
        boolean obavestenje = obavestenjeService.generateDocuments(broj);
        if (obavestenje)
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(Paths.get(path)));

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/xml; charset=utf-8");
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=obavestenje" + broj + ".pdf");
                return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bis));
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<>(obavestenje, HttpStatus.OK);

        // return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI') || hasRole('ROLE_USER')")
    @RequestMapping(value = "/toXhtml/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadObavestenjeXHTML(@PathVariable String broj) {
        String path = "src/main/resources/generated_files/documents/obavestenje" + broj + ".html";
        boolean obavestenje = obavestenjeService.generateDocuments(broj);
        if (obavestenje)
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(Paths.get(path)));

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/xml; charset=utf-8");
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=obavestenje" + broj + ".html");
                return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bis));
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<>(obavestenje, HttpStatus.OK);

        // return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
