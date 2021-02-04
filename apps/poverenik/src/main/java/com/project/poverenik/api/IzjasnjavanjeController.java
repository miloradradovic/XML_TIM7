package com.project.poverenik.api;

import com.project.poverenik.model.util.lists.MessageList;
import com.project.poverenik.service.IzjasnjavanjeService;
import com.project.poverenik.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;

@CrossOrigin(origins = "https://localhost:4201")
@RestController
@RequestMapping(value = "/izjasnjavanje", produces = MediaType.APPLICATION_XML_VALUE)
public class IzjasnjavanjeController {

    @Autowired
    MessageService messageService;

    @Autowired
    IzjasnjavanjeService izjasnjavanjeService;

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> setIzjasnjavanje(@RequestBody String id) throws JAXBException, XMLDBException {

        if (izjasnjavanjeService.setIzjasnajvanje(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getMessageList() throws XMLDBException, JAXBException {
        MessageList messageList = messageService.getAll();

        if (messageList != null)
            return new ResponseEntity<>(messageList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
