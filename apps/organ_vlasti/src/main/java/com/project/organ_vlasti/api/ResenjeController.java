package com.project.organ_vlasti.api;

import com.project.organ_vlasti.client.ResenjeClient;
import com.project.organ_vlasti.model.resenje.client.getResenjeByBroj;
import com.project.organ_vlasti.model.resenje.client.getResenjeByBrojResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/resenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ResenjeController {

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value="/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getResenje(@PathVariable String broj) {
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
            return new ResponseEntity(getResenjeByBrojResponse, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
