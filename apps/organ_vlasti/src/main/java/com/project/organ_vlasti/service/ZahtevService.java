package com.project.organ_vlasti.service;

import com.project.organ_vlasti.database.ExistManager;
import com.project.organ_vlasti.jaxb.JaxB;
import com.project.organ_vlasti.mappers.ZahtevMapper;
import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import com.project.organ_vlasti.model.util.lists.ZahtevList;
import com.project.organ_vlasti.model.zahtev.Zahtev;
import com.project.organ_vlasti.repository.ZahtevRepository;
import com.project.organ_vlasti.transformer.Transformator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZahtevService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ZahtevRepository zahtevRepository;

    @Autowired
    private ExistManager existManager;
    
    private String getMaxId() throws XMLDBException, JAXBException {
        ResourceSet max = zahtevRepository.getMaxId();
        ResourceIterator resourceIterator = max.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return "0";
            JAXBContext context = JAXBContext.newInstance(Zahtev.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Zahtev zahteveMax = (Zahtev) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return zahteveMax.getZahtevBody().getId();
        }
        return "0";
    }
     

    public boolean create(Zahtev zahtevDTO, String userEmail) throws XMLDBException, JAXBException {
        if(jaxB.validate(zahtevDTO.getClass(), zahtevDTO)){
        	String id = String.valueOf(Integer.parseInt(getMaxId())+1);
        	
        	Zahtev zahtev = ZahtevMapper.mapFromDTO(zahtevDTO, id, userEmail);
        	if(jaxB.validate(zahtev.getClass(), zahtev)) {
                return zahtevRepository.create(zahtev);
        	}{
        		return false;
        	}

        }else{
            return false;
        }
    }

    public ZahtevList getAll() throws XMLDBException, JAXBException {
        List<Zahtev> zahtevList = new ArrayList<>();

        ResourceSet resourceSet = zahtevRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(Zahtev.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            zahtevList.add(zahtev);
        }
        return new ZahtevList(zahtevList);
    }

    public Zahtev getOne(String id) throws JAXBException, XMLDBException {
        XMLResource xmlResource = zahtevRepository.getOne(id);

        if(xmlResource == null)
            return null;

        Zahtev zahtev = null;

        JAXBContext context = JAXBContext.newInstance(Zahtev.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        zahtev = (Zahtev) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return zahtev;
    }

    public boolean delete(String id) throws XMLDBException {
        return zahtevRepository.delete(id);
    }

    public boolean update(Zahtev zahtev) throws JAXBException, XMLDBException {
        String patch = jaxB.marshall(zahtev.getClass(), zahtev);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        patch = patch.substring(patch.lastIndexOf("<zcir:ciljani_organ_vlasti>"), patch.indexOf("</zcir:fusnote>") + "</zcir:fusnote>".length());
        return zahtevRepository.update(zahtev.getZahtevBody().getId(), patch);
    }

    public boolean generateDocuments(String brojZahteva){
        final String OUTPUT_PDF = "organ_vlasti/src/main/resources/generated_files/documents/zahtev.pdf";
        final String OUTPUT_HTML = "organ_vlasti/src/main/resources/generated_files/documents/zahtev.html";
        final String XSL_FO = "organ_vlasti/src/main/resources/generated_files/xsl-fo/zahtev_fo.xsl";


        System.out.println("[INFO] " + Transformator.class.getSimpleName());


        try {
            Transformator transformator = new Transformator();
            Zahtev xml = getOne("1");
            transformator.generateHTML(existManager.getOutputStream(xml),
                    "organ_vlasti/src/main/resources/generated_files/xslt/zahtev.xsl", OUTPUT_HTML);
            transformator.generatePDF(XSL_FO, existManager.getOutputStream(xml), OUTPUT_PDF);
        } catch (XMLDBException | IOException | JAXBException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
		/*pdfTransformer.generateHTML(existManager.getOutputStream(), XSL_FILE);
		pdfTransformer.generatePDF(OUTPUT_FILE);
*/
        System.out.println("[INFO] File \"" + OUTPUT_HTML + "\" generated successfully.");
        System.out.println("[INFO] End.");
        return true;
    }

}
