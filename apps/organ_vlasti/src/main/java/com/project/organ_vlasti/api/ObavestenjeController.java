package com.project.organ_vlasti.api;

import com.project.organ_vlasti.client.EmailClient;
import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import com.project.organ_vlasti.model.user.User;
import com.project.organ_vlasti.model.util.email.Tbody;
import com.project.organ_vlasti.model.util.email.client.sendAttach;
import com.project.organ_vlasti.model.util.lists.ObavestenjeList;
import com.project.organ_vlasti.model.util.lists.ZahtevList;
import com.project.organ_vlasti.model.zahtev.Zahtev;
import com.project.organ_vlasti.service.ObavestenjeService;

import com.project.organ_vlasti.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/obavestenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ObavestenjeController {

    @Autowired
    ObavestenjeService obavestenjeService;
    
    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value="/search-metadata", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ObavestenjeList> searchMetadata(@RequestParam("datumAfter") String datumAfter, @RequestParam("datumBefore") String datumBefore, @RequestParam("organ_vlasti") String organ_vlasti, @RequestParam("userEmail") String userEmail, @RequestParam("zahtev") String zahtev) throws XMLDBException, JAXBException, IOException {

    	ObavestenjeList obavestenjeList = obavestenjeService.searchMetadata(datumAfter, datumBefore, organ_vlasti, userEmail, zahtev);
    	return new ResponseEntity<ObavestenjeList>(obavestenjeList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value="/search-text", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ObavestenjeList> searchText(@RequestParam("input") String input) throws XMLDBException, JAXBException, IOException {

    	ObavestenjeList obavestenjeList = obavestenjeService.searchText(input);
    	return new ResponseEntity<ObavestenjeList>(obavestenjeList, HttpStatus.OK);
    }

    @Autowired
    ZahtevService zahtevService;

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createObavestenje(@RequestBody Obavestenje obavestenje) throws XMLDBException, JAXBException {
        String id = obavestenjeService.create(obavestenje);
        if (id != null){
            Obavestenje o = obavestenjeService.getOne(id);
            String email = o.getObavestenjeBody().getInformacijeOPodnosiocu().getLice().getOsoba().getOtherAttributes().get(new QName("id"));
            String idZahteva = o.getObavestenjeBody().getIdZahteva();
            Zahtev zahtev = zahtevService.getOne(idZahteva);
            zahtevService.update(zahtev, "prihvacen");
            if(sendToUser(id, email)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping( method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ObavestenjeList> getObavestenjeList() throws XMLDBException, JAXBException {
        ObavestenjeList obavestenjeList = obavestenjeService.getAll();

        if(obavestenjeList != null)
            return new ResponseEntity(obavestenjeList, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI') || hasRole('ROLE_USER')")
    @RequestMapping(value="/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getObavestenje(@PathVariable String broj) throws XMLDBException, JAXBException {
        Obavestenje obavestenje = obavestenjeService.getOne(broj);
        if(obavestenje != null)
            return new ResponseEntity(obavestenje, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value="/by-user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ObavestenjeList> getObavestenjeListByUser() throws XMLDBException, JAXBException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ObavestenjeList obavestenjeList = obavestenjeService.getAllByUser(user.getEmail());

        if(obavestenjeList != null)
            return new ResponseEntity(obavestenjeList, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/{broj}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity delete(@PathVariable String broj) throws XMLDBException, JAXBException {
        boolean isDeleted = obavestenjeService.delete(broj);
        if(isDeleted)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity update(@RequestBody Obavestenje obavestenje) throws XMLDBException, JAXBException {
        boolean isUpdated = obavestenjeService.update(obavestenje);
        if(isUpdated)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private boolean sendToUser(String broj, String email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.util.email.client");

        EmailClient emailClient = new EmailClient();
        emailClient.setDefaultUri("http://localhost:8095/ws");
        emailClient.setMarshaller(marshaller);
        emailClient.setUnmarshaller(marshaller);

        sendAttach sendAttach = new sendAttach();
        sendAttach.setEmail(new Tbody());
        sendAttach.getEmail().setTo(email);
        sendAttach.getEmail().setContent("Postovani, <br/><br/> dostavljamo Vam obavestenje na Vas zahtev. <br/><br/> Srdacno,  " + user.getName() + " " + user.getLastName());
        sendAttach.getEmail().setSubject("Obavestenje " + broj);


        //TODO - pozvati transformaciju
        String pdfName = "верзија.pdf";
        sendAttach.getEmail().setFilePdfName(pdfName);
        String htmlName = "obavestenje.html";
        sendAttach.getEmail().setFileHtmlName(htmlName);
        try {
            File filePdf = new File("src/main/resources/pdf/" + pdfName);
            Path pdfPath = filePdf.toPath();
            byte[] pdfBytes = Files.readAllBytes(pdfPath);

            sendAttach.getEmail().setFilePdf(pdfBytes);

            File fileHtml = new File("src/main/resources/pdf/" + htmlName);
            Path htmlPath = fileHtml.toPath();
            byte[] htmlBytes = Files.readAllBytes(htmlPath);

            sendAttach.getEmail().setFileHtml(htmlBytes);


            return emailClient.sentAttach(sendAttach);

        } catch (IOException e) {
            e.getStackTrace();
            return false;
        }
    }
}
