@javax.xml.bind.annotation.XmlSchema(
        namespace = "http://www.zahtevcir",
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
        xmlns={@XmlNs(prefix="zcir",
                namespaceURI="http://www.zahtevcir"),
                @XmlNs(prefix="re", namespaceURI = "http://www.reusability"),
                @XmlNs(prefix="pred", namespaceURI = "http://examples/predicate/")})
package com.project.organ_vlasti.model.zahtev;

import javax.xml.bind.annotation.XmlNs;
