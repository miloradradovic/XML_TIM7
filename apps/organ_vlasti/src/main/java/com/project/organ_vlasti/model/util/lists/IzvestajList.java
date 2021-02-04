package com.project.organ_vlasti.model.util.lists;

import com.project.organ_vlasti.model.izvestaji.Izvestaj;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({Izvestaj.class})
public class IzvestajList {
    private final List<Izvestaj> izvestajList;

    public IzvestajList() {
        this.izvestajList = new ArrayList<>();
    }

    public IzvestajList(List<Izvestaj> izvestajList) {
        this.izvestajList = izvestajList;
    }

    @XmlAnyElement
    public List<Izvestaj> getIzvestajList() {
        return izvestajList;
    }
}
