package com.project.email.endoint;

import com.project.email.wsdl.EmailServiceSoapBindingImpl;
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
    EmailServiceSoapBindingImpl emailServiceSoapBindingImpl;

    @Bean(name="emailRefEndpointBean")
    public Endpoint emailEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, emailServiceSoapBindingImpl);
        endpoint.publish("/email");
        return endpoint;
    }
}
