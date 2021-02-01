
package com.project.organ_vlasti.model.util.ComplexTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Ttip_lica complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ttip_lica"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="osoba" type="{http://www.reusability}Tosoba"/&gt;
 *         &lt;element name="preduzece" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ttip_lica", propOrder = {
    "osoba",
    "preduzece"
})
public class TtipLica {

    protected Tosoba osoba;
    protected String preduzece;

    /**
     * Gets the value of the osoba property.
     * 
     * @return
     *     possible object is
     *     {@link Tosoba }
     *     
     */
    public Tosoba getOsoba() {
        return osoba;
    }

    /**
     * Sets the value of the osoba property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tosoba }
     *     
     */
    public void setOsoba(Tosoba value) {
        this.osoba = value;
    }

    /**
     * Gets the value of the preduzece property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreduzece() {
        return preduzece;
    }

    /**
     * Sets the value of the preduzece property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreduzece(String value) {
        this.preduzece = value;
    }

}
