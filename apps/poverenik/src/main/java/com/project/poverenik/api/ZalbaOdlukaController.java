package com.project.poverenik.api;

import com.project.poverenik.model.util.lists.ZalbaOdlukaList;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import com.project.poverenik.service.ZalbaOdlukaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping(value = "/zalba-odluka", produces = MediaType.APPLICATION_XML_VALUE)
public class ZalbaOdlukaController {

    @Autowired
    ZalbaOdlukaService zalbaOdlukaService;

    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createZalbaOdluka(@RequestBody ZalbaOdluka zalbaOdluka) throws XMLDBException, NumberFormatException, JAXBException {
        if (zalbaOdlukaService.create(zalbaOdluka)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping( method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZalbaOdlukaList> getZalbaOdlukaList() throws XMLDBException, JAXBException {
        ZalbaOdlukaList zalbaOdlukaList = zalbaOdlukaService.getAll();

        if(zalbaOdlukaList != null)
            return new ResponseEntity(zalbaOdlukaList, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalbaOdluka(@PathVariable String id) throws XMLDBException, JAXBException {
        ZalbaOdluka zalbaOdluka = zalbaOdlukaService.getOne(id);
        if(zalbaOdluka != null)
            return new ResponseEntity(zalbaOdluka, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity delete(@PathVariable String id) throws XMLDBException, JAXBException {
        boolean isDeleted = zalbaOdlukaService.delete(id);
        if(isDeleted)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity update(@RequestBody ZalbaOdluka zalbaOdluka) throws XMLDBException, JAXBException {
        boolean isUpdated = zalbaOdlukaService.update(zalbaOdluka);
        if(isUpdated)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
