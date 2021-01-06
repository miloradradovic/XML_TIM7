package com.project.poverenik.repository;

import com.project.poverenik.database.ExistManager;
import com.project.poverenik.model.user.User;
import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

@Repository
public class UserRepository {

    @Autowired
    public ExistManager existManager;

    private final String collectionUri = "/db/proverenik/xml/users";

    private final String TARGET_NAMESPACE = "http://user";

    public final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
            + "</xu:modifications>";
    public final  String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
            + "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
            + "</xu:modifications>";

    public void create(User user) throws XMLDBException {
        existManager.store(collectionUri, user.getUsername(), user);
    }

    public ResourceSet getOne(String username) throws XMLDBException {

        return existManager.retrieve(collectionUri, String.format("/*[local-name()='user'][username[text()='%s']]", username), TARGET_NAMESPACE);
    }

    public ResourceSet getAll() throws XMLDBException {
        return existManager.retrieve(collectionUri, "/*[local-name()='user']", TARGET_NAMESPACE);
    }

    public void update(){

    }

    public void delete(){

    }

    public void append(){

    }

}
