
package com.project.poverenik.model.util.ComplexTypes;

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
 * &lt;complexType name="Ttekst_zahteva_obavestenja">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clan" type="{http://www.reusability}Tclan" minOccurs="0"/>
 *         &lt;element name="godina">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[0-9]{4}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="opis_trazene_informacije" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dan" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="vreme">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="([0-9]|1[0-9]|2[0-4])"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="radno_vreme" type="{http://www.reusability}Tradno_vreme"/>
 *         &lt;element name="adresa" type="{http://www.reusability}Tadresa"/>
 *         &lt;element name="broj_kancelarije" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="opis_troskova" type="{http://www.reusability}Topis_troskova"/>
 *         &lt;element name="ukupan_trosak" type="{http://www.reusability}Tukupan_trosak"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ttekst_zahteva_obavestenja", namespace = "http://www.reusability", propOrder = {
    "content"
})
public class TtekstZahtevaObavestenja {

    @XmlElementRefs({
        @XmlElementRef(name = "vreme", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "dan", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "broj_kancelarije", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "opis_trazene_informacije", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "ukupan_trosak", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "godina", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "radno_vreme", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "clan", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "adresa", namespace = "http://www.reusability", type = JAXBElement.class),
        @XmlElementRef(name = "opis_troskova", namespace = "http://www.reusability", type = JAXBElement.class)
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
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link TukupanTrosak }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link TradnoVreme }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link Tclan }{@code >}
     * {@link JAXBElement }{@code <}{@link Tadresa }{@code >}
     * {@link JAXBElement }{@code <}{@link TopisTroskova }{@code >}
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
