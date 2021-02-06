package com.project.poverenik.endoint;

import com.project.poverenik.wsdl.file.FileServiceSoapBindingImpl;
import com.project.poverenik.wsdl.izjasnjavanje.IzjasnjavanjeServiceSoapBindingImpl;
import com.project.poverenik.wsdl.izvestaji.IzvestajServiceSoapBindingImpl;
import com.project.poverenik.wsdl.resenje.ResenjeServiceSoapBindingImpl;
import com.project.poverenik.wsdl.zalba_cutanje.ZalbaCutanjeServiceSoapBindingImpl;
import com.project.poverenik.wsdl.zalba_odluka.ZalbaOdlukaServiceSoapBindingImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class EndpointConfig {

    @Autowired
    ResenjeServiceSoapBindingImpl resenjeServiceSoapBindingImpl;
    @Autowired
    ZalbaCutanjeServiceSoapBindingImpl zalbaCutanjeServiceSoapBindingImpl;
    @Autowired
    ZalbaOdlukaServiceSoapBindingImpl zalbaOdlukaServiceSoapBindingImpl;
    @Autowired
    IzjasnjavanjeServiceSoapBindingImpl izjasnjavanjeServiceSoapBindingImpl;
    @Autowired
    IzvestajServiceSoapBindingImpl izvestajServiceSoapBindingImpl;
    @Autowired
    FileServiceSoapBindingImpl fileServiceSoapBindingImpl;
    @Autowired
    private Bus bus;

    @Bean(name = "resenjeEndpointBean")
    public Endpoint resenjeEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, resenjeServiceSoapBindingImpl);
        endpoint.publish("/resenje");
        return endpoint;
    }

    @Bean(name = "zalbaCutanjeEndpointBean")
    public Endpoint zalbaCutanjeEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, zalbaCutanjeServiceSoapBindingImpl);
        endpoint.publish("/zalba-cutanje");
        return endpoint;
    }

    @Bean(name = "zalbaOdlukaEndpointBean")
    public Endpoint zalbaOdlukaEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, zalbaOdlukaServiceSoapBindingImpl);
        endpoint.publish("/zalba-odluka");
        return endpoint;
    }

    @Bean(name = "izjasnjavanjeEndpointBean")
    public Endpoint izjasnjavanjeEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, izjasnjavanjeServiceSoapBindingImpl);
        endpoint.publish("/izjasnjavanje");
        return endpoint;
    }

    @Bean(name = "izvestajEndpointBean")
    public Endpoint izvestajEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, izvestajServiceSoapBindingImpl);
        endpoint.publish("/izvestaj");
        return endpoint;
    }

    @Bean(name = "fileEndpointBean")
    public Endpoint fileEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, fileServiceSoapBindingImpl);
        endpoint.publish("/file");
        return endpoint;
    }

}
