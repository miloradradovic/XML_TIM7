package com.project.poverenik.api;

import com.project.poverenik.model.user.User;
import com.project.poverenik.model.util.lists.UserList;
import com.project.poverenik.rdf_utils.AuthenticationUtilities;
import com.project.poverenik.rdf_utils.AuthenticationUtilities.ConnectionProperties;
import com.project.poverenik.service.MetadataService;
import com.project.poverenik.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;


@RestController
@RequestMapping(value = "/metadata", produces = MediaType.APPLICATION_XML_VALUE)
public class MetadataController {
	
	@Autowired
	MetadataService metadataService;	
	
	@RequestMapping(value="/extract", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Object> getUsersA() throws XMLDBException, JAXBException, IOException, SAXException, TransformerException {
		
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();
	 
		String zalba1 = "src/main/resources/rdf_data/zalba_cutanje.xml";
		metadataService.extractMetadata("/zalbe", new FileInputStream(new File(zalba1)));
		
		String zalba2 = "src/main/resources/rdf_data/zalbanaodlukucir.xml";
		metadataService.extractMetadata("/zalbe", new FileInputStream(new File(zalba2)));
		
		String resenje = "src/main/resources/rdf_data/resenje.xml";
		metadataService.extractMetadata("/resenja", new FileInputStream(new File(resenje)));
		
		String resenje1 = "src/main/resources/rdf_data/resenje1.xml";
		metadataService.extractMetadata("/resenja", new FileInputStream(new File(resenje1)));
		
	 
	    return new ResponseEntity("ok", HttpStatus.OK);
	}
	
}
