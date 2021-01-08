package com.project.poverenik.model.util.lists;

import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({ZalbaCutanje.class})
public class ZalbaCutanjeList {

    private final List<ZalbaCutanje> zalbaCutanjeList;

    public ZalbaCutanjeList() {
        this.zalbaCutanjeList = new ArrayList<>();
    }

    public ZalbaCutanjeList(List<ZalbaCutanje> zalbaCutanjeList) {
        this.zalbaCutanjeList = zalbaCutanjeList;
    }

    @XmlAnyElement
    public List<ZalbaCutanje> getZalbaCutanjeList() {
        return zalbaCutanjeList;
    }
}
