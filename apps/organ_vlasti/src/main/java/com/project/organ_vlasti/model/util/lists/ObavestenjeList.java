package com.project.organ_vlasti.model.util.lists;

import com.project.organ_vlasti.model.obavestenje.Obavestenje;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({Obavestenje.class})
public class ObavestenjeList {
    private final List<Obavestenje> obavestenjeList;

    public ObavestenjeList(){
        this.obavestenjeList = new ArrayList<>();
    }
    public ObavestenjeList(List<Obavestenje> obavestenjeList){
        this.obavestenjeList = obavestenjeList;
    }

    @XmlAnyElement
    public List<Obavestenje> getObavestenjeList() {
        return obavestenjeList;
    }
}
