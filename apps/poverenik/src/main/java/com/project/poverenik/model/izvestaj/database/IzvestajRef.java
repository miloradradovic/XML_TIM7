
package com.project.poverenik.model.izvestaj.database;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="body"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.message&gt;Tbody"&gt;
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", namespace = "http://izvestaji", propOrder = {
        "body"
})
@XmlRootElement(name = "izvestaj_ref", namespace = "http://izvestaji")
public class IzvestajRef {

    @XmlElement(required = true, namespace = "http://izvestaji")
    protected Body body;

    /**
     * Gets the value of the body property.
     *
     * @return possible object is
     * {@link Body }
     */
    public Body getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     *
     * @param value allowed object is
     *              {@link Body }
     */
    public void setBody(Body value) {
        this.body = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.message&gt;Tbody"&gt;
     *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class Body {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "id")
        protected String id;
        @XmlAttribute(name = "procitano")
        protected String procitano;

        /**
         * Gets the value of the value property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setValue(String value) {
            this.value = value;
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
         * Gets the value of the id property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getProcitano() {
            return procitano;
        }

        /**
         * Sets the value of the id property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setProcitano(String value) {
            this.procitano = value;
        }

    }

}
