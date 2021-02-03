package com.project.poverenik.model.izvestaj.database.client;

import com.project.poverenik.model.util.parametars.RefIdList;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "refs"
})
@XmlRootElement(name = "getRefsResponse", namespace = "http://izvestaji")
public class getRefsResponse {

    @XmlElement(name = "refs", required = true)
    protected RefIdList refs;

    /**
     * Gets the value of the student property.
     *
     * @return
     *     possible object is
     *     {@link RefIdList }
     *
     */
    public RefIdList getResponse() {
        return refs;
    }

    /**
     * Sets the value of the student property.
     *
     * @param value
     *     allowed object is
     *     {@link RefIdList }
     *
     */
    public void setResponse(RefIdList  value) {
        this.refs = value;
    }
}
