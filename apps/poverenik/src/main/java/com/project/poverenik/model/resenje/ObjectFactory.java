
package com.project.poverenik.model.resenje;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.project.poverenik.model.resenje package. 
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

    private final static QName _TstavTacka_QNAME = new QName("http://resenje", "tacka");
    private final static QName _TodlukaIznos_QNAME = new QName("http://resenje", "iznos");
    private final static QName _TodlukaDatum_QNAME = new QName("http://resenje", "datum");
    private final static QName _TodlukaStav_QNAME = new QName("http://resenje", "stav");
    private final static QName _TodlukaLice_QNAME = new QName("http://resenje", "lice");
    private final static QName _TodlukaMesto_QNAME = new QName("http://resenje", "mesto");
    private final static QName _TodlukaClan_QNAME = new QName("http://resenje", "clan");
    private final static QName _TadresaUlica_QNAME = new QName("http://resenje", "ulica");
    private final static QName _TuvodneInformacijeAdresa_QNAME = new QName("http://resenje", "adresa");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.project.poverenik.model.resenje
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Tstav }
     * 
     */
    public Tstav createTstav() {
        return new Tstav();
    }

    /**
     * Create an instance of {@link Tclan }
     * 
     */
    public Tclan createTclan() {
        return new Tclan();
    }

    /**
     * Create an instance of {@link Tadresa }
     * 
     */
    public Tadresa createTadresa() {
        return new Tadresa();
    }

    /**
     * Create an instance of {@link Resenje }
     * 
     */
    public Resenje createResenje() {
        return new Resenje();
    }

    /**
     * Create an instance of {@link TuvodneInformacije }
     * 
     */
    public TuvodneInformacije createTuvodneInformacije() {
        return new TuvodneInformacije();
    }

    /**
     * Create an instance of {@link TpodaciOResenju }
     * 
     */
    public TpodaciOResenju createTpodaciOResenju() {
        return new TpodaciOResenju();
    }

    /**
     * Create an instance of {@link TpodaciOObrazlozenju }
     * 
     */
    public TpodaciOObrazlozenju createTpodaciOObrazlozenju() {
        return new TpodaciOObrazlozenju();
    }

    /**
     * Create an instance of {@link Todluka }
     * 
     */
    public Todluka createTodluka() {
        return new Todluka();
    }

    /**
     * Create an instance of {@link Tresenje }
     * 
     */
    public Tresenje createTresenje() {
        return new Tresenje();
    }

    /**
     * Create an instance of {@link TpredmetZalbe }
     * 
     */
    public TpredmetZalbe createTpredmetZalbe() {
        return new TpredmetZalbe();
    }

    /**
     * Create an instance of {@link TppostupakPoverenika }
     * 
     */
    public TppostupakPoverenika createTppostupakPoverenika() {
        return new TppostupakPoverenika();
    }

    /**
     * Create an instance of {@link Ttacka }
     * 
     */
    public Ttacka createTtacka() {
        return new Ttacka();
    }

    /**
     * Create an instance of {@link Tstav.Tacka }
     * 
     */
    public Tstav.Tacka createTstavTacka() {
        return new Tstav.Tacka();
    }

    /**
     * Create an instance of {@link Tclan.Tacka }
     * 
     */
    public Tclan.Tacka createTclanTacka() {
        return new Tclan.Tacka();
    }

    /**
     * Create an instance of {@link Tadresa.Ulica }
     * 
     */
    public Tadresa.Ulica createTadresaUlica() {
        return new Tadresa.Ulica();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tstav.Tacka }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "tacka", scope = Tstav.class)
    public JAXBElement<Tstav.Tacka> createTstavTacka(Tstav.Tacka value) {
        return new JAXBElement<Tstav.Tacka>(_TstavTacka_QNAME, Tstav.Tacka.class, Tstav.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "iznos", scope = Todluka.class)
    public JAXBElement<String> createTodlukaIznos(String value) {
        return new JAXBElement<String>(_TodlukaIznos_QNAME, String.class, Todluka.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "datum", scope = Todluka.class)
    public JAXBElement<String> createTodlukaDatum(String value) {
        return new JAXBElement<String>(_TodlukaDatum_QNAME, String.class, Todluka.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tstav }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "stav", scope = Todluka.class)
    public JAXBElement<Tstav> createTodlukaStav(Tstav value) {
        return new JAXBElement<Tstav>(_TodlukaStav_QNAME, Tstav.class, Todluka.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "lice", scope = Todluka.class)
    public JAXBElement<String> createTodlukaLice(String value) {
        return new JAXBElement<String>(_TodlukaLice_QNAME, String.class, Todluka.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "mesto", scope = Todluka.class)
    public JAXBElement<String> createTodlukaMesto(String value) {
        return new JAXBElement<String>(_TodlukaMesto_QNAME, String.class, Todluka.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tclan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "clan", scope = Todluka.class)
    public JAXBElement<Tclan> createTodlukaClan(Tclan value) {
        return new JAXBElement<Tclan>(_TodlukaClan_QNAME, Tclan.class, Todluka.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tadresa.Ulica }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "ulica", scope = Tadresa.class)
    public JAXBElement<Tadresa.Ulica> createTadresaUlica(Tadresa.Ulica value) {
        return new JAXBElement<Tadresa.Ulica>(_TadresaUlica_QNAME, Tadresa.Ulica.class, Tadresa.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "mesto", scope = Tadresa.class)
    public JAXBElement<String> createTadresaMesto(String value) {
        return new JAXBElement<String>(_TodlukaMesto_QNAME, String.class, Tadresa.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "lice", scope = Ttacka.class)
    public JAXBElement<String> createTtackaLice(String value) {
        return new JAXBElement<String>(_TodlukaLice_QNAME, String.class, Ttacka.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "mesto", scope = Ttacka.class)
    public JAXBElement<String> createTtackaMesto(String value) {
        return new JAXBElement<String>(_TodlukaMesto_QNAME, String.class, Ttacka.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "datum", scope = TppostupakPoverenika.class)
    public JAXBElement<String> createTppostupakPoverenikaDatum(String value) {
        return new JAXBElement<String>(_TodlukaDatum_QNAME, String.class, TppostupakPoverenika.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "lice", scope = TppostupakPoverenika.class)
    public JAXBElement<String> createTppostupakPoverenikaLice(String value) {
        return new JAXBElement<String>(_TodlukaLice_QNAME, String.class, TppostupakPoverenika.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "mesto", scope = TppostupakPoverenika.class)
    public JAXBElement<String> createTppostupakPoverenikaMesto(String value) {
        return new JAXBElement<String>(_TodlukaMesto_QNAME, String.class, TppostupakPoverenika.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tclan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "clan", scope = TppostupakPoverenika.class)
    public JAXBElement<Tclan> createTppostupakPoverenikaClan(Tclan value) {
        return new JAXBElement<Tclan>(_TodlukaClan_QNAME, Tclan.class, TppostupakPoverenika.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tstav }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "stav", scope = Tclan.class)
    public JAXBElement<Tstav> createTclanStav(Tstav value) {
        return new JAXBElement<Tstav>(_TodlukaStav_QNAME, Tstav.class, Tclan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tclan.Tacka }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "tacka", scope = Tclan.class)
    public JAXBElement<Tclan.Tacka> createTclanTacka(Tclan.Tacka value) {
        return new JAXBElement<Tclan.Tacka>(_TstavTacka_QNAME, Tclan.Tacka.class, Tclan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "datum", scope = TuvodneInformacije.class)
    public JAXBElement<String> createTuvodneInformacijeDatum(String value) {
        return new JAXBElement<String>(_TodlukaDatum_QNAME, String.class, TuvodneInformacije.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "lice", scope = TuvodneInformacije.class)
    public JAXBElement<String> createTuvodneInformacijeLice(String value) {
        return new JAXBElement<String>(_TodlukaLice_QNAME, String.class, TuvodneInformacije.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tadresa }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "adresa", scope = TuvodneInformacije.class)
    public JAXBElement<Tadresa> createTuvodneInformacijeAdresa(Tadresa value) {
        return new JAXBElement<Tadresa>(_TuvodneInformacijeAdresa_QNAME, Tadresa.class, TuvodneInformacije.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Tclan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "clan", scope = TuvodneInformacije.class)
    public JAXBElement<Tclan> createTuvodneInformacijeClan(Tclan value) {
        return new JAXBElement<Tclan>(_TodlukaClan_QNAME, Tclan.class, TuvodneInformacije.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "datum", scope = TpredmetZalbe.class)
    public JAXBElement<String> createTpredmetZalbeDatum(String value) {
        return new JAXBElement<String>(_TodlukaDatum_QNAME, String.class, TpredmetZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "lice", scope = TpredmetZalbe.class)
    public JAXBElement<String> createTpredmetZalbeLice(String value) {
        return new JAXBElement<String>(_TodlukaLice_QNAME, String.class, TpredmetZalbe.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://resenje", name = "mesto", scope = TpredmetZalbe.class)
    public JAXBElement<String> createTpredmetZalbeMesto(String value) {
        return new JAXBElement<String>(_TodlukaMesto_QNAME, String.class, TpredmetZalbe.class, value);
    }

}
