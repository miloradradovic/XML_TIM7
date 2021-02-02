package com.project.organ_vlasti.service;

import com.itextpdf.text.DocumentException;
import com.project.organ_vlasti.database.ExistManager;
import com.project.organ_vlasti.jaxb.JaxB;
import com.project.organ_vlasti.mappers.ObavestenjeMapper;
import com.project.organ_vlasti.model.obavestenje.Obavestenje;
import com.project.organ_vlasti.model.util.lists.ObavestenjeList;
import com.project.organ_vlasti.repository.ObavestenjeRepository;

import com.project.organ_vlasti.transformer.Transformator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObavestenjeService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    private ObavestenjeRepository obavestenjeRepository;

    @Autowired
    private ExistManager existManager;

    private String getMaxId() throws XMLDBException, JAXBException {
        ResourceSet max = obavestenjeRepository.getMaxId();
        ResourceIterator resourceIterator = max.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return "0000";
            JAXBContext context = JAXBContext.newInstance(Obavestenje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Obavestenje obavestenjeMax = (Obavestenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return obavestenjeMax.getObavestenjeBody().getId();
        }
        return "0000";
    }

    public boolean create(Obavestenje obavestenjeDTO) throws XMLDBException, JAXBException {
        
        if (jaxB.validate(obavestenjeDTO.getClass(), obavestenjeDTO)){
        	String id = String.valueOf(Integer.parseInt(getMaxId())+1);
        	
        	//email usera koji je podnio zahtjev na koji se odnosi obavjestenje
        	String userEmail = ""; 
            Obavestenje obavestenje = ObavestenjeMapper.mapFromDTO(obavestenjeDTO, id, userEmail);

            if(jaxB.validate(obavestenje.getClass(), obavestenje)){
            	 return obavestenjeRepository.create(obavestenje);
            }else {
                return false;
            }
        }
        return false;
         
    }

    public ObavestenjeList getAll() throws XMLDBException, JAXBException {
        List<Obavestenje> obavestenjeList = new ArrayList<>();

        ResourceSet resourceSet = obavestenjeRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(Obavestenje.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Obavestenje obavestenje = (Obavestenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            obavestenjeList.add(obavestenje);
        }
        return new ObavestenjeList(obavestenjeList);
    }

    public Obavestenje getOne(String broj) throws JAXBException, XMLDBException {
        XMLResource xmlResource = obavestenjeRepository.getOne(broj);

        if(xmlResource == null)
            return null;

        Obavestenje obavestenje = null;

        JAXBContext context = JAXBContext.newInstance(Obavestenje.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        obavestenje = (Obavestenje) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return obavestenje;
    }

    public boolean delete(String broj) throws XMLDBException {
        return obavestenjeRepository.delete(broj);
    }

    public boolean update(Obavestenje obavestenje) throws JAXBException, XMLDBException {
        String patch = jaxB.marshall(obavestenje.getClass(), obavestenje);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        patch = patch.substring(patch.lastIndexOf("<oba:naziv_organa property=\"pred:organ_vlasti\" datatype=\"xs:string\" sediste=\"\">"), patch.indexOf("</oba:opcija_dostave>") + "</oba:opcija_dostave>".length());
        return obavestenjeRepository.update(obavestenje.getObavestenjeBody().getBroj(), patch);
    }

    public boolean generateDocuments(String brojObavestenja){
        final String OUTPUT_PDF = "organ_vlasti/src/main/resources/generated_files/documents/obavestenje.pdf";
        final String OUTPUT_HTML = "organ_vlasti/src/main/resources/generated_files/documents/obavestenje.html";


        System.out.println("[INFO] " + Transformator.class.getSimpleName());


        try {
            Transformator transformator = new Transformator();
            Obavestenje xml = getOne("1");
            transformator.generateHTML(existManager.getOutputStream(xml),
                    "organ_vlasti/src/main/resources/generated_files/xslt/obavestenje.xsl");
            transformator.generatePDF(existManager.getOutputStream(xml), OUTPUT_PDF);
        } catch (XMLDBException | IOException | JAXBException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
		/*pdfTransformer.generateHTML(existManager.getOutputStream(), XSL_FILE);
		pdfTransformer.generatePDF(OUTPUT_FILE);
*/
        System.out.println("[INFO] File \"" + "organ_vlasti/src/main/resources/generated_files/documents/bookstore.pdf" + "\" generated successfully.");
        System.out.println("[INFO] End.");
        return true;
    }
}
