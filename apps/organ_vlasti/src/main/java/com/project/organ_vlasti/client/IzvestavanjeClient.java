package com.project.organ_vlasti.client;

import com.project.organ_vlasti.model.util.message.client.SetIzjasnjavanje;
import com.project.organ_vlasti.model.util.message.client.SetIzjasnjavanjeResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class IzvestavanjeClient extends WebServiceGatewaySupport {

    public boolean sendIzjasnjavanje(SetIzjasnjavanje request) {

        SetIzjasnjavanjeResponse response = (SetIzjasnjavanjeResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/ws/izjasnjavanje", request,
                        new SoapActionCallback(
                                "http://www.message/ws/setIzjasnjavanje"));

        return response.getResponse().equals("OK");
    }
}
