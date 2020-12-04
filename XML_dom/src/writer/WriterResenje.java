package writer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.io.Writer;

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
		resenje.setAttribute("tip_resenja", "жалба је основана");
		resenje.setAttribute("datum", "09.06.2020.");
		rad.appendChild(resenje);

		Element uvodneInformacije = document.createElementNS(TARGET_NAMESPACE, "uvodne_informacije");
		resenje.appendChild(uvodneInformacije);

		uvodneInformacije.appendChild(document.createTextNode("Повереник за информације од јавног значаја и заштиту података о личности, у поступку по алби коју је изјавио AA, због непоступања "));
		Element lice = document.createElementNS(TARGET_NAMESPACE, "lice");
		lice.appendChild(document.createTextNode("Учитељског факултета у Призрену"));
		uvodneInformacije.appendChild(lice);

		uvodneInformacije.appendChild(document.createTextNode("са привременим седиштем у"));
		Element adresa = document.createElementNS(TARGET_NAMESPACE, "adresa");

		Element mesto = document.createElementNS(TARGET_NAMESPACE, "mesto");
		mesto.appendChild(document.createTextNode("Лепосавићу"));
		adresa.appendChild(mesto);

		uvodneInformacije.appendChild(document.createTextNode(","));

		Element ulica = document.createElementNS(TARGET_NAMESPACE, "ulica");
		ulica.setAttribute("broj", "66");
		ulica.appendChild(document.createTextNode("ул. Немањина"));
		adresa.appendChild(ulica);
		uvodneInformacije.appendChild(adresa);

		uvodneInformacije.appendChild(document.createTextNode(", по његовом захтеву од "));
		Element datum = document.createElementNS(TARGET_NAMESPACE, "datum");
		datum.appendChild(document.createTextNode("16.04.2020."));
		uvodneInformacije.appendChild(datum);

		uvodneInformacije.appendChild(document.createTextNode(" године за приступ информацијама од јавног значаја, на основу "));

		Element clan = document.createElementNS(TARGET_NAMESPACE, "clan");
		clan.setAttribute("broj", "35");
		clan.appendChild(document.createTextNode("члана 35."));

		Element stav = document.createElementNS(TARGET_NAMESPACE, "stav");
		stav.setAttribute("broj", "1");
		stav.appendChild(document.createTextNode("став 1."));

		Element tacka = document.createElementNS(TARGET_NAMESPACE, "tacka");
		tacka.setAttribute("broj", "5");
		tacka.appendChild(document.createTextNode("тачка 5."));

		stav.appendChild(tacka);
		clan.appendChild(stav);
		uvodneInformacije.appendChild(clan);

		uvodneInformacije.appendChild(document.createTextNode("Закона о слободном приступу информацијама од јавног значаја („Сл. гласник РС“, бр. 120/04, 54/07, 104/09 и 36/10), а у вези са"));

		clan.setAttribute("broj", "3");

		uvodneInformacije.appendChild(clan);

		/*Element univerzitet = document.createElementNS(TARGET_NAMESPACE, "univerzitet");
		univerzitet.appendChild(document.createTextNode("Univerzitet u Novom Sadu"));
		institucija.appendChild(univerzitet);

		Element fakultet = document.createElementNS(TARGET_NAMESPACE, "fakultet");
		fakultet.appendChild(document.createTextNode("Fakultet tehničkih nauka"));
		institucija.appendChild(fakultet);

		Element departman = document.createElementNS(TARGET_NAMESPACE, "departman");
		departman.appendChild(document.createTextNode("Računarstvo i automatika"));
		institucija.appendChild(departman);

		Element katedra = document.createElementNS(TARGET_NAMESPACE, "katedra");
		katedra.appendChild(document.createTextNode("Katedra za informatiku"));
		institucija.appendChild(katedra);

		Element autor = document.createElementNS(TARGET_NAMESPACE, "autor");
		naslovnaStrana.appendChild(autor);

		Element ime = document.createElementNS(TARGET_NAMESPACE, "ime");
		ime.appendChild(document.createTextNode("Petar"));
		autor.appendChild(ime);

		Element prezime = document.createElementNS(TARGET_NAMESPACE, "prezime");
		prezime.appendChild(document.createTextNode("Petrović"));
		autor.appendChild(prezime);

		Element broj_indeksa = document.createElementNS(TARGET_NAMESPACE, "broj_indeksa");
		broj_indeksa.appendChild(document.createTextNode("RA 1/2012"));
		autor.appendChild(broj_indeksa);

		Element temaSrpski = document.createElementNS(TARGET_NAMESPACE, "tema_rada");
		temaSrpski.setAttribute("jezik", "srpski");
		temaSrpski.appendChild(document.createTextNode("Implementacija podsistema banke u okviru sistema platnog prometa."));
		naslovnaStrana.appendChild(temaSrpski);

		Element temaEngleski = document.createElementNS(TARGET_NAMESPACE, "tema_rada");
		temaEngleski.setAttribute("jezik", "engleski");
		temaEngleski.appendChild(document.createTextNode("Implementation of banking subsystem in an electronic payment system."));
		naslovnaStrana.appendChild(temaEngleski);

		Element nivoStudija = document.createElementNS(TARGET_NAMESPACE, "nivo_studija");
		nivoStudija.appendChild(document.createTextNode("OAS"));
		naslovnaStrana.appendChild(nivoStudija);

		Element sadrzaj = document.createElementNS(TARGET_NAMESPACE, "sadrzaj");
		sadrzaj.appendChild(document.createComment("Generisati \"sadrzaj\" analogno."));
		rad.appendChild(sadrzaj);

		Element poglavlja = document.createElementNS(TARGET_NAMESPACE, "poglavlja");
		poglavlja.appendChild(document.createComment("Generisati \"poglavlja\" analogno."));
		rad.appendChild(poglavlja);*/

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

			// Nad "source" objektom (DOM stablo) vrši se transformacija
			DOMSource source = new DOMSource(document);

			// Rezultujući stream (argument metode)
			StreamResult result = new StreamResult(out);

			// Poziv metode koja vrši opisanu transformaciju
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

			filePath = "data/xml/zavrsni_rad.xml";

			System.out.println("[INFO] No input file, using default \""	+ filePath + "\"");

		} else {
			filePath = args[0];
		}

		WriterResenje handler = new WriterResenje();

		// Kreiranje Document čvora
		handler.createDocument();

		// Generisanje DOM stabla
		handler.generateDOM();

		// Prikaz sadržaja (isprobati sa FileOutputStream-om)
		handler.transform(System.out);

		/*
		try {
			handler.transform(new FileOutputStream("data/xml/zavrsni_rad_out_3.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		*/
	}
}
