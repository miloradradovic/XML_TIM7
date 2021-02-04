package com.project.organ_vlasti.model.zalba_odluka.client;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {

    }

    public getZalbaOdlukaById createGetZalbaOdlukaById() {
        return new getZalbaOdlukaById();
    }

    public getZalbaOdlukaByIdResponse createGetZalbaOdlukaByIdResponse() {
        return new getZalbaOdlukaByIdResponse();
    }
}
