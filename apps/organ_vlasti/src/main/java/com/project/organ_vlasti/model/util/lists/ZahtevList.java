package com.project.organ_vlasti.model.util.lists;

import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import com.project.organ_vlasti.model.zahtev.Zahtev;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({Zahtev.class})
public class ZahtevList {
    private final List<Zahtev> zahtevList;

    public ZahtevList(){
        this.zahtevList = new ArrayList<>();
    }
    public ZahtevList(List<Zahtev> zahtevList){
        this.zahtevList = zahtevList;
    }

    @XmlAnyElement
    public List<Zahtev> getZahtevList() {
        return zahtevList;
    }
}
