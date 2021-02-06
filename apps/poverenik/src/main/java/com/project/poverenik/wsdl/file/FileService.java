package com.project.poverenik.wsdl.file;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-02-06T13:06:57.150+01:00
 * Generated source version: 3.2.1
 * 
 */
@WebServiceClient(name = "FileService", 
                  wsdlLocation = "classpath:wsdl/File.wsdl",
                  targetNamespace = "http://file") 
public class FileService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://file", "FileService");
    public final static QName FileServiceSoapBinding = new QName("http://file", "FileServiceSoapBinding");
    static {
        URL url = FileService.class.getClassLoader().getResource("wsdl/File.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(FileService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "classpath:wsdl/File.wsdl");
        }       
        WSDL_LOCATION = url;   
    }

    public FileService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public FileService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FileService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public FileService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public FileService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public FileService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns FileServicePortType
     */
    @WebEndpoint(name = "FileServiceSoapBinding")
    public FileServicePortType getFileServiceSoapBinding() {
        return super.getPort(FileServiceSoapBinding, FileServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FileServicePortType
     */
    @WebEndpoint(name = "FileServiceSoapBinding")
    public FileServicePortType getFileServiceSoapBinding(WebServiceFeature... features) {
        return super.getPort(FileServiceSoapBinding, FileServicePortType.class, features);
    }

}
