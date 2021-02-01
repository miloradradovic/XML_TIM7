package com.project.organ_vlasti.wsdl.resenje_ref;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-02-01T11:11:18.746+01:00
 * Generated source version: 3.2.1
 * 
 */
@WebServiceClient(name = "ResenjeRefService", 
                  wsdlLocation = "classpath:wsdl/ResenjeRef.wsdl",
                  targetNamespace = "http://resenje") 
public class ResenjeRefService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://resenje", "ResenjeRefService");
    public final static QName ResenjeRefServiceSoapBinding = new QName("http://resenje", "ResenjeRefServiceSoapBinding");
    static {
        URL url = ResenjeRefService.class.getClassLoader().getResource("wsdl/ResenjeRef.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(ResenjeRefService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "classpath:wsdl/ResenjeRef.wsdl");
        }       
        WSDL_LOCATION = url;   
    }

    public ResenjeRefService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ResenjeRefService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ResenjeRefService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ResenjeRefService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ResenjeRefService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ResenjeRefService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns ResenjeRefServicePortType
     */
    @WebEndpoint(name = "ResenjeRefServiceSoapBinding")
    public ResenjeRefServicePortType getResenjeRefServiceSoapBinding() {
        return super.getPort(ResenjeRefServiceSoapBinding, ResenjeRefServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ResenjeRefServicePortType
     */
    @WebEndpoint(name = "ResenjeRefServiceSoapBinding")
    public ResenjeRefServicePortType getResenjeRefServiceSoapBinding(WebServiceFeature... features) {
        return super.getPort(ResenjeRefServiceSoapBinding, ResenjeRefServicePortType.class, features);
    }

}
