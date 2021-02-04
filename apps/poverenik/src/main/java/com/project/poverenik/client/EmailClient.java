package com.project.poverenik.client;

import com.project.poverenik.model.util.email.client.sendAttach;
import com.project.poverenik.model.util.email.client.sendAttachResponse;
import com.project.poverenik.model.util.email.client.sendPlain;
import com.project.poverenik.model.util.email.client.sendPlainResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class EmailClient extends WebServiceGatewaySupport {

    public boolean sentPlain(sendPlain request) {

        sendPlainResponse response = (sendPlainResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8095/ws/email", request,
                        new SoapActionCallback(
                                "http://email/ws/sendPlain"));

        return response.getResponse().equals("OK");
    }

    public boolean sentAttach(sendAttach request) {

        sendAttachResponse response = (sendAttachResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8095/ws/email", request,
                        new SoapActionCallback(
                                "http://email/ws/sendAttach"));

        return response.getResponse().equals("OK");
    }
}
