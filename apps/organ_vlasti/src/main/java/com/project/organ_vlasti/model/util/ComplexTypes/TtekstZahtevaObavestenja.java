
package com.project.organ_vlasti.model.util.ComplexTypes;

import java.io.Serializable;
import java.math.BigInteger;
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
 * <p>Java class for Ttekst_zahteva_obavestenja complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ttekst_zahteva_obavestenja"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="clan" type="{http://www.reusability}Tclan" minOccurs="0"/&gt;
 *         &lt;element name="godina"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]{4}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="opis_trazene_informacije" type="{http://www.reusability}Topis"/&gt;
 *         &lt;element name="dan" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="vreme"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="([0-9]|1[0-9]|2[0-4])"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="radno_vreme" type="{http://www.reusability}Tradno_vreme"/&gt;
 *         &lt;element name="adresa" type="{http://www.reusability}Tadresa"/&gt;
 *         &lt;element name="broj_kancelarije" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="opis_troskova" type="{http://www.reusability}Topis_troskova" minOccurs="0"/&gt;
 *         &lt;element name="ukupan_trosak" type="{http://www.reusability}Tukupan_trosak"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ttekst_zahteva_obavestenja", propOrder = {
    "content"
})
public class TtekstZahtevaObavestenja {

    @XmlElementRefs({
        @XmlElementRef(name = "broj_kancelarije", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "adresa", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "vreme", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "opis_troskova", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "radno_vreme", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "clan", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "opis_trazene_informacije", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "dan", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "ukupan_trosak", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "godina", namespace = "http://www.reusability", type = JAXBElement.class)
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
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link Tadresa }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link TopisTroskova }{@code >}
     * {@link JAXBElement }{@code <}{@link TradnoVreme }{@code >}
     * {@link JAXBElement }{@code <}{@link Tclan }{@code >}
     * {@link JAXBElement }{@code <}{@link Topis }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link TukupanTrosak }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
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
