package com.project.poverenik.model.util.file.client;


import com.project.poverenik.model.util.file.Tpath;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "path"
})
@XmlRootElement(name = "sendRdfFileResponse", namespace = "http://file")
public class sendRdfFileResponse {

    @XmlElement(name = "path", required = true)
    protected Tpath path;

    /**
     * Gets the value of the student property.
     *
     * @return possible object is
     * {@link Tpath }
     */
    public Tpath getPath() {
        return path;
    }

    /**
     * Sets the value of the student property.
     *
     * @param value allowed object is
     *              {@link Tpath }
     */
    public void setPath(Tpath value) {
        this.path = value;
    }
}
