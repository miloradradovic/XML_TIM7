
package com.project.poverenik.model.izvestaj;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="izvestaj_body" type="{http://izvestaji}Tbody"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "izvestajBody"
})
@XmlRootElement(name = "izvestaj")
public class Izvestaj {

    @XmlElement(name = "izvestaj_body", required = true)
    protected Tbody izvestajBody;

    /**
     * Gets the value of the izvestajBody property.
     * 
     * @return
     *     possible object is
     *     {@link Tbody }
     *     
     */
    public Tbody getIzvestajBody() {
        return izvestajBody;
    }

    /**
     * Sets the value of the izvestajBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tbody }
     *     
     */
    public void setIzvestajBody(Tbody value) {
        this.izvestajBody = value;
    }

}
