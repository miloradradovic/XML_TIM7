package com.project.poverenik.service;

import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.model.user.User;
import com.project.poverenik.model.util.lists.UserList;
import com.project.poverenik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserService {

    @Autowired
    private JaxB jaxB;

    @Autowired
    UserRepository userRepository;

    public boolean create(User user) throws XMLDBException {
        if (jaxB.validate(user.getClass(), user)){
            userRepository.create(user);
            return true;
        }
        return false;
    }

    public UserList getAll() throws XMLDBException, JAXBException {
        List<User> users = new ArrayList<>();

        ResourceSet resourceSet = userRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()){
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            JAXBContext context = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            User user = (User) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            users.add(user);
        }
        return new UserList(users);
    }

    public User getOne(String username) throws XMLDBException, JAXBException {
        ResourceSet resourceSet = userRepository.getOne(username);
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
