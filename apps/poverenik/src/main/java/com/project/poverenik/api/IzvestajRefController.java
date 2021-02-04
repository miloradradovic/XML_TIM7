package com.project.poverenik.api;

import com.project.poverenik.model.izvestaj.client.getIzvestajByIdResponse;
import com.project.poverenik.model.util.lists.IzvestajRefList;
import com.project.poverenik.service.IzvestajRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;

@CrossOrigin(origins = "https://localhost:4201")
@RestController
@RequestMapping(value = "/izvestaj", produces = MediaType.APPLICATION_XML_VALUE)
public class IzvestajRefController {

    @Autowired
    IzvestajRefService izvestajRefService;

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/{procitano}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<IzvestajRefList> getIzvestajListByStatus(@PathVariable String procitano) throws XMLDBException, JAXBException {
        IzvestajRefList izvestajRefList = izvestajRefService.getAll(procitano);

        if (izvestajRefList != null)
            return new ResponseEntity<>(izvestajRefList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "izvestaj/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getIzvestaj(@PathVariable String id) throws JAXBException, XMLDBException {
        // TODO dodati za front xhtml transformacije
        getIzvestajByIdResponse getIzvestajByIdResponse = izvestajRefService.getIzvestaj(id);

        if (getIzvestajByIdResponse != null) {
            return new ResponseEntity<>(getIzvestajByIdResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/search-metadata/{status}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<IzvestajRefList> searchMetadata(@PathVariable String status, @RequestParam("datumAfter") String datumAfter, @RequestParam("datumBefore") String datumBefore) throws XMLDBException, JAXBException {

        IzvestajRefList izvestajRefList = izvestajRefService.searchMetadata(datumAfter, datumBefore, status);
        if (izvestajRefList != null) {
            return new ResponseEntity<>(izvestajRefList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
