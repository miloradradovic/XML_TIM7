@javax.xml.bind.annotation.XmlSchema(
        namespace = "http://www.zahtevcir",
        location = "http://www.zahtevcir ../xsd/zahtevcir.xsd",
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
        xmlns = {@XmlNs(prefix = "zcir",
                namespaceURI = "http://www.zahtevcir"),
                @XmlNs(prefix = "re", namespaceURI = "http://www.reusability"),
                @XmlNs(prefix = "pred", namespaceURI = "http://examples/predicate/"),
                @XmlNs(prefix = "xsi", namespaceURI = "http://www.w3.org/2001/XMLSchema-instance"),
                @XmlNs(prefix = "xs", namespaceURI = "http://www.w3.org/2001/XMLSchema#")})
package com.project.organ_vlasti.model.zahtev;

import javax.xml.bind.annotation.XmlNs;