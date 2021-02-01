package com.project.organ_vlasti.model.resenje.client;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory(){

    }

    public getResenjeByBroj createGetResenjeByBroj(){
        return new getResenjeByBroj();
    }

    public getResenjeByBrojResponse createGetResenjeByBrojResponse(){
        return new getResenjeByBrojResponse();
    }
}
