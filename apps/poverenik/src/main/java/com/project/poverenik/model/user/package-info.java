@javax.xml.bind.annotation.XmlSchema(
        elementFormDefault = XmlNsForm.QUALIFIED,
        namespace = "http://user",
        xmlns = {@XmlNs(prefix = "u",
                namespaceURI = "http://user")})
package com.project.poverenik.model.user;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;