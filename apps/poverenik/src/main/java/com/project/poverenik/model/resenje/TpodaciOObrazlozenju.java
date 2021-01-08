
package com.project.poverenik.model.resenje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Tpodaci_o_obrazlozenju complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tpodaci_o_obrazlozenju">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="naslov" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="predmet_zalbe" type="{http://resenje}Tpredmet_zalbe"/>
 *         &lt;element name="postupak_poverenika" type="{http://resenje}Tppostupak_poverenika"/>
 *         &lt;element name="odluka" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpodaci_o_obrazlozenju", namespace = "http://resenje", propOrder = {
    "naslov",
    "predmetZalbe",
    "postupakPoverenika",
    "odluka"
})
public class TpodaciOObrazlozenju {

    @XmlElement(namespace = "http://resenje", required = true)
    protected String naslov;
    @XmlElement(name = "predmet_zalbe", namespace = "http://resenje", required = true)
    protected TpredmetZalbe predmetZalbe;
    @XmlElement(name = "postupak_poverenika", namespace = "http://resenje", required = true)
    protected TppostupakPoverenika postupakPoverenika;
    @XmlElement(namespace = "http://resenje", required = true)
    protected Object odluka;

    /**
     * Gets the value of the naslov property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaslov() {
        return naslov;
    }

    /**
     * Sets the value of the naslov property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaslov(String value) {
        this.naslov = value;
    }

    /**
     * Gets the value of the predmetZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link TpredmetZalbe }
     *     
     */
    public TpredmetZalbe getPredmetZalbe() {
        return predmetZalbe;
    }

    /**
     * Sets the value of the predmetZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link TpredmetZalbe }
     *     
     */
    public void setPredmetZalbe(TpredmetZalbe value) {
        this.predmetZalbe = value;
    }

    /**
     * Gets the value of the postupakPoverenika property.
     * 
     * @return
     *     possible object is
     *     {@link TppostupakPoverenika }
     *     
     */
    public TppostupakPoverenika getPostupakPoverenika() {
        return postupakPoverenika;
    }

    /**
     * Sets the value of the postupakPoverenika property.
     * 
     * @param value
     *     allowed object is
     *     {@link TppostupakPoverenika }
     *     
     */
    public void setPostupakPoverenika(TppostupakPoverenika value) {
        this.postupakPoverenika = value;
    }

    /**
     * Gets the value of the odluka property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getOdluka() {
        return odluka;
    }

    /**
     * Sets the value of the odluka property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setOdluka(Object value) {
        this.odluka = value;
    }

}
