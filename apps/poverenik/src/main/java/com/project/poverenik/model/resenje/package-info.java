@javax.xml.bind.annotation.XmlSchema(
        elementFormDefault = XmlNsForm.QUALIFIED,
        namespace="http://resenje",
        xmlns={@XmlNs(prefix="ra",
                namespaceURI="http://resenje"),
        		@XmlNs(prefix="pred", namespaceURI = "http://examples/predicate/"),
        		@XmlNs(prefix="xs", namespaceURI = "http://www.w3.org/2001/XMLSchema#")})
package com.project.poverenik.model.resenje;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;