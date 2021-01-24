package com.project.poverenik.mappers;

import java.math.BigInteger;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.project.poverenik.model.resenje.Resenje;
import com.project.poverenik.model.resenje.Tadresa;
import com.project.poverenik.model.resenje.TuvodneInformacije;
import com.project.poverenik.model.util.ComplexTypes.Tclan;
import com.project.poverenik.model.util.ComplexTypes.Topcije;
import com.project.poverenik.model.util.ComplexTypes.TpodaciPovereniku;
import com.project.poverenik.model.util.ComplexTypes.TsadrzajZalbe;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;

public class ResenjeMapper {
	
	public static Resenje mapFromDTO(Resenje resenje, String id) {

		resenje.setId(id);
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
		resenje.setBroj("071-01-" + id + "-" + resenje.getDatum().toString().substring(0, 7));
		resenje.getOtherAttributes().put(new QName("about"), "http://resenja/" + resenje.getBroj());
    	resenje.getOtherAttributes().put(new QName("rel"),"pred:responseTo");
    	resenje.getOtherAttributes().put(new QName("vocab"),"http://examples/predicate/");
    	resenje.getOtherAttributes().put(new QName("href"),"http://zalbe/" + resenje.getOtherAttributes().get(new QName("idZalbe")));
    	resenje.getOtherAttributes().put(new QName("property"),"pred:datum");
    	resenje.getOtherAttributes().put(new QName("datatype"),"xs:date");
    	resenje.getOtherAttributes().put(new QName("content"),resenje.getDatum().toString());

    	
    	resenje.getTipResenja().getOtherAttributes().put(new QName("property"), "pred:tip");
    	resenje.getTipResenja().getOtherAttributes().put(new QName("datatype"), "xs:string");
    	for (int i = 0; i < resenje.getUvodneInformacije().getContent().size(); i++) {
    		try {
        		JAXBElement<TuvodneInformacije.Trazilac> element = (JAXBElement<TuvodneInformacije.Trazilac>)resenje.getUvodneInformacije().getContent().get(i);
        		element.getValue().getOtherAttributes().put(new QName("property"), "pred:trazilac");
        		element.getValue().getOtherAttributes().put(new QName("content"), element.getValue().getId().toString());
    		} catch (Exception e) {}
    		try {
        		JAXBElement<TuvodneInformacije.Lice> element = (JAXBElement<TuvodneInformacije.Lice>)resenje.getUvodneInformacije().getContent().get(i);
        		element.getValue().getOtherAttributes().put(new QName("property"), "pred:organVlasti");
        		element.getValue().getOtherAttributes().put(new QName("datatype"), "xs:string");
    		} catch (Exception e) {}
    		try {
        		JAXBElement<Tadresa> element = (JAXBElement<Tadresa>)resenje.getUvodneInformacije().getContent().get(i);
        		JAXBElement<Tadresa.Mesto> elementMesto = (JAXBElement<Tadresa.Mesto>)element.getValue().getContent().get(0);
        		elementMesto.getValue().getOtherAttributes().put(new QName("property"), "pred:mesto");
        		elementMesto.getValue().getOtherAttributes().put(new QName("datatype"), "xs:string");
    		} catch (Exception e) {}
    	}
    	resenje.getPoverenik().getOtherAttributes().put(new QName("property"), "pred:poverenik");
    	resenje.getPoverenik().getOtherAttributes().put(new QName("content"), resenje.getPoverenik().getId().toString());
    	
    	return resenje;

	}

}
