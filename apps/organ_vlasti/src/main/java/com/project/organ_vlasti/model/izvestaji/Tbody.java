
package com.project.organ_vlasti.model.izvestaji;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>Java class for Tbody complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Tbody"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="zahtevi_podneti"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
 *                 &lt;anyAttribute processContents='lax'/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zahtevi_prihvaceni"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
 *                 &lt;anyAttribute processContents='lax'/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zahtevi_odbijeni"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
 *                 &lt;anyAttribute processContents='lax'/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zalbe_odluke_podneti"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
 *                 &lt;anyAttribute processContents='lax'/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zalbe_odluke_prihvaceno"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
 *                 &lt;anyAttribute processContents='lax'/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zalbe_odluke_odbijeno"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
 *                 &lt;anyAttribute processContents='lax'/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zalbe_odluke_ponisteno"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
 *                 &lt;anyAttribute processContents='lax'/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zalbe_cutanje_podneti"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
 *                 &lt;anyAttribute processContents='lax'/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zalbe_cutanje_prihvaceno"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
 *                 &lt;anyAttribute processContents='lax'/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zalbe_cutanje_odbijeno"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
 *                 &lt;anyAttribute processContents='lax'/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zalbe_cutanje_ponisteno"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
 *                 &lt;anyAttribute processContents='lax'/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *       &lt;anyAttribute processContents='lax'/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tbody", propOrder = {
        "zahteviPodneti",
        "zahteviPrihvaceni",
        "zahteviOdbijeni",
        "zalbeOdlukePodneti",
        "zalbeOdlukePrihvaceno",
        "zalbeOdlukeOdbijeno",
        "zalbeOdlukePonisteno",
        "zalbeCutanjePodneti",
        "zalbeCutanjePrihvaceno",
        "zalbeCutanjeOdbijeno",
        "zalbeCutanjePonisteno"
})
public class Tbody {

    @XmlElement(name = "zahtevi_podneti", required = true)
    protected Tbody.ZahteviPodneti zahteviPodneti;
    @XmlElement(name = "zahtevi_prihvaceni", required = true)
    protected Tbody.ZahteviPrihvaceni zahteviPrihvaceni;
    @XmlElement(name = "zahtevi_odbijeni", required = true)
    protected Tbody.ZahteviOdbijeni zahteviOdbijeni;
    @XmlElement(name = "zalbe_odluke_podneti", required = true)
    protected Tbody.ZalbeOdlukePodneti zalbeOdlukePodneti;
    @XmlElement(name = "zalbe_odluke_prihvaceno", required = true)
    protected Tbody.ZalbeOdlukePrihvaceno zalbeOdlukePrihvaceno;
    @XmlElement(name = "zalbe_odluke_odbijeno", required = true)
    protected Tbody.ZalbeOdlukeOdbijeno zalbeOdlukeOdbijeno;
    @XmlElement(name = "zalbe_odluke_ponisteno", required = true)
    protected Tbody.ZalbeOdlukePonisteno zalbeOdlukePonisteno;
    @XmlElement(name = "zalbe_cutanje_podneti", required = true)
    protected Tbody.ZalbeCutanjePodneti zalbeCutanjePodneti;
    @XmlElement(name = "zalbe_cutanje_prihvaceno", required = true)
    protected Tbody.ZalbeCutanjePrihvaceno zalbeCutanjePrihvaceno;
    @XmlElement(name = "zalbe_cutanje_odbijeno", required = true)
    protected Tbody.ZalbeCutanjeOdbijeno zalbeCutanjeOdbijeno;
    @XmlElement(name = "zalbe_cutanje_ponisteno", required = true)
    protected Tbody.ZalbeCutanjePonisteno zalbeCutanjePonisteno;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "datum")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the zahteviPodneti property.
     *
     * @return possible object is
     * {@link Tbody.ZahteviPodneti }
     */
    public Tbody.ZahteviPodneti getZahteviPodneti() {
        return zahteviPodneti;
    }

    /**
     * Sets the value of the zahteviPodneti property.
     *
     * @param value allowed object is
     *              {@link Tbody.ZahteviPodneti }
     */
    public void setZahteviPodneti(Tbody.ZahteviPodneti value) {
        this.zahteviPodneti = value;
    }

    /**
     * Gets the value of the zahteviPrihvaceni property.
     *
     * @return possible object is
     * {@link Tbody.ZahteviPrihvaceni }
     */
    public Tbody.ZahteviPrihvaceni getZahteviPrihvaceni() {
        return zahteviPrihvaceni;
    }

    /**
     * Sets the value of the zahteviPrihvaceni property.
     *
     * @param value allowed object is
     *              {@link Tbody.ZahteviPrihvaceni }
     */
    public void setZahteviPrihvaceni(Tbody.ZahteviPrihvaceni value) {
        this.zahteviPrihvaceni = value;
    }

    /**
     * Gets the value of the zahteviOdbijeni property.
     *
     * @return possible object is
     * {@link Tbody.ZahteviOdbijeni }
     */
    public Tbody.ZahteviOdbijeni getZahteviOdbijeni() {
        return zahteviOdbijeni;
    }

    /**
     * Sets the value of the zahteviOdbijeni property.
     *
     * @param value allowed object is
     *              {@link Tbody.ZahteviOdbijeni }
     */
    public void setZahteviOdbijeni(Tbody.ZahteviOdbijeni value) {
        this.zahteviOdbijeni = value;
    }

    /**
     * Gets the value of the zalbeOdlukePodneti property.
     *
     * @return possible object is
     * {@link Tbody.ZalbeOdlukePodneti }
     */
    public Tbody.ZalbeOdlukePodneti getZalbeOdlukePodneti() {
        return zalbeOdlukePodneti;
    }

    /**
     * Sets the value of the zalbeOdlukePodneti property.
     *
     * @param value allowed object is
     *              {@link Tbody.ZalbeOdlukePodneti }
     */
    public void setZalbeOdlukePodneti(Tbody.ZalbeOdlukePodneti value) {
        this.zalbeOdlukePodneti = value;
    }

    /**
     * Gets the value of the zalbeOdlukePrihvaceno property.
     *
     * @return possible object is
     * {@link Tbody.ZalbeOdlukePrihvaceno }
     */
    public Tbody.ZalbeOdlukePrihvaceno getZalbeOdlukePrihvaceno() {
        return zalbeOdlukePrihvaceno;
    }

    /**
     * Sets the value of the zalbeOdlukePrihvaceno property.
     *
     * @param value allowed object is
     *              {@link Tbody.ZalbeOdlukePrihvaceno }
     */
    public void setZalbeOdlukePrihvaceno(Tbody.ZalbeOdlukePrihvaceno value) {
        this.zalbeOdlukePrihvaceno = value;
    }

    /**
     * Gets the value of the zalbeOdlukeOdbijeno property.
     *
     * @return possible object is
     * {@link Tbody.ZalbeOdlukeOdbijeno }
     */
    public Tbody.ZalbeOdlukeOdbijeno getZalbeOdlukeOdbijeno() {
        return zalbeOdlukeOdbijeno;
    }

    /**
     * Sets the value of the zalbeOdlukeOdbijeno property.
     *
     * @param value allowed object is
     *              {@link Tbody.ZalbeOdlukeOdbijeno }
     */
    public void setZalbeOdlukeOdbijeno(Tbody.ZalbeOdlukeOdbijeno value) {
        this.zalbeOdlukeOdbijeno = value;
    }

    /**
     * Gets the value of the zalbeOdlukePonisteno property.
     *
     * @return possible object is
     * {@link Tbody.ZalbeOdlukePonisteno }
     */
    public Tbody.ZalbeOdlukePonisteno getZalbeOdlukePonisteno() {
        return zalbeOdlukePonisteno;
    }

    /**
     * Sets the value of the zalbeOdlukePonisteno property.
     *
     * @param value allowed object is
     *              {@link Tbody.ZalbeOdlukePonisteno }
     */
    public void setZalbeOdlukePonisteno(Tbody.ZalbeOdlukePonisteno value) {
        this.zalbeOdlukePonisteno = value;
    }

    /**
     * Gets the value of the zalbeCutanjePodneti property.
     *
     * @return possible object is
     * {@link Tbody.ZalbeCutanjePodneti }
     */
    public Tbody.ZalbeCutanjePodneti getZalbeCutanjePodneti() {
        return zalbeCutanjePodneti;
    }

    /**
     * Sets the value of the zalbeCutanjePodneti property.
     *
     * @param value allowed object is
     *              {@link Tbody.ZalbeCutanjePodneti }
     */
    public void setZalbeCutanjePodneti(Tbody.ZalbeCutanjePodneti value) {
        this.zalbeCutanjePodneti = value;
    }

    /**
     * Gets the value of the zalbeCutanjePrihvaceno property.
     *
     * @return possible object is
     * {@link Tbody.ZalbeCutanjePrihvaceno }
     */
    public Tbody.ZalbeCutanjePrihvaceno getZalbeCutanjePrihvaceno() {
        return zalbeCutanjePrihvaceno;
    }

    /**
     * Sets the value of the zalbeCutanjePrihvaceno property.
     *
     * @param value allowed object is
     *              {@link Tbody.ZalbeCutanjePrihvaceno }
     */
    public void setZalbeCutanjePrihvaceno(Tbody.ZalbeCutanjePrihvaceno value) {
        this.zalbeCutanjePrihvaceno = value;
    }

    /**
     * Gets the value of the zalbeCutanjeOdbijeno property.
     *
     * @return possible object is
     * {@link Tbody.ZalbeCutanjeOdbijeno }
     */
    public Tbody.ZalbeCutanjeOdbijeno getZalbeCutanjeOdbijeno() {
        return zalbeCutanjeOdbijeno;
    }

    /**
     * Sets the value of the zalbeCutanjeOdbijeno property.
     *
     * @param value allowed object is
     *              {@link Tbody.ZalbeCutanjeOdbijeno }
     */
    public void setZalbeCutanjeOdbijeno(Tbody.ZalbeCutanjeOdbijeno value) {
        this.zalbeCutanjeOdbijeno = value;
    }

    /**
     * Gets the value of the zalbeCutanjePonisteno property.
     *
     * @return possible object is
     * {@link Tbody.ZalbeCutanjePonisteno }
     */
    public Tbody.ZalbeCutanjePonisteno getZalbeCutanjePonisteno() {
        return zalbeCutanjePonisteno;
    }

    /**
     * Sets the value of the zalbeCutanjePonisteno property.
     *
     * @param value allowed object is
     *              {@link Tbody.ZalbeCutanjePonisteno }
     */
    public void setZalbeCutanjePonisteno(Tbody.ZalbeCutanjePonisteno value) {
        this.zalbeCutanjePonisteno = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the datum property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     *
     * <p>
     * the map is keyed by the name of the attribute and
     * the value is the string value of the attribute.
     * <p>
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     *
     * @return always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
     *       &lt;anyAttribute processContents='lax'/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class ZahteviOdbijeni {

        @XmlValue
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         *
         * <p>
         * the map is keyed by the name of the attribute and
         * the value is the string value of the attribute.
         * <p>
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         * @return always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
     *       &lt;anyAttribute processContents='lax'/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class ZahteviPodneti {

        @XmlValue
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         *
         * <p>
         * the map is keyed by the name of the attribute and
         * the value is the string value of the attribute.
         * <p>
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         * @return always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
     *       &lt;anyAttribute processContents='lax'/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class ZahteviPrihvaceni {

        @XmlValue
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         *
         * <p>
         * the map is keyed by the name of the attribute and
         * the value is the string value of the attribute.
         * <p>
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         * @return always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
     *       &lt;anyAttribute processContents='lax'/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class ZalbeCutanjeOdbijeno {

        @XmlValue
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         *
         * <p>
         * the map is keyed by the name of the attribute and
         * the value is the string value of the attribute.
         * <p>
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         * @return always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
     *       &lt;anyAttribute processContents='lax'/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class ZalbeCutanjePodneti {

        @XmlValue
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         *
         * <p>
         * the map is keyed by the name of the attribute and
         * the value is the string value of the attribute.
         * <p>
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         * @return always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
     *       &lt;anyAttribute processContents='lax'/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class ZalbeCutanjePonisteno {

        @XmlValue
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         *
         * <p>
         * the map is keyed by the name of the attribute and
         * the value is the string value of the attribute.
         * <p>
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         * @return always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
     *       &lt;anyAttribute processContents='lax'/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class ZalbeCutanjePrihvaceno {

        @XmlValue
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         *
         * <p>
         * the map is keyed by the name of the attribute and
         * the value is the string value of the attribute.
         * <p>
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         * @return always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
     *       &lt;anyAttribute processContents='lax'/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class ZalbeOdlukeOdbijeno {

        @XmlValue
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         *
         * <p>
         * the map is keyed by the name of the attribute and
         * the value is the string value of the attribute.
         * <p>
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         * @return always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
     *       &lt;anyAttribute processContents='lax'/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class ZalbeOdlukePodneti {

        @XmlValue
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         *
         * <p>
         * the map is keyed by the name of the attribute and
         * the value is the string value of the attribute.
         * <p>
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         * @return always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
     *       &lt;anyAttribute processContents='lax'/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class ZalbeOdlukePonisteno {

        @XmlValue
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         *
         * <p>
         * the map is keyed by the name of the attribute and
         * the value is the string value of the attribute.
         * <p>
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         * @return always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;positiveInteger"&gt;
     *       &lt;anyAttribute processContents='lax'/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class ZalbeOdlukePrihvaceno {

        @XmlValue
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setValue(BigInteger value) {
            this.value = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         *
         * <p>
         * the map is keyed by the name of the attribute and
         * the value is the string value of the attribute.
         * <p>
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         *
         * @return always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

    }

}
