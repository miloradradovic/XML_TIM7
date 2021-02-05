package com.project.poverenik.api;

import com.project.poverenik.model.resenje.Resenje;
import com.project.poverenik.model.user.User;
import com.project.poverenik.model.util.lists.ResenjeList;
import com.project.poverenik.service.ResenjeService;
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
@RequestMapping(value = "/resenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ResenjeController {

    @Autowired
    ResenjeService resenjeService;

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/search-metadata", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeList> searchMetadata(@RequestParam("poverenik") String poverenik, @RequestParam("trazilac") String trazilac, @RequestParam("zalba") String zalba, @RequestParam("datumAfter") String datumAfter, @RequestParam("datumBefore") String datumBefore, @RequestParam("tip") String tip, @RequestParam("organVlasti") String organVlasti, @RequestParam("mesto") String mesto) throws XMLDBException, JAXBException, IOException {

        ResenjeList resenjeList = resenjeService.searchMetadata(poverenik, trazilac, zalba, datumAfter, datumBefore, tip, organVlasti, mesto);
        return new ResponseEntity<>(resenjeList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/search-text", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeList> searchText(@RequestParam("input") String input) throws XMLDBException, JAXBException {

        ResenjeList resenjeList = resenjeService.searchText(input);
        return new ResponseEntity<>(resenjeList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createResenje(@RequestBody Resenje resenje) throws XMLDBException, JAXBException {
        boolean isCreated = resenjeService.create(resenje);
        if (isCreated) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeList> getResenjeList() throws XMLDBException, JAXBException {
        ResenjeList resenjeList = resenjeService.getAll();

        if (resenjeList != null)
            return new ResponseEntity<>(resenjeList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK') || hasRole('ROLE_USER')")
    @RequestMapping(value = "/by-user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeList> getResenjeListByUser() throws XMLDBException, JAXBException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ResenjeList resenjeList = resenjeService.getByUser(user.getEmail());

        if (resenjeList != null)
            return new ResponseEntity<>(resenjeList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK') || hasRole('ROLE_USER')")
    @RequestMapping(value = "/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getResenje(@PathVariable String broj) throws XMLDBException, JAXBException {
        Resenje resenje = resenjeService.getOne(broj);
        if (resenje != null)
            return new ResponseEntity<>(resenje, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK') || hasRole('ROLE_USER')")
    @RequestMapping(value = "/by-zalba/{idZalbe}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getResenjeByZalba(@PathVariable String idZalbe) throws XMLDBException, JAXBException {
        String zalba = idZalbe.replace("-", "/");
        Resenje resenje = resenjeService.getResenjeByZalba(zalba);
        if (resenje != null)
            return new ResponseEntity<>(resenje, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/{broj}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> delete(@PathVariable String broj) throws XMLDBException {
        boolean isDeleted = resenjeService.delete(broj);
        if (isDeleted)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> update(@RequestBody Resenje resenje) throws XMLDBException, JAXBException {
        boolean isUpdated = resenjeService.update(resenje);
        if (isUpdated)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/toPdf/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadResenjePDF(@PathVariable String broj) {
        String path = resenjeService.downloadResenjePDF(broj);
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
        String path = resenjeService.downloadResenjeXHTML(broj);
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
    
    @RequestMapping(value = "/toRdf/{idZalbe}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadZalbaCutanjeRdf(@PathVariable String idZalbe) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {

        String path = resenjeService.generateRdf(idZalbe);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/toJson/{idZalbe}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadZalbaCutanjeJson(@PathVariable String idZalbe) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {

        String path = resenjeService.generateJson(idZalbe);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
