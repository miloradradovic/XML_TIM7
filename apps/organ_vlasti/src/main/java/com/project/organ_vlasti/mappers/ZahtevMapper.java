package com.project.organ_vlasti.mappers;

import com.project.organ_vlasti.model.util.ComplexTypes.Tclan;
import com.project.organ_vlasti.model.util.ComplexTypes.Tfusnote;
import com.project.organ_vlasti.model.util.ComplexTypes.Topcije;
import com.project.organ_vlasti.model.util.ComplexTypes.Topis;
import com.project.organ_vlasti.model.zahtev.ObjectFactory;
import com.project.organ_vlasti.model.zahtev.Tzahtev;
import com.project.organ_vlasti.model.zahtev.Zahtev;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.math.BigInteger;


public class ZahtevMapper {

    public static Zahtev mapFromDTO(Zahtev zahtevDTO, String id, String userEmail) {
        ObjectFactory ofZ = new ObjectFactory();
        Zahtev zahtev = ofZ.createZahtev();

        zahtev.setZahtevBody(ofZ.createTzahtev());

        zahtev.getZahtevBody().setId(id);
        zahtev.getZahtevBody().setStatus(new Tzahtev.Status());
        zahtev.getZahtevBody().getStatus().setValue("neobradjen");
        zahtev.getZahtevBody().getOtherAttributes().put(new QName("about"), "http://zahtevi/" + id);
        zahtev.getZahtevBody().getOtherAttributes().put(new QName("vocab"), "http://examples/predicate/");
        zahtev.getZahtevBody().getOtherAttributes().put(new QName("property"), "pred:datum");
        zahtev.getZahtevBody().getOtherAttributes().put(new QName("datatype"), "xs:dateTime");
        zahtev.getZahtevBody().getOtherAttributes().put(new QName("content"), zahtevDTO.getZahtevBody().getDatum().toString());

        zahtev.getZahtevBody().setMesto(zahtevDTO.getZahtevBody().getMesto());
        zahtev.getZahtevBody().getMesto().getOtherAttributes().put(new QName("property"), "pred:mesto");
        zahtev.getZahtevBody().getMesto().getOtherAttributes().put(new QName("datatype"), "xs:string");

        zahtev.getZahtevBody().setCiljaniOrganVlasti(zahtevDTO.getZahtevBody().getCiljaniOrganVlasti());
        zahtev.getZahtevBody().getCiljaniOrganVlasti().getNazivOrgana().getOtherAttributes().put(new QName("property"), "pred:organ_vlasti");
        zahtev.getZahtevBody().getCiljaniOrganVlasti().getNazivOrgana().getOtherAttributes().put(new QName("datatype"), "xs:string");

        zahtev.getZahtevBody().setNaziv("З А Х Т Е В за приступ информацији од јавног значаја");

        zahtev.getZahtevBody().setTekstZahteva(zahtevDTO.getZahtevBody().getTekstZahteva());
        zahtev.getZahtevBody().getTekstZahteva().getContent().set(0, "На основу ");
        JAXBElement<Tclan> JaxbClan = (JAXBElement<Tclan>) zahtev.getZahtevBody().getTekstZahteva().getContent().get(1);
        JaxbClan.getValue().getContent().add("члана 15. ");
        JaxbClan.getValue().setBroj(BigInteger.valueOf(15));
        JAXBElement<Tclan.Stav> JaxbStav = (JAXBElement<Tclan.Stav>) JaxbClan.getValue().getContent().get(0);
        JaxbClan.getValue().getContent().add("ст. 1.");
        JaxbClan.getValue().setBroj(BigInteger.valueOf(1));
        zahtev.getZahtevBody().getTekstZahteva().getContent().set(1, JaxbClan);
        zahtev.getZahtevBody().getTekstZahteva().getContent().set(2, " Закона о слободном приступу информацијама од јавног значаја („Службени гласник РС“, бр. 120/04, 54/07, 104/09 и 36/10), од горе наведеног органа захтевам:*");
        zahtev.getZahtevBody().getTekstZahteva().getContent().set(3, (JAXBElement<Topcije>) zahtev.getZahtevBody().getTekstZahteva().getContent().get(3));
        zahtev.getZahtevBody().getTekstZahteva().getContent().set(4, "Овај захтев се односи на следеће информације:");
        zahtev.getZahtevBody().getTekstZahteva().getContent().set(5, (JAXBElement<Topis>) zahtev.getZahtevBody().getTekstZahteva().getContent().get(5));
        // zahtev.getZahtevBody().getTekstZahteva().

        zahtev.getZahtevBody().setInformacijeOTraziocu(zahtevDTO.getZahtevBody().getInformacijeOTraziocu());
        zahtev.getZahtevBody().getInformacijeOTraziocu().getLice().getOsoba().getOtherAttributes().put(new QName("id"), userEmail);
        zahtev.getZahtevBody().getInformacijeOTraziocu().getLice().getOsoba().getOtherAttributes().put(new QName("rel"), "pred:podnosilac");
        zahtev.getZahtevBody().getInformacijeOTraziocu().getLice().getOsoba().getOtherAttributes().put(new QName("href"), "http://users/" + userEmail);

        com.project.organ_vlasti.model.util.ComplexTypes.ObjectFactory of = new com.project.organ_vlasti.model.util.ComplexTypes.ObjectFactory();
        Tfusnote fusnote = of.createTfusnote();
        fusnote.getFusnota().add("* У кућици означити која законска права на приступ информацијама желите да остварите.");
        fusnote.getFusnota().add("** У кућици означити начин достављања копије докумената.");
        fusnote.getFusnota().add("* Када захтевате други начин достављања обавезно уписати који начин достављања захтевате.");
        zahtev.getZahtevBody().setFusnote(fusnote);

        return zahtev;
    }
}