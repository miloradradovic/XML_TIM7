package com.project.poverenik.client;

import com.project.poverenik.model.util.file.client.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class FileClient extends WebServiceGatewaySupport {

    public sendRdfFileResponse getRdf(sendRdfFile request) {

        sendRdfFileResponse response = (sendRdfFileResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8090/ws/file", request,
                        new SoapActionCallback(
                                "http://file/ws/sendRdfFile"));

        if (response.getPath() == null) {
            return null;
        }
        return response;
    }

    public sendJsonFileResponse getJson(sendJsonFile request) {

        sendJsonFileResponse response = (sendJsonFileResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8090/ws/file", request,
                        new SoapActionCallback(
                                "http://file/ws/sendJsonFile"));

        if (response.getPath() == null) {
            return null;
        }
        return response;
    }
}
