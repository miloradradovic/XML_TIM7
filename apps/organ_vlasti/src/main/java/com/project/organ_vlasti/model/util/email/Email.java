
package com.project.organ_vlasti.model.util.email;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="body" type="{http://email}Tbody"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "body"
})
@XmlRootElement(name = "email")
public class Email {

    @XmlElement(required = true)
    protected Tbody body;

    /**
     * Gets the value of the body property.
     *
     * @return possible object is
     * {@link Tbody }
     */
    public Tbody getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     *
     * @param value allowed object is
     *              {@link Tbody }
     */
    public void setBody(Tbody value) {
        this.body = value;
    }

}
