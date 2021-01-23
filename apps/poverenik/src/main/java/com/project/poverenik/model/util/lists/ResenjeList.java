package com.project.poverenik.model.util.lists;

import com.project.poverenik.model.resenje.Resenje;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({Resenje.class})
public class ResenjeList {

    private final List<Resenje> resenjeList;

    public ResenjeList() {
        this.resenjeList = new ArrayList<>();
    }

    public ResenjeList(List<Resenje> resenjeList) {
        this.resenjeList = resenjeList;
    }

    @XmlAnyElement
    public List<Resenje> getResenjeList() {
        return resenjeList;
    }
}
