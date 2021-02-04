package com.project.poverenik.client;

import com.project.poverenik.model.resenje.database.client.SetResenjeRef;
import com.project.poverenik.model.resenje.database.client.SetResenjeRefResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ResenjeRefClient extends WebServiceGatewaySupport {

    public boolean sendResenjeRef(SetResenjeRef request) {

        SetResenjeRefResponse response = (SetResenjeRefResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8090/ws/resenjeRef", request,
                        new SoapActionCallback(
                                "http://resenje/ws/setResenjeRef"));

        return response.getResponse().equals("OK");
    }
}
