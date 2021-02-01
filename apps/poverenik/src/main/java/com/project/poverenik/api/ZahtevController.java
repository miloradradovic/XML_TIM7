package com.project.poverenik.api;

import javax.xml.bind.JAXBException;

import com.project.poverenik.client.ZahtevClient;
import com.project.poverenik.model.zahtev.client.getZahtevRequest;
import com.project.poverenik.model.zahtev.client.getZahtevResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;

@CrossOrigin(origins = "https://localhost:4201")
@RestController
@RequestMapping(value = "/zahtev", produces = MediaType.APPLICATION_XML_VALUE)
public class ZahtevController {
	
	@PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value ="/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZahtevSoap(@PathVariable String id) {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.zahtev.client");

        ZahtevClient zahtevClient = new ZahtevClient();
        zahtevClient.setDefaultUri("http://localhost:8090/ws");
        zahtevClient.setMarshaller(marshaller);
        zahtevClient.setUnmarshaller(marshaller);

        getZahtevRequest zahtevRequest = new getZahtevRequest();
        zahtevRequest.setId(id);
        getZahtevResponse zahtevResponse = zahtevClient.getZahtev(zahtevRequest);
        if(zahtevResponse != null){
            return new ResponseEntity(zahtevResponse, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
