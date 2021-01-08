package com.project.poverenik.model.util.lists;

import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({ZalbaOdluka.class})
public class ZalbaOdlukaList {

    private final List<ZalbaOdluka> zalbaOdlukaList;

    public ZalbaOdlukaList() {
        this.zalbaOdlukaList = new ArrayList<>();
    }

    public ZalbaOdlukaList(List<ZalbaOdluka> zalbaOdlukaList) {
        this.zalbaOdlukaList = zalbaOdlukaList;
    }

    @XmlAnyElement
    public List<ZalbaOdluka> getZalbaOdlukaList() {
        return zalbaOdlukaList;
    }
}
