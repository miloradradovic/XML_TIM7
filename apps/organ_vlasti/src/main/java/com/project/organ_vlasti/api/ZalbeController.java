package com.project.organ_vlasti.api;

import com.project.organ_vlasti.model.zalba_cutanje.client.getZalbaCutanjeByIdResponse;
import com.project.organ_vlasti.model.zalba_odluka.client.getZalbaOdlukaByIdResponse;
import com.project.organ_vlasti.service.ZalbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/zalbe", produces = MediaType.APPLICATION_XML_VALUE)
public class ZalbeController {

    @Autowired
    ZalbeService zalbeService;

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI') || hasRole('ROLE_USER')")
    @RequestMapping(value = "/cutanje/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalbaCutanje(@PathVariable String id) {
        getZalbaCutanjeByIdResponse getZalbaCutanjeByIdResponse = zalbeService.getZalbaCutanje(id);
        if (getZalbaCutanjeByIdResponse != null) {
            return new ResponseEntity<>(getZalbaCutanjeByIdResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI') || hasRole('ROLE_USER')")
    @RequestMapping(value = "/odluka/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalbaOdluka(@PathVariable String id) {

        getZalbaOdlukaByIdResponse getZalbaOdlukaByIdResponse = zalbeService.getZalbaOdluka(id);
        if (getZalbaOdlukaByIdResponse != null) {
            return new ResponseEntity<>(getZalbaOdlukaByIdResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
