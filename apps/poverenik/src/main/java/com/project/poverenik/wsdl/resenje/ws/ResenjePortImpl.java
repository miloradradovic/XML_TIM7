
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.project.poverenik.wsdl.resenje.ws;

import com.project.poverenik.wsdl.resenje.Tresenje;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-01-26T18:21:53.653+01:00
 * Generated source version: 3.2.1
 * 
 */

@WebService(
                      serviceName = "ResenjeService",
                      portName = "ResenjePort",
                      targetNamespace = "http://resenje/ws",
                      wsdlLocation = "classpath:wsdl/Resenje.wsdl",
                      endpointInterface = "com.project.poverenik.wsdl.resenje.ws.ResenjePortType")
                      
public class ResenjePortImpl implements ResenjePortType {

    private static final Logger LOG = Logger.getLogger(ResenjePortImpl.class.getName());

    /* (non-Javadoc)
     * @see resenje.ws.ResenjePortType#getResenje(java.lang.String broj)*
     */
    public Tresenje getResenje(String broj) {
        LOG.info("Executing operation getResenje");
        System.out.println(broj);
        try {
            Tresenje _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
