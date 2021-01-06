package com.project.poverenik.rdf;

import com.project.poverenik.model.user.User;
import com.project.poverenik.model.util.lists.UserList;
import com.project.poverenik.rdf.AuthenticationUtilities.ConnectionProperties;
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
	
	private static final String SPARQL_NAMED_GRAPH_URI = "/example/sparql/metadata";
	
	
	@RequestMapping(value="/extract", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Object> getUsersA() throws XMLDBException, JAXBException, IOException, SAXException, TransformerException {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();
	 
		
		// Referencing XML file with RDF data in attributes
		String xmlFilePath = "src/main/java/com/project/poverenik/rdf/contacts.xml";
		
		String rdfFilePath = "src/main/java/com/project/poverenik/rdf/contacts.rdf";
		
		// Automatic extraction of RDF triples from XML file
		MetadataExtractor metadataExtractor = new MetadataExtractor();
		
		System.out.println("[INFO] Extracting metadata from RDFa attributes...");
		metadataExtractor.extractMetadata(
				new FileInputStream(new File(xmlFilePath)), 
				new FileOutputStream(new File(rdfFilePath)));
				
		
		// Loading a default model with extracted metadata
		Model model = ModelFactory.createDefaultModel();
		model.read(rdfFilePath);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		model.write(out, SparqlUtil.NTRIPLES);
		
		System.out.println("[INFO] Extracted metadata as RDF/XML...");
		model.write(System.out, SparqlUtil.RDF_XML);

		
		// Writing the named graph
		System.out.println("[INFO] Populating named graph \"" + SPARQL_NAMED_GRAPH_URI + "\" with extracted metadata.");
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, new String(out.toByteArray()));
		System.out.println(sparqlUpdate);
		
		// UpdateRequest represents a unit of execution
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
		
		
		
		// Read the triples from the named graph
		System.out.println();
		System.out.println("[INFO] Retrieving triples from RDF store.");
		System.out.println("[INFO] Using \"" + SPARQL_NAMED_GRAPH_URI + "\" named graph.");

		System.out.println("[INFO] Selecting the triples from the named graph \"" + SPARQL_NAMED_GRAPH_URI + "\".");
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, "?s ?p ?o");
		
		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		
		// Query the collection, dump output response as XML
		ResultSet results = query.execSelect();
		
		ResultSetFormatter.out(System.out, results);
		
		query.close() ;
		
		System.out.println("[INFO] End.");
	 
	 
	    return new ResponseEntity("ok", HttpStatus.OK);
	}
	
}
