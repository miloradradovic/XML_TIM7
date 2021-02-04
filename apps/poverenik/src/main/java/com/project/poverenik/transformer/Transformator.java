package com.project.poverenik.transformer;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class Transformator {

    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    private FopFactory fopFactory;

    public Transformator(){
        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();

        // Initialize FOP factory object
        try {
            this.fopFactory = FopFactory.newInstance(new File("src/main/resources/generated_files/xsl-fo/fop.xconf"));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void generateHTML(String xmlPath, String xslPath, String outputFile) throws FileNotFoundException {

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
            StreamResult result = new StreamResult(new FileOutputStream(outputFile));
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


    public void generatePDF(String xslFo,String xmlPath, String outputPath) throws Exception {

        System.out.println("[INFO] " + Transformator.class.getSimpleName());

        // Point to the XSL-FO file
        File xslFile = new File(xslFo);

        // Create transformation source
        StreamSource transformSource = new StreamSource(xslFile);

        // Initialize the transformation subject
        Source source = new StreamSource(new StringReader(xmlPath));

        //StreamSource source = new StreamSource(new File(INPUT_FILE));


        // Initialize user agent needed for the transformation
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        // Create the output stream to store the results
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // Initialize the XSL-FO transformer object
        Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

        // Construct FOP instance with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

        // Resulting SAX events
        Result res = new SAXResult(fop.getDefaultHandler());

        // Start XSLT transformation and FOP processing
        xslFoTransformer.transform(source, res);

        // Generate PDF file
        File pdfFile = new File(outputPath);
        if (!pdfFile.getParentFile().exists()) {
            System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
            pdfFile.getParentFile().mkdir();
        }

        OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
        out.write(outStream.toByteArray());

        System.out.println("[INFO] File \"" + pdfFile.getCanonicalPath() + "\" generated successfully.");
        out.close();

        System.out.println("[INFO] End.");

    }

}
