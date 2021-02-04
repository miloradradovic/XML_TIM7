
package com.project.organ_vlasti.model.util.ComplexTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Tzalilac complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Tzalilac"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tip_lica" type="{http://www.reusability}Ttip_lica"/&gt;
 *         &lt;element name="adresa" type="{http://www.reusability}Tadresa"/&gt;
 *         &lt;element name="sediste_zalioca" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tzalilac", propOrder = {
        "tipLica",
        "adresa",
        "sedisteZalioca"
})
public class Tzalilac {

    @XmlElement(name = "tip_lica", required = true)
    protected TtipLica tipLica;
    @XmlElement(required = true)
    protected Tadresa adresa;
    @XmlElement(name = "sediste_zalioca", required = true)
    protected String sedisteZalioca;

    /**
     * Gets the value of the tipLica property.
     *
     * @return possible object is
     * {@link TtipLica }
     */
    public TtipLica getTipLica() {
        return tipLica;
    }

    /**
     * Sets the value of the tipLica property.
     *
     * @param value allowed object is
     *              {@link TtipLica }
     */
    public void setTipLica(TtipLica value) {
        this.tipLica = value;
    }

    /**
     * Gets the value of the adresa property.
     *
     * @return possible object is
     * {@link Tadresa }
     */
    public Tadresa getAdresa() {
        return adresa;
    }

    /**
     * Sets the value of the adresa property.
     *
     * @param value allowed object is
     *              {@link Tadresa }
     */
    public void setAdresa(Tadresa value) {
        this.adresa = value;
    }

    /**
     * Gets the value of the sedisteZalioca property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSedisteZalioca() {
        return sedisteZalioca;
    }

    /**
     * Sets the value of the sedisteZalioca property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSedisteZalioca(String value) {
        this.sedisteZalioca = value;
    }

}
