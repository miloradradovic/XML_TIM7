package com.project.poverenik.model.izvestaj.database.client;

import com.project.poverenik.model.util.parametars.ParametarMap;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "parametars"
})

@XmlRootElement(name = "getRefs")
public class getRefs {

    @XmlElement(required = true)
    protected ParametarMap parametars;

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link ParametarMap }
     *
     */
    public ParametarMap getParametars() {
        return parametars;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link ParametarMap }
     *
     */
    public void setParametars(ParametarMap value) {
        this.parametars = value;
    }

}
