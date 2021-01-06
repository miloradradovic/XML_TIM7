package com.project.poverenik.api;

import com.project.poverenik.database.ExistManager;
import com.project.poverenik.helper.EntityList;
import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/exist", produces = MediaType.APPLICATION_XML_VALUE)
public class ExistController {

    @Autowired
    public ExistManager existManager;

    @Autowired
    private JaxB jaxB;

    /*@RequestMapping( method = RequestMethod.GET)
    public void getOrCreateCollection() throws XMLDBException {
        existManager.getOrCreateCollection("db/xml/proba", 0);
    }*/

    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user) throws XMLDBException {
        if (jaxB.validate(user.getClass(), user)){
            existManager.store("db/proverenik/xml/users", user.getUsername(), user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping( method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public EntityList<User> getUsers() throws XMLDBException, JAXBException {
        ArrayList<User> users = new ArrayList<>();

        ResourceSet resourceSet = existManager.retrieve("db/proverenik/xml/users", "/user");
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            JAXBContext context = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            User user = (User) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            users.add(user);
        }
        EntityList<User> usersXML = new EntityList<>  (users);
        return usersXML;
    }

    @RequestMapping(value="/{username}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public User getUser(@PathVariable String username) throws XMLDBException, JAXBException {

        ResourceSet resourceSet = existManager.retrieve("db/proverenik/xml/users", "/user[username='" + username + "']");
        ResourceIterator resourceIterator = resourceSet.getIterator();
        User user = null;

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            JAXBContext context = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            user = (User) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
        }

        return user;
    }

}
