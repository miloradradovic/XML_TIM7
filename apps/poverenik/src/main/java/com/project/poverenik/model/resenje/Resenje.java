
package com.project.poverenik.model.resenje;

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
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resenje_body" type="{http://resenje}Tresenje"/>
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
    "resenjeBody"
})
@XmlRootElement(name = "resenje", namespace = "http://resenje")
public class Resenje {

    @XmlElement(name = "resenje_body", namespace = "http://resenje", required = true)
    protected Tresenje resenjeBody;

    /**
     * Gets the value of the resenjeBody property.
     * 
     * @return
     *     possible object is
     *     {@link Tresenje }
     *     
     */
    public Tresenje getResenjeBody() {
        return resenjeBody;
    }

    /**
     * Sets the value of the resenjeBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tresenje }
     *     
     */
    public void setResenjeBody(Tresenje value) {
        this.resenjeBody = value;
    }

}
