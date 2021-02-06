package com.project.organ_vlasti.api;

import com.project.organ_vlasti.model.zalba_cutanje.client.getZalbaCutanjeByIdResponse;
import com.project.organ_vlasti.model.zalba_odluka.client.getZalbaOdlukaByIdResponse;
import com.project.organ_vlasti.service.ZalbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/zalbe", produces = MediaType.APPLICATION_XML_VALUE)
public class ZalbeController {

    @Autowired
    ZalbeService zalbeService;

    //id zahteva
    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI') || hasRole('ROLE_USER')")
    @RequestMapping(value = "/cutanje/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalbaCutanje(@PathVariable String id) {
        getZalbaCutanjeByIdResponse getZalbaCutanjeByIdResponse = zalbeService.getZalbaCutanje(id);
        if (getZalbaCutanjeByIdResponse != null) {
            return new ResponseEntity<>(getZalbaCutanjeByIdResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //id zahteva -> 1    id zalbe -> cutanje-1 / odluka-1
    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI') || hasRole('ROLE_USER')")
    @RequestMapping(value = "/odluka/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalbaOdluka(@PathVariable String id) {

        getZalbaOdlukaByIdResponse getZalbaOdlukaByIdResponse = zalbeService.getZalbaOdluka(id);
        if (getZalbaOdlukaByIdResponse != null) {
            return new ResponseEntity<>(getZalbaOdlukaByIdResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/cutanjeDownload/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadZalbaCutanje(@PathVariable String id) {
        String path = zalbeService.downloadZalbaCutanjeXHTML(id);
        if (!path.equals(""))
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(Paths.get(path)));

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/xml; charset=utf-8");
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=zalbacutanje" + id + ".pdf");
                return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bis));
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/odlukaDownload/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> downloadZalbaOdluka(@PathVariable String id) {
        String path = zalbeService.downloadZalbaOdlukaXHTML(id);
        if (!path.equals(""))
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(Paths.get(path)));

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/xml; charset=utf-8");
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=zalbaodluka" + id + ".pdf");
                return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bis));
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
