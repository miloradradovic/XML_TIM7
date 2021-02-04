package com.project.organ_vlasti.model.resenje.database.client;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.project.organ_vlasti.model.zahtev
     *
     */
    public ObjectFactory() {
    }
    /**
     * Create an instance of {@link getRefs }
     *
     */
    public getRefs createGetRefs() {
        return new getRefs();
    }

    /**
     * Create an instance of {@link getRefsResponse }
     *
     */
    public getRefsResponse createGetRefsResponse() {
        return new getRefsResponse();
    }
}
