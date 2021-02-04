package com.project.organ_vlasti.model.util.lists;

import com.project.organ_vlasti.model.resenje.database.ResenjeRef;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({ResenjeRef.class})
public class ResenjeRefList {
    private final List<ResenjeRef> resenjeRefList;

    public ResenjeRefList() {
        this.resenjeRefList = new ArrayList<>();
    }

    public ResenjeRefList(List<ResenjeRef> resenjeRefList) {
        this.resenjeRefList = resenjeRefList;
    }

    @XmlAnyElement
    public List<ResenjeRef> getMessageList() {
        return resenjeRefList;
    }
}