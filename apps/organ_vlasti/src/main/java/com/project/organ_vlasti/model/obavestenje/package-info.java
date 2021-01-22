@javax.xml.bind.annotation.XmlSchema(
        namespace = "http://www.obavestenje",
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED,
        xmlns={@XmlNs(prefix="oba",
                namespaceURI="http://www.obavestenje"),
                @XmlNs(prefix="re", namespaceURI = "http://www.reusability"),
                @XmlNs(prefix="pred", namespaceURI = "http://examples/predicate/")})
package com.project.organ_vlasti.model.obavestenje;

import javax.xml.bind.annotation.XmlNs;