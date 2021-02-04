
package com.project.organ_vlasti.model.zahtev;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="zahtev_body" type="{http://www.zahtevcir}Tzahtev"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "zahtevBody"
})
@XmlRootElement(name = "zahtev", namespace = "http://www.zahtevcir")
public class Zahtev {

    @XmlElement(name = "zahtev_body", namespace = "http://www.zahtevcir", required = true)
    protected Tzahtev zahtevBody;

    /**
     * Gets the value of the zahtevBody property.
     *
     * @return possible object is
     * {@link Tzahtev }
     */
    public Tzahtev getZahtevBody() {
        return zahtevBody;
    }

    /**
     * Sets the value of the zahtevBody property.
     *
     * @param value allowed object is
     *              {@link Tzahtev }
     */
    public void setZahtevBody(Tzahtev value) {
        this.zahtevBody = value;
    }

}
