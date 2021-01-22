
package com.project.organ_vlasti.model.util.ComplexTypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Tnacini_dostave complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tnacini_dostave">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nacin_dostave" type="{http://www.reusability}Tnacin_dostave" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tnacini_dostave", propOrder = {
    "nacinDostave"
})
public class TnaciniDostave {

    @XmlElement(name = "nacin_dostave", required = true)
    protected List<TnacinDostave> nacinDostave;

    /**
     * Gets the value of the nacinDostave property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nacinDostave property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNacinDostave().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TnacinDostave }
     * 
     * 
     */
    public List<TnacinDostave> getNacinDostave() {
        if (nacinDostave == null) {
            nacinDostave = new ArrayList<TnacinDostave>();
        }
        return this.nacinDostave;
    }

}
