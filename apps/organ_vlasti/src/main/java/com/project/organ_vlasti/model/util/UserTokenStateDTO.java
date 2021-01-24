package com.project.organ_vlasti.model.util;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "jwt"
})
@XmlRootElement(name = "token", namespace = "http://token")
public class UserTokenStateDTO {
    @XmlElement(namespace = "http://token", required = true)
    public String jwt;

    public UserTokenStateDTO(){}
    public UserTokenStateDTO(String jwt) {
        this.jwt = jwt;
    }
}
