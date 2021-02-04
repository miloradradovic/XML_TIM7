
package com.project.organ_vlasti.model.zalba_cutanje;

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
 *         &lt;element name="zalba_cutanje_body" type="{http://www.zalbacutanje}Tzalba"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "zalbaCutanjeBody"
})
@XmlRootElement(name = "zalba_cutanje", namespace = "http://www.zalbacutanje")
public class ZalbaCutanje {

    @XmlElement(name = "zalba_cutanje_body", namespace = "http://www.zalbacutanje", required = true)
    protected Tzalba zalbaCutanjeBody;

    /**
     * Gets the value of the zalbaCutanjeBody property.
     * 
     * @return
     *     possible object is
     *     {@link Tzalba }
     *     
     */
    public Tzalba getZalbaCutanjeBody() {
        return zalbaCutanjeBody;
    }

    /**
     * Sets the value of the zalbaCutanjeBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tzalba }
     *     
     */
    public void setZalbaCutanjeBody(Tzalba value) {
        this.zalbaCutanjeBody = value;
    }

}
