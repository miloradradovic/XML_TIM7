
package com.project.organ_vlasti.model.zahtev;

import com.project.organ_vlasti.model.util.ComplexTypes.TciljaniOrganVlasti;
import com.project.organ_vlasti.model.util.ComplexTypes.Tfusnote;
import com.project.organ_vlasti.model.util.ComplexTypes.TinformacijeOTraziocu;
import com.project.organ_vlasti.model.util.ComplexTypes.TtekstZahtevaZahtevcir;

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
 * <p>Java class for Tzahtev complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tzahtev">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mesto">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;anyAttribute processContents='lax'/>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ciljani_organ_vlasti" type="{http://www.reusability}Tciljani_organ_vlasti"/>
 *         &lt;element name="naziv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tekst_zahteva" type="{http://www.reusability}Ttekst_zahteva_zahtevcir"/>
 *         &lt;element name="informacije_o_traziocu" type="{http://www.reusability}Tinformacije_o_traziocu"/>
 *         &lt;element name="fusnote" type="{http://www.reusability}Tfusnote" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "Tzahtev", namespace = "http://www.zahtevcir", propOrder = {
    "mesto",
    "ciljaniOrganVlasti",
    "naziv",
    "tekstZahteva",
    "informacijeOTraziocu",
    "fusnote"
})
public class Tzahtev {

    @XmlElement(namespace = "http://www.zahtevcir", required = true)
    protected Tzahtev.Mesto mesto;
    @XmlElement(name = "ciljani_organ_vlasti", namespace = "http://www.zahtevcir", required = true)
    protected TciljaniOrganVlasti ciljaniOrganVlasti;
    @XmlElement(namespace = "http://www.zahtevcir")
    protected String naziv;
    @XmlElement(name = "tekst_zahteva", namespace = "http://www.zahtevcir", required = true)
    protected TtekstZahtevaZahtevcir tekstZahteva;
    @XmlElement(name = "informacije_o_traziocu", namespace = "http://www.zahtevcir", required = true)
    protected TinformacijeOTraziocu informacijeOTraziocu;
    @XmlElement(namespace = "http://www.zahtevcir")
    protected Tfusnote fusnote;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "datum")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the mesto property.
     * 
     * @return
     *     possible object is
     *     {@link Tzahtev.Mesto }
     *     
     */
    public Tzahtev.Mesto getMesto() {
        return mesto;
    }

    /**
     * Sets the value of the mesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tzahtev.Mesto }
     *     
     */
    public void setMesto(Tzahtev.Mesto value) {
        this.mesto = value;
    }

    /**
     * Gets the value of the ciljaniOrganVlasti property.
     * 
     * @return
     *     possible object is
     *     {@link TciljaniOrganVlasti }
     *     
     */
    public TciljaniOrganVlasti getCiljaniOrganVlasti() {
        return ciljaniOrganVlasti;
    }

    /**
     * Sets the value of the ciljaniOrganVlasti property.
     * 
     * @param value
     *     allowed object is
     *     {@link TciljaniOrganVlasti }
     *     
     */
    public void setCiljaniOrganVlasti(TciljaniOrganVlasti value) {
        this.ciljaniOrganVlasti = value;
    }

    /**
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaziv(String value) {
        this.naziv = value;
    }

    /**
     * Gets the value of the tekstZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link TtekstZahtevaZahtevcir }
     *     
     */
    public TtekstZahtevaZahtevcir getTekstZahteva() {
        return tekstZahteva;
    }

    /**
     * Sets the value of the tekstZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link TtekstZahtevaZahtevcir }
     *     
     */
    public void setTekstZahteva(TtekstZahtevaZahtevcir value) {
        this.tekstZahteva = value;
    }

    /**
     * Gets the value of the informacijeOTraziocu property.
     * 
     * @return
     *     possible object is
     *     {@link TinformacijeOTraziocu }
     *     
     */
    public TinformacijeOTraziocu getInformacijeOTraziocu() {
        return informacijeOTraziocu;
    }

    /**
     * Sets the value of the informacijeOTraziocu property.
     * 
     * @param value
     *     allowed object is
     *     {@link TinformacijeOTraziocu }
     *     
     */
    public void setInformacijeOTraziocu(TinformacijeOTraziocu value) {
        this.informacijeOTraziocu = value;
    }

    /**
     * Gets the value of the fusnote property.
     * 
     * @return
     *     possible object is
     *     {@link Tfusnote }
     *     
     */
    public Tfusnote getFusnote() {
        return fusnote;
    }

    /**
     * Sets the value of the fusnote property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tfusnote }
     *     
     */
    public void setFusnote(Tfusnote value) {
        this.fusnote = value;
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
    public static class Mesto {

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
