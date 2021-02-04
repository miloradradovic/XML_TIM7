package com.project.organ_vlasti.model.resenje.client;

import com.project.organ_vlasti.model.resenje.Tresenje;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "resenje"
})
@XmlRootElement(name = "getResenjeByBrojResponse", namespace = "http://resenje")
public class getResenjeByBrojResponse {

    @XmlElement(name = "resenje", required = true)
    protected Tresenje resenje;

    /**
     * Gets the value of the student property.
     *
     * @return possible object is
     * {@link Tresenje }
     */
    public Tresenje getResenje() {
        return resenje;
    }

    /**
     * Sets the value of the student property.
     *
     * @param value allowed object is
     *              {@link Tresenje }
     */
    public void setResenje(Tresenje value) {
        this.resenje = value;
    }
}
