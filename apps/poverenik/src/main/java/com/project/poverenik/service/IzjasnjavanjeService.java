package com.project.poverenik.service;

import com.project.poverenik.client.IzvestavanjeClient;
import com.project.poverenik.model.util.message.client.SetIzjasnjavanje;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;

@Service
public class IzjasnjavanjeService {
    @Autowired
    ZalbaOdlukaService zalbaOdlukaService;

    @Autowired
    ZalbaCutanjeService zalbaCutanjeService;

    public boolean setIzjasnajvanje(String id) throws JAXBException, XMLDBException {
        //id_zalbe = tip/id
        String idZahteva;

        String tip = id.split("/")[0];
        String idZalbe = id.split("/")[1];

        if (tip.equals("cutanje")) {
            ZalbaCutanje zalbaCutanje = zalbaCutanjeService.getOne(idZalbe);
            int last = zalbaCutanje.getZalbaCutanjeBody().getZahtev().getValue().split("/").length - 1;
            idZahteva = zalbaCutanje.getZalbaCutanjeBody().getZahtev().getValue().split("/")[last];

            zalbaCutanjeService.update(zalbaCutanje, "u obradi");
        } else {
            ZalbaOdluka zalbaOdluka = zalbaOdlukaService.getOne(idZalbe);
            int last = zalbaOdluka.getZalbaOdlukaBody().getZahtev().getValue().split("/").length - 1;
            idZahteva = zalbaOdluka.getZalbaOdlukaBody().getZahtev().getValue().split("/")[last];

            zalbaOdlukaService.update(zalbaOdluka, "u obradi");
        }

        return setIzjasnjavanjeClient(idZahteva);
    }

    public boolean setIzjasnjavanjeClient(String idZahteva) {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.util.message.client");

        IzvestavanjeClient izvestavanjeClient = new IzvestavanjeClient();
        izvestavanjeClient.setDefaultUri("http://localhost:8090/ws");
        izvestavanjeClient.setMarshaller(marshaller);
        izvestavanjeClient.setUnmarshaller(marshaller);

        SetIzjasnjavanje setIzjasnjavanje = new SetIzjasnjavanje();
        setIzjasnjavanje.setMessage("Molim izjasnjenje povodom zahteva sa ID-jem: " + idZahteva);
        return izvestavanjeClient.sendIzjasnjavanje(setIzjasnjavanje);
    }


}
