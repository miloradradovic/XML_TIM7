package com.project.organ_vlasti.model.izvestaji.client;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.project.organ_vlasti.model.zahtev
     *
     */
    public ObjectFactory() {}

    /**
     * Create an instance of {@link getPodaci }
     *
     */
    public getPodaci createGetPodaci() {
        return new getPodaci();
    }

    /**
     * Create an instance of {@link getPodaciResponse }
     *
     */
    public getPodaciResponse createGetPodaciResponse() {
        return new getPodaciResponse();
    }
}
