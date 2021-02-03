package com.project.organ_vlasti.model.izvestaji.client;

import com.project.organ_vlasti.model.izvestaji.Tbody;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "response"
})
@XmlRootElement(name = "getPodaciResponse", namespace = "http://izvestaji")
public class getPodaciResponse {

    @XmlElement(name = "response", required = true)
    protected Tbody response;

    /**
     * Gets the value of the student property.
     *
     * @return
     *     possible object is
     *     {@link Tbody }
     *
     */
    public Tbody getResponse() {
        return response;
    }

    /**
     * Sets the value of the student property.
     *
     * @param value
     *     allowed object is
     *     {@link Tbody }
     *
     */
    public void setResponse(Tbody value) {
        this.response = value;
    }
}
