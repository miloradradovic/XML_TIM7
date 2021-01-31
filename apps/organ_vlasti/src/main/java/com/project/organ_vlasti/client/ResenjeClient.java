package com.project.organ_vlasti.client;

import com.project.organ_vlasti.model.resenje.client.getAllResenjaRequest;
import com.project.organ_vlasti.model.resenje.client.getAllResenjaResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ResenjeClient extends WebServiceGatewaySupport {

    public getAllResenjaResponse getAllResenja(getAllResenjaRequest request) {

        getAllResenjaResponse response = (getAllResenjaResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/ws/resenje", request,
                        new SoapActionCallback(
                                "http://resenje/ws/getAllResenja"));

        if(response.getResenjeList().getAny().isEmpty()){
            return null;
        }

        return response;
    }
}
