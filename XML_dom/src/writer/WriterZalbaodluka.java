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

public class WriterZalbaodluka {

	private static String TARGET_NAMESPACE = "http://www.zalbanaodlukucir";

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
		Element zalba = document.createElementNS(TARGET_NAMESPACE,  "zoc:zalba");
		document.appendChild(zalba);

		zalba.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "http://www.zalbanaodlukucir ../xsd/zalbanaodlukucir.xsd");

		zalba.setAttribute("mesto", "");
		zalba.setAttribute("datum", "2000-12-12");

		Element naslov = document.createElement("naslov");
		zalba.appendChild(naslov);
		
		Element podaciPovereniku = document.createElement("podaci_povereniku");
		zalba.appendChild(podaciPovereniku);
		
		Element uloga = document.createElement("uloga");
		podaciPovereniku.appendChild(uloga);
		
		Element adresa = document.createElement("adresa");
		podaciPovereniku.appendChild(adresa);
				
		Element mesto = document.createElement("mesto");
		adresa.appendChild(mesto);
		
		Element ulica = document.createElement("ulica");
		ulica.setAttribute("broj", "0");
		adresa.appendChild(ulica);
		
		Element podnaslov = document.createElement("podnaslov");
		zalba.appendChild(podnaslov);
		
		Element zalilac = document.createElement("zalilac");
		zalba.appendChild(zalilac);
		
		Element tipLica = document.createElement("tip_lica");
		zalilac.appendChild(tipLica);
		
		Element osoba = document.createElement("osoba");
		tipLica.appendChild(osoba);

		Element ime = document.createElement("ime");
		osoba.appendChild(ime);
		
		Element prezime = document.createElement("prezime");
		osoba.appendChild(prezime);
		
		Element adresa1 = document.createElement("adresa");
		podaciPovereniku.appendChild(adresa1);
				
		Element mesto1 = document.createElement("mesto");
		adresa1.appendChild(mesto1);
		
		Element ulica1 = document.createElement("ulica");
		ulica1.setAttribute("broj", "0");
		adresa1.appendChild(ulica1);
		
		zalilac.appendChild(adresa1);
		
		Element sedisteZalioca = document.createElement("sediste_zalioca");
		zalilac.appendChild(sedisteZalioca);
		
		Element protivResenjaZakljucak = document.createElement("protiv_resenja_zakljucka");
		zalba.appendChild(protivResenjaZakljucak);
		
		Element nazivOrgana = document.createElement("naziv_organa_koji_je_doneo_odluku");
		protivResenjaZakljucak.appendChild(nazivOrgana);
		
		Element broj = document.createElement("broj");
		broj.appendChild(document.createTextNode("1"));
		protivResenjaZakljucak.appendChild(broj);
		
		Element odGodine = document.createElement("od_godine");
		odGodine.appendChild(document.createTextNode("2000"));
		protivResenjaZakljucak.appendChild(odGodine);
		
		Element sadrzaj = document.createElement("sadrzaj");
		zalba.appendChild(sadrzaj);
		
		Element datum = document.createElement("datum");
		datum.appendChild(document.createTextNode("2000-12-12"));
		sadrzaj.appendChild(datum);
		
		Element osnovaZaZalbu = document.createElement("osnova_za_zalbu");
		sadrzaj.appendChild(osnovaZaZalbu);
		
		Element clan = document.createElement("clan");
		sadrzaj.appendChild(clan);
		
		Element podnosiocZalbe = document.createElement("podaci_o_podnosiocu_zalbe");
		zalba.appendChild(podnosiocZalbe);
		
		Element osoba1 = document.createElement("osoba");
		podnosiocZalbe.appendChild(osoba1);

		Element ime1 = document.createElement("ime");
		osoba1.appendChild(ime1);
		
		Element prezime1 = document.createElement("prezime");
		osoba1.appendChild(prezime1);

		
		Element adresa2 = document.createElement("adresa");
		podnosiocZalbe.appendChild(adresa2);
				
		Element mesto2 = document.createElement("mesto");
		adresa2.appendChild(mesto2);
		
		Element ulica2 = document.createElement("ulica");
		ulica2.setAttribute("broj", "0");
		adresa2.appendChild(ulica2);
		
		
		Element drugiPodaci = document.createElement("drugi_podaci_za_kontakt");
		podnosiocZalbe.appendChild(drugiPodaci);
		
		Element napomena = document.createElement("napomena");
		zalba.appendChild(napomena);
		
		Element naslov1 = document.createElement("naslov");
		napomena.appendChild(naslov1);
		
		Element tacka1 = document.createElement("tacka");
		napomena.appendChild(tacka1);
		
		Element tacka2 = document.createElement("tacka");
		napomena.appendChild(tacka2);
		
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

			filePath = "data/xml/zalbanaodlukacir_out.xml";

			System.out.println("[INFO] No input file, using default \""	+ filePath + "\"");

		} else {
			filePath = args[0];
		}

		WriterZalbaodluka handler = new WriterZalbaodluka();

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
