
package com.project.organ_vlasti.model.obavestenje;

import com.project.organ_vlasti.model.util.ComplexTypes.TinformacijeOPodnosiocu;
import com.project.organ_vlasti.model.util.ComplexTypes.Topcije;
import com.project.organ_vlasti.model.util.ComplexTypes.TtekstZahtevaObavestenja;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


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
 *         &lt;element name="naziv_organa">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="sediste" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;anyAttribute processContents='lax'/>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="informacije_o_podnosiocu" type="{http://www.reusability}Tinformacije_o_podnosiocu"/>
 *         &lt;element name="naslov" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="podnaslov" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tekst_zahteva" type="{http://www.reusability}Ttekst_zahteva_obavestenja"/>
 *         &lt;element name="opcija_dostave" type="{http://www.reusability}Topcije"/>
 *       &lt;/sequence>
 *       &lt;attribute name="br_predmeta" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nazivOrgana",
    "informacijeOPodnosiocu",
    "naslov",
    "podnaslov",
    "tekstZahteva",
    "opcijaDostave"
})
@XmlRootElement(name = "obavestenje", namespace = "http://www.obavestenje")
public class Obavestenje {

    @XmlElement(name = "naziv_organa", namespace = "http://www.obavestenje", required = true)
    protected Obavestenje.NazivOrgana nazivOrgana;
    @XmlElement(name = "informacije_o_podnosiocu", namespace = "http://www.obavestenje", required = true)
    protected TinformacijeOPodnosiocu informacijeOPodnosiocu;
    @XmlElement(namespace = "http://www.obavestenje", required = false)
    protected String naslov;
    @XmlElement(namespace = "http://www.obavestenje", required = false)
    protected String podnaslov;
    @XmlElement(name = "tekst_zahteva", namespace = "http://www.obavestenje", required = true)
    protected TtekstZahtevaObavestenja tekstZahteva;
    @XmlElement(name = "opcija_dostave", namespace = "http://www.obavestenje", required = true)
    protected Topcije opcijaDostave;
    @XmlAttribute(name = "datum")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "idZahteva")
    protected String idZahteva;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the nazivOrgana property.
     * 
     * @return
     *     possible object is
     *     {@link Obavestenje.NazivOrgana }
     *     
     */
    public Obavestenje.NazivOrgana getNazivOrgana() {
        return nazivOrgana;
    }

    /**
     * Sets the value of the nazivOrgana property.
     * 
     * @param value
     *     allowed object is
     *     {@link Obavestenje.NazivOrgana }
     *     
     */
    public void setNazivOrgana(Obavestenje.NazivOrgana value) {
        this.nazivOrgana = value;
    }

    /**
     * Gets the value of the informacijeOPodnosiocu property.
     * 
     * @return
     *     possible object is
     *     {@link TinformacijeOPodnosiocu }
     *     
     */
    public TinformacijeOPodnosiocu getInformacijeOPodnosiocu() {
        return informacijeOPodnosiocu;
    }

    /**
     * Sets the value of the informacijeOPodnosiocu property.
     * 
     * @param value
     *     allowed object is
     *     {@link TinformacijeOPodnosiocu }
     *     
     */
    public void setInformacijeOPodnosiocu(TinformacijeOPodnosiocu value) {
        this.informacijeOPodnosiocu = value;
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
     * Gets the value of the tekstZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link TtekstZahtevaObavestenja }
     *     
     */
    public TtekstZahtevaObavestenja getTekstZahteva() {
        return tekstZahteva;
    }

    /**
     * Sets the value of the tekstZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link TtekstZahtevaObavestenja }
     *     
     */
    public void setTekstZahteva(TtekstZahtevaObavestenja value) {
        this.tekstZahteva = value;
    }

    /**
     * Gets the value of the opcijaDostave property.
     * 
     * @return
     *     possible object is
     *     {@link Topcije }
     *     
     */
    public Topcije getOpcijaDostave() {
        return opcijaDostave;
    }

    /**
     * Sets the value of the opcijaDostave property.
     * 
     * @param value
     *     allowed object is
     *     {@link Topcije }
     *     
     */
    public void setOpcijaDostave(Topcije value) {
        this.opcijaDostave = value;
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
     * Gets the value of the idZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdzahteva() {
        return idZahteva;
    }

    /**
     * Sets the value of the idZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdZahteva(String value) {
        this.idZahteva = value;
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
     *       &lt;attribute name="sediste" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    public static class NazivOrgana {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "sediste")
        protected String sediste;
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
         * Gets the value of the sediste property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSediste() {
            return sediste;
        }

        /**
         * Sets the value of the sediste property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSediste(String value) {
            this.sediste = value;
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
