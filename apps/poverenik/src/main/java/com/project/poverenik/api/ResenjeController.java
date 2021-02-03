package com.project.poverenik.api;

import com.project.poverenik.client.EmailClient;
import com.project.poverenik.client.ResenjeRefClient;
import com.project.poverenik.model.resenje.TuvodneInformacije;
import com.project.poverenik.model.resenje.database.client.SetResenjeRef;
import com.project.poverenik.model.resenje.Resenje;
import com.project.poverenik.model.user.User;
import com.project.poverenik.model.util.email.Tbody;
import com.project.poverenik.model.util.email.client.sendAttach;
import com.project.poverenik.model.util.lists.ResenjeList;
import com.project.poverenik.model.util.lists.ZalbaOdlukaList;
import com.project.poverenik.service.ResenjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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

import java.io.IOException;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "https://localhost:4201")
@RestController
@RequestMapping(value = "/resenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ResenjeController {

    @Autowired
    ResenjeService resenjeService;
    
    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value="/search-metadata", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeList> searchMetadata(@RequestParam("poverenik") String poverenik, @RequestParam("trazilac") String trazilac, @RequestParam("zalba") String zalba, @RequestParam("datumAfter") String datumAfter, @RequestParam("datumBefore") String datumBefore, @RequestParam("tip") String tip, @RequestParam("organVlasti") String organVlasti, @RequestParam("mesto") String mesto) throws XMLDBException, JAXBException, IOException {

    	ResenjeList resenjeList = resenjeService.searchMetadata(poverenik, trazilac, zalba, datumAfter, datumBefore, tip, organVlasti, mesto);
    	return new ResponseEntity<ResenjeList>(resenjeList, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value="/search-text", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeList> searchText(@RequestParam("input") String input) throws XMLDBException, JAXBException, IOException {

    	ResenjeList resenjeList = resenjeService.searchText(input);
    	return new ResponseEntity<ResenjeList>(resenjeList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createResenje(@RequestBody Resenje resenje) throws XMLDBException, JAXBException {
        String broj = resenjeService.create(resenje);
        if (broj != null){
            Resenje r = resenjeService.getOne(broj);
            JAXBElement< TuvodneInformacije.Trazilac> element = (JAXBElement<TuvodneInformacije.Trazilac>)resenje.getResenjeBody().getUvodneInformacije().getContent().get(1);
            String email = element.getValue().getId();
            if(sendToOrganVlasti(broj) && sendToUser(broj, email)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping( method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeList> getResenjeList() throws XMLDBException, JAXBException {
        ResenjeList resenjeList = resenjeService.getAll();

        if(resenjeList != null)
            return new ResponseEntity(resenjeList, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK') || hasRole('ROLE_USER')")
    @RequestMapping(value = "/by-user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeList> getResenjeListByUser() throws XMLDBException, JAXBException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ResenjeList resenjeList = resenjeService.getByUser(user.getEmail());

        if(resenjeList != null)
            return new ResponseEntity(resenjeList, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK') || hasRole('ROLE_USER')")
    @RequestMapping(value="/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getResenje(@PathVariable String broj) throws XMLDBException, JAXBException {
        Resenje resenje = resenjeService.getOne(broj);
        if(resenje != null)
            return new ResponseEntity(resenje, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value="/{broj}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity delete(@PathVariable String broj) throws XMLDBException, JAXBException {
        boolean isDeleted = resenjeService.delete(broj);
        if(isDeleted)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity update(@RequestBody Resenje resenje) throws XMLDBException, JAXBException {
        boolean isUpdated = resenjeService.update(resenje);
        if(isUpdated)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private boolean sendToOrganVlasti(String broj){

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.resenje.database.client");

        ResenjeRefClient resenjeRefClient = new ResenjeRefClient();
        resenjeRefClient.setDefaultUri("http://localhost:8090/ws");
        resenjeRefClient.setMarshaller(marshaller);
        resenjeRefClient.setUnmarshaller(marshaller);

        SetResenjeRef setResenjeRef = new SetResenjeRef();
        setResenjeRef.setResenje_ref(broj);
        return resenjeRefClient.sendResenjeRef(setResenjeRef);

    }

    private boolean sendToUser(String broj, String email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.util.email.client");

        EmailClient emailClient = new EmailClient();
        emailClient.setDefaultUri("http://localhost:8095/ws");
        emailClient.setMarshaller(marshaller);
        emailClient.setUnmarshaller(marshaller);

        sendAttach sendAttach = new sendAttach();
        sendAttach.setEmail(new Tbody());
        sendAttach.getEmail().setTo("eromana98@gmail.com"); //email
        sendAttach.getEmail().setContent("Postovani, <br/><br/> Dostavljamo Vam resenje na Vasu zalbu. <br/><br/> Srdacno,  " + user.getName() + " " + user.getLastName());
        sendAttach.getEmail().setSubject("Resenje " + broj);

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
