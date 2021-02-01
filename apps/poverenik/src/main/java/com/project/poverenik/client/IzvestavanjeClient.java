package com.project.poverenik.client;

import com.project.poverenik.model.util.message.client.SetIzjasnjavanje;
import com.project.poverenik.model.util.message.client.SetIzjasnjavanjeResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class IzvestavanjeClient extends WebServiceGatewaySupport {

    public boolean sendIzjasnjavanje(SetIzjasnjavanje request) {

        SetIzjasnjavanjeResponse response = (SetIzjasnjavanjeResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8090/ws/izjasnjavanje", request,
                        new SoapActionCallback(
                                "http://www.message/ws/setIzjasnjavanje"));

        if(response.getResponse().equals("OK")){
            return true;
        }

        return false;
    }
}
