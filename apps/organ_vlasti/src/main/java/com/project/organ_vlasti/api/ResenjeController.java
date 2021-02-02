package com.project.organ_vlasti.api;

import com.project.organ_vlasti.client.ResenjeClient;
import com.project.organ_vlasti.model.resenje.client.getResenjeByBroj;
import com.project.organ_vlasti.model.resenje.client.getResenjeByBrojResponse;
import com.project.organ_vlasti.model.resenje.database.ResenjeRef;
import com.project.organ_vlasti.model.util.lists.ResenjeRefList;
import com.project.organ_vlasti.service.ResenjeRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;


@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/resenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ResenjeController {

    @Autowired
    ResenjeRefService resenjeRefService;

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value="/resenje/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getResenje(@PathVariable String broj) throws JAXBException, XMLDBException {
        // TODO dodati za front xhtml transformacije
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.resenje.client");

        ResenjeClient resenjeClient = new ResenjeClient();
        resenjeClient.setDefaultUri("http://localhost:8085/ws");
        resenjeClient.setMarshaller(marshaller);
        resenjeClient.setUnmarshaller(marshaller);


        getResenjeByBroj getResenjeByBroj = new getResenjeByBroj();
        getResenjeByBroj.setBroj(broj);

        getResenjeByBrojResponse getResenjeByBrojResponse = resenjeClient.getOneResenje(getResenjeByBroj);
        if(getResenjeByBrojResponse != null){
            ResenjeRef resenjeRef = resenjeRefService.getOneByBroj(broj);
            resenjeRef.getBody().setProcitano("da");
            resenjeRefService.update(resenjeRef);
            return new ResponseEntity(getResenjeByBrojResponse, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value ="/{param}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeRefList> getResenjaList(@PathVariable String param) throws XMLDBException, JAXBException {
        ResenjeRefList resenjeRefList = resenjeRefService.getAll(param);

        if(resenjeRefList != null)
            return new ResponseEntity(resenjeRefList, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
