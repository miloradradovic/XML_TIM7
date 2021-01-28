
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.project.poverenik.wsdl.zalba_cutanje;

import com.project.poverenik.model.zalba_cutanje.Tzalba;
import com.project.poverenik.service.ZalbaCutanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-01-28T01:12:23.511+01:00
 * Generated source version: 3.2.1
 * 
 */

@WebService(
                      serviceName = "ZalbaCutanjeService",
                      portName = "ZalbaCutanjeServiceSoapBinding",
                      targetNamespace = "http://www.zalbacutanje",
                      wsdlLocation = "classpath:wsdl/ZalbaCutanje.wsdl",
                      endpointInterface = "com.project.poverenik.wsdl.zalba_cutanje.ZalbaCutanjeServicePortType")
@Service
public class ZalbaCutanjeServiceSoapBindingImpl implements ZalbaCutanjeServicePortType {

    @Autowired
    ZalbaCutanjeService zalbaCutanjeService;
    private static final Logger LOG = Logger.getLogger(ZalbaCutanjeServiceSoapBindingImpl.class.getName());

    /* (non-Javadoc)
     * @see zalbacutanje.ZalbaCutanjeServicePortType#getZalbaCutanjeById(java.lang.String id)*
     */
    public Tzalba getZalbaCutanjeById(String id) {
        LOG.info("Executing operation getZalbaCutanjeById");
        System.out.println(id);
        try {
            Tzalba _return = zalbaCutanjeService.getOne(id).getZalbaCutanjeBody();
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}