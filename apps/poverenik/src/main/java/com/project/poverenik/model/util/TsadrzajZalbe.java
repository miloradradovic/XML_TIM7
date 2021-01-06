
package com.project.poverenik.model.util;

import com.project.poverenik.model.util.Tclan;
import com.project.poverenik.model.util.Topcije;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Tsadrzaj_zalbe complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tsadrzaj_zalbe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clan" type="{http://www.reusability}Tclan" minOccurs="0"/>
 *         &lt;element name="ciljani_organ_vlasti" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="razlog_zalbe" type="{http://www.reusability}Topcije"/>
 *         &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="podaci_o_zahtjevu_i_informacijama" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="napomena" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tsadrzaj_zalbe", namespace = "http://www.reusability", propOrder = {
    "content"
})
public class TsadrzajZalbe {

    @XmlElementRefs({
        @XmlElementRef(name = "ciljani_organ_vlasti", type = JAXBElement.class),
        @XmlElementRef(name = "podaci_o_zahtjevu_i_informacijama", type = JAXBElement.class),
        @XmlElementRef(name = "napomena", type = JAXBElement.class),
        @XmlElementRef(name = "clan", type = JAXBElement.class),
        @XmlElementRef(name = "razlog_zalbe", type = JAXBElement.class),
        @XmlElementRef(name = "datum", type = JAXBElement.class)
    })
    @XmlMixed
    protected List<Serializable> content;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Tclan }{@code >}
     * {@link JAXBElement }{@code <}{@link Topcije }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link String }
     * 
     * 
     */
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
    }

}
