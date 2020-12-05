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

public class WriterZahtevcir {

	private static String TARGET_NAMESPACE = "http://www.zahtevcir";

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
				Element zahtevcir = document.createElementNS(TARGET_NAMESPACE,  "zcir:zahtev");
				document.appendChild(zahtevcir);

				zahtevcir.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "http://www.zahtevcir ../xsd/zahtevcir.xsd");

				zahtevcir.setAttribute("mesto", "");
				zahtevcir.setAttribute("datum", "2000-12-12");

				Element ciljaniOrganVlasti = document.createElement("ciljani_organ_vlasti");
				
				Element nazivOrgana = document.createElement("naziv_organa");
				Element sedisteOrgana = document.createElement("sediste_organa");
				ciljaniOrganVlasti.appendChild(nazivOrgana);
				ciljaniOrganVlasti.appendChild(sedisteOrgana);
				zahtevcir.appendChild(ciljaniOrganVlasti);

				

				Element naziv = document.createElement("naziv");
				zahtevcir.appendChild(naziv);
				Element tekstZahteva = document.createElement("tekst_zahteva");
				Element clan = document.createElement("clan");
				clan.setAttribute("broj", "15");
				Element stav = document.createElement("stav");
				stav.setAttribute("broj", "1");
				clan.appendChild(stav);
				tekstZahteva.appendChild(clan);
				
				Element opcije = document.createElement("opcije");
				Element opcija1 = document.createElement("opcija");
				opcija1.setAttribute("izabran", "false");
				Element opcija2 = document.createElement("opcija");
				opcija2.setAttribute("izabran", "false");
				Element opcija3 = document.createElement("opcija");
				opcija3.setAttribute("izabran", "false");
				Element opcija4 = document.createElement("opcija");
				opcija4.setAttribute("izabran", "false");
				opcije.appendChild(opcija1);
				opcije.appendChild(opcija2);
				opcije.appendChild(opcija3);
				opcije.appendChild(opcija4);
				Element naciniDostave = document.createElement("nacini_dostave");
				Element nacinDostave1 = document.createElement("nacin_dostave");
				nacinDostave1.setAttribute("izabran", "false");
				Element nacinDostave2 = document.createElement("nacin_dostave");
				nacinDostave2.setAttribute("izabran", "false");
				Element nacinDostave3 = document.createElement("nacin_dostave");
				nacinDostave3.setAttribute("izabran", "false");
				Element nacinDostave4 = document.createElement("nacin_dostave");
				nacinDostave4.setAttribute("izabran", "false");
				Element nacinDostaveInput = document.createElement("nacin_dostave_input");
				nacinDostave4.appendChild(nacinDostaveInput);
				naciniDostave.appendChild(nacinDostave1);
				naciniDostave.appendChild(nacinDostave2);
				naciniDostave.appendChild(nacinDostave3);
				naciniDostave.appendChild(nacinDostave4);
				opcije.appendChild(naciniDostave);
				tekstZahteva.appendChild(opcije);
				
				Element informacijaOZahtevu = document.createElement("informacija_o_zahtevu");
				tekstZahteva.appendChild(informacijaOZahtevu);
				
				Element informacijeOTraziocu = document.createElement("informacije_o_traziocu");
				Element lice = document.createElement("lice");
				Element osoba = document.createElement("osoba");
				Element ime = document.createElement("ime");
				Element prezime = document.createElement("prezime");
				osoba.appendChild(ime);
				osoba.appendChild(prezime);
				lice.appendChild(osoba);
				informacijeOTraziocu.appendChild(lice);
				Element adresa = document.createElement("adresa");
				Element mesto = document.createElement("mesto");
				Element ulica = document.createElement("ulica");
				ulica.setAttribute("broj", "0");
				adresa.appendChild(mesto);
				adresa.appendChild(ulica);
				informacijeOTraziocu.appendChild(adresa);
				
				Element drugiPodaciZaKontakt = document.createElement("drugi_podaci_za_kontakt");
				informacijeOTraziocu.appendChild(drugiPodaciZaKontakt);
				
				zahtevcir.appendChild(informacijeOTraziocu);
				
				Element fusnote = document.createElement("fusnote");
				Element fusnota1 = document.createElement("fusnota");
				Element fusnota2 = document.createElement("fusnota");
				Element fusnota3 = document.createElement("fusnota");
				fusnote.appendChild(fusnota1);
				fusnote.appendChild(fusnota2);
				fusnote.appendChild(fusnota3);
				
				zahtevcir.appendChild(fusnote);
		

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

			filePath = "data/xml/zahtevcir_out.xml";

			System.out.println("[INFO] No input file, using default \""	+ filePath + "\"");

		} else {
			filePath = args[0];
		}

		WriterZahtevcir handler = new WriterZahtevcir();

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
