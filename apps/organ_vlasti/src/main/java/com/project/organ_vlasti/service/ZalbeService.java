package com.project.organ_vlasti.service;

import com.project.organ_vlasti.client.ResenjeClient;
import com.project.organ_vlasti.client.ZalbeClient;
import com.project.organ_vlasti.database.ExistManager;
import com.project.organ_vlasti.model.resenje.Resenje;
import com.project.organ_vlasti.model.resenje.client.getResenjeByBroj;
import com.project.organ_vlasti.model.resenje.client.getResenjeByBrojResponse;
import com.project.organ_vlasti.model.zalba_cutanje.ZalbaCutanje;
import com.project.organ_vlasti.model.zalba_cutanje.client.getZalbaCutanjeById;
import com.project.organ_vlasti.model.zalba_cutanje.client.getZalbaCutanjeByIdResponse;
import com.project.organ_vlasti.model.zalba_odluka.ZalbaOdluka;
import com.project.organ_vlasti.model.zalba_odluka.client.getZalbaOdlukaById;
import com.project.organ_vlasti.model.zalba_odluka.client.getZalbaOdlukaByIdResponse;
import com.project.organ_vlasti.transformer.Transformator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

@Service
public class ZalbeService {

    @Autowired
    ExistManager existManager;

    public getZalbaCutanjeByIdResponse getZalbaCutanje(String id) {
        // TODO dodati za front xhtml transformacije
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.zalba_cutanje.client");

        ZalbeClient zalbeClient = new ZalbeClient();
        zalbeClient.setDefaultUri("http://localhost:8085/ws");
        zalbeClient.setMarshaller(marshaller);
        zalbeClient.setUnmarshaller(marshaller);


        getZalbaCutanjeById getZalbaCutanjeById = new getZalbaCutanjeById();
        getZalbaCutanjeById.setId(id);

        return zalbeClient.getZalbaCutanje(getZalbaCutanjeById);
    }

    public getZalbaOdlukaByIdResponse getZalbaOdluka(String id) {
        // TODO dodati za front xhtml transformacije
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.zalba_odluka.client");

        ZalbeClient zalbeClient = new ZalbeClient();
        zalbeClient.setDefaultUri("http://localhost:8085/ws");
        zalbeClient.setMarshaller(marshaller);
        zalbeClient.setUnmarshaller(marshaller);


        getZalbaOdlukaById getZalbaOdlukaById = new getZalbaOdlukaById();
        getZalbaOdlukaById.setId(id);

        return zalbeClient.getZalbaOdluka(getZalbaOdlukaById);
    }

    public boolean downloadZalbaCutanje(String broj){
        //pocetak soap
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.zalba_cutanje.client");

        ZalbeClient zalbeClient = new ZalbeClient();
        zalbeClient.setDefaultUri("http://localhost:8085/ws");
        zalbeClient.setMarshaller(marshaller);
        zalbeClient.setUnmarshaller(marshaller);


        getZalbaCutanjeById getZalbaCutanjeById = new getZalbaCutanjeById();
        getZalbaCutanjeById.setId(broj);

        getZalbaCutanjeByIdResponse getZalbaCutanjeByIdResponse = zalbeClient.getZalbaCutanje(getZalbaCutanjeById);
        //kraj soap
        if(getZalbaCutanjeByIdResponse == null){
            return false;
        }

        com.project.organ_vlasti.model.zalba_cutanje.ObjectFactory of = new com.project.organ_vlasti.model.zalba_cutanje.ObjectFactory();
        ZalbaCutanje zc = of.createZalbaCutanje();
        zc.setZalbaCutanjeBody(getZalbaCutanjeByIdResponse.getZalba_cutanje());

        final String OUTPUT_PDF = "src/main/resources/generated_files/documents/zalbacutanje" + broj + ".pdf";
        final String OUTPUT_HTML = "src/main/resources/generated_files/documents/zalbacutanje" + broj + ".html";
        final String XSL_FO = "src/main/resources/generated_files/xsl-fo/zalbacutanje_fo.xsl";
        try {
            Transformator transformator = new Transformator();
            transformator.generateHTML(existManager.getOutputStream(zc),
                    "src/main/resources/generated_files/xslt/zalbacutanje.xsl", OUTPUT_HTML);
            transformator.generatePDF(XSL_FO, existManager.getOutputStream(zc), OUTPUT_PDF);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public String downloadZalbaCutanjeXHTML(String broj) {
        String path = "src/main/resources/generated_files/documents/zalbacutanje" + broj + ".html";
        boolean resenje = downloadZalbaCutanje(broj);
        if (resenje) {
            return path;
        }
        return "";
    }

    public boolean downloadZalbaOdluka(String broj){
        //pocetak soap
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.zalba_odluka.client");

        ZalbeClient zalbeClient = new ZalbeClient();
        zalbeClient.setDefaultUri("http://localhost:8085/ws");
        zalbeClient.setMarshaller(marshaller);
        zalbeClient.setUnmarshaller(marshaller);


        getZalbaOdlukaById getZalbaOdlukaById = new getZalbaOdlukaById();
        getZalbaOdlukaById.setId(broj);

        getZalbaOdlukaByIdResponse getZalbaOdlukaByIdResponse = zalbeClient.getZalbaOdluka(getZalbaOdlukaById);
        //kraj soap
        if(getZalbaOdlukaByIdResponse == null){
            return false;
        }

        com.project.organ_vlasti.model.zalba_odluka.ObjectFactory of = new com.project.organ_vlasti.model.zalba_odluka.ObjectFactory();
        ZalbaOdluka zo = of.createZalbaOdluka();
        zo.setZalbaOdlukaBody(getZalbaOdlukaByIdResponse.getZalba_odluka());

        final String OUTPUT_PDF = "src/main/resources/generated_files/documents/zalbaodluka" + broj + ".pdf";
        final String OUTPUT_HTML = "src/main/resources/generated_files/documents/zalbaodluka" + broj + ".html";
        final String XSL_FO = "src/main/resources/generated_files/xsl-fo/zalbaodluka_fo.xsl";
        try {
            Transformator transformator = new Transformator();
            transformator.generateHTML(existManager.getOutputStream(zo),
                    "src/main/resources/generated_files/xslt/zalbaodluka.xsl", OUTPUT_HTML);
            transformator.generatePDF(XSL_FO, existManager.getOutputStream(zo), OUTPUT_PDF);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public String downloadZalbaOdlukaXHTML(String broj) {
        String path = "src/main/resources/generated_files/documents/zalbaodluka" + broj + ".html";
        boolean resenje = downloadZalbaOdluka(broj);
        if (resenje) {
            return path;
        }
        return "";
    }
}
