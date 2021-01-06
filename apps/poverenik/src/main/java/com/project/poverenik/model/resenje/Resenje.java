
package com.project.poverenik.model.resenje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="uvodne_informacije" type="{http://resenje}Tuvodne_informacije"/>
 *         &lt;element name="podaci_o_resenju" type="{http://resenje}Tpodaci_o_resenju"/>
 *         &lt;element name="podaci_o_obrazlozenju" type="{http://resenje}Tpodaci_o_obrazlozenju"/>
 *         &lt;element name="poverenik" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tip_resenja" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="broj">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="[0-9]{3}\-[0-9]{2}\-[0-9]{4}\/[0-9]{4}\-[0-9]{2}"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "uvodneInformacije",
    "podaciOResenju",
    "podaciOObrazlozenju",
    "poverenik"
})
@XmlRootElement(name = "resenje")
public class Resenje {

    @XmlElement(name = "uvodne_informacije", required = true)
    protected TuvodneInformacije uvodneInformacije;
    @XmlElement(name = "podaci_o_resenju", required = true)
    protected TpodaciOResenju podaciOResenju;
    @XmlElement(name = "podaci_o_obrazlozenju", required = true)
    protected TpodaciOObrazlozenju podaciOObrazlozenju;
    @XmlElement(required = true)
    protected String poverenik;
    @XmlAttribute(name = "datum")
    protected String datum;
    @XmlAttribute(name = "tip_resenja")
    protected String tipResenja;
    @XmlAttribute(name = "broj")
    protected String broj;

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
     *     {@link String }
     *     
     */
    public String getPoverenik() {
        return poverenik;
    }

    /**
     * Sets the value of the poverenik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoverenik(String value) {
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
     * Gets the value of the tipResenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipResenja() {
        return tipResenja;
    }

    /**
     * Sets the value of the tipResenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipResenja(String value) {
        this.tipResenja = value;
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

}
