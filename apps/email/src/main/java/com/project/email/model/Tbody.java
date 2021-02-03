
package com.project.email.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="file_pdf_name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="file_html_name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="file_pdf" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="file_html" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tbody", propOrder = {
        "to",
        "content",
        "subject",
        "filePdfName",
        "fileHtmlName",
        "filePdf",
        "fileHtml"
})
public class Tbody {

    @XmlElement(required = true)
    protected String to;
    @XmlElement(required = true)
    protected String content;
    @XmlElement(required = true)
    protected String subject;
    @XmlElement(name = "file_pdf_name", required = true)
    protected String filePdfName;
    @XmlElement(name = "file_html_name", required = true)
    protected String fileHtmlName;
    @XmlElement(name = "file_pdf", required = true)
    protected Object filePdf;
    @XmlElement(name = "file_html", required = true)
    protected Object fileHtml;

    /**
     * Gets the value of the to property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTo(String value) {
        this.to = value;
    }

    /**
     * Gets the value of the content property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the subject property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the filePdfName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFilePdfName() {
        return filePdfName;
    }

    /**
     * Sets the value of the filePdfName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFilePdfName(String value) {
        this.filePdfName = value;
    }

    /**
     * Gets the value of the fileHtmlName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFileHtmlName() {
        return fileHtmlName;
    }

    /**
     * Sets the value of the fileHtmlName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFileHtmlName(String value) {
        this.fileHtmlName = value;
    }

    /**
     * Gets the value of the filePdf property.
     *
     * @return
     *     possible object is
     *     {@link Object }
     *
     */
    public Object getFilePdf() {
        return filePdf;
    }

    /**
     * Sets the value of the filePdf property.
     *
     * @param value
     *     allowed object is
     *     {@link Object }
     *
     */
    public void setFilePdf(Object value) {
        this.filePdf = value;
    }

    /**
     * Gets the value of the fileHtml property.
     *
     * @return
     *     possible object is
     *     {@link Object }
     *
     */
    public Object getFileHtml() {
        return fileHtml;
    }

    /**
     * Sets the value of the fileHtml property.
     *
     * @param value
     *     allowed object is
     *     {@link Object }
     *
     */
    public void setFileHtml(Object value) {
        this.fileHtml = value;
    }

}
