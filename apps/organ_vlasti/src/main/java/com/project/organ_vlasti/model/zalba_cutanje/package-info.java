@javax.xml.bind.annotation.XmlSchema(
        elementFormDefault = XmlNsForm.QUALIFIED,
        namespace = "http://www.zalbacutanje",
        location="http://www.zalbacutanje ../xsd/zalba_cutanje.xsd",
        xmlns={@XmlNs(prefix="zc",
                namespaceURI="http://www.zalbacutanje"),
        @XmlNs(prefix="re", namespaceURI = "http://www.reusability"),
        @XmlNs(prefix="pred", namespaceURI = "http://examples/predicate/"),
        @XmlNs(prefix="xsi", namespaceURI = "http://www.w3.org/2001/XMLSchema-instance"),
        @XmlNs(prefix="xs", namespaceURI = "http://www.w3.org/2001/XMLSchema#")})
package com.project.organ_vlasti.model.zalba_cutanje;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;

