package com.project.poverenik.service;

import com.project.poverenik.client.ZahtevClient;
import com.project.poverenik.model.zahtev.client.getZahtevRequest;
import com.project.poverenik.model.zahtev.client.getZahtevResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

@Service
public class ZahtevService {

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
}
