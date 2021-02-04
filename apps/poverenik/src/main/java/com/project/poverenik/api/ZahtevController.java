package com.project.poverenik.api;


import com.project.poverenik.model.zahtev.client.getZahtevResponse;
import com.project.poverenik.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "https://localhost:4201")
@RestController
@RequestMapping(value = "/zahtev", produces = MediaType.APPLICATION_XML_VALUE)
public class ZahtevController {

    @Autowired
    ZahtevService zahtevService;

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZahtevSoap(@PathVariable String id) {
        getZahtevResponse zahtevResponse = zahtevService.getZahtev(id);
        if (zahtevResponse != null) {
            return new ResponseEntity<>(zahtevResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
