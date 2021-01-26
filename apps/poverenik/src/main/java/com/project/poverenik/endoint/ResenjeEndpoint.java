package com.project.poverenik.endoint;

import com.project.poverenik.wsdl.resenje.ws.ResenjePortImpl;
import org.apache.cxf.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.cxf.jaxws.EndpointImpl;
import javax.xml.ws.Endpoint;

@Configuration
public class ResenjeEndpoint {

    @Autowired
    private Bus bus;

    @Bean(name="resenjeEndpointBean")
    public Endpoint resenjeEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new ResenjePortImpl());
        endpoint.publish("/resenje");
        return endpoint;
    }

}
