package com.project.organ_vlasti.mappers;

import java.math.BigInteger;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.project.organ_vlasti.model.util.ComplexTypes.Tclan;
import com.project.organ_vlasti.model.util.ComplexTypes.Tfusnote;
import com.project.organ_vlasti.model.util.ComplexTypes.Topcije;
import com.project.organ_vlasti.model.zahtev.Zahtev;



public class ZahtevMapper {

	public static Zahtev mapFromDTO(Zahtev zahtevDTO, String id) {
		Zahtev zahtev = new Zahtev();

		zahtev.setId(id);
		zahtev.getOtherAttributes().put(new QName("about"), "http://zahtevi/" + id);
		zahtev.getOtherAttributes().put(new QName("vocab"),"http://examples/predicate/");
		zahtev.getOtherAttributes().put(new QName("property"),"pred:datum");
		zahtev.getOtherAttributes().put(new QName("datatype"),"xs:date");
		zahtev.getOtherAttributes().put(new QName("content"),zahtevDTO.getDatum().toString());
		
		zahtev.setMesto(zahtev.getMesto());
		
		zahtev.setCiljaniOrganVlasti(zahtevDTO.getCiljaniOrganVlasti());
		zahtev.getCiljaniOrganVlasti().getNazivOrgana().getOtherAttributes().put(new QName("property"),"pred:organ_vlasti");
		zahtev.getCiljaniOrganVlasti().getNazivOrgana().getOtherAttributes().put(new QName("datatype"),"xs:string");
		
		zahtev.setNaziv("З А Х Т Е В за приступ информацији од јавног значаја");
		
		zahtev.setTekstZahteva(zahtevDTO.getTekstZahteva());
		zahtev.getTekstZahteva().getContent().set(0, "На основу ");
		JAXBElement<Tclan> JaxbClan = (JAXBElement<Tclan>) zahtev.getTekstZahteva().getContent().get(1);
        JaxbClan.getValue().getContent().add("члана 15. ");
        JaxbClan.getValue().setBroj(BigInteger.valueOf(15));
        JAXBElement<Tclan.Stav> JaxbStav = (JAXBElement<Tclan.Stav>) JaxbClan.getValue().getContent().get(0);
        JaxbClan.getValue().getContent().add("ст. 1.");
        JaxbClan.getValue().setBroj(BigInteger.valueOf(1));
        zahtev.getTekstZahteva().getContent().set(1, JaxbClan);
        zahtev.getTekstZahteva().getContent().set(2, " Закона о слободном приступу информацијама од јавног значаја („Службени гласник РС“, бр. 120/04, 54/07, 104/09 и 36/10), од горе наведеног органа захтевам:*");
        zahtev.getTekstZahteva().getContent().set(3, (JAXBElement<Topcije>) zahtev.getTekstZahteva().getContent().get(3));
		zahtev.getTekstZahteva().getContent().set(4, "Овај захтев се односи на следеће информације:");
        zahtev.getTekstZahteva().getContent().set(5, (JAXBElement<String>) zahtev.getTekstZahteva().getContent().get(5));
        
        zahtev.setInformacijeOTraziocu(zahtevDTO.getInformacijeOTraziocu());
        String idUlogovanog = "1";
        zahtev.getInformacijeOTraziocu().getLice().getOsoba().getOtherAttributes().put(new QName("id"), idUlogovanog);
        zahtev.getInformacijeOTraziocu().getLice().getOsoba().getOtherAttributes().put(new QName("property"),"pred:podnosilac");
        zahtev.getInformacijeOTraziocu().getLice().getOsoba().getOtherAttributes().put(new QName("content"), idUlogovanog.toString());
        
        Tfusnote fusnote = new Tfusnote();
        fusnote.getFusnota().add("* У кућици означити која законска права на приступ информацијама желите да остварите.");
        fusnote.getFusnota().add("** У кућици означити начин достављања копије докумената.");
        fusnote.getFusnota().add("*** Када захтевате други начин достављања обавезно уписати који начин достављања захтевате.");
        zahtev.setFusnote(fusnote);

        return zahtev;
	}
}
