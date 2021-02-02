package com.project.organ_vlasti.api;

import com.project.organ_vlasti.client.EmailClient;
import com.project.organ_vlasti.model.user.User;
import com.project.organ_vlasti.model.util.email.Tbody;
import com.project.organ_vlasti.model.util.email.client.sendPlain;
import com.project.organ_vlasti.model.util.lists.ZahtevList;
import com.project.organ_vlasti.model.zahtev.Zahtev;
import com.project.organ_vlasti.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/zahtev", produces = MediaType.APPLICATION_XML_VALUE)
public class ZahtevController {

    @Autowired
    ZahtevService zahtevService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createZahtev(@RequestBody Zahtev zahtev) throws XMLDBException, JAXBException {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
    	//User user = new User(); user.setEmail("s");
        if (zahtevService.create(zahtev, user.getEmail())){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping( method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahtevList> getZahtevList() throws XMLDBException, JAXBException {
        ZahtevList zahtevList = zahtevService.getAll();

        if(zahtevList != null)
            return new ResponseEntity(zahtevList, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value="/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getZahtev(@PathVariable String id) throws XMLDBException, JAXBException {
        Zahtev zahtev = zahtevService.getOne(id);
        if(zahtev != null)
            return new ResponseEntity(zahtev, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity delete(@PathVariable String id) throws XMLDBException, JAXBException {
        boolean isDeleted = zahtevService.delete(id);
        if(isDeleted)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity update(@RequestBody Zahtev zahtev) throws XMLDBException, JAXBException {
        boolean isUpdated = zahtevService.update(zahtev);
        if(isUpdated)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value ="/odbij", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> odbijZahtev(@RequestBody String info) throws XMLDBException, JAXBException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        //info = "zahtevId:poruka"
        String zahtevId = info.split(":")[0];
        String poruka = info.split(":")[1];

        Zahtev zahtev = zahtevService.getOne(zahtevId);
        //String email = zahtev.getZahtevBody().getInformacijeOTraziocu().getLice().getOsoba().getOtherAttributes(new QName("id"));

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.util.email.client");

        EmailClient emailClient = new EmailClient();
        emailClient.setDefaultUri("http://localhost:8095/ws");
        emailClient.setMarshaller(marshaller);
        emailClient.setUnmarshaller(marshaller);

        sendPlain sendPlain = new sendPlain();
        sendPlain.setEmail(new Tbody());
        //sendPlain.getEmail().setTo(email);
        sendPlain.getEmail().setContent("Postovani, " + poruka + " Srdacno,  " + user.getName() + " " + user.getLastName());
        sendPlain.getEmail().setSubject("Zahtev: " + zahtevId + " odbijen");
        sendPlain.getEmail().setFile("");
        if(emailClient.sentPlain(sendPlain)){
            return new ResponseEntity<>(HttpStatus.OK);
        };
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
