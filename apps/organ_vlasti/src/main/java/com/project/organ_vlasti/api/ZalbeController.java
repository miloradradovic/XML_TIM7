package com.project.organ_vlasti.api;

import com.project.organ_vlasti.client.ZalbeClient;
import com.project.organ_vlasti.model.zalba_cutanje.client.getZalbaCutanjeById;
import com.project.organ_vlasti.model.zalba_cutanje.client.getZalbaCutanjeByIdResponse;
import com.project.organ_vlasti.model.zalba_odluka.client.getZalbaOdlukaById;
import com.project.organ_vlasti.model.zalba_odluka.client.getZalbaOdlukaByIdResponse;
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
@RequestMapping(value = "/zalbe", produces = MediaType.APPLICATION_XML_VALUE)
public class ZalbeController {

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI') || hasRole('ROLE_USER')")
    @RequestMapping(value="/cutanje/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalbaCutanje(@PathVariable String id) throws JAXBException, XMLDBException {
        // TODO dodati za front xhtml transformacije
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.zalba_cutanje.client");

        ZalbeClient zalbeClient = new ZalbeClient();
        zalbeClient.setDefaultUri("http://localhost:8085/ws");
        zalbeClient.setMarshaller(marshaller);
        zalbeClient.setUnmarshaller(marshaller);


        getZalbaCutanjeById getZalbaCutanjeById = new getZalbaCutanjeById();
        getZalbaCutanjeById.setId(id);

        getZalbaCutanjeByIdResponse getZalbaCutanjeByIdResponse = zalbeClient.getZalbaCutanje(getZalbaCutanjeById);
        if(getZalbaCutanjeByIdResponse != null){
            return new ResponseEntity(getZalbaCutanjeByIdResponse, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI') || hasRole('ROLE_USER')")
    @RequestMapping(value="/odluka/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZalbaOdluka(@PathVariable String id) throws JAXBException, XMLDBException {
        // TODO dodati za front xhtml transformacije
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.zalba_odluka.client");

        ZalbeClient zalbeClient = new ZalbeClient();
        zalbeClient.setDefaultUri("http://localhost:8085/ws");
        zalbeClient.setMarshaller(marshaller);
        zalbeClient.setUnmarshaller(marshaller);


        getZalbaOdlukaById getZalbaOdlukaById = new getZalbaOdlukaById();
        getZalbaOdlukaById.setId(id);

        getZalbaOdlukaByIdResponse getZalbaOdlukaByIdResponse = zalbeClient.getZalbaOdluka(getZalbaOdlukaById);
        if(getZalbaOdlukaByIdResponse != null){
            return new ResponseEntity(getZalbaOdlukaByIdResponse, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
