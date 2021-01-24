package com.project.poverenik.mappers;

import java.math.BigInteger;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.project.poverenik.model.util.ComplexTypes.Tadresa;
import com.project.poverenik.model.util.ComplexTypes.Tclan;
import com.project.poverenik.model.util.ComplexTypes.Topcije;
import com.project.poverenik.model.util.ComplexTypes.TpodaciPovereniku;
import com.project.poverenik.model.util.ComplexTypes.TsadrzajZalbe;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;

public class ZalbaCutanjeMapper {
	
	public static ZalbaCutanje mapFromDTO(ZalbaCutanje zalbaCutanjeDTO, String id, String userEmail) {
		ZalbaCutanje zalbaCutanje = new ZalbaCutanje();

		zalbaCutanje.setId(id);
    	zalbaCutanje.getOtherAttributes().put(new QName("about"), "http://zalbe/cutanje/" + id);
    	zalbaCutanje.getOtherAttributes().put(new QName("vocab"),"http://examples/predicate/");
    	zalbaCutanje.getOtherAttributes().put(new QName("property"),"pred:datum");
    	zalbaCutanje.getOtherAttributes().put(new QName("datatype"),"xs:date");
    	zalbaCutanje.getOtherAttributes().put(new QName("content"),zalbaCutanjeDTO.getDatum().toString());
    	zalbaCutanje.setNaziv("ЖАЛБА КАДА ОРГАН ВЛАСТИ НИЈЕ ПОСТУПИО/ није поступио у целости/ ПО ЗАХТЕВУ ТРАЖИОЦА У ЗАКОНСКОМ  РОКУ  (ЋУТАЊЕ УПРАВЕ)");
    	zalbaCutanje.setPodaciOPrimaocu(new TpodaciPovereniku());
    	zalbaCutanje.getPodaciOPrimaocu().setAdresa(new Tadresa());
    	zalbaCutanje.getPodaciOPrimaocu().getAdresa().setMesto(new Tadresa.Mesto());
    	zalbaCutanje.getPodaciOPrimaocu().getAdresa().setUlica(new Tadresa.Ulica());
    	zalbaCutanje.getPodaciOPrimaocu().setUloga("Повереник за информације од јавног значаја и заштиту података о личности");
    	zalbaCutanje.getPodaciOPrimaocu().getAdresa().getMesto().setValue("Београд");
    	zalbaCutanje.getPodaciOPrimaocu().getAdresa().getMesto().getOtherAttributes().put(new QName("property"), "pred:mesto");
    	zalbaCutanje.getPodaciOPrimaocu().getAdresa().getMesto().getOtherAttributes().put(new QName("datatype"), "xs:string");
    	zalbaCutanje.getPodaciOPrimaocu().getAdresa().getUlica().setValue("Булевар краља Александрa");
    	zalbaCutanje.getPodaciOPrimaocu().getAdresa().getUlica().setBroj(15);

    	zalbaCutanje.setSadrzajZalbe(zalbaCutanjeDTO.getSadrzajZalbe());
        zalbaCutanje.getSadrzajZalbe().getContent().set(0, "У складу са ");
        JAXBElement<Tclan> JaxbClan = (JAXBElement<Tclan>) zalbaCutanje.getSadrzajZalbe().getContent().get(1);
        JaxbClan.getValue().getContent().add("чланом 22.");
        JaxbClan.getValue().setBroj(BigInteger.valueOf(22));
        zalbaCutanje.getSadrzajZalbe().getContent().set(1, JaxbClan);
        zalbaCutanje.getSadrzajZalbe().getContent().set(2, " Закона о слободном приступу информацијама од јавног значаја подносим: Ж А Л Б У против ");
        JAXBElement<TsadrzajZalbe.CiljaniOrganVlasti> JaxbClanOrganVlasti = (JAXBElement<TsadrzajZalbe.CiljaniOrganVlasti>) zalbaCutanjeDTO.getSadrzajZalbe().getContent().get(3);
        JaxbClanOrganVlasti.getValue().getOtherAttributes().put(new QName("property"), "pred:organ_vlasti");
        JaxbClanOrganVlasti.getValue().getOtherAttributes().put(new QName("datatype"), "xs:string");
        zalbaCutanje.getSadrzajZalbe().getContent().set(3, JaxbClanOrganVlasti);
        zalbaCutanje.getSadrzajZalbe().getContent().set(4, " ( навести назив органа) због тога што орган власти: ");
        zalbaCutanje.getSadrzajZalbe().getContent().set(5, (JAXBElement<Topcije>) zalbaCutanjeDTO.getSadrzajZalbe().getContent().get(5));
        zalbaCutanje.getSadrzajZalbe().getContent().set(6, "(подвући  због чега се изјављује жалба) по мом захтеву  за слободан приступ информацијама од јавног значаја који сам поднео  том органу  дана ");
        zalbaCutanje.getSadrzajZalbe().getContent().set(7, (JAXBElement<XMLGregorianCalendar>) zalbaCutanjeDTO.getSadrzajZalbe().getContent().get(7));
        zalbaCutanje.getSadrzajZalbe().getContent().set(8, " године, а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног значаја омогући увид- копија документа који садржи информације  о /у вези са : ");
        zalbaCutanje.getSadrzajZalbe().getContent().set(9, (JAXBElement<String>) zalbaCutanjeDTO.getSadrzajZalbe().getContent().get(9));
        zalbaCutanje.getSadrzajZalbe().getContent().set(10, "На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им  информацији/ма. Као доказ , уз жалбу достављам копију захтева са доказом о предаји органу власти.");
        JAXBElement<String> JaxbNapomena = (JAXBElement<String>) zalbaCutanje.getSadrzajZalbe().getContent().get(11);
        JaxbNapomena.setValue("Напомена: Код жалбе  због непоступању по захтеву у целости, треба приложити и добијени одговор органа власти.");
        zalbaCutanje.getSadrzajZalbe().getContent().set(11, JaxbNapomena);
        
        zalbaCutanje.setPodaciOPodnosiocu(zalbaCutanjeDTO.getPodaciOPodnosiocu());
        zalbaCutanje.getPodaciOPodnosiocu().getOsoba().getOtherAttributes().put(new QName("id"), userEmail);
        zalbaCutanje.getPodaciOPodnosiocu().getOsoba().getOtherAttributes().put(new QName("property"), "pred:podnosilac");
        zalbaCutanje.getPodaciOPodnosiocu().getOsoba().getOtherAttributes().put(new QName("content"), userEmail);
        
        return zalbaCutanje;

	}

}
