package com.project.poverenik.model.util.lists;


import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement(namespace = "http://parametars")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parametarMap", propOrder = {
        "any"
})
public class ParametarMap {

    @XmlAnyElement
    private final Map<String, String> any;

    public ParametarMap() {
        this.any = new HashMap<>();
    }

    public ParametarMap(Map<String, String> any) {
        this.any = any;
    }

    public Map<String, String> getAny() {
        return any;
    }
}
