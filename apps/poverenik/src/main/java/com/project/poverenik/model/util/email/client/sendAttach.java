package com.project.poverenik.model.util.email.client;

import com.project.poverenik.model.util.email.Tbody;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "email"
})
@XmlRootElement(name = "sendAttach", namespace="http://email")
public class sendAttach {
    @XmlElement(name = "email", required = true)
    protected Tbody email;

    /**
     * Gets the value of the student property.
     *
     * @return
     *     possible object is
     *     {@link Tbody }
     *
     */
    public Tbody getEmail() {
        return email;
    }

    /**
     * Sets the value of the student property.
     *
     * @param value
     *     allowed object is
     *     {@link Tbody }
     *
     */
    public void setEmail(Tbody value) {
        this.email = value;
    }
}
