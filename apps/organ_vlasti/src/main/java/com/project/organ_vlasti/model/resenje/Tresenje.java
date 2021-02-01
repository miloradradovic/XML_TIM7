
package com.project.organ_vlasti.model.resenje;

import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>Java class for Tresenje complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tresenje">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tip_resenja">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;anyAttribute processContents='lax'/>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="uvodne_informacije" type="{http://resenje}Tuvodne_informacije"/>
 *         &lt;element name="podaci_o_resenju" type="{http://resenje}Tpodaci_o_resenju"/>
 *         &lt;element name="podaci_o_obrazlozenju" type="{http://resenje}Tpodaci_o_obrazlozenju"/>
 *         &lt;element name="poverenik">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *                 &lt;anyAttribute processContents='lax'/>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="broj">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="[0-9]{3}\-[0-9]{2}\-[0-9]{4}\-[0-9]{4}\-[0-9]{2}"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
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
@XmlType(name = "Tresenje", namespace = "http://resenje", propOrder = {
    "tipResenja",
    "uvodneInformacije",
    "podaciOResenju",
    "podaciOObrazlozenju",
    "poverenik"
})
public class Tresenje {

    @XmlElement(name = "tip_resenja", namespace = "http://resenje", required = true)
    protected TipResenja tipResenja;
    @XmlElement(name = "uvodne_informacije", namespace = "http://resenje", required = true)
    protected TuvodneInformacije uvodneInformacije;
    @XmlElement(name = "podaci_o_resenju", namespace = "http://resenje", required = true)
    protected TpodaciOResenju podaciOResenju;
    @XmlElement(name = "podaci_o_obrazlozenju", namespace = "http://resenje", required = true)
    protected TpodaciOObrazlozenju podaciOObrazlozenju;
    @XmlElement(namespace = "http://resenje", required = true)
    protected Poverenik poverenik;
    @XmlAttribute(name = "datum")
    protected String datum;
    @XmlAttribute(name = "broj")
    protected String broj;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the tipResenja property.
     *
     * @return
     *     possible object is
     *     {@link TipResenja }
     *
     */
    public TipResenja getTipResenja() {
        return tipResenja;
    }

    /**
     * Sets the value of the tipResenja property.
     *
     * @param value
     *     allowed object is
     *     {@link TipResenja }
     *
     */
    public void setTipResenja(TipResenja value) {
        this.tipResenja = value;
    }

    /**
     * Gets the value of the uvodneInformacije property.
     *
     * @return
     *     possible object is
     *     {@link TuvodneInformacije }
     *
     */
    public TuvodneInformacije getUvodneInformacije() {
        return uvodneInformacije;
    }

    /**
     * Sets the value of the uvodneInformacije property.
     *
     * @param value
     *     allowed object is
     *     {@link TuvodneInformacije }
     *
     */
    public void setUvodneInformacije(TuvodneInformacije value) {
        this.uvodneInformacije = value;
    }

    /**
     * Gets the value of the podaciOResenju property.
     *
     * @return
     *     possible object is
     *     {@link TpodaciOResenju }
     *
     */
    public TpodaciOResenju getPodaciOResenju() {
        return podaciOResenju;
    }

    /**
     * Sets the value of the podaciOResenju property.
     *
     * @param value
     *     allowed object is
     *     {@link TpodaciOResenju }
     *
     */
    public void setPodaciOResenju(TpodaciOResenju value) {
        this.podaciOResenju = value;
    }

    /**
     * Gets the value of the podaciOObrazlozenju property.
     *
     * @return
     *     possible object is
     *     {@link TpodaciOObrazlozenju }
     *
     */
    public TpodaciOObrazlozenju getPodaciOObrazlozenju() {
        return podaciOObrazlozenju;
    }

    /**
     * Sets the value of the podaciOObrazlozenju property.
     *
     * @param value
     *     allowed object is
     *     {@link TpodaciOObrazlozenju }
     *
     */
    public void setPodaciOObrazlozenju(TpodaciOObrazlozenju value) {
        this.podaciOObrazlozenju = value;
    }

    /**
     * Gets the value of the poverenik property.
     *
     * @return
     *     possible object is
     *     {@link Poverenik }
     *
     */
    public Poverenik getPoverenik() {
        return poverenik;
    }

    /**
     * Sets the value of the poverenik property.
     *
     * @param value
     *     allowed object is
     *     {@link Poverenik }
     *
     */
    public void setPoverenik(Poverenik value) {
        this.poverenik = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatum(String value) {
        this.datum = value;
    }

    /**
     * Gets the value of the broj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBroj() {
        return broj;
    }

    /**
     * Sets the value of the broj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBroj(String value) {
        this.broj = value;
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
     *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
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
    public static class Poverenik {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "id")
        @XmlSchemaType(name = "anySimpleType")
        protected String id;
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
    public static class TipResenja {

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
