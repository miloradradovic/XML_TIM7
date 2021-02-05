package com.project.poverenik.service;

import com.project.poverenik.client.ZahtevClient;
import com.project.poverenik.database.ExistManager;
import com.project.poverenik.model.zahtev.Zahtev;
import com.project.poverenik.model.zahtev.client.getZahtevRequest;
import com.project.poverenik.model.zahtev.client.getZahtevResponse;
import com.project.poverenik.transformer.Transformator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

@Service
public class ZahtevService {

    @Autowired
    ExistManager existManager;

    public getZahtevResponse getZahtev(String id) {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.zahtev.client");

        ZahtevClient zahtevClient = new ZahtevClient();
        zahtevClient.setDefaultUri("http://localhost:8090/ws");
        zahtevClient.setMarshaller(marshaller);
        zahtevClient.setUnmarshaller(marshaller);

        getZahtevRequest zahtevRequest = new getZahtevRequest();
        zahtevRequest.setId(id);
        return zahtevClient.getZahtev(zahtevRequest);
    }

    public boolean downloadResenje(String broj){
        //pocetak soap
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.zahtev.client");

        ZahtevClient zahtevClient = new ZahtevClient();
        zahtevClient.setDefaultUri("http://localhost:8090/ws");
        zahtevClient.setMarshaller(marshaller);
        zahtevClient.setUnmarshaller(marshaller);

        getZahtevRequest zahtevRequest = new getZahtevRequest();
        zahtevRequest.setId(broj);

        getZahtevResponse getZahtevResponse = zahtevClient.getZahtev(zahtevRequest);
        //kraj soap
        if(getZahtevResponse == null){
            return false;
        }
        com.project.poverenik.model.zahtev.ObjectFactory of = new com.project.poverenik.model.zahtev.ObjectFactory();
        Zahtev z = of.createZahtev();
        z.setZahtevBody(getZahtevResponse.getZahtev());

        final String OUTPUT_PDF = "src/main/resources/generated_files/documents/zahtev" + broj + ".pdf";
        final String OUTPUT_HTML = "src/main/resources/generated_files/documents/zahtev" + broj + ".html";
        final String XSL_FO = "src/main/resources/generated_files/xsl-fo/zahtev_fo.xsl";
        try {
            Transformator transformator = new Transformator();
            transformator.generateHTML(existManager.getOutputStream(z),
                    "src/main/resources/generated_files/xslt/zahtev.xsl", OUTPUT_HTML);
            transformator.generatePDF(XSL_FO, existManager.getOutputStream(z), OUTPUT_PDF);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String downloadZahtevXHTML(String broj) {
        String path = "src/main/resources/generated_files/documents/zahtev" + broj + ".html";
        boolean resenje = downloadResenje(broj);
        if (resenje) {
            return path;
        }
        return "";
    }
}
