package com.project.organ_vlasti.client;

import com.project.organ_vlasti.model.zalba_cutanje.client.getZalbaCutanjeById;
import com.project.organ_vlasti.model.zalba_cutanje.client.getZalbaCutanjeByIdResponse;
import com.project.organ_vlasti.model.zalba_odluka.client.getZalbaOdlukaById;
import com.project.organ_vlasti.model.zalba_odluka.client.getZalbaOdlukaByIdResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ZalbeClient extends WebServiceGatewaySupport {

    public getZalbaCutanjeByIdResponse getZalbaCutanje(getZalbaCutanjeById request) {

        getZalbaCutanjeByIdResponse response = (getZalbaCutanjeByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/ws/zalba-cutanje", request,
                        new SoapActionCallback(
                                "http://www.zalbacutanje/ws/getZalbaCutanjeById"));

        if(response.getZalba_cutanje().getId().equals("-1")){
            return null;
        }

        return response;
    }

    public getZalbaOdlukaByIdResponse getZalbaOdluka(getZalbaOdlukaById request) {

        getZalbaOdlukaByIdResponse response = (getZalbaOdlukaByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/ws/zalba-odluka", request,
                        new SoapActionCallback(
                                "http://www.zalbanaodlukucir/ws/getZalbaOdlukaById"));

        if(response.getZalba_odluka().getId().equals("-1")){
            return null;
        }

        return response;
    }
}
