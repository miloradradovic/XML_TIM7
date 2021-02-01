package com.project.organ_vlasti.model.util.lists;

import com.project.organ_vlasti.model.resenje.Resenje;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(namespace = "http://resenje")
@XmlSeeAlso({Resenje.class})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resenjeList", propOrder = {
        "any"
})
public class ResenjeList {

    @XmlAnyElement
    private final List<Resenje> any;

    public ResenjeList() {
        this.any = new ArrayList<>();
    }

    public ResenjeList(List<Resenje> any) {
        this.any = any;
    }

    public List<Resenje> getAny() {
        return any;
    }
}
