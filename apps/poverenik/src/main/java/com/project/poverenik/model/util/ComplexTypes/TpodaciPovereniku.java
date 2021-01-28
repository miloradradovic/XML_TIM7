
package com.project.poverenik.model.util.ComplexTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Tpodaci_povereniku complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tpodaci_povereniku">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uloga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adresa" type="{http://www.reusability}Tadresa" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpodaci_povereniku", namespace = "http://www.reusability", propOrder = {
    "uloga",
    "adresa"
})
public class TpodaciPovereniku {

    @XmlElement(namespace = "http://www.reusability")
    protected String uloga;
    @XmlElement(namespace = "http://www.reusability")
    protected Tadresa adresa;

    /**
     * Gets the value of the uloga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUloga() {
        return uloga;
    }

    /**
     * Sets the value of the uloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUloga(String value) {
        this.uloga = value;
    }

    /**
     * Gets the value of the adresa property.
     * 
     * @return
     *     possible object is
     *     {@link Tadresa }
     *     
     */
    public Tadresa getAdresa() {
        return adresa;
    }

    /**
     * Sets the value of the adresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tadresa }
     *     
     */
    public void setAdresa(Tadresa value) {
        this.adresa = value;
    }

}
