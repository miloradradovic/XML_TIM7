@javax.xml.bind.annotation.XmlSchema(
        elementFormDefault = XmlNsForm.QUALIFIED,
        namespace = "http://resenje",
        xmlns = {@XmlNs(prefix = "ra",
                namespaceURI = "http://resenje"),
                @XmlNs(prefix = "pred", namespaceURI = "http://examples/predicate/")})
package com.project.organ_vlasti.model.resenje;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;