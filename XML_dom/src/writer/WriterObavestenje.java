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

public class WriterObavestenje {

	private static String TARGET_NAMESPACE = "http://www.obavestenje";

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
		Element obavestenje = document.createElementNS(TARGET_NAMESPACE,  "oba:obavestenje");
		document.appendChild(obavestenje);

		obavestenje.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "http://www.obavestenje ../xsd/obavestenje.xsd");

		obavestenje.setAttribute("br_predmeta", "");
		obavestenje.setAttribute("datum", "2000-12-12");

		Element nazivOrgana = document.createElement("naziv_organa");
		nazivOrgana.setAttribute("sediste", "");
		obavestenje.appendChild(nazivOrgana);

		Element informacijeOPodnosiocu = document.createElement("informacije_o_podnosiocu");
		Element lice = document.createElement("lice");
		Element osoba = document.createElement("osoba");
		Element ime = document.createElement("ime");
		Element prezime = document.createElement("prezime");

		osoba.appendChild(ime);
		osoba.appendChild(prezime);
		lice.appendChild(osoba);

		Element adresa = document.createElement("adresa");
		Element mesto = document.createElement("mesto");
		Element ulica = document.createElement("ulica");

		ulica.setAttribute("broj","0");
		adresa.appendChild(mesto);
		adresa.appendChild(ulica);
		informacijeOPodnosiocu.appendChild(lice);
		informacijeOPodnosiocu.appendChild(adresa);
		obavestenje.appendChild(informacijeOPodnosiocu);

		Element naslov = document.createElement("naslov");
		obavestenje.appendChild(naslov);

		Element podnaslov = document.createElement("podnaslov");
		obavestenje.appendChild(podnaslov);

		Element tekstZahteva = document.createElement("tekst_zahteva");
		informacijeOPodnosiocu.appendChild(tekstZahteva);

		Element clan = document.createElement("clan");
		clan.setAttribute("broj", "16");

		Element stav = document.createElement("stav");
		stav.setAttribute("broj", "1");

		clan.appendChild(stav);
		tekstZahteva.appendChild(clan);


		Element godina = document.createElement("godina");
		godina.appendChild(document.createTextNode("2000"));
		tekstZahteva.appendChild(godina);


		Element opisTrazeneInformacije = document.createElement("opis_trazene_informacije");
		tekstZahteva.appendChild(opisTrazeneInformacije);

		Element dan = document.createElement("dan");
		dan.appendChild(document.createTextNode("2000-12-12"));
		Element vreme = document.createElement("vreme");
		vreme.appendChild(document.createTextNode("12"));
		tekstZahteva.appendChild(dan);
		tekstZahteva.appendChild(vreme);

		Element radnoVreme = document.createElement("radno_vreme");
		radnoVreme.appendChild(document.createTextNode("12"));


		Element pocetak = document.createElement("pocetak");
		pocetak.appendChild(document.createTextNode("12"));
		radnoVreme.appendChild(pocetak);

		Element kraj = document.createElement("kraj");
		kraj.appendChild(document.createTextNode("12"));

		radnoVreme.appendChild(kraj);

		tekstZahteva.appendChild(radnoVreme);


		Element adresa1 = document.createElement("adresa");
		Element mesto1 = document.createElement("mesto");
		Element ulica1 = document.createElement("ulica");

		ulica.setAttribute("broj","0");
		adresa1.appendChild(mesto1);
		adresa1.appendChild(ulica1);

		tekstZahteva.appendChild(adresa1);

		Element broj_kancelarije = document.createElement("broj_kancelarije");
		broj_kancelarije.appendChild(document.createTextNode("1"));

		tekstZahteva.appendChild(broj_kancelarije);

		Element opisTroskova = document.createElement("opis_troskova");
		Element cena1 = document.createElement("cena");
		cena1.setAttribute("valuta", "dinar");
		cena1.appendChild(document.createTextNode("100,00"));

		Element cena2 = document.createElement("cena");
		cena2.setAttribute("valuta", "dinar");
		cena2.appendChild(document.createTextNode("100,00"));


		Element cena3 = document.createElement("cena");
		cena3.setAttribute("valuta", "dinar");
		cena3.appendChild(document.createTextNode("100,00"));


		Element cena4 = document.createElement("cena");
		cena4.setAttribute("valuta", "dinar");
		cena4.appendChild(document.createTextNode("100,00"));


		Element cena5 = document.createElement("cena");
		cena5.setAttribute("valuta", "dinar");
		cena5.appendChild(document.createTextNode("100,00"));


		Element cena6 = document.createElement("cena");
		cena6.setAttribute("valuta", "dinar");
		cena6.appendChild(document.createTextNode("100,00"));


		Element cena7 = document.createElement("cena");
		cena7.setAttribute("valuta", "dinar");
		cena7.appendChild(document.createTextNode("100,00"));


		Element cena8 = document.createElement("cena");
		cena8.setAttribute("valuta", "dinar");
		cena8.appendChild(document.createTextNode("100,00"));


		opisTroskova.appendChild(cena1);
		opisTroskova.appendChild(cena2);
		opisTroskova.appendChild(cena3);
		opisTroskova.appendChild(cena4);
		opisTroskova.appendChild(cena5);
		opisTroskova.appendChild(cena6);
		opisTroskova.appendChild(cena7);
		opisTroskova.appendChild(cena8);

		tekstZahteva.appendChild(opisTroskova);

		Element ukupanTrosak = document.createElement("ukupan_trosak");

		Element iznos = document.createElement("iznos");
		iznos.appendChild(document.createTextNode("0,00"));
		ukupanTrosak.appendChild(iznos);

		Element brojRacuna = document.createElement("broj_racuna");
		brojRacuna.appendChild(document.createTextNode("840-742328-843-30"));
		ukupanTrosak.appendChild(brojRacuna);

		Element pozivNaBroj = document.createElement("poziv_na_broj");
		pozivNaBroj.appendChild(document.createTextNode("97"));
		ukupanTrosak.appendChild(pozivNaBroj);

		tekstZahteva.appendChild(ukupanTrosak);

		obavestenje.appendChild(tekstZahteva);

		Element opcijaDostave = document.createElement("opcija_dostave");

		Element opcija1 = document.createElement("opcija");
		opcija1.setAttribute("izabran", "false");

		Element opcija2 = document.createElement("opcija");
		opcija2.setAttribute("izabran", "false");

		opcijaDostave.appendChild(opcija1);
		opcijaDostave.appendChild(opcija2);

		obavestenje.appendChild(opcijaDostave);

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

			filePath = "data/xml/obavestenje_out.xml";

			System.out.println("[INFO] No input file, using default \""	+ filePath + "\"");

		} else {
			filePath = args[0];
		}

		WriterObavestenje handler = new WriterObavestenje();

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
