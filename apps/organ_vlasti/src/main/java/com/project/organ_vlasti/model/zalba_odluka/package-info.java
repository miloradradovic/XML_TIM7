@javax.xml.bind.annotation.XmlSchema(
        elementFormDefault = XmlNsForm.QUALIFIED,
        namespace="http://www.zalbanaodlukucir",
        location="http://www.zalbanaodlukucir ../xsd/zalbanaodlukucir.xsd",
        xmlns={@XmlNs(prefix="zoc",
                namespaceURI="http://www.zalbanaodlukucir"),
                @XmlNs(prefix="re", namespaceURI = "http://www.reusability"),
                @XmlNs(prefix="pred", namespaceURI = "http://examples/predicate/"),
                @XmlNs(prefix="xsi", namespaceURI = "http://www.w3.org/2001/XMLSchema-instance"),
                @XmlNs(prefix="xs", namespaceURI = "http://www.w3.org/2001/XMLSchema#")})
package com.project.organ_vlasti.model.zalba_odluka;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;