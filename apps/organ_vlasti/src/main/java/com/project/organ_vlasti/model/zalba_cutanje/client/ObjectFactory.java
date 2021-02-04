package com.project.organ_vlasti.model.zalba_cutanje.client;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {

    }

    public getZalbaCutanjeById createGetZalbaCutanjeById() {
        return new getZalbaCutanjeById();
    }

    public getZalbaCutanjeByIdResponse createGetZalbaCutanjeByIdResponse() {
        return new getZalbaCutanjeByIdResponse();
    }
}
