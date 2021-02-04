package com.project.organ_vlasti.api;

import com.project.organ_vlasti.model.resenje.client.getResenjeByBrojResponse;
import com.project.organ_vlasti.model.util.lists.ResenjeRefList;
import com.project.organ_vlasti.service.ResenjeRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;


@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/resenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ResenjeController {

    @Autowired
    ResenjeRefService resenjeRefService;

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/resenje/{broj}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getResenje(@PathVariable String broj) throws JAXBException, XMLDBException {
        getResenjeByBrojResponse getResenjeByBrojResponse = resenjeRefService.getResenje(broj);
        if (getResenjeByBrojResponse != null) {
            return new ResponseEntity<>(getResenjeByBrojResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/{param}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeRefList> getResenjaList(@PathVariable String param) throws XMLDBException, JAXBException {
        ResenjeRefList resenjeRefList = resenjeRefService.getAll(param);
        if (resenjeRefList != null)
            return new ResponseEntity<>(resenjeRefList, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/search-metadata/{status}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeRefList> searchMetadata(@PathVariable String status,
                                                         @RequestParam("poverenik") String poverenik,
                                                         @RequestParam("trazilac") String trazilac,
                                                         @RequestParam("zalba") String zalba,
                                                         @RequestParam("datumAfter") String datumAfter,
                                                         @RequestParam("datumBefore") String datumBefore,
                                                         @RequestParam("tip") String tip,
                                                         @RequestParam("organVlasti") String organVlasti,
                                                         @RequestParam("mesto") String mesto) throws XMLDBException, JAXBException {

        ResenjeRefList resenjeRefList = resenjeRefService.searchMetadata(
                status, poverenik, trazilac, zalba, datumAfter, datumBefore, tip, organVlasti, mesto);
        if (resenjeRefList != null) {
            return new ResponseEntity<>(resenjeRefList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(value = "/search-text/{status}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ResenjeRefList> searchText(@PathVariable String status, @RequestParam("input") String input) throws XMLDBException, JAXBException {
        ResenjeRefList resenjeRefList = resenjeRefService.searchText(status, input);
        if (resenjeRefList != null) {
            return new ResponseEntity<>(resenjeRefList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}