
package com.project.organ_vlasti.model.util.ComplexTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Tinformacije_o_podnosiocu complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Tinformacije_o_podnosiocu"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lice" type="{http://www.reusability}Ttip_lica"/&gt;
 *         &lt;element name="adresa" type="{http://www.reusability}Tadresa"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tinformacije_o_podnosiocu", propOrder = {
        "lice",
        "adresa"
})
public class TinformacijeOPodnosiocu {

    @XmlElement(required = true)
    protected TtipLica lice;
    @XmlElement(required = true)
    protected Tadresa adresa;

    /**
     * Gets the value of the lice property.
     *
     * @return possible object is
     * {@link TtipLica }
     */
    public TtipLica getLice() {
        return lice;
    }

    /**
     * Sets the value of the lice property.
     *
     * @param value allowed object is
     *              {@link TtipLica }
     */
    public void setLice(TtipLica value) {
        this.lice = value;
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

}
