package com.project.organ_vlasti.api;

import com.project.organ_vlasti.model.izvestaji.Izvestaj;
import com.project.organ_vlasti.model.util.lists.IzvestajList;
import com.project.organ_vlasti.service.IzvestajiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.IOException;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/izvestaji", produces = MediaType.APPLICATION_XML_VALUE)
public class IzvestajiController {

    @Autowired
    IzvestajiService izvestajiService;

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createIzvestaj() throws XMLDBException, JAXBException, DatatypeConfigurationException {
        if (izvestajiService.generate()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<IzvestajList> getIzvestajList() throws XMLDBException, JAXBException {
        IzvestajList izvestajList = izvestajiService.getAll();

        if (izvestajList != null)
            return new ResponseEntity<>(izvestajList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getIzvestaj(@PathVariable String id) throws JAXBException, XMLDBException {
        Izvestaj izvestaj = izvestajiService.getOne(id);
        if (izvestaj != null)
            return new ResponseEntity<>(izvestaj, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/search-metadata", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<IzvestajList> searchMetadata(@RequestParam("datumAfter") String datumAfter, @RequestParam("datumBefore") String datumBefore) throws XMLDBException, JAXBException, IOException {

        IzvestajList izvestajList = izvestajiService.searchMetadata(datumAfter, datumBefore);
        return new ResponseEntity<>(izvestajList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/toRdf/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadIzvestajRdf(@PathVariable String id) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {

        String path = izvestajiService.generateRdf(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/toJson/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadIzvestajJson(@PathVariable String id) throws XMLDBException, JAXBException, IOException, TransformerException, SAXException {

        String path = izvestajiService.generateJson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
