package com.project.organ_vlasti.api;

import com.project.organ_vlasti.model.util.lists.ZahtevList;
import com.project.organ_vlasti.model.zahtev.Zahtev;
import com.project.organ_vlasti.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/zahtev", produces = MediaType.APPLICATION_XML_VALUE)
public class ZahtevController {

    @Autowired
    ZahtevService zahtevService;

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/search-metadata", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahtevList> searchMetadata(@RequestParam("datumAfter") String datumAfter,
                                                     @RequestParam("datumBefore") String datumBefore,
                                                     @RequestParam("mesto") String mesto,
                                                     @RequestParam("organ_vlasti") String organ_vlasti,
                                                     @RequestParam("userEmail") String userEmail) throws XMLDBException, JAXBException, IOException {
        ZahtevList zahtevList = zahtevService.searchMetadata(datumAfter, datumBefore, mesto, organ_vlasti, userEmail);
        return new ResponseEntity<>(zahtevList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/search-text", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahtevList> searchText(@RequestParam("input") String input) throws XMLDBException, JAXBException, IOException {
        ZahtevList zahtevList = zahtevService.searchText(input);
        return new ResponseEntity<>(zahtevList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createZahtev(@RequestBody Zahtev zahtev) throws XMLDBException, JAXBException {
        if (zahtevService.create(zahtev)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahtevList> getZahtevList() throws XMLDBException, JAXBException {
        ZahtevList zahtevList = zahtevService.getAll();
        if (zahtevList != null)
            return new ResponseEntity<>(zahtevList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/neobradjen", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahtevList> getZahtevListNeobradjen() throws XMLDBException, JAXBException {
        ZahtevList zahtevList = zahtevService.getAllNeobradjen();
        if (zahtevList != null)
            return new ResponseEntity<>(zahtevList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/by-user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahtevList> getZahtevListByUser() throws XMLDBException, JAXBException {
        ZahtevList zahtevList = zahtevService.getAllByUser();
        if (zahtevList != null)
            return new ResponseEntity<>(zahtevList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZahtev(@PathVariable String id) throws XMLDBException, JAXBException {
        Zahtev zahtev = zahtevService.getOne(id);
        if (zahtev != null){
            return new ResponseEntity<>(zahtev, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/toPdf/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadZahtevPDF(@PathVariable String broj) {
        String path = zahtevService.downloadResenjePDF(broj);
        if (!path.equals(""))
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(Paths.get(path)));

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/xml; charset=utf-8");
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=zahtev" + broj + ".pdf");
                return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bis));
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/toXhtml/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadZahtevXHTML(@PathVariable String broj) {
        String path = zahtevService.downloadResenjeXHTML(broj);
        if (!path.equals(""))
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(Paths.get(path)));

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/xml; charset=utf-8");
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=zahtev" + broj + ".html");
                return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bis));
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        // return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/odbij", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> odbijZahtev(@RequestBody String info) throws XMLDBException, JAXBException {
        if (zahtevService.odbijZahtev(info)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
