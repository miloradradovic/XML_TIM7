package com.project.poverenik.wsdl.zalba_cutanje;

import com.project.poverenik.model.zalba_cutanje.Tzalba;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-01-28T01:12:23.521+01:00
 * Generated source version: 3.2.1
 * 
 */
@WebService(targetNamespace = "http://www.zalbacutanje", name = "ZalbaCutanjeServicePortType")
@XmlSeeAlso({com.project.poverenik.model.zalba_cutanje.ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ZalbaCutanjeServicePortType {

    @WebMethod(action = "http://www.zalbacutanje/ws/getZalbaCutanjeById")
    @WebResult(name = "result", targetNamespace = "http://www.zalbacutanje", partName = "result")
    public Tzalba getZalbaCutanjeById(
        @WebParam(partName = "id", name = "id")
        String id
    );
}