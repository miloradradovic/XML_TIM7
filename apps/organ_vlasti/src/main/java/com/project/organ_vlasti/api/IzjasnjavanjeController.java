package com.project.organ_vlasti.api;

import com.project.organ_vlasti.client.IzvestavanjeClient;
import com.project.organ_vlasti.model.util.lists.MessageList;
import com.project.organ_vlasti.model.util.message.Message;
import com.project.organ_vlasti.model.util.message.client.SetIzjasnjavanje;
import com.project.organ_vlasti.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/izjasnjavanje", produces = MediaType.APPLICATION_XML_VALUE)
public class IzjasnjavanjeController {

    @Autowired
    MessageService messageService;

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> setIzjasnjavanje(@RequestBody Message message) throws XMLDBException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.util.message.client");

        IzvestavanjeClient izvestavanjeClient = new IzvestavanjeClient();
        izvestavanjeClient.setDefaultUri("http://localhost:8085/ws");
        izvestavanjeClient.setMarshaller(marshaller);
        izvestavanjeClient.setUnmarshaller(marshaller);

        SetIzjasnjavanje setIzjasnjavanje = new SetIzjasnjavanje();
        setIzjasnjavanje.setMessage(message.getBody().getValue());
         boolean isSet = izvestavanjeClient.sendIzjasnjavanje(setIzjasnjavanje);
        if(isSet){
            messageService.delete(message.getBody().getId());
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping( method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getMessageList() throws XMLDBException, JAXBException {
        MessageList messageList = messageService.getAll();

        if(messageList != null)
            return new ResponseEntity(messageList, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
