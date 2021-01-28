package com.project.poverenik.mappers;

import java.math.BigInteger;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.project.poverenik.model.util.ComplexTypes.Tclan;
import com.project.poverenik.model.util.ComplexTypes.Tnapomena;
import com.project.poverenik.model.util.ComplexTypes.ObjectFactory;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;

public class ZalbaOdlukaMapper {
	
	public static ZalbaOdluka mapFromDTO(ZalbaOdluka zalbaOdlukaDTO, String id, String userEmail) {
		ObjectFactory of = new ObjectFactory();
		com.project.poverenik.model.zalba_odluka.ObjectFactory ofZO = new com.project.poverenik.model.zalba_odluka.ObjectFactory();
		ZalbaOdluka zalbaOdluka = ofZO.createZalbaOdluka();

		zalbaOdluka.setZalbaOdlukaBody(ofZO.createTzalba());
		zalbaOdluka.getZalbaOdlukaBody().setId(id);
    	zalbaOdluka.getZalbaOdlukaBody().getOtherAttributes().put(new QName("about"), "http://zalbe/odluka/" + id);
    	zalbaOdluka.getZalbaOdlukaBody().getOtherAttributes().put(new QName("vocab"),"http://examples/predicate/");
    	zalbaOdluka.getZalbaOdlukaBody().getOtherAttributes().put(new QName("property"),"pred:datum");
    	zalbaOdluka.getZalbaOdlukaBody().getOtherAttributes().put(new QName("datatype"),"xs:date");
    	zalbaOdluka.getZalbaOdlukaBody().getOtherAttributes().put(new QName("content"),zalbaOdlukaDTO.getZalbaOdlukaBody().getDatum().toString());
    	zalbaOdluka.getZalbaOdlukaBody().setNaslov("ЖАЛБА  ПРОТИВ  ОДЛУКЕ ОРГАНА  ВЛАСТИ КОЈОМ ЈЕ ОДБИЈЕН ИЛИ ОДБАЧЕН ЗАХТЕВ ЗА ПРИСТУП ИНФОРМАЦИЈИ");
    	zalbaOdluka.getZalbaOdlukaBody().setPodaciPovereniku(of.createTpodaciPovereniku());
    	zalbaOdluka.getZalbaOdlukaBody().getPodaciPovereniku().setAdresa(of.createTadresa());
    	zalbaOdluka.getZalbaOdlukaBody().getPodaciPovereniku().getAdresa().setMesto(of.createTadresaMesto());
    	zalbaOdluka.getZalbaOdlukaBody().getPodaciPovereniku().getAdresa().setUlica(of.createTadresaUlica());
    	zalbaOdluka.getZalbaOdlukaBody().getPodaciPovereniku().setUloga("Поверенику за информације од јавног значаја и заштиту података о личности");
    	zalbaOdluka.getZalbaOdlukaBody().getPodaciPovereniku().getAdresa().getMesto().setValue("Београд");
    	zalbaOdluka.getZalbaOdlukaBody().getPodaciPovereniku().getAdresa().getMesto().getOtherAttributes().put(new QName("property"), "pred:mesto");
    	zalbaOdluka.getZalbaOdlukaBody().getPodaciPovereniku().getAdresa().getMesto().getOtherAttributes().put(new QName("datatype"), "xs:string");
    	zalbaOdluka.getZalbaOdlukaBody().getPodaciPovereniku().getAdresa().getUlica().setValue("Булевар краља Александрa");
    	zalbaOdluka.getZalbaOdlukaBody().getPodaciPovereniku().getAdresa().getUlica().setBroj(15);
    	zalbaOdluka.getZalbaOdlukaBody().setPodnaslov("Ж А Л Б А ");

    	zalbaOdluka.getZalbaOdlukaBody().setZalilac(zalbaOdlukaDTO.getZalbaOdlukaBody().getZalilac());
    	zalbaOdluka.getZalbaOdlukaBody().getZalilac().getTipLica().getOsoba().getOtherAttributes().put(new QName("id"), userEmail);
    	zalbaOdluka.getZalbaOdlukaBody().getZalilac().getTipLica().getOsoba().getOtherAttributes().put(new QName("property"), "pred:podnosilac");
    	zalbaOdluka.getZalbaOdlukaBody().getZalilac().getTipLica().getOsoba().getOtherAttributes().put(new QName("content"), userEmail);

    	zalbaOdluka.getZalbaOdlukaBody().setProtivResenjaZakljucka(zalbaOdlukaDTO.getZalbaOdlukaBody().getProtivResenjaZakljucka());
    	zalbaOdluka.getZalbaOdlukaBody().getProtivResenjaZakljucka().getNazivOrganaKojiJeDoneoOdluku().getOtherAttributes().put(new QName("property"), "pred:organ_vlasti");
    	zalbaOdluka.getZalbaOdlukaBody().getProtivResenjaZakljucka().getNazivOrganaKojiJeDoneoOdluku().getOtherAttributes().put(new QName("datatype"), "xs:string");
    	zalbaOdluka.getZalbaOdlukaBody().getProtivResenjaZakljucka().getBroj().getOtherAttributes().put(new QName("rel"), "pred:referenceTo");
    	zalbaOdluka.getZalbaOdlukaBody().getProtivResenjaZakljucka().getBroj().getOtherAttributes().put(new QName("href"), "http://"+zalbaOdlukaDTO.getZalbaOdlukaBody().getProtivResenjaZakljucka().getBroj().getValue());

    	zalbaOdluka.getZalbaOdlukaBody().setSadrzaj(zalbaOdlukaDTO.getZalbaOdlukaBody().getSadrzaj());
    	zalbaOdluka.getZalbaOdlukaBody().getSadrzaj().getContent().set(0, "Наведеном одлуком органа власти (решењем, закључком, обавештењем у писаној форми са елементима одлуке) , супротно закону, одбијен-одбачен је мој захтев који сам поднео/ла-упутио/ла дана");
    	zalbaOdluka.getZalbaOdlukaBody().getSadrzaj().getContent().set(1, (JAXBElement<String>) zalbaOdlukaDTO.getZalbaOdlukaBody().getSadrzaj().getContent().get(1));
    	zalbaOdluka.getZalbaOdlukaBody().getSadrzaj().getContent().set(2, "године и тако ми ускраћено-онемогућено остваривање уставног и законског права на слободан приступ информацијама од јавног значаја. Oдлуку побијам у целости, односно у делу којим");
    	zalbaOdluka.getZalbaOdlukaBody().getSadrzaj().getContent().set(3, (JAXBElement<XMLGregorianCalendar>) zalbaOdlukaDTO.getZalbaOdlukaBody().getSadrzaj().getContent().get(3));
    	zalbaOdluka.getZalbaOdlukaBody().getSadrzaj().getContent().set(4, " јер није заснована на Закону о слободном приступу информацијама од јавног значаја. На основу изнетих разлога, предлажем да Повереник уважи моју жалбу,  поништи одлука првостепеног органа и омогући ми приступ траженој/им  информацији/ма.\r\n"
        		+ "    Жалбу подносим благовремено, у законском року утврђеном у");
    	zalbaOdluka.getZalbaOdlukaBody().getSadrzaj().getContent().set(5, (JAXBElement<Tclan.Stav>) zalbaOdlukaDTO.getZalbaOdlukaBody().getSadrzaj().getContent().get(5));
    	JAXBElement<Tclan> JaxbClan = (JAXBElement<Tclan>) zalbaOdlukaDTO.getZalbaOdlukaBody().getSadrzaj().getContent().get(5);
        JAXBElement<Tclan.Stav> stav = (JAXBElement<Tclan.Stav>) JaxbClan.getValue().getContent().get(0);
        stav.getValue().setValue("ст. 1.");
        stav.getValue().setBroj(Integer.valueOf(1));
        JaxbClan.getValue().getContent().set(0, "члану 22. ");
        JaxbClan.getValue().setBroj(BigInteger.valueOf(22));
        JaxbClan.getValue().getContent().add(stav);
        zalbaOdluka.getZalbaOdlukaBody().getSadrzaj().getContent().set(5, JaxbClan);
    	zalbaOdluka.getZalbaOdlukaBody().getSadrzaj().getContent().set(6, "Закона о слободном приступу информацијама од јавног значаја.");
    	zalbaOdluka.getZalbaOdlukaBody().setPodaciOPodnosiocuZalbe(zalbaOdlukaDTO.getZalbaOdlukaBody().getPodaciOPodnosiocuZalbe());
    	
    	Tnapomena napomena = of.createTnapomena();
    	napomena.setNaslov("Напомена:");
    	napomena.getTacka().add("У жалби се мора навести одлука која се побија (решење, закључак, обавештење), назив \r\n"
    			+ "            органа који је одлуку донео, као и број и датум одлуке. Довољно је да жалилац наведе у \r\n"
    			+ "            жалби у ком погледу је незадовољан одлуком, с тим да жалбу не мора посебно образложити. ");
    	napomena.getTacka().add("Ако жалбу изјављује на овом обрасцу, додатно образложење може  посебно приложити. \r\n"
    			+ "             Уз жалбу обавезно приложити копију поднетог захтева и доказ о његовој предаји-упућивању органу \r\n"
    			+ "             као и копију одлуке органа која се оспорава жалбом.");
    	zalbaOdluka.getZalbaOdlukaBody().setNapomena(napomena);
    
		
		return zalbaOdluka;

	}

}
