package com.project.organ_vlasti.api;

import com.project.organ_vlasti.client.IzvestajiClient;
import com.project.organ_vlasti.model.izvestaji.Izvestaj;
import com.project.organ_vlasti.model.izvestaji.client.getPodaci;
import com.project.organ_vlasti.model.izvestaji.client.getPodaciResponse;
import com.project.organ_vlasti.model.izvestaji.database.client.podnesiIzvestaj;
import com.project.organ_vlasti.model.util.lists.IzvestajList;
import com.project.organ_vlasti.model.util.lists.ZahtevList;
import com.project.organ_vlasti.service.IzvestajiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/izvestaji", produces = MediaType.APPLICATION_XML_VALUE)
public class IzvestajiController {

    @Autowired
    IzvestajiService izvestajiService;

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createIzvestaj() throws XMLDBException, JAXBException, DatatypeConfigurationException {

        if(izvestajiService.generate()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping( method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<IzvestajList> getIzvestajList() throws XMLDBException, JAXBException {
        IzvestajList izvestajList = izvestajiService.getAll();

        if(izvestajList != null)
            return new ResponseEntity(izvestajList, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value="/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getIzvestaj(@PathVariable String id) throws JAXBException, XMLDBException {
        Izvestaj izvestaj = izvestajiService.getOne(id);
        if(izvestaj != null)
            return new ResponseEntity(izvestaj, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value="/search-metadata", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<IzvestajList> searchMetadata(@RequestParam("datumAfter") String datumAfter, @RequestParam("datumBefore") String datumBefore) throws XMLDBException, JAXBException, IOException {

    	IzvestajList izvestajList = izvestajiService.searchMetadata(datumAfter, datumBefore);
    	return new ResponseEntity<IzvestajList>(izvestajList, HttpStatus.OK);
    }


}
