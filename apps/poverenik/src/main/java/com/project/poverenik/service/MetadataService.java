package com.project.poverenik.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import com.project.poverenik.rdf_utils.AuthenticationUtilities;
import com.project.poverenik.rdf_utils.MetadataExtractor;
import com.project.poverenik.rdf_utils.SparqlUtil;
import com.project.poverenik.rdf_utils.AuthenticationUtilities.ConnectionProperties;

@Service
public class MetadataService {

	private static final String SPARQL_NAMED_GRAPH_URI = "/tim7/metadata";
	
	public void extractMetadata(String graph, OutputStream os) throws XMLDBException, JAXBException, IOException, SAXException, TransformerException {
	
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		
		MetadataExtractor metadataExtractor = new MetadataExtractor();
		
		String rdfFilePath = "src/main/resources/rdf_data/output.rdf";

		ByteArrayInputStream inStream = new ByteArrayInputStream( ((ByteArrayOutputStream) os).toByteArray() );

		metadataExtractor.extractMetadata(
				inStream,
				new FileOutputStream(new File(rdfFilePath)));
		
		Model model = ModelFactory.createDefaultModel();
		model.read(rdfFilePath);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		model.write(out, SparqlUtil.NTRIPLES);
		
		System.out.println("[INFO] Extracted metadata as RDF/XML...");
		model.write(System.out, SparqlUtil.RDF_XML);

		
		// Writing the named graph
		System.out.println("[INFO] Populating named graph \"" + SPARQL_NAMED_GRAPH_URI + graph + "\" with extracted metadata.");
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI + graph, new String(out.toByteArray()));
		System.out.println(sparqlUpdate);
		
		// UpdateRequest represents a unit of execution
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
		
		
		
		// Read the triples from the named graph
		System.out.println();
		System.out.println("[INFO] Retrieving triples from RDF store.");
		System.out.println("[INFO] Using \"" + SPARQL_NAMED_GRAPH_URI + graph + "\" named graph.");

		System.out.println("[INFO] Selecting the triples from the named graph \"" + SPARQL_NAMED_GRAPH_URI + graph + "\".");
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI + graph, "?s ?p ?o");
		
		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		
		// Query the collection, dump output response as XML
		ResultSet results = query.execSelect();
		
		ResultSetFormatter.out(System.out, results);
		
		query.close() ;
		
		System.out.println("[INFO] End.");
		
	}
}
