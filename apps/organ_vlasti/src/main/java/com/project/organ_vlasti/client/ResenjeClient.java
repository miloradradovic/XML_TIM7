package com.project.organ_vlasti.client;

import com.project.organ_vlasti.model.resenje.client.getResenjeByBroj;
import com.project.organ_vlasti.model.resenje.client.getResenjeByBrojResponse;
import com.project.organ_vlasti.model.resenje.database.client.getRefs;
import com.project.organ_vlasti.model.resenje.database.client.getRefsResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ResenjeClient extends WebServiceGatewaySupport {

    public getResenjeByBrojResponse getOneResenje(getResenjeByBroj request) {

        getResenjeByBrojResponse response = (getResenjeByBrojResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/ws/resenje", request,
                        new SoapActionCallback(
                                "http://resenje/ws/getResenjeByBroj"));

        if (response.getResenje().getBroj().equals("-1")) {
            return null;
        }
        return response;
    }

    public getRefsResponse getRefs(getRefs request) {

        getRefsResponse response = (getRefsResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/ws/resenje", request,
                        new SoapActionCallback(
                                "http://resenje/ws/getRefs"));

        if (!response.getResponse().getRef().isEmpty()) {
            if (response.getResponse().getRef().get(0).equals("-1"))
                return null;
        }
        return response;
    }
}
