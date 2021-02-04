
package com.project.organ_vlasti.model.util.ComplexTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Tpodaci_povereniku complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Tpodaci_povereniku"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="uloga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="adresa" type="{http://www.reusability}Tadresa" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tpodaci_povereniku", propOrder = {
        "uloga",
        "adresa"
})
public class TpodaciPovereniku {

    protected String uloga;
    protected Tadresa adresa;

    /**
     * Gets the value of the uloga property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getUloga() {
        return uloga;
    }

    /**
     * Sets the value of the uloga property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUloga(String value) {
        this.uloga = value;
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
