
package com.project.poverenik.model.zalba_cutanje;

import com.project.poverenik.model.util.*;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.project.poverenik.model.zalba_cutanje package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TopisTroskovaCena_QNAME = new QName("", "cena");
    private final static QName _TtekstZahtevaObavestenjaDan_QNAME = new QName("", "dan");
    private final static QName _TtekstZahtevaObavestenjaRadnoVreme_QNAME = new QName("", "radno_vreme");
    private final static QName _TtekstZahtevaObavestenjaVreme_QNAME = new QName("", "vreme");
    private final static QName _TtekstZahtevaObavestenjaClan_QNAME = new QName("", "clan");
    private final static QName _TtekstZahtevaObavestenjaAdresa_QNAME = new QName("", "adresa");
    private final static QName _TtekstZahtevaObavestenjaUkupanTrosak_QNAME = new QName("", "ukupan_trosak");
    private final static QName _TtekstZahtevaObavestenjaBrojKancelarije_QNAME = new QName("", "broj_kancelarije");
    private final static QName _TtekstZahtevaObavestenjaOpisTroskova_QNAME = new QName("", "opis_troskova");
    private final static QName _TtekstZahtevaObavestenjaGodina_QNAME = new QName("", "godina");
    private final static QName _TtekstZahtevaObavestenjaOpisTrazeneInformacije_QNAME = new QName("", "opis_trazene_informacije");
    private final static QName _TtekstZahtevaZahtevcirOpcije_QNAME = new QName("", "opcije");
    private final static QName _TtekstZahtevaZahtevcirInformacijaOZahtevu_QNAME = new QName("", "informacija_o_zahtevu");
    private final static QName _TsadrzajZalbeCiljaniOrganVlasti_QNAME = new QName("", "ciljani_organ_vlasti");
    private final static QName _TsadrzajZalbeDatum_QNAME = new QName("", "datum");
    private final static QName _TsadrzajZalbeRazlogZalbe_QNAME = new QName("", "razlog_zalbe");
    private final static QName _TsadrzajZalbeNapomena_QNAME = new QName("", "napomena");
    private final static QName _TsadrzajZalbePodaciOZahtjevuIInformacijama_QNAME = new QName("", "podaci_o_zahtjevu_i_informacijama");
    private final static QName _TsadrzajOsnovaZaZalbu_QNAME = new QName("", "osnova_za_zalbu");
    private final static QName _TclanStav_QNAME = new QName("", "stav");
    private final static QName _TradnoVremeKraj_QNAME = new QName("", "kraj");
    private final static QName _TradnoVremePocetak_QNAME = new QName("", "pocetak");
    private final static QName _TukupanTrosakIznos_QNAME = new QName("", "iznos");
    private final static QName _TukupanTrosakPozivNaBroj_QNAME = new QName("", "poziv_na_broj");
    private final static QName _TukupanTrosakBrojRacuna_QNAME = new QName("", "broj_racuna");
    private final static QName _TnacinDostaveNacinDostaveInput_QNAME = new QName("", "nacin_dostave_input");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.project.poverenik.model.zalba_cutanje
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Tadresa }
     * 
     */
    public Tadresa createTadresa() {
        return new Tadresa();
    }

    /**
     * Create an instance of {@link TopisTroskova }
     * 
     */
    public TopisTroskova createTopisTroskova() {
        return new TopisTroskova();
    }

    /**
     * Create an instance of {@link Tclan }
     * 
     */
    public Tclan createTclan() {
        return new Tclan();
    }

    /**
     * Create an instance of {@link TopcijeDostave }
     * 
     */
    public TopcijeDostave createTopcijeDostave() {
        return new TopcijeDostave();
    }

    /**
     * Create an instance of {@link Topcije }
     * 
     */
    public Topcije createTopcije() {
        return new Topcije();
    }

    /**
     * Create an instance of {@link ZalbaCutanje }
     * 
     */
    public ZalbaCutanje createZalbaCutanje() {
        return new ZalbaCutanje();
    }

    /**
     * Create an instance of {@link TpodaciPovereniku }
     * 
     */
    public TpodaciPovereniku createTpodaciPovereniku() {
        return new TpodaciPovereniku();
    }

    /**
     * Create an instance of {@link TsadrzajZalbe }
     * 
     */
    public TsadrzajZalbe createTsadrzajZalbe() {
        return new TsadrzajZalbe();
    }

    /**
     * Create an instance of {@link TpodaciOPodnosiocu }
     * 
     */
    public TpodaciOPodnosiocu createTpodaciOPodnosiocu() {
        return new TpodaciOPodnosiocu();
    }

    /**
     * Create an instance of {@link TnacinDostave }
     * 
     */
    public TnacinDostave createTnacinDostave() {
        return new TnacinDostave();
    }

    /**
     * Create an instance of {@link TnaciniDostave }
     * 
     */
    public TnaciniDostave createTnaciniDostave() {
        return new TnaciniDostave();
    }

    /**
     * Create an instance of {@link TprotivResenjaZakljucka }
     * 
     */
    public TprotivResenjaZakljucka createTprotivResenjaZakljucka() {
        return new TprotivResenjaZakljucka();
    }

    /**
     * Create an instance of {@link Tzalilac }
     * 
     */
    public Tzalilac createTzalilac() {
        return new Tzalilac();
    }

    /**
     * Create an instance of {@link TradnoVreme }
     * 
     */
    public TradnoVreme createTradnoVreme() {
        return new TradnoVreme();
    }

    /**
     * Create an instance of {@link TtekstZahtevaObavestenja }
     * 
     */
    public TtekstZahtevaObavestenja createTtekstZahtevaObavestenja() {
        return new TtekstZahtevaObavestenja();
    }

    /**
     * Create an instance of {@link Tosoba }
     * 
     */
    public Tosoba createTosoba() {
        return new Tosoba();
    }

    /**
     * Create an instance of {@link TinformacijeOPodnosiocu }
     * 
     */
    public TinformacijeOPodnosiocu createTinformacijeOPodnosiocu() {
        return new TinformacijeOPodnosiocu();
    }

    /**
     * Create an instance of {@link TukupanTrosak }
     * 
     */
    public TukupanTrosak createTukupanTrosak() {
        return new TukupanTrosak();
    }

    /**
     * Create an instance of {@link TtipLica }
     * 
     */
    public TtipLica createTtipLica() {
        return new TtipLica();
    }

    /**
     * Create an instance of {@link Tfusnote }
     * 
     */
    public Tfusnote createTfusnote() {
        return new Tfusnote();
    }

    /**
     * Create an instance of {@link Tnapomena }
     * 
     */
    public Tnapomena createTnapomena() {
        return new Tnapomena();
    }

    /**
     * Create an instance of {@link TtekstZahtevaZahtevcir }
     * 
     */
    public TtekstZahtevaZahtevcir createTtekstZahtevaZahtevcir() {
        return new TtekstZahtevaZahtevcir();
    }

    /**
     * Create an instance of {@link TciljaniOrganVlasti }
     * 
     */
    public TciljaniOrganVlasti createTciljaniOrganVlasti() {
        return new TciljaniOrganVlasti();
    }

    /**
     * Create an instance of {@link Tsadrzaj }
     * 
     */
    public Tsadrzaj createTsadrzaj() {
        return new Tsadrzaj();
    }

    /**
     * Create an instance of {@link TinformacijeOTraziocu }
     * 
     */
    public TinformacijeOTraziocu createTinformacijeOTraziocu() {
        return new TinformacijeOTraziocu();
    }

    /**
     * Create an instance of {@link Tadresa.Ulica }
     * 
     */
    public Tadresa.Ulica createTadresaUlica() {
        return new Tadresa.Ulica();
    }

    /**
     * Create an instance of {@link TopisTroskova.Cena }
     * 
     */
    public TopisTroskova.Cena createTopisTroskovaCena() {
        return new TopisTroskova.Cena();
    }

    /**
     * Create an instance of {@link Tclan.Stav }
     * 
     */
    public Tclan.Stav createTclanStav() {
        return new Tclan.Stav();
    }

    /**
     * Create an instance of {@link TopcijeDostave.Opcija }
     * 
     */
    public TopcijeDostave.Opcija createTopcijeDostaveOpcija() {
        return new TopcijeDostave.Opcija();
    }

    /**
     * Create an instance of {@link Topcije.Opcija }
     * 
     */
    public Topcije.Opcija createTopcijeOpcija() {
        return new Topcije.Opcija();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopisTroskova.Cena }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "cena", scope = TopisTroskova.class)
    public JAXBElement<TopisTroskova.Cena> createTopisTroskovaCena(TopisTroskova.Cena value) {
        return new JAXBElement<TopisTroskova.Cena>(_TopisTroskovaCena_QNAME, TopisTroskova.Cena.class, TopisTroskova.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "dan", scope = TtekstZahtevaObavestenja.class)
    public JAXBElement<XMLGregorianCalendar> createTtekstZahtevaObavestenjaDan(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TtekstZahtevaObavestenjaDan_QNAME, XMLGregorianCalendar.class, TtekstZahtevaObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TradnoVreme }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "radno_vreme", scope = TtekstZahtevaObavestenja.class)
    public JAXBElement<TradnoVreme> createTtekstZahtevaObavestenjaRadnoVreme(TradnoVreme value) {
        return new JAXBElement<TradnoVreme>(_TtekstZahtevaObavestenjaRadnoVreme_QNAME, TradnoVreme.class, TtekstZahtevaObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "vreme", scope = TtekstZahtevaObavestenja.class)
    public JAXBElement<String> createTtekstZahtevaObavestenjaVreme(String value) {
        return new JAXBElement<String>(_TtekstZahtevaObavestenjaVreme_QNAME, String.class, TtekstZahtevaObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tclan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "clan", scope = TtekstZahtevaObavestenja.class)
    public JAXBElement<Tclan> createTtekstZahtevaObavestenjaClan(Tclan value) {
        return new JAXBElement<Tclan>(_TtekstZahtevaObavestenjaClan_QNAME, Tclan.class, TtekstZahtevaObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tadresa }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "adresa", scope = TtekstZahtevaObavestenja.class)
    public JAXBElement<Tadresa> createTtekstZahtevaObavestenjaAdresa(Tadresa value) {
        return new JAXBElement<Tadresa>(_TtekstZahtevaObavestenjaAdresa_QNAME, Tadresa.class, TtekstZahtevaObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TukupanTrosak }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ukupan_trosak", scope = TtekstZahtevaObavestenja.class)
    public JAXBElement<TukupanTrosak> createTtekstZahtevaObavestenjaUkupanTrosak(TukupanTrosak value) {
        return new JAXBElement<TukupanTrosak>(_TtekstZahtevaObavestenjaUkupanTrosak_QNAME, TukupanTrosak.class, TtekstZahtevaObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "broj_kancelarije", scope = TtekstZahtevaObavestenja.class)
    public JAXBElement<BigInteger> createTtekstZahtevaObavestenjaBrojKancelarije(BigInteger value) {
        return new JAXBElement<BigInteger>(_TtekstZahtevaObavestenjaBrojKancelarije_QNAME, BigInteger.class, TtekstZahtevaObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopisTroskova }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "opis_troskova", scope = TtekstZahtevaObavestenja.class)
    public JAXBElement<TopisTroskova> createTtekstZahtevaObavestenjaOpisTroskova(TopisTroskova value) {
        return new JAXBElement<TopisTroskova>(_TtekstZahtevaObavestenjaOpisTroskova_QNAME, TopisTroskova.class, TtekstZahtevaObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "godina", scope = TtekstZahtevaObavestenja.class)
    public JAXBElement<String> createTtekstZahtevaObavestenjaGodina(String value) {
        return new JAXBElement<String>(_TtekstZahtevaObavestenjaGodina_QNAME, String.class, TtekstZahtevaObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "opis_trazene_informacije", scope = TtekstZahtevaObavestenja.class)
    public JAXBElement<String> createTtekstZahtevaObavestenjaOpisTrazeneInformacije(String value) {
        return new JAXBElement<String>(_TtekstZahtevaObavestenjaOpisTrazeneInformacije_QNAME, String.class, TtekstZahtevaObavestenja.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopcijeDostave }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "opcije", scope = TtekstZahtevaZahtevcir.class)
    public JAXBElement<TopcijeDostave> createTtekstZahtevaZahtevcirOpcije(TopcijeDostave value) {
        return new JAXBElement<TopcijeDostave>(_TtekstZahtevaZahtevcirOpcije_QNAME, TopcijeDostave.class, TtekstZahtevaZahtevcir.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "informacija_o_zahtevu", scope = TtekstZahtevaZahtevcir.class)
    public JAXBElement<String> createTtekstZahtevaZahtevcirInformacijaOZahtevu(String value) {
        return new JAXBElement<String>(_TtekstZahtevaZahtevcirInformacijaOZahtevu_QNAME, String.class, TtekstZahtevaZahtevcir.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tclan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "clan", scope = TtekstZahtevaZahtevcir.class)
    public JAXBElement<Tclan> createTtekstZahtevaZahtevcirClan(Tclan value) {
        return new JAXBElement<Tclan>(_TtekstZahtevaObavestenjaClan_QNAME, Tclan.class, TtekstZahtevaZahtevcir.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ciljani_organ_vlasti", scope = TsadrzajZalbe.class)
    public JAXBElement<String> createTsadrzajZalbeCiljaniOrganVlasti(String value) {
        return new JAXBElement<String>(_TsadrzajZalbeCiljaniOrganVlasti_QNAME, String.class, TsadrzajZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "datum", scope = TsadrzajZalbe.class)
    public JAXBElement<XMLGregorianCalendar> createTsadrzajZalbeDatum(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TsadrzajZalbeDatum_QNAME, XMLGregorianCalendar.class, TsadrzajZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Topcije }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "razlog_zalbe", scope = TsadrzajZalbe.class)
    public JAXBElement<Topcije> createTsadrzajZalbeRazlogZalbe(Topcije value) {
        return new JAXBElement<Topcije>(_TsadrzajZalbeRazlogZalbe_QNAME, Topcije.class, TsadrzajZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "napomena", scope = TsadrzajZalbe.class)
    public JAXBElement<String> createTsadrzajZalbeNapomena(String value) {
        return new JAXBElement<String>(_TsadrzajZalbeNapomena_QNAME, String.class, TsadrzajZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tclan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "clan", scope = TsadrzajZalbe.class)
    public JAXBElement<Tclan> createTsadrzajZalbeClan(Tclan value) {
        return new JAXBElement<Tclan>(_TtekstZahtevaObavestenjaClan_QNAME, Tclan.class, TsadrzajZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "podaci_o_zahtjevu_i_informacijama", scope = TsadrzajZalbe.class)
    public JAXBElement<String> createTsadrzajZalbePodaciOZahtjevuIInformacijama(String value) {
        return new JAXBElement<String>(_TsadrzajZalbePodaciOZahtjevuIInformacijama_QNAME, String.class, TsadrzajZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "datum", scope = Tsadrzaj.class)
    public JAXBElement<XMLGregorianCalendar> createTsadrzajDatum(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TsadrzajZalbeDatum_QNAME, XMLGregorianCalendar.class, Tsadrzaj.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "osnova_za_zalbu", scope = Tsadrzaj.class)
    public JAXBElement<String> createTsadrzajOsnovaZaZalbu(String value) {
        return new JAXBElement<String>(_TsadrzajOsnovaZaZalbu_QNAME, String.class, Tsadrzaj.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tclan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "clan", scope = Tsadrzaj.class)
    public JAXBElement<Tclan> createTsadrzajClan(Tclan value) {
        return new JAXBElement<Tclan>(_TtekstZahtevaObavestenjaClan_QNAME, Tclan.class, Tsadrzaj.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tclan.Stav }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "stav", scope = Tclan.class)
    public JAXBElement<Tclan.Stav> createTclanStav(Tclan.Stav value) {
        return new JAXBElement<Tclan.Stav>(_TclanStav_QNAME, Tclan.Stav.class, Tclan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "kraj", scope = TradnoVreme.class)
    public JAXBElement<String> createTradnoVremeKraj(String value) {
        return new JAXBElement<String>(_TradnoVremeKraj_QNAME, String.class, TradnoVreme.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pocetak", scope = TradnoVreme.class)
    public JAXBElement<String> createTradnoVremePocetak(String value) {
        return new JAXBElement<String>(_TradnoVremePocetak_QNAME, String.class, TradnoVreme.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "iznos", scope = TukupanTrosak.class)
    public JAXBElement<String> createTukupanTrosakIznos(String value) {
        return new JAXBElement<String>(_TukupanTrosakIznos_QNAME, String.class, TukupanTrosak.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "poziv_na_broj", scope = TukupanTrosak.class)
    public JAXBElement<BigInteger> createTukupanTrosakPozivNaBroj(BigInteger value) {
        return new JAXBElement<BigInteger>(_TukupanTrosakPozivNaBroj_QNAME, BigInteger.class, TukupanTrosak.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "broj_racuna", scope = TukupanTrosak.class)
    public JAXBElement<String> createTukupanTrosakBrojRacuna(String value) {
        return new JAXBElement<String>(_TukupanTrosakBrojRacuna_QNAME, String.class, TukupanTrosak.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "nacin_dostave_input", scope = TnacinDostave.class)
    public JAXBElement<String> createTnacinDostaveNacinDostaveInput(String value) {
        return new JAXBElement<String>(_TnacinDostaveNacinDostaveInput_QNAME, String.class, TnacinDostave.class, value);
    }

}
