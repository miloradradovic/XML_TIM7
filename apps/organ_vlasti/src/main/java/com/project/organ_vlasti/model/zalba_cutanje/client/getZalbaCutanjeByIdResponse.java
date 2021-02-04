package com.project.organ_vlasti.model.zalba_cutanje.client;

import com.project.organ_vlasti.model.zalba_cutanje.Tzalba;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "zalba_cutanje"
})
@XmlRootElement(name = "getZalbaCutanjeByIdResponse", namespace = "http://www.zalbacutanje")
public class getZalbaCutanjeByIdResponse {

    @XmlElement(name = "zalba_cutanje", required = true)
    protected Tzalba zalba_cutanje;

    /**
     * Gets the value of the student property.
     *
     * @return possible object is
     * {@link Tzalba }
     */
    public Tzalba getZalba_cutanje() {
        return zalba_cutanje;
    }

    /**
     * Sets the value of the student property.
     *
     * @param value allowed object is
     *              {@link Tzalba }
     */
    public void setZalba_cutanje(Tzalba value) {
        this.zalba_cutanje = value;
    }
}
