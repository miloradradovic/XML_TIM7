@javax.xml.bind.annotation.XmlSchema(
        elementFormDefault = XmlNsForm.QUALIFIED,
        namespace = "http://www.zalbacutanje",
        xmlns={@XmlNs(prefix="zc",
                namespaceURI="http://www.zalbacutanje"),
        @XmlNs(prefix="re", namespaceURI = "http://www.reusability"),
        @XmlNs(prefix="pred", namespaceURI = "http://examples/predicate/")})
package com.project.poverenik.model.zalba_cutanje;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;

