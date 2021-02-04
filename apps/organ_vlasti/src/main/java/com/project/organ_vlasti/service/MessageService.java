package com.project.organ_vlasti.service;

import com.project.organ_vlasti.client.IzvestavanjeClient;
import com.project.organ_vlasti.model.util.lists.MessageList;
import com.project.organ_vlasti.model.util.message.Message;
import com.project.organ_vlasti.model.util.message.client.SetIzjasnjavanje;
import com.project.organ_vlasti.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    private String getMaxId() throws XMLDBException, JAXBException {
        ResourceSet max = messageRepository.getMaxId();
        ResourceIterator resourceIterator = max.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return "0000";
            JAXBContext context = JAXBContext.newInstance(Message.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Message messageMax = (Message) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return messageMax.getBody().getId();
        }
        return "0000";
    }

    public boolean create(Message message) throws XMLDBException, JAXBException {

        String id = String.valueOf(Integer.parseInt(getMaxId())+1);
        message.getBody().setId(id);

        return messageRepository.create(message);

    }

    public MessageList getAll() throws XMLDBException, JAXBException {
        List<Message> messageList = new ArrayList<>();

        ResourceSet resourceSet = messageRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if(xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(Message.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Message message = (Message) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            messageList.add(message);
        }
        return new MessageList(messageList);
    }

    public Message getOne(String id) throws JAXBException, XMLDBException {
        XMLResource xmlResource = messageRepository.getOne(id);

        if(xmlResource == null)
            return null;

        Message message;

        JAXBContext context = JAXBContext.newInstance(Message.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        message = (Message) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return message;
    }

    public boolean delete(String id) throws XMLDBException {
        return messageRepository.delete(id);
    }

    public boolean sendIzjasnjavanje(Message message) throws XMLDBException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.organ_vlasti.model.util.message.client");

        IzvestavanjeClient izvestavanjeClient = new IzvestavanjeClient();
        izvestavanjeClient.setDefaultUri("http://localhost:8085/ws");
        izvestavanjeClient.setMarshaller(marshaller);
        izvestavanjeClient.setUnmarshaller(marshaller);

        SetIzjasnjavanje setIzjasnjavanje = new SetIzjasnjavanje();
        setIzjasnjavanje.setMessage(message.getBody().getValue());
        boolean isSet = izvestavanjeClient.sendIzjasnjavanje(setIzjasnjavanje);
        if(isSet){
            delete(message.getBody().getId());
            return true;
        }
        return false;

    }

}
