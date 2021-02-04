package com.project.poverenik.client;

import com.project.poverenik.model.izvestaj.client.getIzvestajById;
import com.project.poverenik.model.izvestaj.client.getIzvestajByIdResponse;
import com.project.poverenik.model.izvestaj.database.client.getRefs;
import com.project.poverenik.model.izvestaj.database.client.getRefsResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class IzvestajClient extends WebServiceGatewaySupport {

    public getIzvestajByIdResponse getOneResenje(getIzvestajById request) {

        getIzvestajByIdResponse response = (getIzvestajByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8090/ws/izvestaj", request,
                        new SoapActionCallback(
                                "http://izvestaji/ws/getIzvestajById"));

        if (response.getIzvestaj().getId().equals("-1")) {
            return null;
        }
        return response;
    }

    public getRefsResponse getRefs(getRefs request) {

        getRefsResponse response = (getRefsResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8090/ws/izvestaj", request,
                        new SoapActionCallback(
                                "http://izvestaji/ws/getRefs"));

        if (!response.getResponse().getRef().isEmpty()) {
            if (response.getResponse().getRef().get(0).equals("-1"))
                return null;
        }
        return response;
    }
}
