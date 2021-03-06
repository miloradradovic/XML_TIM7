
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.project.poverenik.wsdl.zalba_odluka;

import com.project.poverenik.model.zalba_odluka.ObjectFactory;
import com.project.poverenik.model.zalba_odluka.Tzalba;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import com.project.poverenik.service.ZalbaOdlukaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-01-28T22:45:30.899+01:00
 * Generated source version: 3.2.1
 */

@WebService(
        serviceName = "ZalbaOdlukaService",
        portName = "ZalbaOdlukaServiceSoapBinding",
        targetNamespace = "http://www.zalbanaodlukucir",
        wsdlLocation = "classpath:wsdl/ZalbaOdluka.wsdl",
        endpointInterface = "com.project.poverenik.wsdl.zalba_odluka.ZalbaOdlukaServicePortType")
@Service
public class ZalbaOdlukaServiceSoapBindingImpl implements ZalbaOdlukaServicePortType {

    @Autowired
    ZalbaOdlukaService zalbaOdlukaService;

    private static final Logger LOG = Logger.getLogger(ZalbaOdlukaServiceSoapBindingImpl.class.getName());

    /* (non-Javadoc)
     * @see zalbanaodlukucir.ZalbaOdlukaServicePortType#getZalbaOdlukaById(java.lang.String id)*
     */
    public Tzalba getZalbaOdlukaById(String id) {
        LOG.info("Executing operation getZalbaOdlukaById");
        System.out.println(id);
        try {
            Tzalba _return;
            if(id.contains("-")){
                ZalbaOdluka odluka = zalbaOdlukaService.getOne(id);
                if(odluka == null){
                    throw new Exception("nema");
                }
                _return = odluka.getZalbaOdlukaBody();
            }else{
                _return = zalbaOdlukaService.getByZahtevId(id);
            }
            return _return;
        } catch (Exception ex) {
            ObjectFactory objectFactory = new ObjectFactory();
            Tzalba tzalba = objectFactory.createTzalba();
            tzalba.setId("-1");
            return tzalba;
        }
    }

}
