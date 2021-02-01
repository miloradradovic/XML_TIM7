package com.project.poverenik.repository;

import com.project.poverenik.database.ExistManager;
import com.project.poverenik.model.util.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

@Repository
public class MessageRepository {

    @Autowired
    public ExistManager existManager;

    private final String collectionUri = "db/poverenik/xml/message";

    private final String TARGET_NAMESPACE = "http://www.message";


    public boolean create(Message message) throws XMLDBException {
        return existManager.store(collectionUri, message.getBody().getId(), message);
    }

    public ResourceSet getAll() throws XMLDBException {
        return existManager.retrieve(collectionUri, "/message", TARGET_NAMESPACE);
    }

    public XMLResource getOne(String id) throws XMLDBException {
        return existManager.load(collectionUri, id);
    }

    public boolean delete(String id) throws XMLDBException {
        return existManager.remove(collectionUri, id);
    }

    public ResourceSet getMaxId() throws XMLDBException  {
        String xpath = "/message/body[@id = max(/message/body/@id)]/ancestor::message";
        return existManager.retrieve(collectionUri, xpath, TARGET_NAMESPACE);
    }
}
