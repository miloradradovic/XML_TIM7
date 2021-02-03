package com.project.poverenik.mappers;

import com.project.poverenik.model.resenje.Resenje;
import com.project.poverenik.model.resenje.Tadresa;
import com.project.poverenik.model.resenje.TuvodneInformacije;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class ResenjeMapper {
	
	public static Resenje mapFromDTO(Resenje resenje, String id, String email) {

		resenje.getResenjeBody().setId(id);
		switch (id.length()){
			case 1:
				id = "000" + id;
				break;
			case 2:
				id = "00" + id;
				break;
			case 3:
				id = "0" + id;
				break;
			default:
				break;
		}
		String linkNaZalbu = resenje.getResenjeBody().getOtherAttributes().get(new QName("idZalbe")).split("/")[0].equals("cutanje") ?
				"http://localhost:8085/zalba-cutanje/" + resenje.getResenjeBody().getOtherAttributes().get(new QName("idZalbe")).split("/")[1] :
				"http://localhost:8085/zalba-odluka/" + resenje.getResenjeBody().getOtherAttributes().get(new QName("idZalbe")).split("/")[1];
		resenje.getResenjeBody().setBroj("071-01-" + id + "-" + resenje.getResenjeBody().getDatum().toString().substring(0, 7));
		resenje.getResenjeBody().getOtherAttributes().put(new QName("about"), "http://resenja/" + resenje.getResenjeBody().getBroj());
    	resenje.getResenjeBody().getOtherAttributes().put(new QName("rel"),"pred:responseTo");
    	resenje.getResenjeBody().getOtherAttributes().put(new QName("vocab"),"http://examples/predicate/"); //cutanje/1 odluka/1
    	resenje.getResenjeBody().getOtherAttributes().put(new QName("href"),"http://zalbe/" + resenje.getResenjeBody().getOtherAttributes().get(new QName("idZalbe")));
    	resenje.getResenjeBody().getOtherAttributes().put(new QName("property"),"pred:datum");
    	resenje.getResenjeBody().getOtherAttributes().put(new QName("datatype"),"xs:date");
    	resenje.getResenjeBody().getOtherAttributes().put(new QName("content"),resenje.getResenjeBody().getDatum().toString());
    	resenje.getResenjeBody().getOtherAttributes().put(new QName("link_na_zalbu"), linkNaZalbu);

    	
    	resenje.getResenjeBody().getTipResenja().getOtherAttributes().put(new QName("property"), "pred:tip");
    	resenje.getResenjeBody().getTipResenja().getOtherAttributes().put(new QName("datatype"), "xs:string");
    	for (int i = 0; i < resenje.getResenjeBody().getUvodneInformacije().getContent().size(); i++) {
    		try {
        		JAXBElement<TuvodneInformacije.Trazilac> element = (JAXBElement<TuvodneInformacije.Trazilac>)resenje.getResenjeBody().getUvodneInformacije().getContent().get(i);
				element.getValue().setId(email);
        		element.getValue().getOtherAttributes().put(new QName("rel"), "pred:trazilac");
        		element.getValue().getOtherAttributes().put(new QName("href"), "http://users/"+element.getValue().getId().toString());
    		} catch (Exception e) {}
    		try {
        		JAXBElement<TuvodneInformacije.Lice> element = (JAXBElement<TuvodneInformacije.Lice>)resenje.getResenjeBody().getUvodneInformacije().getContent().get(i);
        		element.getValue().getOtherAttributes().put(new QName("property"), "pred:organVlasti");
        		element.getValue().getOtherAttributes().put(new QName("datatype"), "xs:string");
    		} catch (Exception e) {}
    		try {
        		JAXBElement<Tadresa> element = (JAXBElement<Tadresa>)resenje.getResenjeBody().getUvodneInformacije().getContent().get(i);
        		JAXBElement<Tadresa.Mesto> elementMesto = (JAXBElement<Tadresa.Mesto>)element.getValue().getContent().get(0);
        		elementMesto.getValue().getOtherAttributes().put(new QName("property"), "pred:mesto");
        		elementMesto.getValue().getOtherAttributes().put(new QName("datatype"), "xs:string");
    		} catch (Exception e) {}
    	}
    	resenje.getResenjeBody().getPoverenik().getOtherAttributes().put(new QName("rel"), "pred:poverenik");
    	resenje.getResenjeBody().getPoverenik().getOtherAttributes().put(new QName("href"), "http://users/"+resenje.getResenjeBody().getPoverenik().getId().toString());
    	
    	return resenje;

	}

}
