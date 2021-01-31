package com.project.organ_vlasti.endpoint;

import com.project.organ_vlasti.wsdl.izjasnjavanje.IzjasnjavanjeServiceSoapBindingImpl;
import com.project.organ_vlasti.wsdl.obavestenje.ObavestenjeServiceSoapBindingImpl;
import com.project.organ_vlasti.wsdl.zahtev.ZahtevServiceSoapBindingImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class EndpointConfig {

    @Autowired
    private Bus bus;

    @Autowired
    ObavestenjeServiceSoapBindingImpl obavestenjeServiceSoapBindingImpl;

    @Autowired
    ZahtevServiceSoapBindingImpl zahtevServiceSoapBindingImpl;

    @Autowired
    IzjasnjavanjeServiceSoapBindingImpl izjasnjavanjeServiceSoapBindingImpl;

    @Bean(name="obavestenjeEndpointBean")
    public Endpoint obavestenjeEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, obavestenjeServiceSoapBindingImpl);
        endpoint.publish("/obavestenje");
        return endpoint;
    }

    @Bean(name="zahtevEndpointBean")
    public Endpoint zahtevEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, zahtevServiceSoapBindingImpl);
        endpoint.publish("/zahtev");
        return endpoint;
    }

    @Bean(name="izjasnjavanjeEndpointBean")
    public Endpoint izjasnjavanjeEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, izjasnjavanjeServiceSoapBindingImpl);
        endpoint.publish("/izjasnjavanje");
        return endpoint;
    }
}
