@javax.xml.bind.annotation.XmlSchema(
        namespace = "http://www.obavestenje",
        location = "http://www.obavestenje ../xsd/obavestenje.xsd",
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
        xmlns = {@XmlNs(prefix = "oba",
                namespaceURI = "http://www.obavestenje"),
                @XmlNs(prefix = "re", namespaceURI = "http://www.reusability"),
                @XmlNs(prefix = "pred", namespaceURI = "http://examples/predicate/"),
                @XmlNs(prefix = "xsi", namespaceURI = "http://www.w3.org/2001/XMLSchema-instance"),
                @XmlNs(prefix = "xs", namespaceURI = "http://www.w3.org/2001/XMLSchema#")})
package com.project.organ_vlasti.model.obavestenje;

import javax.xml.bind.annotation.XmlNs;