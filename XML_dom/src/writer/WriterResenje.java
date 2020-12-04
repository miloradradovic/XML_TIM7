package writer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class WriterResenje {

	private static String TARGET_NAMESPACE = "http://resenja/resenje";

	private static String XSI_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";

	private static DocumentBuilderFactory factory;

	private static TransformerFactory transformerFactory;

	private Document document;

	/*
	 * Factory initialization static-block
	 */
	static {
		factory = DocumentBuilderFactory.newInstance();

		transformerFactory = TransformerFactory.newInstance();
	}

	/**
	 * Generates document object model for a given XML file.
	 */
	public void createDocument() {

		try {

			DocumentBuilder builder = factory.newDocumentBuilder();

			// Kreiranje novog dokumenta
			document = builder.newDocument();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates sample document object model
	 * programmatically using DOM API methods.
	 */
	public void generateDOM() {

		// Kreiranje i postavljanje korenskog elementa
		Element rad = document.createElementNS(TARGET_NAMESPACE, "resenja");
		document.appendChild(rad);

		rad.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "http://resenja/resenje ../xsd/resenje.xsd");

		Element resenje = document.createElementNS(TARGET_NAMESPACE, "resenje");
		resenje.setAttribute("broj", "071-01-1114/2020-03");
		resenje.setAttribute("tip_resenja", "");
		resenje.setAttribute("datum", "");
		rad.appendChild(resenje);

		Element uvodneInformacije = document.createElementNS(TARGET_NAMESPACE, "uvodne_informacije");
		resenje.appendChild(uvodneInformacije);
		
		Element podaciOResenju = document.createElementNS(TARGET_NAMESPACE, "podaci_o_resenju");
		resenje.appendChild(podaciOResenju);
		
		Element naslov = document.createElementNS(TARGET_NAMESPACE, "naslov");
		podaciOResenju.appendChild(naslov);
		
		Element tacka2 = document.createElementNS(TARGET_NAMESPACE, "tacka");
		tacka2.setAttribute("broj", "1");
		podaciOResenju.appendChild(tacka2);
				
		Element tacka3 = document.createElementNS(TARGET_NAMESPACE, "tacka");
		tacka3.setAttribute("broj", "2");
		podaciOResenju.appendChild(tacka3);
		
		Element podaciOObrazlozenju = document.createElementNS(TARGET_NAMESPACE, "podaci_o_obrazlozenju");
		resenje.appendChild(podaciOObrazlozenju);
		
		Element naslov1 = document.createElementNS(TARGET_NAMESPACE, "naslov");
		podaciOObrazlozenju.appendChild(naslov1);
		
		Element predmetZalbe = document.createElementNS(TARGET_NAMESPACE, "predmet_zalbe");
		podaciOObrazlozenju.appendChild(predmetZalbe);
		
		Element postupakPoverenika = document.createElementNS(TARGET_NAMESPACE, "postupak_poverenika");
		podaciOObrazlozenju.appendChild(postupakPoverenika);
		
		Element odluka = document.createElementNS(TARGET_NAMESPACE, "odluka");
		podaciOObrazlozenju.appendChild(odluka);

		Element poverenik = document.createElementNS(TARGET_NAMESPACE, "poverenik");
		resenje.appendChild(poverenik);
		
	}

	/**
	 * Serializes DOM tree to an arbitrary OutputStream.
	 */
	public void transform(OutputStream out) {
		try {

			// Kreiranje instance objekta zaduzenog za serijalizaciju DOM modela
			Transformer transformer = transformerFactory.newTransformer();

			// Indentacija serijalizovanog izlaza
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// Nad "source" objektom (DOM stablo) vrÅ¡i se transformacija
			DOMSource source = new DOMSource(document);

			// RezultujuÄ‡i stream (argument metode)
			StreamResult result = new StreamResult(out);

			// Poziv metode koja vrÅ¡i opisanu transformaciju
			transformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		String filePath = null;

		System.out.println("[INFO] DOM Parser");

		if (args.length != 1) {

			filePath = "data/xml/resenje_out.xml";

			System.out.println("[INFO] No input file, using default \""	+ filePath + "\"");

		} else {
			filePath = args[0];
		}

		WriterResenje handler = new WriterResenje();

		// Kreiranje Document Ä�vora
		handler.createDocument();

		// Generisanje DOM stabla
		handler.generateDOM();

		// Prikaz sadrÅ¾aja (isprobati sa FileOutputStream-om)
		//handler.transform(System.out);

		try {
			handler.transform(new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
