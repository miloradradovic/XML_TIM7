@javax.xml.bind.annotation.XmlSchema(
        elementFormDefault = XmlNsForm.QUALIFIED,
        namespace="http://www.zalbanaodlukucir",
        xmlns={@XmlNs(prefix="zoc",
                namespaceURI="http://www.zalbanaodlukucir"),
                @XmlNs(prefix="re", namespaceURI = "http://www.reusability")})
package com.project.poverenik.model.zalba_odluka;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;