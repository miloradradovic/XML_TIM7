
package com.project.poverenik.model.izvestaj;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="zahtevi_podneti" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="zahtevi_prihvaceni" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="zahtevi_odbijeni" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="zalbe_odluke_podneti" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="zalbe_odluke_prihvaceno" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="zalbe_odluke_odbijeno" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="zalbe_odluke_ponisteno" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="zalbe_cutanje_podneti" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="zalbe_cutanje_prihvaceno" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="zalbe_cutanje_odbijeno" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="zalbe_cutanje_ponisteno" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
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
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger zahteviPodneti;
    @XmlElement(name = "zahtevi_prihvaceni", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger zahteviPrihvaceni;
    @XmlElement(name = "zahtevi_odbijeni", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger zahteviOdbijeni;
    @XmlElement(name = "zalbe_odluke_podneti", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger zalbeOdlukePodneti;
    @XmlElement(name = "zalbe_odluke_prihvaceno", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger zalbeOdlukePrihvaceno;
    @XmlElement(name = "zalbe_odluke_odbijeno", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger zalbeOdlukeOdbijeno;
    @XmlElement(name = "zalbe_odluke_ponisteno", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger zalbeOdlukePonisteno;
    @XmlElement(name = "zalbe_cutanje_podneti", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger zalbeCutanjePodneti;
    @XmlElement(name = "zalbe_cutanje_prihvaceno", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger zalbeCutanjePrihvaceno;
    @XmlElement(name = "zalbe_cutanje_odbijeno", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger zalbeCutanjeOdbijeno;
    @XmlElement(name = "zalbe_cutanje_ponisteno", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger zalbeCutanjePonisteno;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "datum")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;

    /**
     * Gets the value of the zahteviPodneti property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZahteviPodneti() {
        return zahteviPodneti;
    }

    /**
     * Sets the value of the zahteviPodneti property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZahteviPodneti(BigInteger value) {
        this.zahteviPodneti = value;
    }

    /**
     * Gets the value of the zahteviPrihvaceni property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZahteviPrihvaceni() {
        return zahteviPrihvaceni;
    }

    /**
     * Sets the value of the zahteviPrihvaceni property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZahteviPrihvaceni(BigInteger value) {
        this.zahteviPrihvaceni = value;
    }

    /**
     * Gets the value of the zahteviOdbijeni property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZahteviOdbijeni() {
        return zahteviOdbijeni;
    }

    /**
     * Sets the value of the zahteviOdbijeni property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZahteviOdbijeni(BigInteger value) {
        this.zahteviOdbijeni = value;
    }

    /**
     * Gets the value of the zalbeOdlukePodneti property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZalbeOdlukePodneti() {
        return zalbeOdlukePodneti;
    }

    /**
     * Sets the value of the zalbeOdlukePodneti property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZalbeOdlukePodneti(BigInteger value) {
        this.zalbeOdlukePodneti = value;
    }

    /**
     * Gets the value of the zalbeOdlukePrihvaceno property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZalbeOdlukePrihvaceno() {
        return zalbeOdlukePrihvaceno;
    }

    /**
     * Sets the value of the zalbeOdlukePrihvaceno property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZalbeOdlukePrihvaceno(BigInteger value) {
        this.zalbeOdlukePrihvaceno = value;
    }

    /**
     * Gets the value of the zalbeOdlukeOdbijeno property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZalbeOdlukeOdbijeno() {
        return zalbeOdlukeOdbijeno;
    }

    /**
     * Sets the value of the zalbeOdlukeOdbijeno property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZalbeOdlukeOdbijeno(BigInteger value) {
        this.zalbeOdlukeOdbijeno = value;
    }

    /**
     * Gets the value of the zalbeOdlukePonisteno property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZalbeOdlukePonisteno() {
        return zalbeOdlukePonisteno;
    }

    /**
     * Sets the value of the zalbeOdlukePonisteno property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZalbeOdlukePonisteno(BigInteger value) {
        this.zalbeOdlukePonisteno = value;
    }

    /**
     * Gets the value of the zalbeCutanjePodneti property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZalbeCutanjePodneti() {
        return zalbeCutanjePodneti;
    }

    /**
     * Sets the value of the zalbeCutanjePodneti property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZalbeCutanjePodneti(BigInteger value) {
        this.zalbeCutanjePodneti = value;
    }

    /**
     * Gets the value of the zalbeCutanjePrihvaceno property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZalbeCutanjePrihvaceno() {
        return zalbeCutanjePrihvaceno;
    }

    /**
     * Sets the value of the zalbeCutanjePrihvaceno property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZalbeCutanjePrihvaceno(BigInteger value) {
        this.zalbeCutanjePrihvaceno = value;
    }

    /**
     * Gets the value of the zalbeCutanjeOdbijeno property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZalbeCutanjeOdbijeno() {
        return zalbeCutanjeOdbijeno;
    }

    /**
     * Sets the value of the zalbeCutanjeOdbijeno property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZalbeCutanjeOdbijeno(BigInteger value) {
        this.zalbeCutanjeOdbijeno = value;
    }

    /**
     * Gets the value of the zalbeCutanjePonisteno property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZalbeCutanjePonisteno() {
        return zalbeCutanjePonisteno;
    }

    /**
     * Sets the value of the zalbeCutanjePonisteno property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZalbeCutanjePonisteno(BigInteger value) {
        this.zalbeCutanjePonisteno = value;
    }

    /**
     * Gets the value of the id property.
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
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

}
