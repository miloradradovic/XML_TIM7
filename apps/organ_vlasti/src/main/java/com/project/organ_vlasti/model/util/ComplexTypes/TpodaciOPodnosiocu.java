
package com.project.organ_vlasti.model.util.ComplexTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Tpodaci_o_podnosiocu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tpodaci_o_podnosiocu">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="osoba" type="{http://www.reusability}Tosoba"/>
 *         &lt;element name="adresa" type="{http://www.reusability}Tadresa"/>
 *         &lt;element name="drugi_podaci_za_kontakt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpodaci_o_podnosiocu", namespace = "http://www.reusability", propOrder = {
    "osoba",
    "adresa",
    "drugiPodaciZaKontakt"
})
public class TpodaciOPodnosiocu {

    @XmlElement(namespace = "http://www.reusability", required = true)
    protected Tosoba osoba;
    @XmlElement(namespace = "http://www.reusability", required = true)
    protected Tadresa adresa;
    @XmlElement(name = "drugi_podaci_za_kontakt", namespace = "http://www.reusability", required = true)
    protected String drugiPodaciZaKontakt;

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

    /**
     * Gets the value of the drugiPodaciZaKontakt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugiPodaciZaKontakt() {
        return drugiPodaciZaKontakt;
    }

    /**
     * Sets the value of the drugiPodaciZaKontakt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugiPodaciZaKontakt(String value) {
        this.drugiPodaciZaKontakt = value;
    }

}
