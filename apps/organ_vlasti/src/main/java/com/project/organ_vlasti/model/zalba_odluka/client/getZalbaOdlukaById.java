package com.project.organ_vlasti.model.zalba_odluka.client;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id"
})
@XmlRootElement(name = "getZalbaOdlukaById")
public class getZalbaOdlukaById {

    @XmlElement(required = true)
    protected String id;

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }
}
