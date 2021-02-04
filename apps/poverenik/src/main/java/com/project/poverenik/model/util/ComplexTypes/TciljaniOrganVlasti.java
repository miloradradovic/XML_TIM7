
package com.project.poverenik.model.util.ComplexTypes;

import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>Java class for Tciljani_organ_vlasti complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Tciljani_organ_vlasti">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="naziv_organa">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;anyAttribute processContents='lax'/>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="sediste_organa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tciljani_organ_vlasti", namespace = "http://www.reusability", propOrder = {
        "nazivOrgana",
        "sedisteOrgana"
})
public class TciljaniOrganVlasti {

    @XmlElement(name = "naziv_organa", namespace = "http://www.reusability", required = true)
    protected TciljaniOrganVlasti.NazivOrgana nazivOrgana;
    @XmlElement(name = "sediste_organa", namespace = "http://www.reusability", required = true)
    protected String sedisteOrgana;

    /**
     * Gets the value of the nazivOrgana property.
     *
     * @return possible object is
     * {@link TciljaniOrganVlasti.NazivOrgana }
     */
    public TciljaniOrganVlasti.NazivOrgana getNazivOrgana() {
        return nazivOrgana;
    }

    /**
     * Sets the value of the nazivOrgana property.
     *
     * @param value allowed object is
     *              {@link TciljaniOrganVlasti.NazivOrgana }
     */
    public void setNazivOrgana(TciljaniOrganVlasti.NazivOrgana value) {
        this.nazivOrgana = value;
    }

    /**
     * Gets the value of the sedisteOrgana property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSedisteOrgana() {
        return sedisteOrgana;
    }

    /**
     * Sets the value of the sedisteOrgana property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSedisteOrgana(String value) {
        this.sedisteOrgana = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;anyAttribute processContents='lax'/>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class NazivOrgana {

        @XmlValue
        protected String value;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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
