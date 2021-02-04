package com.project.poverenik.api;

import com.project.poverenik.client.IzvestajClient;
import com.project.poverenik.model.izvestaj.client.getIzvestajById;
import com.project.poverenik.model.izvestaj.client.getIzvestajByIdResponse;
import com.project.poverenik.model.izvestaj.database.IzvestajRef;
import com.project.poverenik.model.izvestaj.database.client.getRefs;
import com.project.poverenik.model.izvestaj.database.client.getRefsResponse;
import com.project.poverenik.model.util.lists.IzvestajRefList;
import com.project.poverenik.model.util.parametars.ParametarMap;
import com.project.poverenik.model.util.parametars.Tvalue;
import com.project.poverenik.service.IzvestajRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@CrossOrigin(origins = "https://localhost:4201")
@RestController
@RequestMapping(value = "/izvestaj", produces = MediaType.APPLICATION_XML_VALUE)
public class IzvestajRefController {

    @Autowired
    IzvestajRefService izvestajRefService;

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value="/{procitano}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<IzvestajRefList> getIzvestajListByStatus(@PathVariable String procitano) throws XMLDBException, JAXBException {
        IzvestajRefList izvestajRefList = izvestajRefService.getAll(procitano);

        if(izvestajRefList != null)
            return new ResponseEntity(izvestajRefList, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value="izvestaj/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getIzvestaj(@PathVariable String id) throws JAXBException, XMLDBException {
        // TODO dodati za front xhtml transformacije
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.izvestaj.client");

        IzvestajClient izvestajClient = new IzvestajClient();
        izvestajClient.setDefaultUri("http://localhost:8090/ws");
        izvestajClient.setMarshaller(marshaller);
        izvestajClient.setUnmarshaller(marshaller);


        getIzvestajById getIzvestajById = new getIzvestajById();
        getIzvestajById.setId(id);

        getIzvestajByIdResponse getIzvestajByIdResponse = izvestajClient.getOneResenje(getIzvestajById);
        if(getIzvestajByIdResponse != null){
            IzvestajRef izvestajRef = izvestajRefService.getOneByBroj(id);
            izvestajRef.getBody().setProcitano("da");
            izvestajRefService.update(izvestajRef);
            return new ResponseEntity(getIzvestajByIdResponse, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value="/search-metadata", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<IzvestajRefList> searchMetadata(@RequestParam("datumAfter") String datumAfter, @RequestParam("datumBefore") String datumBefore) throws XMLDBException, JAXBException, IOException {

        com.project.poverenik.model.util.parametars.ObjectFactory of = new com.project.poverenik.model.util.parametars.ObjectFactory();
        ParametarMap parametarMap = of.createParametarMap();

        Tvalue tvalue = of.createTvalue();
        tvalue.setName("datumAfter");
        tvalue.setValue(datumAfter);
        parametarMap.getValue().add(tvalue);

        Tvalue tvalue1 = of.createTvalue();
        tvalue1.setName("datumBefore");
        tvalue1.setValue(datumBefore);
        parametarMap.getValue().add(tvalue1);

        IzvestajRefList izvestajRefList = sendToPoverenik(parametarMap);

        if (izvestajRefList != null){
            return new ResponseEntity<>(izvestajRefList, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private IzvestajRefList sendToPoverenik(ParametarMap parametarMap) throws XMLDBException, JAXBException {

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.izvestaj.database.client");

        IzvestajClient izvestajClient = new IzvestajClient();
        izvestajClient.setDefaultUri("http://localhost:8090/ws");
        izvestajClient.setMarshaller(marshaller);
        izvestajClient.setUnmarshaller(marshaller);

        getRefs getRefs = new getRefs();
        getRefs.setParametars(parametarMap);

        getRefsResponse refsResponse = izvestajClient.getRefs(getRefs);
        if(refsResponse != null){
            return izvestajRefService.getRefs(refsResponse.getResponse().getRef());
        }
        return null;

    }
}
