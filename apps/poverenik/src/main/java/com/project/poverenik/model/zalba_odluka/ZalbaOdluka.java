
package com.project.poverenik.model.zalba_odluka;

import com.project.poverenik.model.util.ComplexTypes.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="naslov" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="podaci_povereniku" type="{http://www.reusability}Tpodaci_povereniku"/>
 *         &lt;element name="podnaslov" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="zalilac" type="{http://www.reusability}Tzalilac"/>
 *         &lt;element name="protiv_resenja_zakljucka" type="{http://www.reusability}Tprotiv_resenja_zakljucka"/>
 *         &lt;element name="sadrzaj" type="{http://www.reusability}Tsadrzaj"/>
 *         &lt;element name="podaci_o_podnosiocu_zalbe" type="{http://www.reusability}Tpodaci_o_podnosiocu"/>
 *         &lt;element name="napomena" type="{http://www.reusability}Tnapomena"/>
 *       &lt;/sequence>
 *       &lt;attribute name="mesto" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "naslov",
    "podaciPovereniku",
    "podnaslov",
    "zalilac",
    "protivResenjaZakljucka",
    "sadrzaj",
    "podaciOPodnosiocuZalbe",
    "napomena"
})
@XmlRootElement(name = "zalba_odluka", namespace = "http://www.zalbanaodlukucir")
public class ZalbaOdluka {

    @XmlElement(required = true)
    protected String naslov;
    @XmlElement(name = "podaci_povereniku", required = true)
    protected TpodaciPovereniku podaciPovereniku;
    @XmlElement(required = true)
    protected String podnaslov;
    @XmlElement(required = true)
    protected Tzalilac zalilac;
    @XmlElement(name = "protiv_resenja_zakljucka", required = true)
    protected TprotivResenjaZakljucka protivResenjaZakljucka;
    @XmlElement(required = true)
    protected Tsadrzaj sadrzaj;
    @XmlElement(name = "podaci_o_podnosiocu_zalbe", required = true)
    protected TpodaciOPodnosiocu podaciOPodnosiocuZalbe;
    @XmlElement(required = true)
    protected Tnapomena napomena;
    @XmlAttribute(name = "mesto")
    protected String mesto;
    @XmlAttribute(name = "datum")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;

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

}
