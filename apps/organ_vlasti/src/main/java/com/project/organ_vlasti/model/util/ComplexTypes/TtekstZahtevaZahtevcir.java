
package com.project.organ_vlasti.model.util.ComplexTypes;

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


/**
 * <p>Java class for Ttekst_zahteva_zahtevcir complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ttekst_zahteva_zahtevcir"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="clan" type="{http://www.reusability}Tclan" minOccurs="0"/&gt;
 *         &lt;element name="opcije" type="{http://www.reusability}Topcije_dostave"/&gt;
 *         &lt;element name="informacija_o_zahtevu" type="{http://www.reusability}Topis"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ttekst_zahteva_zahtevcir", propOrder = {
    "content"
})
public class TtekstZahtevaZahtevcir {

    @XmlElementRefs({
        @XmlElementRef(name = "clan", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "opcije", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "informacija_o_zahtevu", namespace = "http://www.reusability", type = JAXBElement.class)
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
     * {@link JAXBElement }{@code <}{@link Tclan }{@code >}
     * {@link JAXBElement }{@code <}{@link TopcijeDostave }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link Topis }{@code >}
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
