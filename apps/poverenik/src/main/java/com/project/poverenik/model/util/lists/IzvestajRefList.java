package com.project.poverenik.model.util.lists;


import com.project.poverenik.model.izvestaj.database.IzvestajRef;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({IzvestajRef.class})
public class IzvestajRefList {
    private final List<IzvestajRef> izvestajRefList;

    public IzvestajRefList(){
        this.izvestajRefList = new ArrayList<>();
    }
    public IzvestajRefList(List<IzvestajRef> izvestajRefList){
        this.izvestajRefList = izvestajRefList;
    }

    @XmlAnyElement
    public List<IzvestajRef> getIzvestajRefList() { return izvestajRefList; }
}