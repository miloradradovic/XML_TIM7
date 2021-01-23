package com.project.poverenik.mappers;

import java.math.BigInteger;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.project.poverenik.model.util.ComplexTypes.Tadresa;
import com.project.poverenik.model.util.ComplexTypes.Tclan;
import com.project.poverenik.model.util.ComplexTypes.Tnapomena;
import com.project.poverenik.model.util.ComplexTypes.Topcije;
import com.project.poverenik.model.util.ComplexTypes.TpodaciPovereniku;
import com.project.poverenik.model.util.ComplexTypes.TprotivResenjaZakljucka;
import com.project.poverenik.model.util.ComplexTypes.TprotivResenjaZakljucka.NazivOrganaKojiJeDoneoOdluku;
import com.project.poverenik.model.util.ComplexTypes.TsadrzajZalbe;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;

public class ZalbaOdlukaMapper {
	
	public static ZalbaOdluka mapFromDTO(ZalbaOdluka zalbaOdluka, String id) {
		
		zalbaOdluka.setId(id);
    	zalbaOdluka.getOtherAttributes().put(new QName("about"), "http://zalbe/odluka/" + id);
    	zalbaOdluka.getOtherAttributes().put(new QName("vocab"),"http://examples/predicate/");
    	zalbaOdluka.getOtherAttributes().put(new QName("property"),"pred:datum");
    	zalbaOdluka.getOtherAttributes().put(new QName("datatype"),"xs:date");
    	zalbaOdluka.getOtherAttributes().put(new QName("content"),zalbaOdluka.getDatum().toString());
    	zalbaOdluka.setNaslov("ЖАЛБА  ПРОТИВ  ОДЛУКЕ ОРГАНА  ВЛАСТИ КОЈОМ ЈЕ ОДБИЈЕН ИЛИ ОДБАЧЕН ЗАХТЕВ ЗА ПРИСТУП ИНФОРМАЦИЈИ");
    	zalbaOdluka.setPodaciPovereniku(new TpodaciPovereniku());
    	zalbaOdluka.getPodaciPovereniku().setAdresa(new Tadresa());
    	zalbaOdluka.getPodaciPovereniku().getAdresa().setMesto(new Tadresa.Mesto());
    	zalbaOdluka.getPodaciPovereniku().getAdresa().setUlica(new Tadresa.Ulica());
    	zalbaOdluka.getPodaciPovereniku().setUloga("Поверенику за информације од јавног значаја и заштиту података о личности");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getMesto().setValue("Београд");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getMesto().getOtherAttributes().put(new QName("property"), "pred:mesto");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getMesto().getOtherAttributes().put(new QName("datatype"), "xs:string");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getUlica().setValue("Булевар краља Александрa");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getUlica().setBroj(15);
    	zalbaOdluka.setPodnaslov("Ж А Л Б А ");
    	String idUlogovanog = "2";
    	zalbaOdluka.getZalilac().getTipLica().getOsoba().getOtherAttributes().put(new QName("id"), idUlogovanog);
    	zalbaOdluka.getZalilac().getTipLica().getOsoba().getOtherAttributes().put(new QName("property"), "pred:podnosilac");
    	zalbaOdluka.getZalilac().getTipLica().getOsoba().getOtherAttributes().put(new QName("content"), idUlogovanog);
    	zalbaOdluka.getProtivResenjaZakljucka().getNazivOrganaKojiJeDoneoOdluku().getOtherAttributes().put(new QName("property"), "pred:organ_vlasti");
    	zalbaOdluka.getProtivResenjaZakljucka().getNazivOrganaKojiJeDoneoOdluku().getOtherAttributes().put(new QName("datatype"), "xs:string");
    	zalbaOdluka.getProtivResenjaZakljucka().getBroj().getOtherAttributes().put(new QName("rel"), "pred:referenceTo");
    	zalbaOdluka.getProtivResenjaZakljucka().getBroj().getOtherAttributes().put(new QName("href"), "http://"+zalbaOdluka.getProtivResenjaZakljucka().getBroj().getValue());

    	zalbaOdluka.getSadrzaj().getContent().set(0, "Наведеном одлуком органа власти (решењем, закључком, обавештењем у писаној форми са елементима одлуке) , супротно закону, одбијен-одбачен је мој захтев који сам поднео/ла-упутио/ла дана");
    	zalbaOdluka.getSadrzaj().getContent().set(1, (JAXBElement<String>) zalbaOdluka.getSadrzaj().getContent().get(1));
    	zalbaOdluka.getSadrzaj().getContent().set(2, "године и тако ми ускраћено-онемогућено остваривање уставног и законског права на слободан приступ информацијама од јавног значаја. Oдлуку побијам у целости, односно у делу којим");
    	zalbaOdluka.getSadrzaj().getContent().set(3, (JAXBElement<XMLGregorianCalendar>) zalbaOdluka.getSadrzaj().getContent().get(3));
    	zalbaOdluka.getSadrzaj().getContent().set(4, " јер није заснована на Закону о слободном приступу информацијама од јавног значаја. На основу изнетих разлога, предлажем да Повереник уважи моју жалбу,  поништи одлука првостепеног органа и омогући ми приступ траженој/им  информацији/ма.\r\n"
        		+ "    Жалбу подносим благовремено, у законском року утврђеном у");
    	zalbaOdluka.getSadrzaj().getContent().set(5, (JAXBElement<Tclan.Stav>) zalbaOdluka.getSadrzaj().getContent().get(5));
    	JAXBElement<Tclan> JaxbClan = (JAXBElement<Tclan>) zalbaOdluka.getSadrzaj().getContent().get(5);
        JAXBElement<Tclan.Stav> stav = (JAXBElement<Tclan.Stav>) JaxbClan.getValue().getContent().get(0);
        stav.getValue().setValue("ст. 1.");
        stav.getValue().setBroj(Integer.valueOf(1));
        JaxbClan.getValue().getContent().set(0, "члану 22. ");
        JaxbClan.getValue().setBroj(BigInteger.valueOf(22));
        JaxbClan.getValue().getContent().add(stav);
        zalbaOdluka.getSadrzaj().getContent().set(5, JaxbClan);
    	zalbaOdluka.getSadrzaj().getContent().set(6, "Закона о слободном приступу информацијама од јавног значаја.");
    	
    	Tnapomena napomena = new Tnapomena();
    	napomena.setNaslov("Напомена:");
    	napomena.getTacka().add("У жалби се мора навести одлука која се побија (решење, закључак, обавештење), назив \r\n"
    			+ "            органа који је одлуку донео, као и број и датум одлуке. Довољно је да жалилац наведе у \r\n"
    			+ "            жалби у ком погледу је незадовољан одлуком, с тим да жалбу не мора посебно образложити. ");
    	napomena.getTacka().add("Ако жалбу изјављује на овом обрасцу, додатно образложење може  посебно приложити. \r\n"
    			+ "             Уз жалбу обавезно приложити копију поднетог захтева и доказ о његовој предаји-упућивању органу \r\n"
    			+ "             као и копију одлуке органа која се оспорава жалбом.");
    	zalbaOdluka.setNapomena(napomena);
    	
		/*ZalbaOdluka zalbaOdluka = new ZalbaOdluka();
		zalbaOdluka = zalbaOdlukaDTO;
		zalbaOdluka.setId(id);
    	zalbaOdluka.getOtherAttributes().put(new QName("about"), "http://zalbe/" + id);
    	zalbaOdluka.getOtherAttributes().put(new QName("vocab"),"http://examples/predicate/");
    	zalbaOdluka.getOtherAttributes().put(new QName("property"),"pred:datum");
    	zalbaOdluka.getOtherAttributes().put(new QName("datatype"),"xs:date");
    	zalbaOdluka.getOtherAttributes().put(new QName("content"),zalbaOdlukaDTO.getDatum().toString());
    	zalbaOdluka.setNaslov("ЖАЛБА  ПРОТИВ  ОДЛУКЕ ОРГАНА  ВЛАСТИ КОЈОМ ЈЕ ОДБИЈЕН ИЛИ ОДБАЧЕН ЗАХТЕВ ЗА ПРИСТУП ИНФОРМАЦИЈИ");
    	zalbaOdluka.setPodaciPovereniku(new TpodaciPovereniku());
    	zalbaOdluka.getPodaciPovereniku().setAdresa(new Tadresa());
    	zalbaOdluka.getPodaciPovereniku().getAdresa().setMesto(new Tadresa.Mesto());
    	zalbaOdluka.getPodaciPovereniku().getAdresa().setUlica(new Tadresa.Ulica());
    	zalbaOdluka.getPodaciPovereniku().setUloga("Поверенику за информације од јавног значаја и заштиту података о личности");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getMesto().setValue("Београд");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getMesto().getOtherAttributes().put(new QName("property"), "pred:mesto");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getMesto().getOtherAttributes().put(new QName("datatype"), "xs:string");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getUlica().setValue("Булевар краља Александрa");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getUlica().setBroj(15);
    	zalbaOdluka.setPodnaslov("Ж А Л Б А ");
    	String idUlogovanog = "2";
    	zalbaOdluka.setZalilac(zalbaOdlukaDTO.getZalilac());
    	zalbaOdluka.getZalilac().getTipLica().getOsoba().getOtherAttributes().put(new QName("id"), idUlogovanog);
    	zalbaOdluka.getZalilac().getTipLica().getOsoba().getOtherAttributes().put(new QName("property"), "pred:podnosilac");
    	zalbaOdluka.getZalilac().getTipLica().getOsoba().getOtherAttributes().put(new QName("content"), idUlogovanog);
    	zalbaOdluka.setProtivResenjaZakljucka(new TprotivResenjaZakljucka());
    	zalbaOdluka.getProtivResenjaZakljucka().setNazivOrganaKojiJeDoneoOdluku(new NazivOrganaKojiJeDoneoOdluku());
    	zalbaOdluka.getProtivResenjaZakljucka().getNazivOrganaKojiJeDoneoOdluku().getOtherAttributes().put(new QName("property"), "pred:organ_vlasti");
    	zalbaOdluka.setProtivResenjaZakljucka(zalbaOdlukaDTO.getProtivResenjaZakljucka());
    	zalbaOdluka.getProtivResenjaZakljucka().getNazivOrganaKojiJeDoneoOdluku().getOtherAttributes().put(new QName("datatype"), "xs:string");
    	zalbaOdluka.getProtivResenjaZakljucka().getBroj().getOtherAttributes().put(new QName("rel"), "pred:referenceTo");
    	zalbaOdluka.getProtivResenjaZakljucka().getBroj().getOtherAttributes().put(new QName("href"), "http://"+zalbaOdluka.getProtivResenjaZakljucka().getBroj().getValue());

    	zalbaOdluka.setSadrzaj(zalbaOdlukaDTO.getSadrzaj());
    	zalbaOdluka.getSadrzaj().getContent().set(0, "Наведеном одлуком органа власти (решењем, закључком, обавештењем у писаној форми са елементима одлуке) , супротно закону, одбијен-одбачен је мој захтев који сам поднео/ла-упутио/ла дана");
    	zalbaOdluka.getSadrzaj().getContent().set(1, (JAXBElement<String>) zalbaOdlukaDTO.getSadrzaj().getContent().get(1));
    	zalbaOdluka.getSadrzaj().getContent().set(2, "године и тако ми ускраћено-онемогућено остваривање уставног и законског права на слободан приступ информацијама од јавног значаја. Oдлуку побијам у целости, односно у делу којим");
    	zalbaOdluka.getSadrzaj().getContent().set(3, (JAXBElement<XMLGregorianCalendar>) zalbaOdlukaDTO.getSadrzaj().getContent().get(3));
    	zalbaOdluka.getSadrzaj().getContent().set(4, " јер није заснована на Закону о слободном приступу информацијама од јавног значаја. На основу изнетих разлога, предлажем да Повереник уважи моју жалбу,  поништи одлука првостепеног органа и омогући ми приступ траженој/им  информацији/ма.\r\n"
        		+ "    Жалбу подносим благовремено, у законском року утврђеном у");
    	JAXBElement<Tclan> JaxbClan = (JAXBElement<Tclan>) zalbaOdluka.getSadrzaj().getContent().get(5);
        JaxbClan.getValue().getContent().add("члану 22. ");
        JaxbClan.getValue().setBroj(BigInteger.valueOf(22));
        zalbaOdluka.getSadrzaj().getContent().set(5, JaxbClan);
    	zalbaOdluka.getSadrzaj().getContent().set(6, "Закона о слободном приступу информацијама од јавног значаја.");
    	
    	Tnapomena napomena = new Tnapomena();
    	napomena.setNaslov("Напомена:");
    	napomena.getTacka().add("У жалби се мора навести одлука која се побија (решење, закључак, обавештење), назив \r\n"
    			+ "            органа који је одлуку донео, као и број и датум одлуке. Довољно је да жалилац наведе у \r\n"
    			+ "            жалби у ком погледу је незадовољан одлуком, с тим да жалбу не мора посебно образложити. ");
    	napomena.getTacka().add("Ако жалбу изјављује на овом обрасцу, додатно образложење може  посебно приложити. \r\n"
    			+ "             Уз жалбу обавезно приложити копију поднетог захтева и доказ о његовој предаји-упућивању органу \r\n"
    			+ "             као и копију одлуке органа која се оспорава жалбом.");
    	zalbaOdluka.setNapomena(napomena);*/
    	
    	
    	
    	/*zalbaOdluka.setId(id);
    	zalbaOdluka.getOtherAttributes().put(new QName("about"), "http://zalbe/" + id);
    	zalbaOdluka.getOtherAttributes().put(new QName("vocab"),"http://examples/predicate/");
    	zalbaOdluka.getOtherAttributes().put(new QName("property"),"pred:datum");
    	zalbaOdluka.getOtherAttributes().put(new QName("datatype"),"xs:date");
    	zalbaOdluka.getOtherAttributes().put(new QName("content"),zalbaOdluka.getDatum().toString());
    	zalbaOdluka.setNaslov("ЖАЛБА  ПРОТИВ  ОДЛУКЕ ОРГАНА  ВЛАСТИ КОЈОМ ЈЕ ОДБИЈЕН ИЛИ ОДБАЧЕН ЗАХТЕВ ЗА ПРИСТУП ИНФОРМАЦИЈИ");
    	zalbaOdluka.setPodaciPovereniku(new TpodaciPovereniku());
    	zalbaOdluka.getPodaciPovereniku().setAdresa(new Tadresa());
    	zalbaOdluka.getPodaciPovereniku().getAdresa().setMesto(new Tadresa.Mesto());
    	zalbaOdluka.getPodaciPovereniku().getAdresa().setUlica(new Tadresa.Ulica());
    	zalbaOdluka.getPodaciPovereniku().setUloga("Поверенику за информације од јавног значаја и заштиту података о личности");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getMesto().setValue("Београд");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getMesto().getOtherAttributes().put(new QName("property"), "pred:mesto");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getMesto().getOtherAttributes().put(new QName("datatype"), "xs:string");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getUlica().setValue("Булевар краља Александрa");
    	zalbaOdluka.getPodaciPovereniku().getAdresa().getUlica().setBroj(15);
    	zalbaOdluka.setPodnaslov("Ж А Л Б А ");
    	String idUlogovanog = "2";
    	zalbaOdluka.getZalilac().getTipLica().getOsoba().getOtherAttributes().put(new QName("id"), idUlogovanog);
    	zalbaOdluka.getZalilac().getTipLica().getOsoba().getOtherAttributes().put(new QName("property"), "pred:podnosilac");
    	zalbaOdluka.getZalilac().getTipLica().getOsoba().getOtherAttributes().put(new QName("content"), idUlogovanog);
    	zalbaOdluka.getProtivResenjaZakljucka().getNazivOrganaKojiJeDoneoOdluku().getOtherAttributes().put(new QName("property"), "pred:organ_vlasti");
    	zalbaOdluka.getProtivResenjaZakljucka().getNazivOrganaKojiJeDoneoOdluku().getOtherAttributes().put(new QName("datatype"), "xs:string");
    	zalbaOdluka.getProtivResenjaZakljucka().getBroj().getOtherAttributes().put(new QName("rel"), "pred:referenceTo");
    	zalbaOdluka.getProtivResenjaZakljucka().getBroj().getOtherAttributes().put(new QName("href"), "http://"+zalbaOdluka.getProtivResenjaZakljucka().getBroj().getValue());

    	zalbaOdluka.getSadrzaj().getContent().set(0, "Наведеном одлуком органа власти (решењем, закључком, обавештењем у писаној форми са елементима одлуке) , супротно закону, одбијен-одбачен је мој захтев који сам поднео/ла-упутио/ла дана");
    	zalbaOdluka.getSadrzaj().getContent().set(2, "године и тако ми ускраћено-онемогућено остваривање уставног и законског права на слободан приступ информацијама од јавног значаја. Oдлуку побијам у целости, односно у делу којим");
    	zalbaOdluka.getSadrzaj().getContent().set(4, " јер није заснована на Закону о слободном приступу информацијама од јавног значаја. На основу изнетих разлога, предлажем да Повереник уважи моју жалбу,  поништи одлука првостепеног органа и омогући ми приступ траженој/им  информацији/ма.\r\n"
        		+ "    Жалбу подносим благовремено, у законском року утврђеном у");
    	zalbaOdluka.getSadrzaj().getContent().set(6, "Закона о слободном приступу информацијама од јавног значаја.");
    	
    	Tnapomena napomena = new Tnapomena();
    	napomena.setNaslov("Напомена:");
    	napomena.getTacka().add("У жалби се мора навести одлука која се побија (решење, закључак, обавештење), назив \r\n"
    			+ "            органа који је одлуку донео, као и број и датум одлуке. Довољно је да жалилац наведе у \r\n"
    			+ "            жалби у ком погледу је незадовољан одлуком, с тим да жалбу не мора посебно образложити. ");
    	napomena.getTacka().add("Ако жалбу изјављује на овом обрасцу, додатно образложење може  посебно приложити. \r\n"
    			+ "             Уз жалбу обавезно приложити копију поднетог захтева и доказ о његовој предаји-упућивању органу \r\n"
    			+ "             као и копију одлуке органа која се оспорава жалбом.");
    	zalbaOdluka.setNapomena(napomena);*/
		
		return zalbaOdluka;

	}

}
