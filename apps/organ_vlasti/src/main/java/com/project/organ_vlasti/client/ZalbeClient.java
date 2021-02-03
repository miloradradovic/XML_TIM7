package com.project.organ_vlasti.client;

import com.project.organ_vlasti.model.resenje.client.getResenjeByBroj;
import com.project.organ_vlasti.model.resenje.client.getResenjeByBrojResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ZalbeClient extends WebServiceGatewaySupport {

    public getResenjeByBrojResponse getZalbaCutanje(getResenjeByBroj request) {

        getResenjeByBrojResponse response = (getResenjeByBrojResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/ws/resenje", request,
                        new SoapActionCallback(
                                "http://resenje/ws/getResenjeByBroj"));

        if(response.getResenje().getBroj().equals("-1")){
            return null;
        }

        return response;
    }

    public getResenjeByBrojResponse getZalbaOdluka(getResenjeByBroj request) {

        getResenjeByBrojResponse response = (getResenjeByBrojResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/ws/resenje", request,
                        new SoapActionCallback(
                                "http://resenje/ws/getResenjeByBroj"));

        if(response.getResenje().getBroj().equals("-1")){
            return null;
        }

        return response;
    }
}
