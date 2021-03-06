package com.project.organ_vlasti.mappers;

import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import com.project.organ_vlasti.model.obavestenje.ObjectFactory;
import com.project.organ_vlasti.model.util.ComplexTypes.*;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.math.BigInteger;


public class ObavestenjeMapper {

    public static Obavestenje mapFromDTO(Obavestenje obavestenjeDTO, String id, String userEmail) {
        ObjectFactory ofO = new ObjectFactory();
        Obavestenje obavestenje = ofO.createObavestenje();

        obavestenje.setObavestenjeBody(ofO.createTobavestenje());

        String linkNaZahtev = "http://localhost:4200/detaljni-prikaz-zahteva?zahtev_id=" + obavestenjeDTO.getObavestenjeBody().getIdZahteva();
        obavestenje.getObavestenjeBody().setId(id);
        obavestenje.getObavestenjeBody().setBroj(id);
        obavestenje.getObavestenjeBody().setIdZahteva(obavestenjeDTO.getObavestenjeBody().getIdZahteva());
        obavestenje.getObavestenjeBody().getOtherAttributes().put(new QName("about"), "http://obavestenja/" + obavestenje.getObavestenjeBody().getBroj());
        obavestenje.getObavestenjeBody().getOtherAttributes().put(new QName("vocab"), "http://examples/predicate/");
        obavestenje.getObavestenjeBody().getOtherAttributes().put(new QName("rel"), "pred:zahtev");
        obavestenje.getObavestenjeBody().getOtherAttributes().put(new QName("href"), "http://zahtevi/" + obavestenjeDTO.getObavestenjeBody().getIdZahteva());
        obavestenje.getObavestenjeBody().getOtherAttributes().put(new QName("property"), "pred:datum");
        obavestenje.getObavestenjeBody().getOtherAttributes().put(new QName("datatype"), "xs:dateTime");
        obavestenje.getObavestenjeBody().getOtherAttributes().put(new QName("content"), obavestenjeDTO.getObavestenjeBody().getDatum().toString());
        obavestenje.getObavestenjeBody().getOtherAttributes().put(new QName("link_na_zahtev"), linkNaZahtev);

        obavestenje.getObavestenjeBody().setNazivOrgana(obavestenjeDTO.getObavestenjeBody().getNazivOrgana());
        obavestenje.getObavestenjeBody().getNazivOrgana().getOtherAttributes().put(new QName("property"), "pred:organ_vlasti");
        obavestenje.getObavestenjeBody().getNazivOrgana().getOtherAttributes().put(new QName("datatype"), "xs:string");

        obavestenje.getObavestenjeBody().setInformacijeOPodnosiocu(obavestenjeDTO.getObavestenjeBody().getInformacijeOPodnosiocu());
        obavestenje.getObavestenjeBody().getInformacijeOPodnosiocu().getLice().getOsoba().getOtherAttributes().put(new QName("id"), userEmail);
        obavestenje.getObavestenjeBody().getInformacijeOPodnosiocu().getLice().getOsoba().getOtherAttributes().put(new QName("rel"), "pred:podnosilac");
        obavestenje.getObavestenjeBody().getInformacijeOPodnosiocu().getLice().getOsoba().getOtherAttributes().put(new QName("href"), "http://users/" + userEmail);

        obavestenje.getObavestenjeBody().setNaslov("ОБАВЕШТЕЊЕ");
        obavestenje.getObavestenjeBody().setPodnaslov("о стављању на увид документа који садржи тражену информацију и о изради копије");

        obavestenje.getObavestenjeBody().setTekstZahteva(obavestenjeDTO.getObavestenjeBody().getTekstZahteva());
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(0, "На основу ");
        JAXBElement<Tclan> JaxbClan = (JAXBElement<Tclan>) obavestenje.getObavestenjeBody().getTekstZahteva().getContent().get(1);
        JaxbClan.getValue().getContent().add("члана 16. ");
        JaxbClan.getValue().setBroj(BigInteger.valueOf(16));
        JAXBElement<Tclan.Stav> JaxbStav = (JAXBElement<Tclan.Stav>) JaxbClan.getValue().getContent().get(0);
        JaxbClan.getValue().getContent().add("ст. 1.");
        JaxbClan.getValue().setBroj(BigInteger.valueOf(1));
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(1, JaxbClan);
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(2, " Закона о слободном приступу информацијама од јавног значаја, поступајући по вашем захтеву за слободан приступ информацијама од");
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(3, (JAXBElement<String>) obavestenje.getObavestenjeBody().getTekstZahteva().getContent().get(3));
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(4, " којим сте тражили увид у докменте са инфомрацијама о/у вези са:");
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(5, (JAXBElement<Topis>) obavestenje.getObavestenjeBody().getTekstZahteva().getContent().get(5));
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(6, "обавештавамо вас да дана ");
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(7, (JAXBElement<XMLGregorianCalendar>) obavestenje.getObavestenjeBody().getTekstZahteva().getContent().get(7));
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(8, ", у ");
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(9, (JAXBElement<String>) obavestenje.getObavestenjeBody().getTekstZahteva().getContent().get(9));
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(10, "часова, односно у времену од");
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(11, (JAXBElement<TradnoVreme>) obavestenje.getObavestenjeBody().getTekstZahteva().getContent().get(11));
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(12, " часова,у просторијама органа у");
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(13, (JAXBElement<Tadresa>) obavestenje.getObavestenjeBody().getTekstZahteva().getContent().get(13));
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(14, " канцеларија бр. ");
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(15, (JAXBElement<BigInteger>) obavestenje.getObavestenjeBody().getTekstZahteva().getContent().get(15));
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(16, "можете извршити увид\r\n"
                + "        у документ/е у коме је садржана тражена информација.\r\n"
                + "        Том приликом на ваш захтев, може вам се издати и копија документа са траженом\r\n"
                + "        информацијом.");

        JAXBElement<TopisTroskova> opis = (JAXBElement<TopisTroskova>) obavestenjeDTO.getObavestenjeBody().getTekstZahteva().getContent().get(17);
        opis.getValue().getContent().set(0, "Troшкови су утвршени Уредбом Владе Србије (\"Сл. гласник РС\", бр 8/06), и\r\n"
                + "           то: копија стране А4 формата износи");
        JAXBElement<TopisTroskova.Cena> cena1 = (JAXBElement<TopisTroskova.Cena>) opis.getValue().getContent().get(1);
        cena1.getValue().setValue("3,00");
        cena1.getValue().setValuta("dinar");
        opis.getValue().getContent().set(1, cena1);
        opis.getValue().getContent().set(2, " динара, А3 формата ");
        JAXBElement<TopisTroskova.Cena> cena3 = (JAXBElement<TopisTroskova.Cena>) opis.getValue().getContent().get(3);
        cena3.getValue().setValue("6,00");
        cena3.getValue().setValuta("dinar");
        opis.getValue().getContent().set(3, cena3);
        opis.getValue().getContent().set(4, " динара, CD ");
        JAXBElement<TopisTroskova.Cena> cena5 = (JAXBElement<TopisTroskova.Cena>) opis.getValue().getContent().get(5);
        cena5.getValue().setValue("35,00");
        cena5.getValue().setValuta("dinar");
        opis.getValue().getContent().set(5, cena5);
        opis.getValue().getContent().set(6, " динара, дискете ");
        JAXBElement<TopisTroskova.Cena> cena7 = (JAXBElement<TopisTroskova.Cena>) opis.getValue().getContent().get(7);
        cena7.getValue().setValue("20,00");
        cena7.getValue().setValuta("dinar");
        opis.getValue().getContent().set(7, cena7);
        opis.getValue().getContent().set(8, " динара, DVD ");
        JAXBElement<TopisTroskova.Cena> cena9 = (JAXBElement<TopisTroskova.Cena>) opis.getValue().getContent().get(9);
        cena9.getValue().setValue("40,00");
        cena9.getValue().setValuta("dinar");
        opis.getValue().getContent().set(9, cena9);
        opis.getValue().getContent().set(10, " динара, аудио-касета -");
        JAXBElement<TopisTroskova.Cena> cena11 = (JAXBElement<TopisTroskova.Cena>) opis.getValue().getContent().get(11);
        cena11.getValue().setValue("150,00");
        cena11.getValue().setValuta("dinar");
        opis.getValue().getContent().set(11, cena11);
        opis.getValue().getContent().set(12, " динара, видео-касета ");
        JAXBElement<TopisTroskova.Cena> cena13 = (JAXBElement<TopisTroskova.Cena>) opis.getValue().getContent().get(13);
        cena13.getValue().setValue("300,00");
        cena13.getValue().setValuta("dinar");
        opis.getValue().getContent().set(13, cena13);
        opis.getValue().getContent().set(14, " динара, претварање једне стране документа из физичком у електронски облик - ");
        JAXBElement<TopisTroskova.Cena> cena15 = (JAXBElement<TopisTroskova.Cena>) opis.getValue().getContent().get(15);
        cena15.getValue().setValue("30,00");
        cena15.getValue().setValuta("dinar");
        opis.getValue().getContent().set(15, cena15);
        opis.getValue().getContent().set(16, " динара.");
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(17, (JAXBElement<TopisTroskova>) obavestenje.getObavestenjeBody().getTekstZahteva().getContent().get(17));

        Object o = obavestenjeDTO.getObavestenjeBody().getTekstZahteva().getContent().get(18);
        JAXBElement<TukupanTrosak> trosak = (JAXBElement<TukupanTrosak>) obavestenjeDTO.getObavestenjeBody().getTekstZahteva().getContent().get(18);
        trosak.getValue().getContent().set(0, "Износ укупних трошкова израде копије докумената по вашем захтеву износи ");
        trosak.getValue().getContent().set(1, (JAXBElement<String>) trosak.getValue().getContent().get(1));
        trosak.getValue().getContent().set(2, "  динара и уплаћује се на жиро-рачун Буџета Републиек Србије бр. ");
        JAXBElement<String> brojRacuna = (JAXBElement<String>) trosak.getValue().getContent().get(3);
        brojRacuna.setValue("840-742328-843-30");
        trosak.getValue().getContent().set(3, brojRacuna);
        trosak.getValue().getContent().set(4, "с позивом на број ");
        JAXBElement<BigInteger> pozivNaBroj = (JAXBElement<BigInteger>) trosak.getValue().getContent().get(5);
        pozivNaBroj.setValue(BigInteger.valueOf(97));
        trosak.getValue().getContent().set(5, pozivNaBroj);
        trosak.getValue().getContent().set(6, " - ознака шифре општине/града где се налази орган власти\r\n"
                + "            (из Правилника о условима и начину вођења рачуна - \"Сл. гласник РС\", 20/07...40/10).");
        obavestenje.getObavestenjeBody().getTekstZahteva().getContent().set(18, (JAXBElement<TukupanTrosak>) obavestenje.getObavestenjeBody().getTekstZahteva().getContent().get(18));

        obavestenje.getObavestenjeBody().setOpcijaDostave(obavestenjeDTO.getObavestenjeBody().getOpcijaDostave());

        return obavestenje;
    }
}