
package com.project.poverenik.model.util.ComplexTypes;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="naziv_organa_koji_je_doneo_odluku" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="broj" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
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

    @XmlElement(name = "naziv_organa_koji_je_doneo_odluku", required = true)
    protected String nazivOrganaKojiJeDoneoOdluku;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger broj;
    @XmlElement(name = "od_godine", required = true)
    protected String odGodine;

    /**
     * Gets the value of the nazivOrganaKojiJeDoneoOdluku property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivOrganaKojiJeDoneoOdluku() {
        return nazivOrganaKojiJeDoneoOdluku;
    }

    /**
     * Sets the value of the nazivOrganaKojiJeDoneoOdluku property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivOrganaKojiJeDoneoOdluku(String value) {
        this.nazivOrganaKojiJeDoneoOdluku = value;
    }

    /**
     * Gets the value of the broj property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBroj() {
        return broj;
    }

    /**
     * Sets the value of the broj property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBroj(BigInteger value) {
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

}
