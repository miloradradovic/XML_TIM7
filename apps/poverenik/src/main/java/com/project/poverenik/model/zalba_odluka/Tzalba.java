
package com.project.poverenik.model.zalba_odluka;

import com.project.poverenik.model.util.ComplexTypes.*;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * <p>Java class for Tzalba complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tzalba">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zahtev">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;anyAttribute processContents='lax'/>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="status">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;anyAttribute processContents='lax'/>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="naslov" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="podaci_povereniku" type="{http://www.reusability}Tpodaci_povereniku" minOccurs="0"/>
 *         &lt;element name="podnaslov" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zalilac" type="{http://www.reusability}Tzalilac"/>
 *         &lt;element name="protiv_resenja_zakljucka" type="{http://www.reusability}Tprotiv_resenja_zakljucka"/>
 *         &lt;element name="sadrzaj" type="{http://www.reusability}Tsadrzaj"/>
 *         &lt;element name="podaci_o_podnosiocu_zalbe" type="{http://www.reusability}Tpodaci_o_podnosiocu"/>
 *         &lt;element name="napomena" type="{http://www.reusability}Tnapomena" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mesto" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tzalba", namespace = "http://www.zalbanaodlukucir", propOrder = {
    "zahtev",
    "status",
    "naslov",
    "podaciPovereniku",
    "podnaslov",
    "zalilac",
    "protivResenjaZakljucka",
    "sadrzaj",
    "podaciOPodnosiocuZalbe",
    "napomena"
})
public class Tzalba {

    @XmlElement(namespace = "http://www.zalbanaodlukucir", required = true)
    protected Tzalba.Zahtev zahtev;
    @XmlElement(namespace = "http://www.zalbanaodlukucir")
    protected Tzalba.Status status;
    @XmlElement(namespace = "http://www.zalbanaodlukucir")
    protected String naslov;
    @XmlElement(name = "podaci_povereniku", namespace = "http://www.zalbanaodlukucir")
    protected TpodaciPovereniku podaciPovereniku;
    @XmlElement(namespace = "http://www.zalbanaodlukucir")
    protected String podnaslov;
    @XmlElement(namespace = "http://www.zalbanaodlukucir", required = true)
    protected Tzalilac zalilac;
    @XmlElement(name = "protiv_resenja_zakljucka", namespace = "http://www.zalbanaodlukucir", required = true)
    protected TprotivResenjaZakljucka protivResenjaZakljucka;
    @XmlElement(namespace = "http://www.zalbanaodlukucir", required = true)
    protected Tsadrzaj sadrzaj;
    @XmlElement(name = "podaci_o_podnosiocu_zalbe", namespace = "http://www.zalbanaodlukucir", required = true)
    protected TpodaciOPodnosiocu podaciOPodnosiocuZalbe;
    @XmlElement(namespace = "http://www.zalbanaodlukucir")
    protected Tnapomena napomena;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "mesto")
    protected String mesto;
    @XmlAttribute(name = "datum")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the zahtev property.
     * 
     * @return
     *     possible object is
     *     {@link Tzalba.Zahtev }
     *     
     */
    public Tzalba.Zahtev getZahtev() {
        return zahtev;
    }

    /**
     * Sets the value of the zahtev property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tzalba.Zahtev }
     *     
     */
    public void setZahtev(Tzalba.Zahtev value) {
        this.zahtev = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Tzalba.Status }
     *     
     */
    public Tzalba.Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tzalba.Status }
     *     
     */
    public void setStatus(Tzalba.Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the naslov property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaslov() {
        return naslov;
    }

    /**
     * Sets the value of the naslov property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaslov(String value) {
        this.naslov = value;
    }

    /**
     * Gets the value of the podaciPovereniku property.
     * 
     * @return
     *     possible object is
     *     {@link TpodaciPovereniku }
     *     
     */
    public TpodaciPovereniku getPodaciPovereniku() {
        return podaciPovereniku;
    }

    /**
     * Sets the value of the podaciPovereniku property.
     * 
     * @param value
     *     allowed object is
     *     {@link TpodaciPovereniku }
     *     
     */
    public void setPodaciPovereniku(TpodaciPovereniku value) {
        this.podaciPovereniku = value;
    }

    /**
     * Gets the value of the podnaslov property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodnaslov() {
        return podnaslov;
    }

    /**
     * Sets the value of the podnaslov property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodnaslov(String value) {
        this.podnaslov = value;
    }

    /**
     * Gets the value of the zalilac property.
     * 
     * @return
     *     possible object is
     *     {@link Tzalilac }
     *     
     */
    public Tzalilac getZalilac() {
        return zalilac;
    }

    /**
     * Sets the value of the zalilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tzalilac }
     *     
     */
    public void setZalilac(Tzalilac value) {
        this.zalilac = value;
    }

    /**
     * Gets the value of the protivResenjaZakljucka property.
     * 
     * @return
     *     possible object is
     *     {@link TprotivResenjaZakljucka }
     *     
     */
    public TprotivResenjaZakljucka getProtivResenjaZakljucka() {
        return protivResenjaZakljucka;
    }

    /**
     * Sets the value of the protivResenjaZakljucka property.
     * 
     * @param value
     *     allowed object is
     *     {@link TprotivResenjaZakljucka }
     *     
     */
    public void setProtivResenjaZakljucka(TprotivResenjaZakljucka value) {
        this.protivResenjaZakljucka = value;
    }

    /**
     * Gets the value of the sadrzaj property.
     * 
     * @return
     *     possible object is
     *     {@link Tsadrzaj }
     *     
     */
    public Tsadrzaj getSadrzaj() {
        return sadrzaj;
    }

    /**
     * Sets the value of the sadrzaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tsadrzaj }
     *     
     */
    public void setSadrzaj(Tsadrzaj value) {
        this.sadrzaj = value;
    }

    /**
     * Gets the value of the podaciOPodnosiocuZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link TpodaciOPodnosiocu }
     *     
     */
    public TpodaciOPodnosiocu getPodaciOPodnosiocuZalbe() {
        return podaciOPodnosiocuZalbe;
    }

    /**
     * Sets the value of the podaciOPodnosiocuZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link TpodaciOPodnosiocu }
     *     
     */
    public void setPodaciOPodnosiocuZalbe(TpodaciOPodnosiocu value) {
        this.podaciOPodnosiocuZalbe = value;
    }

    /**
     * Gets the value of the napomena property.
     * 
     * @return
     *     possible object is
     *     {@link Tnapomena }
     *     
     */
    public Tnapomena getNapomena() {
        return napomena;
    }

    /**
     * Sets the value of the napomena property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tnapomena }
     *     
     */
    public void setNapomena(Tnapomena value) {
        this.napomena = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the mesto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMesto() {
        return mesto;
    }

    /**
     * Sets the value of the mesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMesto(String value) {
        this.mesto = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;anyAttribute processContents='lax'/>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Status {

        @XmlValue
        protected String value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         * 
         * <p>
         * the map is keyed by the name of the attribute and 
         * the value is the string value of the attribute.
         * 
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         * 
         * 
         * @return
         *     always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;anyAttribute processContents='lax'/>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Zahtev {

        @XmlValue
        protected String value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         * 
         * <p>
         * the map is keyed by the name of the attribute and 
         * the value is the string value of the attribute.
         * 
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         * 
         * 
         * @return
         *     always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }

}
