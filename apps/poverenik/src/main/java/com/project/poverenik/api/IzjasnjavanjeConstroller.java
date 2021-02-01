package com.project.poverenik.api;

import com.project.poverenik.client.IzvestavanjeClient;
import com.project.poverenik.model.util.message.client.SetIzjasnjavanje;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;

@CrossOrigin(origins = "https://localhost:4201")
@RestController
@RequestMapping(value = "/izjasnjavanje", produces = MediaType.APPLICATION_XML_VALUE)
public class IzjasnjavanjeConstroller {

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> setIzjasnjavanje(@RequestBody String id){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.util.message.client");

        IzvestavanjeClient izvestavanjeClient = new IzvestavanjeClient();
        izvestavanjeClient.setDefaultUri("http://localhost:8090/ws");
        izvestavanjeClient.setMarshaller(marshaller);
        izvestavanjeClient.setUnmarshaller(marshaller);

        SetIzjasnjavanje setIzjasnjavanje = new SetIzjasnjavanje();
        setIzjasnjavanje.setMessage("Molim izjasnjenje povodom zahteva sa ID-jem: " + id);
         boolean isSet = izvestavanjeClient.sendIzjasnjavanje(setIzjasnjavanje);
        if(isSet){
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);


    }
}
