package com.project.organ_vlasti.service;

import com.project.organ_vlasti.client.ZalbeClient;
import com.project.organ_vlasti.model.zalba_cutanje.client.getZalbaCutanjeById;
import com.project.organ_vlasti.model.zalba_cutanje.client.getZalbaCutanjeByIdResponse;
import com.project.organ_vlasti.model.zalba_odluka.client.getZalbaOdlukaById;
import com.project.organ_vlasti.model.zalba_odluka.client.getZalbaOdlukaByIdResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

@Service
public class ZalbeService {


    public getZalbaCutanjeByIdResponse getZalbaCutanje(String id){
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

    public getZalbaOdlukaByIdResponse getZalbaOdluka(String id){
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
}
