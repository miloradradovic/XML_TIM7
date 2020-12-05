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

public class WriterZalbacutanje {

	private static String TARGET_NAMESPACE = "http://www.zalbacutanje";

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
		Element zalba = document.createElementNS(TARGET_NAMESPACE,  "zc:zalba");
		document.appendChild(zalba);

		zalba.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "http://www.zalbacutanje ../xsd/zalba_cutanje.xsd");
		
		zalba.setAttribute("mjesto", "");
		zalba.setAttribute("datum", "2000-12-12");
		
		Element naziv = document.createElement("naziv");
		zalba.appendChild(naziv);
		
		Element podaciOPrimaocu = document.createElement("podaci_o_primaocu");
		zalba.appendChild(podaciOPrimaocu);
		
		Element uloga = document.createElement("uloga");
		podaciOPrimaocu.appendChild(uloga);
		
		Element adresa = document.createElement("adresa");
		podaciOPrimaocu.appendChild(adresa);
		Element mesto = document.createElement("mesto");
		adresa.appendChild(mesto);
		Element ulica = document.createElement("ulica");
		ulica.setAttribute("broj", "15");
		adresa.appendChild(ulica);
		
		Element sadrzajZalbe = document.createElement("sadrzaj_zalbe");
		zalba.appendChild(sadrzajZalbe);
		
		Element clan = document.createElement("clan");
		clan.setAttribute("broj", "22");
		sadrzajZalbe.appendChild(clan);
		
		Element ciljaniOrganVlasti = document.createElement("ciljani_organ_vlasti");
		sadrzajZalbe.appendChild(ciljaniOrganVlasti);
		
		Element razlogZalbe = document.createElement("razlog_zalbe");
		sadrzajZalbe.appendChild(razlogZalbe);
		Element opcija1 = document.createElement("opcija");
		opcija1.setAttribute("izabran", "false");
		razlogZalbe.appendChild(opcija1);
		Element opcija2 = document.createElement("opcija");
		opcija2.setAttribute("izabran", "false");
		razlogZalbe.appendChild(opcija2);
		Element opcija3 = document.createElement("opcija");
		opcija3.setAttribute("izabran", "false");
		razlogZalbe.appendChild(opcija3);
		
		Element datum1 = document.createElement("datum");
		datum1.appendChild(document.createTextNode("2000-12-12"));
		sadrzajZalbe.appendChild(datum1);
		
		Element podaciOZahtjevuIInformacijama = document.createElement("podaci_o_zahtjevu_i_informacijama");
		sadrzajZalbe.appendChild(podaciOZahtjevuIInformacijama);
		
		Element napomena = document.createElement("napomena");
		sadrzajZalbe.appendChild(napomena);
		
		Element podaciOPodnosiocu = document.createElement("podaci_o_podnosiocu");
		zalba.appendChild(podaciOPodnosiocu);
		
		Element osoba = document.createElement("osoba");
		podaciOPodnosiocu.appendChild(osoba);
		Element ime = document.createElement("ime");
		osoba.appendChild(ime);
		Element prezime = document.createElement("prezime");
		osoba.appendChild(prezime);
		
		Element adresa1 = document.createElement("adresa");
		podaciOPodnosiocu.appendChild(adresa1);
		Element mesto1 = document.createElement("mesto");
		adresa1.appendChild(mesto1);
		Element ulica1 = document.createElement("ulica");
		ulica.setAttribute("broj", "0");
		adresa1.appendChild(ulica1);
		
		Element drugiPodaciZaKontakt = document.createElement("drugi_podaci_za_kontakt");
		podaciOPodnosiocu.appendChild(drugiPodaciZaKontakt);
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

			filePath = "data/xml/zalba_cutanje_out.xml";

			System.out.println("[INFO] No input file, using default \""	+ filePath + "\"");

		} else {
			filePath = args[0];
		}

		WriterZalbacutanje handler = new WriterZalbacutanje();

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
