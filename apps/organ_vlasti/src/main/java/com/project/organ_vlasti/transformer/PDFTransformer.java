package com.project.organ_vlasti.transformer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.project.organ_vlasti.database.ExistManager;
import com.project.organ_vlasti.repository.ObavestenjeRepository;
import com.project.organ_vlasti.service.ObavestenjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 *
 * Primer demonstrira koriscenje iText PDF programskog API-a za
 * renderovanje PDF-a na osnovu XML dokumenta. Alternativa Apache FOP-u.
 *
 */
public class PDFTransformer {

	@Autowired
	private static ExistManager existManager;

	@Autowired
	private static ObavestenjeRepository obavestenjeRepository;

	private static DocumentBuilderFactory documentFactory;

	private static TransformerFactory transformerFactory;

	public static final String INPUT_FILE = "data/xslt/bookstore.xml";

	public static final String XSL_FILE = "data/xslt/bookstore.xsl";

	public static final String HTML_FILE = "organ_vlasti/src/main/resources/generated_files/documents/bookstore.html";

	public static final String OUTPUT_FILE = "gen/itext/bookstore.pdf";


	static {

		/* Inicijalizacija DOM fabrike */
		documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setNamespaceAware(true);
		documentFactory.setIgnoringComments(true);
		documentFactory.setIgnoringElementContentWhitespace(true);

		/* Inicijalizacija Transformer fabrike */
		transformerFactory = TransformerFactory.newInstance();

	}

    /**
     * Creates a PDF using iText Java API
     * @param filePath
     * @throws IOException
     * @throws DocumentException
     */
    public void generatePDF(String filePath) throws IOException, DocumentException {

    	// Step 1
    	Document document = new Document();

    	// Step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

        // Step 3
        document.open();


        // Step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(HTML_FILE));

        // Step 5
        document.close();

    }

    /*public org.w3c.dom.Document buildDocument(String filePath) {

    	org.w3c.dom.Document document = null;
		try {

			DocumentBuilder builder = documentFactory.newDocumentBuilder();
			document = builder.parse(new File(filePath));

			if (document != null)
				System.out.println("[INFO] File parsed with no errors.");
			else
				System.out.println("[WARN] Document is null.");

		} catch (Exception e) {
			return null;

		}

		return document;
	}*/

    public void generateHTML(String xmlPath, String xslPath) throws FileNotFoundException {

		try {

			// Initialize Transformer instance
			StreamSource transformSource = new StreamSource(new File(xslPath));
			Transformer transformer = transformerFactory.newTransformer(transformSource);
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// Generate XHTML
			transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

			// ResourceSet -
			// Transform DOM to HTML
			//DOMSource source = new DOMSource(buildDocument(xmlPath));
			StreamResult result = new StreamResult(new FileOutputStream(HTML_FILE));
			Source src = new StreamSource(new StringReader(xmlPath));
			transformer.transform(src, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

    }


}
