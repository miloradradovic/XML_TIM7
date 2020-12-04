package parser;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.util.Scanner;

import static org.apache.xerces.jaxp.JAXPConstants.JAXP_SCHEMA_LANGUAGE;
import static org.apache.xerces.jaxp.JAXPConstants.W3C_XML_SCHEMA;

public class Parser implements ErrorHandler{

	private static DocumentBuilderFactory factory;

	private Document document;

	private static Scanner scanner = new Scanner(System.in);

	/*
	 * Factory initialization static-block
	 */
	static {
		factory = DocumentBuilderFactory.newInstance();

		/* Uključuje validaciju. */
		factory.setValidating(true);

		factory.setNamespaceAware(true);
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);

		/* Validacija u odnosu na XML šemu. */
		factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
	}

	/**
	 * Generates document object model for a given XML file.
	 *
	 * @param filePath XML document file path
	 */
	public void buildDocument(String filePath) {

		try {

			DocumentBuilder builder = factory.newDocumentBuilder();

			/* Postavlja error handler. */
			builder.setErrorHandler((ErrorHandler) this);

			document = builder.parse(new File(filePath));

			/* Detektuju eventualne greske */
			if (document != null)
				System.out.println("[INFO] File parsed with no errors.");
			else
				System.out.println("[WARN] Document is null.");

		} catch (SAXParseException e) {

			System.out.println("[ERROR] Parsing error, line: " + e.getLineNumber() + ", uri: " + e.getSystemId());
			System.out.println("[ERROR] " + e.getMessage() );
			System.out.print("[ERROR] Embedded exception: ");

			Exception embeddedException = e;
			if (e.getException() != null)
				embeddedException = e.getException();

			// Print stack trace...
			embeddedException.printStackTrace();

			System.exit(0);

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ispis pojedinih elemenata i atributa DOM
	 * stabla upotrebom DOM API-ja.
	 */
	public void printElement() {

		System.out.println("Prikaz sadržaja DOM stabla parsiranog XML dokumenta.");
		String elementName, attrName, choice = "";
		Element element;

		while (!choice.equals("*")) {

			System.out.println("\n[INPUT] Unesite 0 - za prikaz celog dokumenta, 1 - prikaz elemenata, 2 - prikaz atributa, * - povratak: ");
			choice = scanner.next();

			if (choice.equals("0")) {
				printNode(document);

			} else if (choice.equals("1")) {

				System.out.print("\n[INPUT] Unesite naziv elementa: ");
				elementName = scanner.next();
				NodeList nodes = document.getElementsByTagName(elementName);

				System.out.println("\nPronađeno " + nodes.getLength() + " elemenata. ");

				for (int i = 0; i < nodes.getLength(); i++)
					printNode(nodes.item(i));

			} else if (choice.equals("2")) {

				System.out.print("\n[INPUT] Unesite naziv elementa: ");
				elementName = scanner.next();

				System.out.print("\n[INPUT] Unesite naziv atributa: ");
				attrName = scanner.next();

				NodeList nodes = document.getElementsByTagName(elementName);

				System.out.println("\nPronađeno " + nodes.getLength() + " \"" + elementName + "\" elemenata.");

				for (int i = 0; i < nodes.getLength(); i++) {

					element = (Element) nodes.item(i);

					if (!element.getAttribute(attrName).equals("")) {
						System.out.println("\n" + (i+1) + ". element ima vrednost atributa \"" + attrName + "\": " + element.getAttribute(attrName) + ".");
					} else {
						System.out.println("\n" + (i+1) + ". element \"" + elementName + "\" ne poseduje atribut \"" + attrName + "\".");
					}
				}

			} else if (choice.equals("*")){
				break;
			} else {
				System.out.println("Nepoznata komanda.");
			}
		}
		System.out.println("[INFO] Kraj.");
	}

	/**
	 * A recursive helper method for iterating
	 * over the elements of a DOM tree.
	 *
	 * @param node current node
	 */
	private void printNode(Node node) {

		// Uslov za izlazak iz rekurzije
		if (node == null)
			return;

		// Ispis uopštenih podataka o čvoru iz Node interfejsa
		// printNodeDetails(node, indent);

		// Ako je upitanju dokument čvor (korenski element)
		if (node instanceof Document) {

			System.out.println("START_DOCUMENT");

			// Rekurzivni poziv za prikaz korenskog elementa
			Document doc = (Document) node;
			printNode(doc.getDocumentElement());
		} else if (node instanceof Element) {

			Element element = (Element) node;

			System.out.print("START_ELEMENT: " + element.getTagName());

			// Preuzimanje liste atributa
			NamedNodeMap attributes = element.getAttributes();

			if (attributes.getLength() > 0) {

				System.out.print(", ATTRIBUTES: ");

				for (int i = 0; i < attributes.getLength(); i++) {
					Node attribute = attributes.item(i);
					printNode(attribute);
					if (i < attributes.getLength()-1)
						System.out.print(", ");
				}
			}

			System.out.println();

			// Prikaz svakog od child nodova, rekurzivnim pozivom
			NodeList children = element.getChildNodes();

			if (children != null) {
				for (int i = 0; i < children.getLength(); i++) {
					Node aChild = children.item(i);
					printNode(aChild);
				}
			}
		}
		// Za naredne čvorove nema rekurzivnog poziva jer ne mogu imati podelemente
		else if (node instanceof Attr) {

			Attr attr = (Attr) node;
			System.out.print(attr.getName() + "=" + attr.getValue());

		}
		else if (node instanceof Text) {
			Text text = (Text) node;

			if (text.getTextContent().trim().length() > 0)
				System.out.println("CHARACTERS: " + text.getTextContent().trim());
		}
		else if (node instanceof CDATASection) {
			System.out.println("CDATA: " + node.getNodeValue());
		}
		else if (node instanceof Comment) {
			System.out.println("COMMENT: " + node.getNodeValue());
		}
		else if (node instanceof ProcessingInstruction) {
			System.out.print("PROCESSING INSTRUCTION: ");

			ProcessingInstruction instruction = (ProcessingInstruction) node;
			System.out.print("data: " + instruction.getData());
			System.out.println(", target: " + instruction.getTarget());
		}
		else if (node instanceof Entity) {
			Entity entity = (Entity) node;
			System.out.println("ENTITY: " + entity.getNotationName());
		}
	}

	/*
	 * Error handling methods
	 */

	public void error(SAXParseException err) throws SAXParseException {
		// Propagate the exception
		throw err;
	}

	public void fatalError(SAXParseException err) throws SAXException {
		// Propagate the exception
		throw err;
	}

	public void warning(SAXParseException err) throws SAXParseException {
		System.out.println("[WARN] Warning, line: " + err.getLineNumber() + ", uri: " + err.getSystemId());
		System.out.println("[WARN] " + err.getMessage());
	}

	public static void main(String args[]) {

		String choice = "";
		String filePath = null;


		while(!choice.equalsIgnoreCase("q")){
			System.out.println("Unesite opciju: \n1)Resenje.xml\n2)Obavestenje.xml\n3)Zahtevcir.xml\n4)Zalba_cutanje.xml\n5)Zalbanaodlukucir.xml");
			Parser handler = new Parser();
			choice = scanner.next();
			switch (choice) {
				case "1" :
					filePath = "data/xml/resenje.xml"; break;
				case "2" : filePath = "data/xml/obavestenje.xml"; break;
				case "3" : filePath = "data/xml/zahtevcir.xml"; break;
				case "4" : filePath = "data/xml/zalba_cutanje.xml"; break;
				case "5" : filePath = "data/xml/zalbanaodlukucir.xml"; break;
				case "q" : {
					continue;
				}
				default : {
					System.out.println("Nevalidan unos");
					continue;
				}
			}

			// Kreiranje DOM stabla na osnovu XML fajla
			handler.buildDocument(filePath);

			// Prikaz sadržaja korišćenjem DOM API-ja
			handler.printElement();

		}

	}

}
