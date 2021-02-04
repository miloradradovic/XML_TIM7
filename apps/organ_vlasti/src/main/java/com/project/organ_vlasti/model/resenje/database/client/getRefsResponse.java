package com.project.organ_vlasti.model.resenje.database.client;

import com.project.organ_vlasti.model.util.parametars.RefIdList;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "refs"
})
@XmlRootElement(name = "getRefsResponse", namespace = "http://resenje")
public class getRefsResponse {

    @XmlElement(name = "refs", required = true)
    protected RefIdList refs;

    /**
     * Gets the value of the student property.
     *
     * @return possible object is
     * {@link RefIdList }
     */
    public RefIdList getResponse() {
        return refs;
    }

    /**
     * Sets the value of the student property.
     *
     * @param value allowed object is
     *              {@link RefIdList }
     */
    public void setResponse(RefIdList value) {
        this.refs = value;
    }
}
