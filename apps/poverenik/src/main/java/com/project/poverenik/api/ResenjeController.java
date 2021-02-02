package com.project.poverenik.api;

import com.project.poverenik.client.ResenjeRefClient;
import com.project.poverenik.model.resenje.database.client.SetResenjeRef;
import com.project.poverenik.model.resenje.Resenje;
import com.project.poverenik.model.util.lists.ResenjeList;
import com.project.poverenik.model.util.lists.ZalbaOdlukaList;
import com.project.poverenik.service.ResenjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import java.io.IOException;

import javax.xml.bind.JAXBException;

@CrossOrigin(origins = "https://localhost:4201")
@RestController
@RequestMapping(value = "/resenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ResenjeController {

    @Autowired
    ResenjeService resenjeService;
    
    @RequestMapping(value="/search-metadata", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeList> searchMetadata(@RequestParam("poverenik") String poverenik, @RequestParam("trazilac") String trazilac, @RequestParam("zalba") String zalba, @RequestParam("datumAfter") String datumAfter, @RequestParam("datumBefore") String datumBefore, @RequestParam("tip") String tip, @RequestParam("organVlasti") String organVlasti, @RequestParam("mesto") String mesto) throws XMLDBException, JAXBException, IOException {

    	ResenjeList resenjeList = resenjeService.searchMetadata(poverenik, trazilac, zalba, datumAfter, datumBefore, tip, organVlasti, mesto);
    	return new ResponseEntity<ResenjeList>(resenjeList, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createResenje(@RequestBody Resenje resenje) throws XMLDBException, JAXBException {
        String broj = resenjeService.create(resenje);
        if (broj != null){

            //if(sendToOrganVlasti(broj) && sendToUser(broj)){
                return new ResponseEntity<>(HttpStatus.OK);
            //}

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

    private boolean sendToUser(String broj){
        return true;
    }
}
