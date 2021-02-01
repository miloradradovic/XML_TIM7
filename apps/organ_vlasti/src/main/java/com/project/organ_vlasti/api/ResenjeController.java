package com.project.organ_vlasti.api;

import com.project.organ_vlasti.client.ResenjeClient;
import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import com.project.organ_vlasti.model.resenje.client.getAllResenjaRequest;
import com.project.organ_vlasti.model.resenje.client.getAllResenjaResponse;
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

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getResenja() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.resenje.client");

        ResenjeClient resenjeClient = new ResenjeClient();
        resenjeClient.setDefaultUri("http://localhost:8085/ws");
        resenjeClient.setMarshaller(marshaller);
        resenjeClient.setUnmarshaller(marshaller);

        getAllResenjaRequest getAllResenjaRequest = new getAllResenjaRequest();

        getAllResenjaResponse getAllResenjaResponse = resenjeClient.getAllResenja(getAllResenjaRequest);
        if(getAllResenjaResponse != null){
            return new ResponseEntity(getAllResenjaResponse.getResenjeList(), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
