package com.project.organ_vlasti.api;

import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import com.project.organ_vlasti.model.util.lists.ObavestenjeList;
import com.project.organ_vlasti.service.ObavestenjeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/obavestenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ObavestenjeController {

    @Autowired
    ObavestenjeService obavestenjeService;

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createObavestenje(@RequestBody Obavestenje obavestenje) throws XMLDBException, JAXBException {
        if (obavestenjeService.create(obavestenje)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping( method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ObavestenjeList> getObavestenjeList() throws XMLDBException, JAXBException {
        ObavestenjeList obavestenjeList = obavestenjeService.getAll();

        if(obavestenjeList != null)
            return new ResponseEntity(obavestenjeList, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI') || hasRole('ROLE_USER')")
    @RequestMapping(value="/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getObavestenje(@PathVariable String broj) throws XMLDBException, JAXBException {
        Obavestenje obavestenje = obavestenjeService.getOne(broj);
        if(obavestenje != null)
            return new ResponseEntity(obavestenje, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity delete(@PathVariable String broj) throws XMLDBException, JAXBException {
        boolean isDeleted = obavestenjeService.delete(broj);
        if(isDeleted)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity update(@RequestBody Obavestenje obavestenje) throws XMLDBException, JAXBException {
        boolean isUpdated = obavestenjeService.update(obavestenje);
        if(isUpdated)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value="/toPdf", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadObavestenje() {
        boolean obavestenje = obavestenjeService.generateDocuments("1");
        if(!obavestenje)
            return new ResponseEntity(obavestenje, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
