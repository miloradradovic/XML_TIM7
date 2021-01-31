package com.project.organ_vlasti.api;

import com.project.organ_vlasti.client.IzvestavanjeClient;
import com.project.organ_vlasti.model.util.message.client.SetIzjasnjavanje;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://localhost:4201")
@RestController
@RequestMapping(value = "/izjasnjavanje", produces = MediaType.APPLICATION_XML_VALUE)
public class IzjasnjavanjeConstroller {

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> setIzjasnjavanje(@RequestBody String message){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.util.message.client");

        IzvestavanjeClient izvestavanjeClient = new IzvestavanjeClient();
        izvestavanjeClient.setDefaultUri("http://localhost:8085/ws");
        izvestavanjeClient.setMarshaller(marshaller);
        izvestavanjeClient.setUnmarshaller(marshaller);

        SetIzjasnjavanje setIzjasnjavanje = new SetIzjasnjavanje();
        setIzjasnjavanje.setMessage(message);
         boolean isSet = izvestavanjeClient.sendIzjasnjavanje(setIzjasnjavanje);
        if(isSet){
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);


    }
}
