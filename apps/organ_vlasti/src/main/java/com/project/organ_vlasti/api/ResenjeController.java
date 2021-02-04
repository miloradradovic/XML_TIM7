package com.project.organ_vlasti.api;

import com.project.organ_vlasti.client.ResenjeClient;
import com.project.organ_vlasti.model.resenje.client.getResenjeByBroj;
import com.project.organ_vlasti.model.resenje.client.getResenjeByBrojResponse;
import com.project.organ_vlasti.model.resenje.database.ObjectFactory;
import com.project.organ_vlasti.model.resenje.database.ResenjeRef;
import com.project.organ_vlasti.model.resenje.database.client.getRefs;
import com.project.organ_vlasti.model.resenje.database.client.getRefsResponse;
import com.project.organ_vlasti.model.util.lists.ResenjeRefList;
import com.project.organ_vlasti.model.util.parametars.ParametarMap;
import com.project.organ_vlasti.model.util.parametars.Tvalue;
import com.project.organ_vlasti.service.ResenjeRefService;
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


@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/resenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ResenjeController {

    @Autowired
    ResenjeRefService resenjeRefService;

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value="/resenje/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getResenje(@PathVariable String broj) throws JAXBException, XMLDBException {
        // TODO dodati za front xhtml transformacije
        //pocetak soap
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.resenje.client");

        ResenjeClient resenjeClient = new ResenjeClient();
        resenjeClient.setDefaultUri("http://localhost:8085/ws");
        resenjeClient.setMarshaller(marshaller);
        resenjeClient.setUnmarshaller(marshaller);


        getResenjeByBroj getResenjeByBroj = new getResenjeByBroj();
        getResenjeByBroj.setBroj(broj);

        getResenjeByBrojResponse getResenjeByBrojResponse = resenjeClient.getOneResenje(getResenjeByBroj);
        //kraj soap
        // ObjectFactory of = new ObjectFactory() com.project.organ_vlasti.model.resenje
        // Resenje r = of.createResenje();
        // r.setResenjeBody(getResenjeByBrojResponse.getResenje());
        if(getResenjeByBrojResponse != null){
            ResenjeRef resenjeRef = resenjeRefService.getOneByBroj(broj);
            resenjeRef.getBody().setProcitano("da");
            resenjeRefService.update(resenjeRef);
            return new ResponseEntity(getResenjeByBrojResponse, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value ="/{param}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeRefList> getResenjaList(@PathVariable String param) throws XMLDBException, JAXBException {
        ResenjeRefList resenjeRefList = resenjeRefService.getAll(param);

        if(resenjeRefList != null)
            return new ResponseEntity(resenjeRefList, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value="/search-metadata", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeRefList> searchMetadata(@RequestParam("poverenik") String poverenik, @RequestParam("trazilac") String trazilac, @RequestParam("zalba") String zalba, @RequestParam("datumAfter") String datumAfter, @RequestParam("datumBefore") String datumBefore, @RequestParam("tip") String tip, @RequestParam("organVlasti") String organVlasti, @RequestParam("mesto") String mesto) throws XMLDBException, JAXBException, IOException {

        com.project.organ_vlasti.model.util.parametars.ObjectFactory of = new com.project.organ_vlasti.model.util.parametars.ObjectFactory();
        ParametarMap parametarMap = of.createParametarMap();

        Tvalue tvalue = of.createTvalue();
        tvalue.setName("poverenik");
        tvalue.setValue(poverenik);
        parametarMap.getValue().add(tvalue);

        Tvalue tvalue1 = of.createTvalue();
        tvalue1.setName("trazilac");
        tvalue1.setValue(trazilac);
        parametarMap.getValue().add(tvalue1);

        Tvalue tvalue2 = of.createTvalue();
        tvalue2.setName("zalba");
        tvalue2.setValue(zalba);
        parametarMap.getValue().add(tvalue2);

        Tvalue tvalue3 = of.createTvalue();
        tvalue3.setName("datumAfter");
        tvalue3.setValue(datumAfter);
        parametarMap.getValue().add(tvalue3);

        Tvalue tvalue4 = of.createTvalue();
        tvalue4.setName("datumBefore");
        tvalue4.setValue(datumBefore);
        parametarMap.getValue().add(tvalue4);

        Tvalue tvalue5 = of.createTvalue();
        tvalue5.setName("tip");
        tvalue5.setValue(tip);
        parametarMap.getValue().add(tvalue5);

        Tvalue tvalue6 = of.createTvalue();
        tvalue6.setName("organVlasti");
        tvalue6.setValue(organVlasti);
        parametarMap.getValue().add(tvalue6);

        Tvalue tvalue7 = of.createTvalue();
        tvalue7.setName("mesto");
        tvalue7.setValue(mesto);
        parametarMap.getValue().add(tvalue7);

        ResenjeRefList resenjeRefList = sendToPoverenik(parametarMap);

        if (resenjeRefList != null){
            return new ResponseEntity<>(resenjeRefList, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value="/search-text", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeRefList> searchText(@RequestParam("input") String input) throws XMLDBException, JAXBException, IOException {

        com.project.organ_vlasti.model.util.parametars.ObjectFactory of = new com.project.organ_vlasti.model.util.parametars.ObjectFactory();
        ParametarMap parametarMap = of.createParametarMap();

        Tvalue tvalue = of.createTvalue();
        tvalue.setName("input");
        tvalue.setValue(input);
        parametarMap.getValue().add(tvalue);

        ResenjeRefList resenjeRefList = sendToPoverenik(parametarMap);

        if (resenjeRefList != null){
            return new ResponseEntity<>(resenjeRefList, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    private ResenjeRefList sendToPoverenik(ParametarMap parametarMap) throws XMLDBException, JAXBException {

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.resenje.database.client");

        ResenjeClient resenjeClient = new ResenjeClient();
        resenjeClient.setDefaultUri("http://localhost:8085/ws");
        resenjeClient.setMarshaller(marshaller);
        resenjeClient.setUnmarshaller(marshaller);

        getRefs getRefs = new getRefs();
        getRefs.setParametars(parametarMap);

        getRefsResponse refsResponse = resenjeClient.getRefs(getRefs);
        if(refsResponse != null){
            return resenjeRefService.getRefs(refsResponse.getResponse().getRef());
        }
        return null;

    }
}