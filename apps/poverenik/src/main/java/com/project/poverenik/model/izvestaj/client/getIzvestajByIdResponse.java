package com.project.poverenik.model.izvestaj.client;

import com.project.poverenik.model.izvestaj.Tbody;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "izvestaj"
})
@XmlRootElement(name = "getIzvestajByIdResponse", namespace = "http://izvestaji")
public class getIzvestajByIdResponse {

    @XmlElement(name = "izvestaj", required = true)
    protected Tbody izvestaj;

    /**
     * Gets the value of the student property.
     *
     * @return
     *     possible object is
     *     {@link Tbody }
     *
     */
    public Tbody getIzvestaj() {
        return izvestaj;
    }

    /**
     * Sets the value of the student property.
     *
     * @param value
     *     allowed object is
     *     {@link Tbody }
     *
     */
    public void setIzvestaj(Tbody value) {
        this.izvestaj = value;
    }
}
