package com.project.organ_vlasti.model.zalba_odluka.client;


import com.project.organ_vlasti.model.zalba_odluka.Tzalba;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "zalba_odluka"
})
@XmlRootElement(name = "getZalbaOdlukaByIdResponse", namespace = "http://www.zalbanaodlukucir")
public class getZalbaOdlukaByIdResponse {

    @XmlElement(name = "zalba_odluka", required = true)
    protected Tzalba zalba_odluka;

    /**
     * Gets the value of the student property.
     *
     * @return possible object is
     * {@link Tzalba }
     */
    public Tzalba getZalba_odluka() {
        return zalba_odluka;
    }

    /**
     * Sets the value of the student property.
     *
     * @param value allowed object is
     *              {@link Tzalba }
     */
    public void setZalba_odluka(Tzalba value) {
        this.zalba_odluka = value;
    }
}
