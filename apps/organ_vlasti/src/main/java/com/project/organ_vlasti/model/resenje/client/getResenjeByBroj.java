package com.project.organ_vlasti.model.resenje.client;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "broj"
})
@XmlRootElement(name = "getResenjeByBroj")
public class getResenjeByBroj {

    @XmlElement(required = true)
    protected String broj;

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getBroj() {
        return broj;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setBroj(String value) {
        this.broj = value;
    }
}
