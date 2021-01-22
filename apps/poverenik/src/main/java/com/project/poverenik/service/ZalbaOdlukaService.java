package com.project.poverenik.service;

import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.model.util.ComplexTypes.Tadresa;
import com.project.poverenik.model.util.ComplexTypes.Tnapomena;
import com.project.poverenik.model.util.ComplexTypes.TpodaciPovereniku;
import com.project.poverenik.model.util.lists.ZalbaOdlukaList;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import com.project.poverenik.repository.ZalbaOdlukaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZalbaOdlukaService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ZalbaOdlukaRepository zalbaOdlukaRepository;

    private String getMaxId() throws XMLDBException, JAXBException {
    	ResourceSet max = zalbaOdlukaRepository.getMaxId();
    	ResourceIterator resourceIterator = max.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return "0";
            JAXBContext context = JAXBContext.newInstance(ZalbaOdluka.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ZalbaOdluka zalbaOdlukaMax = (ZalbaOdluka) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return zalbaOdlukaMax.getId();
        }
        return "0";
    }

    public boolean create(ZalbaOdluka zalbaOdluka) throws XMLDBException, NumberFormatException, JAXBException {
        if (jaxB.validate(zalbaOdluka.getClass(), zalbaOdluka)){
        	
        	String id = String.valueOf(Integer.parseInt(getMaxId())+1);
        	zalbaOdluka.setId(id);
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
        	zalbaOdluka.setNapomena(napomena);
        	
            return zalbaOdlukaRepository.create(zalbaOdluka);
        }
        return false;
    }

    public ZalbaOdlukaList getAll() throws XMLDBException, JAXBException {
        List<ZalbaOdluka> zalbaOdlukaList = new ArrayList<>();

        ResourceSet resourceSet = zalbaOdlukaRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(ZalbaOdluka.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ZalbaOdluka zalbaOdluka = (ZalbaOdluka) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            zalbaOdlukaList.add(zalbaOdluka);
        }
        return new ZalbaOdlukaList(zalbaOdlukaList);
    }

    public ZalbaOdluka getOne(String id) throws JAXBException, XMLDBException {
        XMLResource xmlResource = zalbaOdlukaRepository.getOne(id);

        if(xmlResource == null)
            return null;

        ZalbaOdluka zalbaOdluka = null;

        JAXBContext context = JAXBContext.newInstance(ZalbaOdluka.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        zalbaOdluka = (ZalbaOdluka) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return zalbaOdluka;
    }

    public boolean delete(String id) throws XMLDBException {
        return zalbaOdlukaRepository.delete(id);
    }

    public boolean update(ZalbaOdluka zalbaOdluka) throws JAXBException, XMLDBException {
        String patch = jaxB.marshall(zalbaOdluka.getClass(), zalbaOdluka);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        patch = patch.substring(patch.lastIndexOf("<zoc:naslov>"), patch.indexOf("</zoc:napomena>") + "</zoc:napomena>".length());
        return zalbaOdlukaRepository.update(zalbaOdluka.getId(), patch);
    }
}
