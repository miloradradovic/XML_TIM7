package com.project.poverenik.service;

import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.model.util.ComplexTypes.Tadresa;
import com.project.poverenik.model.util.ComplexTypes.TpodaciPovereniku;
import com.project.poverenik.model.util.ComplexTypes.TsadrzajZalbe;
import com.project.poverenik.model.util.lists.ZalbaCutanjeList;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.repository.ZalbaCutanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import javax.xml.namespace.QName;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZalbaCutanjeService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ZalbaCutanjeRepository zalbaCutanjeRepository;

    private String getMaxId() throws XMLDBException, JAXBException {
    	ResourceSet max = zalbaCutanjeRepository.getMaxId();
    	ResourceIterator resourceIterator = max.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return "0";
            JAXBContext context = JAXBContext.newInstance(ZalbaCutanje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ZalbaCutanje zalbaCutanjeMax = (ZalbaCutanje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return zalbaCutanjeMax.getId();
        }
        return "0";
    }

    //TODO
    public boolean create(ZalbaCutanje zalbaCutanje) throws XMLDBException, JAXBException {
        if (jaxB.validate(zalbaCutanje.getClass(), zalbaCutanje)){
        	String id = String.valueOf(Integer.parseInt(getMaxId())+1);
        	zalbaCutanje.setId(id);
        	zalbaCutanje.getOtherAttributes().put(new QName("about"), "http://zalbe/" + id);
        	zalbaCutanje.getOtherAttributes().put(new QName("vocab"),"http://examples/predicate/");
        	zalbaCutanje.getOtherAttributes().put(new QName("property"),"pred:datum");
        	zalbaCutanje.getOtherAttributes().put(new QName("datatype"),"xs:date");
        	zalbaCutanje.getOtherAttributes().put(new QName("content"),zalbaCutanje.getDatum().toString());
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

            zalbaCutanje.getSadrzajZalbe().getContent().set(0, "У складу са ");
            zalbaCutanje.getSadrzajZalbe().getContent().set(2, " Закона о слободном приступу информацијама од јавног значаја подносим: Ж А Л Б У против ");
            zalbaCutanje.getSadrzajZalbe().getContent().set(4, " ( навести назив органа) због тога што орган власти: ");
            zalbaCutanje.getSadrzajZalbe().getContent().set(6, "(подвући  због чега се изјављује жалба) по мом захтеву  за слободан приступ информацијама од јавног значаја који сам поднео  том органу  дана ");
            zalbaCutanje.getSadrzajZalbe().getContent().set(8, " године, а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног значаја омогући увид- копија документа који садржи информације  о /у вези са : ");
            zalbaCutanje.getSadrzajZalbe().getContent().set(10, "На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им  информацији/ма. Као доказ , уз жалбу достављам копију захтева са доказом о предаји органу власти.");

            String idUlogovanog = "1";
            zalbaCutanje.getPodaciOPodnosiocu().getOsoba().getOtherAttributes().put(new QName("id"), idUlogovanog);
            zalbaCutanje.getPodaciOPodnosiocu().getOsoba().getOtherAttributes().put(new QName("property"), "pred:podnosilac");
            zalbaCutanje.getPodaciOPodnosiocu().getOsoba().getOtherAttributes().put(new QName("content"), idUlogovanog);
            
            return zalbaCutanjeRepository.create(zalbaCutanje);
        }
        return false;
    }

    public ZalbaCutanjeList getAll() throws XMLDBException, JAXBException {
        List<ZalbaCutanje> zalbaCutanjeList = new ArrayList<>();

        ResourceSet resourceSet = zalbaCutanjeRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(ZalbaCutanje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ZalbaCutanje zalbaCutanje = (ZalbaCutanje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            zalbaCutanjeList.add(zalbaCutanje);
        }
        return new ZalbaCutanjeList(zalbaCutanjeList);
    }

    public ZalbaCutanje getOne(String id) throws JAXBException, XMLDBException {
        XMLResource xmlResource = zalbaCutanjeRepository.getOne(id);

        if(xmlResource == null)
            return null;

        ZalbaCutanje zalbaCutanje = null;

        JAXBContext context = JAXBContext.newInstance(ZalbaCutanje.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        zalbaCutanje = (ZalbaCutanje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return zalbaCutanje;
    }

    public boolean delete(String id) throws XMLDBException {
        return zalbaCutanjeRepository.delete(id);
    }

    public boolean update(ZalbaCutanje zalbaCutanje) throws JAXBException, XMLDBException {
        String patch = jaxB.marshall(zalbaCutanje.getClass(), zalbaCutanje);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        patch = patch.substring(patch.lastIndexOf("<zc:naziv>"), patch.indexOf("</zc:podaci_o_podnosiocu>") + "</zc:podaci_o_podnosiocu>".length());
        return zalbaCutanjeRepository.update(zalbaCutanje.getId(), patch);
    }
}
