package com.project.organ_vlasti.model.util.file.client;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {

    }

    public sendRdfFile createSendRdfFile() {
        return new sendRdfFile();
    }

    public sendRdfFileResponse createSendRdfFileResponse() {
        return new sendRdfFileResponse();
    }

    public sendJsonFile createSendJsonFile() {
        return new sendJsonFile();
    }

    public sendJsonFileResponse createSendJsonFileResponse() {
        return new sendJsonFileResponse();
    }
}
