package com.project.organ_vlasti.client;

import com.project.organ_vlasti.model.util.file.client.sendJsonFile;
import com.project.organ_vlasti.model.util.file.client.sendJsonFileResponse;
import com.project.organ_vlasti.model.util.file.client.sendRdfFile;
import com.project.organ_vlasti.model.util.file.client.sendRdfFileResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class FileClient extends WebServiceGatewaySupport {

    public sendRdfFileResponse getRdf(sendRdfFile request) {

        sendRdfFileResponse response = (sendRdfFileResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/ws/file", request,
                        new SoapActionCallback(
                                "http://file/ws/sendRdfFile"));

        if (response.getPath() == null) {
            return null;
        }
        return response;
    }

    public sendJsonFileResponse getJson(sendJsonFile request) {

        sendJsonFileResponse response = (sendJsonFileResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/ws/file", request,
                        new SoapActionCallback(
                                "http://file/ws/sendJsonFile"));

        if (response.getPath() == null) {
            return null;
        }
        return response;
    }
}
