package com.project.poverenik.client;

import com.project.poverenik.model.zahtev.client.getZahtevRequest;
import com.project.poverenik.model.zahtev.client.getZahtevResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ZahtevClient extends WebServiceGatewaySupport {

    public getZahtevResponse getZahtev(getZahtevRequest request) {


        getZahtevResponse response = (getZahtevResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8090/ws/zahtev", request,
                        new SoapActionCallback(
                                "http://www.zahtevcir/ws/getZahtevById"));

        return response;
    }
}
