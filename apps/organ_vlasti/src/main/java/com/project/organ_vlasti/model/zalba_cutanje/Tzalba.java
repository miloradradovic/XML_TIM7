
package com.project.organ_vlasti.model.zalba_cutanje;

import com.project.organ_vlasti.model.util.ComplexTypes.TpodaciOPodnosiocu;
import com.project.organ_vlasti.model.util.ComplexTypes.TpodaciPovereniku;
import com.project.organ_vlasti.model.util.ComplexTypes.TsadrzajZalbe;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;


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
 *         &lt;element name="naziv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="podaci_o_primaocu" type="{http://www.reusability}Tpodaci_povereniku" minOccurs="0"/>
 *         &lt;element name="sadrzaj_zalbe" type="{http://www.reusability}Tsadrzaj_zalbe"/>
 *         &lt;element name="podaci_o_podnosiocu" type="{http://www.reusability}Tpodaci_o_podnosiocu"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mjesto" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tzalba", namespace = "http://www.zalbacutanje", propOrder = {
        "zahtev",
        "status",
        "naziv",
        "podaciOPrimaocu",
        "sadrzajZalbe",
        "podaciOPodnosiocu"
})
public class Tzalba {

    @XmlElement(namespace = "http://www.zalbacutanje", required = true)
    protected Zahtev zahtev;
    @XmlElement(namespace = "http://www.zalbacutanje")
    protected Status status;
    @XmlElement(namespace = "http://www.zalbacutanje")
    protected String naziv;
    @XmlElement(name = "podaci_o_primaocu", namespace = "http://www.zalbacutanje")
    protected TpodaciPovereniku podaciOPrimaocu;
    @XmlElement(name = "sadrzaj_zalbe", namespace = "http://www.zalbacutanje", required = true)
    protected TsadrzajZalbe sadrzajZalbe;
    @XmlElement(name = "podaci_o_podnosiocu", namespace = "http://www.zalbacutanje", required = true)
    protected TpodaciOPodnosiocu podaciOPodnosiocu;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "mjesto")
    protected String mjesto;
    @XmlAttribute(name = "datum")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the zahtev property.
     *
     * @return possible object is
     * {@link Zahtev }
     */
    public Zahtev getZahtev() {
        return zahtev;
    }

    /**
     * Sets the value of the zahtev property.
     *
     * @param value allowed object is
     *              {@link Zahtev }
     */
    public void setZahtev(Zahtev value) {
        this.zahtev = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is
     * {@link Status }
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is
     *              {@link Status }
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the naziv property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNaziv(String value) {
        this.naziv = value;
    }

    /**
     * Gets the value of the podaciOPrimaocu property.
     *
     * @return possible object is
     * {@link TpodaciPovereniku }
     */
    public TpodaciPovereniku getPodaciOPrimaocu() {
        return podaciOPrimaocu;
    }

    /**
     * Sets the value of the podaciOPrimaocu property.
     *
     * @param value allowed object is
     *              {@link TpodaciPovereniku }
     */
    public void setPodaciOPrimaocu(TpodaciPovereniku value) {
        this.podaciOPrimaocu = value;
    }

    /**
     * Gets the value of the sadrzajZalbe property.
     *
     * @return possible object is
     * {@link TsadrzajZalbe }
     */
    public TsadrzajZalbe getSadrzajZalbe() {
        return sadrzajZalbe;
    }

    /**
     * Sets the value of the sadrzajZalbe property.
     *
     * @param value allowed object is
     *              {@link TsadrzajZalbe }
     */
    public void setSadrzajZalbe(TsadrzajZalbe value) {
        this.sadrzajZalbe = value;
    }

    /**
     * Gets the value of the podaciOPodnosiocu property.
     *
     * @return possible object is
     * {@link TpodaciOPodnosiocu }
     */
    public TpodaciOPodnosiocu getPodaciOPodnosiocu() {
        return podaciOPodnosiocu;
    }

    /**
     * Sets the value of the podaciOPodnosiocu property.
     *
     * @param value allowed object is
     *              {@link TpodaciOPodnosiocu }
     */
    public void setPodaciOPodnosiocu(TpodaciOPodnosiocu value) {
        this.podaciOPodnosiocu = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the mjesto property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMjesto() {
        return mjesto;
    }

    /**
     * Sets the value of the mjesto property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMjesto(String value) {
        this.mjesto = value;
    }

    /**
     * Gets the value of the datum property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
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
     * <p>
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     *
     * @return always non-null
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
         * @return possible object is
         * {@link String }
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link String }
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
         * <p>
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         * @return always non-null
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
         * @return possible object is
         * {@link String }
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link String }
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
         * <p>
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         * @return always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }

}
