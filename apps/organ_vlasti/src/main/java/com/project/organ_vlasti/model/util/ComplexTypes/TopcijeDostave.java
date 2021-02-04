
package com.project.organ_vlasti.model.util.ComplexTypes;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for Topcije_dostave complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Topcije_dostave"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="opcija" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                 &lt;attribute name="izabran" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="nacini_dostave" type="{http://www.reusability}Tnacini_dostave"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Topcije_dostave", propOrder = {
        "opcija",
        "naciniDostave"
})
public class TopcijeDostave {

    @XmlElement(required = true)
    protected List<Opcija> opcija;
    @XmlElement(name = "nacini_dostave", required = true)
    protected TnaciniDostave naciniDostave;

    /**
     * Gets the value of the opcija property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the opcija property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOpcija().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Opcija }
     */
    public List<Opcija> getOpcija() {
        if (opcija == null) {
            opcija = new ArrayList<Opcija>();
        }
        return this.opcija;
    }

    /**
     * Gets the value of the naciniDostave property.
     *
     * @return possible object is
     * {@link TnaciniDostave }
     */
    public TnaciniDostave getNaciniDostave() {
        return naciniDostave;
    }

    /**
     * Sets the value of the naciniDostave property.
     *
     * @param value allowed object is
     *              {@link TnaciniDostave }
     */
    public void setNaciniDostave(TnaciniDostave value) {
        this.naciniDostave = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *       &lt;attribute name="izabran" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class Opcija {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "izabran")
        protected Boolean izabran;

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
         * Gets the value of the izabran property.
         *
         * @return possible object is
         * {@link Boolean }
         */
        public Boolean isIzabran() {
            return izabran;
        }

        /**
         * Sets the value of the izabran property.
         *
         * @param value allowed object is
         *              {@link Boolean }
         */
        public void setIzabran(Boolean value) {
            this.izabran = value;
        }

    }

}
