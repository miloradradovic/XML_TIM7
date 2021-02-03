package com.project.poverenik.api;

import com.project.poverenik.client.IzvestavanjeClient;
import com.project.poverenik.model.util.lists.MessageList;
import com.project.poverenik.model.util.message.client.SetIzjasnjavanje;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import com.project.poverenik.service.MessageService;
import com.project.poverenik.service.ZalbaCutanjeService;
import com.project.poverenik.service.ZalbaOdlukaService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
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
    ZalbaOdlukaService zalbaOdlukaService;

    @Autowired
    ZalbaCutanjeService zalbaCutanjeService;

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> setIzjasnjavanje(@RequestBody String id) throws JAXBException, XMLDBException {
        //id_zalbe = tip/id
        String idZahteva = null;

        String tip = id.split("/")[0];
        String idZalbe = id.split("/")[1];

        if(tip.equals("cutanje")){
            ZalbaCutanje zalbaCutanje = zalbaCutanjeService.getOne(idZalbe);
            int last = zalbaCutanje.getZalbaCutanjeBody().getZahtev().getValue().split("/").length - 1;
            idZahteva = zalbaCutanje.getZalbaCutanjeBody().getZahtev().getValue().split("/")[last];

            zalbaCutanjeService.update(zalbaCutanje, "u obradi");
        }else{
            ZalbaOdluka zalbaOdluka = zalbaOdlukaService.getOne(idZalbe);
            int last = zalbaOdluka.getZalbaOdlukaBody().getZahtev().getValue().split("/").length - 1;
            idZahteva = zalbaOdluka.getZalbaOdlukaBody().getZahtev().getValue().split("/")[last];

            zalbaOdlukaService.update(zalbaOdluka, "u obradi");
        }


        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.util.message.client");

        IzvestavanjeClient izvestavanjeClient = new IzvestavanjeClient();
        izvestavanjeClient.setDefaultUri("http://localhost:8090/ws");
        izvestavanjeClient.setMarshaller(marshaller);
        izvestavanjeClient.setUnmarshaller(marshaller);

        SetIzjasnjavanje setIzjasnjavanje = new SetIzjasnjavanje();
        setIzjasnjavanje.setMessage("Molim izjasnjenje povodom zahteva sa ID-jem: " + idZahteva);
         boolean isSet = izvestavanjeClient.sendIzjasnjavanje(setIzjasnjavanje);
        if(isSet){
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping( method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getMessageList() throws XMLDBException, JAXBException {
        MessageList messageList = messageService.getAll();

        if(messageList != null)
            return new ResponseEntity(messageList, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
