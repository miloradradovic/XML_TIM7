
package com.project.organ_vlasti.model.util.ComplexTypes;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.namespace.QName;


/**
 * <p>Java class for Tprotiv_resenja_zakljucka complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tprotiv_resenja_zakljucka">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="naziv_organa_koji_je_doneo_odluku">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;anyAttribute processContents='lax'/>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="broj">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;anyAttribute processContents='lax'/>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="od_godine">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[0-9]{4}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tprotiv_resenja_zakljucka", namespace = "http://www.reusability", propOrder = {
    "nazivOrganaKojiJeDoneoOdluku",
    "broj",
    "odGodine"
})
public class TprotivResenjaZakljucka {

    @XmlElement(name = "naziv_organa_koji_je_doneo_odluku", namespace = "http://www.reusability", required = true)
    protected TprotivResenjaZakljucka.NazivOrganaKojiJeDoneoOdluku nazivOrganaKojiJeDoneoOdluku;
    @XmlElement(namespace = "http://www.reusability", required = true)
    protected TprotivResenjaZakljucka.Broj broj;
    @XmlElement(name = "od_godine", namespace = "http://www.reusability", required = true)
    protected String odGodine;

    /**
     * Gets the value of the nazivOrganaKojiJeDoneoOdluku property.
     * 
     * @return
     *     possible object is
     *     {@link TprotivResenjaZakljucka.NazivOrganaKojiJeDoneoOdluku }
     *     
     */
    public TprotivResenjaZakljucka.NazivOrganaKojiJeDoneoOdluku getNazivOrganaKojiJeDoneoOdluku() {
        return nazivOrganaKojiJeDoneoOdluku;
    }

    /**
     * Sets the value of the nazivOrganaKojiJeDoneoOdluku property.
     * 
     * @param value
     *     allowed object is
     *     {@link TprotivResenjaZakljucka.NazivOrganaKojiJeDoneoOdluku }
     *     
     */
    public void setNazivOrganaKojiJeDoneoOdluku(TprotivResenjaZakljucka.NazivOrganaKojiJeDoneoOdluku value) {
        this.nazivOrganaKojiJeDoneoOdluku = value;
    }

    /**
     * Gets the value of the broj property.
     * 
     * @return
     *     possible object is
     *     {@link TprotivResenjaZakljucka.Broj }
     *     
     */
    public TprotivResenjaZakljucka.Broj getBroj() {
        return broj;
    }

    /**
     * Sets the value of the broj property.
     * 
     * @param value
     *     allowed object is
     *     {@link TprotivResenjaZakljucka.Broj }
     *     
     */
    public void setBroj(TprotivResenjaZakljucka.Broj value) {
        this.broj = value;
    }

    /**
     * Gets the value of the odGodine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdGodine() {
        return odGodine;
    }

    /**
     * Sets the value of the odGodine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdGodine(String value) {
        this.odGodine = value;
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
    public static class Broj {

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
    public static class NazivOrganaKojiJeDoneoOdluku {

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
