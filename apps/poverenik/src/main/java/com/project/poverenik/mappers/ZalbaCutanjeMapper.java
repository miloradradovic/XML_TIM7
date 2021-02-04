package com.project.poverenik.mappers;

import com.project.poverenik.model.util.ComplexTypes.ObjectFactory;
import com.project.poverenik.model.util.ComplexTypes.Tclan;
import com.project.poverenik.model.util.ComplexTypes.Topcije;
import com.project.poverenik.model.util.ComplexTypes.TsadrzajZalbe;
import com.project.poverenik.model.zalba_cutanje.Tzalba;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.math.BigInteger;

public class ZalbaCutanjeMapper {

    public static ZalbaCutanje mapFromDTO(ZalbaCutanje zalbaCutanjeDTO, String id, String userEmail) {
        ObjectFactory of = new ObjectFactory();
        com.project.poverenik.model.zalba_cutanje.ObjectFactory ofZC = new com.project.poverenik.model.zalba_cutanje.ObjectFactory();
        ZalbaCutanje zalbaCutanje = ofZC.createZalbaCutanje();

        zalbaCutanje.setZalbaCutanjeBody(ofZC.createTzalba());
        zalbaCutanje.getZalbaCutanjeBody().setId(id);
        zalbaCutanje.getZalbaCutanjeBody().getOtherAttributes().put(new QName("about"), "http://zalbe/cutanje/" + id);
        zalbaCutanje.getZalbaCutanjeBody().getOtherAttributes().put(new QName("vocab"), "http://examples/predicate/");
        zalbaCutanje.getZalbaCutanjeBody().getOtherAttributes().put(new QName("property"), "pred:datum");
        zalbaCutanje.getZalbaCutanjeBody().getOtherAttributes().put(new QName("datatype"), "xs:date");
        zalbaCutanje.getZalbaCutanjeBody().getOtherAttributes().put(new QName("content"), zalbaCutanjeDTO.getZalbaCutanjeBody().getDatum().toString());

        zalbaCutanje.getZalbaCutanjeBody().setZahtev(zalbaCutanjeDTO.getZalbaCutanjeBody().getZahtev());
        zalbaCutanje.getZalbaCutanjeBody().getZahtev().getOtherAttributes().put(new QName("property"), "pred:zahtev");
        zalbaCutanje.getZalbaCutanjeBody().getZahtev().getOtherAttributes().put(new QName("datatype"), "xs:string");
        zalbaCutanje.getZalbaCutanjeBody().getZahtev().getOtherAttributes().put(new QName("content"), zalbaCutanje.getZalbaCutanjeBody().getZahtev().getValue());
        zalbaCutanje.getZalbaCutanjeBody().getZahtev().setValue("http://localhost:8085/zahtev/" + zalbaCutanje.getZalbaCutanjeBody().getZahtev().getValue());

        zalbaCutanje.getZalbaCutanjeBody().setStatus(new Tzalba.Status());
        zalbaCutanje.getZalbaCutanjeBody().getStatus().setValue("neobradjena");
        zalbaCutanje.getZalbaCutanjeBody().getStatus().getOtherAttributes().put(new QName("property"), "pred:status");
        zalbaCutanje.getZalbaCutanjeBody().getStatus().getOtherAttributes().put(new QName("datatype"), "xs:string");

        zalbaCutanje.getZalbaCutanjeBody().setNaziv("ЖАЛБА КАДА ОРГАН ВЛАСТИ НИЈЕ ПОСТУПИО/ није поступио у целости/ ПО ЗАХТЕВУ ТРАЖИОЦА У ЗАКОНСКОМ  РОКУ  (ЋУТАЊЕ УПРАВЕ)");
        zalbaCutanje.getZalbaCutanjeBody().setPodaciOPrimaocu(of.createTpodaciPovereniku());
        zalbaCutanje.getZalbaCutanjeBody().getPodaciOPrimaocu().setAdresa(of.createTadresa());
        zalbaCutanje.getZalbaCutanjeBody().getPodaciOPrimaocu().getAdresa().setMesto(of.createTadresaMesto());
        zalbaCutanje.getZalbaCutanjeBody().getPodaciOPrimaocu().getAdresa().setUlica(of.createTadresaUlica());
        zalbaCutanje.getZalbaCutanjeBody().getPodaciOPrimaocu().setUloga("Повереник за информације од јавног значаја и заштиту података о личности");
        zalbaCutanje.getZalbaCutanjeBody().getPodaciOPrimaocu().getAdresa().getMesto().setValue("Београд");
        zalbaCutanje.getZalbaCutanjeBody().getPodaciOPrimaocu().getAdresa().getUlica().setValue("Булевар краља Александрa");
        zalbaCutanje.getZalbaCutanjeBody().getPodaciOPrimaocu().getAdresa().getUlica().setBroj(15);

        zalbaCutanje.getZalbaCutanjeBody().setSadrzajZalbe(zalbaCutanjeDTO.getZalbaCutanjeBody().getSadrzajZalbe());
        zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().set(0, "У складу са ");
        JAXBElement<Tclan> JaxbClan = (JAXBElement<Tclan>) zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().get(1);
        JaxbClan.getValue().getContent().add("чланом 22.");
        JaxbClan.getValue().setBroj(BigInteger.valueOf(22));
        zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().set(1, JaxbClan);
        zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().set(2, " Закона о слободном приступу информацијама од јавног значаја подносим: Ж А Л Б У против ");
        JAXBElement<TsadrzajZalbe.CiljaniOrganVlasti> JaxbClanOrganVlasti = (JAXBElement<TsadrzajZalbe.CiljaniOrganVlasti>) zalbaCutanjeDTO.getZalbaCutanjeBody().getSadrzajZalbe().getContent().get(3);
        JaxbClanOrganVlasti.getValue().getOtherAttributes().put(new QName("property"), "pred:organ_vlasti");
        JaxbClanOrganVlasti.getValue().getOtherAttributes().put(new QName("datatype"), "xs:string");
        zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().set(3, JaxbClanOrganVlasti);
        zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().set(4, " ( навести назив органа) због тога што орган власти: ");
        zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().set(5, zalbaCutanjeDTO.getZalbaCutanjeBody().getSadrzajZalbe().getContent().get(5));
        zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().set(6, "(подвући  због чега се изјављује жалба) по мом захтеву  за слободан приступ информацијама од јавног значаја који сам поднео  том органу  дана ");
        zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().set(7, zalbaCutanjeDTO.getZalbaCutanjeBody().getSadrzajZalbe().getContent().get(7));
        zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().set(8, " године, а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног значаја омогући увид- копија документа који садржи информације  о /у вези са : ");
        zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().set(9, zalbaCutanjeDTO.getZalbaCutanjeBody().getSadrzajZalbe().getContent().get(9));
        zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().set(10, "На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им  информацији/ма. Као доказ , уз жалбу достављам копију захтева са доказом о предаји органу власти.");
        JAXBElement<String> JaxbNapomena = (JAXBElement<String>) zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().get(11);
        JaxbNapomena.setValue("Напомена: Код жалбе  због непоступању по захтеву у целости, треба приложити и добијени одговор органа власти.");
        zalbaCutanje.getZalbaCutanjeBody().getSadrzajZalbe().getContent().set(11, JaxbNapomena);

        zalbaCutanje.getZalbaCutanjeBody().setPodaciOPodnosiocu(zalbaCutanjeDTO.getZalbaCutanjeBody().getPodaciOPodnosiocu());
        zalbaCutanje.getZalbaCutanjeBody().getPodaciOPodnosiocu().getOsoba().getOtherAttributes().put(new QName("id"), userEmail);
        zalbaCutanje.getZalbaCutanjeBody().getPodaciOPodnosiocu().getOsoba().getOtherAttributes().put(new QName("rel"), "pred:podnosilac");
        zalbaCutanje.getZalbaCutanjeBody().getPodaciOPodnosiocu().getOsoba().getOtherAttributes().put(new QName("href"), "http://users/" + userEmail);

        zalbaCutanje.getZalbaCutanjeBody().getPodaciOPodnosiocu().getAdresa().getMesto().getOtherAttributes().put(new QName("property"), "pred:mesto");
        zalbaCutanje.getZalbaCutanjeBody().getPodaciOPodnosiocu().getAdresa().getMesto().getOtherAttributes().put(new QName("datatype"), "xs:string");

        return zalbaCutanje;

    }

}
