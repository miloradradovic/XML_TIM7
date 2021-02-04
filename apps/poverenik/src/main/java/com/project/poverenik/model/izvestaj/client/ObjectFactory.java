package com.project.poverenik.model.izvestaj.client;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {

    }

    public getIzvestajById createGetIzvestajById() {
        return new getIzvestajById();
    }

    public getIzvestajByIdResponse createGetIzvestajByIdResponse() {
        return new getIzvestajByIdResponse();
    }
}
