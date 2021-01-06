
package com.project.poverenik.model.util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Tciljani_organ_vlasti complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tciljani_organ_vlasti">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="naziv_organa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sediste_organa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tciljani_organ_vlasti", namespace = "http://www.reusability", propOrder = {
    "nazivOrgana",
    "sedisteOrgana"
})
public class TciljaniOrganVlasti {

    @XmlElement(name = "naziv_organa", required = true)
    protected String nazivOrgana;
    @XmlElement(name = "sediste_organa", required = true)
    protected String sedisteOrgana;

    /**
     * Gets the value of the nazivOrgana property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivOrgana() {
        return nazivOrgana;
    }

    /**
     * Sets the value of the nazivOrgana property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivOrgana(String value) {
        this.nazivOrgana = value;
    }

    /**
     * Gets the value of the sedisteOrgana property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSedisteOrgana() {
        return sedisteOrgana;
    }

    /**
     * Sets the value of the sedisteOrgana property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSedisteOrgana(String value) {
        this.sedisteOrgana = value;
    }

}
