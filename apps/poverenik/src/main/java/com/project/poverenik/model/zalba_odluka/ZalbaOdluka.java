
package com.project.poverenik.model.zalba_odluka;

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
 *         &lt;element name="zalba_odluka_body" type="{http://www.zalbanaodlukucir}Tzalba"/>
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
    "zalbaOdlukaBody"
})
@XmlRootElement(name = "zalba_odluka", namespace = "http://www.zalbanaodlukucir")
public class ZalbaOdluka {

    @XmlElement(name = "zalba_odluka_body", namespace = "http://www.zalbanaodlukucir", required = true)
    protected Tzalba zalbaOdlukaBody;

    /**
     * Gets the value of the zalbaOdlukaBody property.
     * 
     * @return
     *     possible object is
     *     {@link Tzalba }
     *     
     */
    public Tzalba getZalbaOdlukaBody() {
        return zalbaOdlukaBody;
    }

    /**
     * Sets the value of the zalbaOdlukaBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tzalba }
     *     
     */
    public void setZalbaOdlukaBody(Tzalba value) {
        this.zalbaOdlukaBody = value;
    }

}
