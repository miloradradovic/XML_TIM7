
package com.project.organ_vlasti.model.obavestenje;

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
 *         &lt;element name="obavestenje_body" type="{http://www.obavestenje}Tobavestenje"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "obavestenjeBody"
})
@XmlRootElement(name = "obavestenje", namespace = "http://www.obavestenje")
public class Obavestenje {

    @XmlElement(name = "obavestenje_body", namespace = "http://www.obavestenje", required = true)
    protected Tobavestenje obavestenjeBody;

    /**
     * Gets the value of the obavestenjeBody property.
     *
     * @return possible object is
     * {@link Tobavestenje }
     */
    public Tobavestenje getObavestenjeBody() {
        return obavestenjeBody;
    }

    /**
     * Sets the value of the obavestenjeBody property.
     *
     * @param value allowed object is
     *              {@link Tobavestenje }
     */
    public void setObavestenjeBody(Tobavestenje value) {
        this.obavestenjeBody = value;
    }

}
