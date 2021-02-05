package com.project.poverenik.service;

import com.project.poverenik.client.EmailClient;
import com.project.poverenik.jaxb.JaxB;
import com.project.poverenik.model.user.User;
import com.project.poverenik.model.util.email.Tbody;
import com.project.poverenik.model.util.email.client.sendPlain;
import com.project.poverenik.model.util.lists.UserList;
import com.project.poverenik.model.zalba_cutanje.ZalbaCutanje;
import com.project.poverenik.model.zalba_odluka.ZalbaOdluka;
import com.project.poverenik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    ZalbaOdlukaService zalbaOdlukaService;

    @Autowired
    ZalbaCutanjeService zalbaCutanjeService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean create(User user, String role) throws XMLDBException, JAXBException {
    	if (getOne(user.getEmail()) != null) {
        	return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        if (jaxB.validate(user.getClass(), user)) {
            return userRepository.create(user);
        }
        return false;
    }

    public UserList getAll() throws XMLDBException, JAXBException {
        List<User> users = new ArrayList<>();

        ResourceSet resourceSet = userRepository.getAll();
        ResourceIterator resourceIterator = resourceSet.getIterator();

        while (resourceIterator.hasMoreResources()) {
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            if (xmlResource == null)
                return null;
            JAXBContext context = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            User user = (User) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            users.add(user);
        }
        return new UserList(users);
    }

    public User getOne(String email) throws XMLDBException, JAXBException {
        XMLResource xmlResource = userRepository.getOne(email);

        if (xmlResource == null)
            return null;

        User user;

        JAXBContext context = JAXBContext.newInstance(User.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        user = (User) unmarshaller.unmarshal(xmlResource.getContentAsDOM());

        return user;
    }

    public boolean delete(String email) throws XMLDBException {
        return userRepository.delete(email);
    }

    public boolean update(User user) throws JAXBException, XMLDBException {
    	if (getOne(user.getEmail()) == null) {
    		return false;
    	}
    	if (user.getPassword().equals("")) {
    		user.setPassword(getOne(user.getEmail()).getPassword());
    	} else {
    		user.setPassword(passwordEncoder.encode(user.getPassword()));
    	}
        String patch = jaxB.marshall(user.getClass(), user);
        //u patch moraju biti navedeni svi elementi unutar root elementa inace ce biti obrisani
        patch = patch.substring(patch.lastIndexOf("<u:name>"), patch.indexOf("</u:role>") + "</u:role>".length());
        return userRepository.update(user.getEmail(), patch);
    }

    public Long hasRolePoverenik() throws XMLDBException {
        ResourceSet resourceSet = userRepository.hasRolePoverenik();
        return resourceSet.getSize();
    }

    public boolean updateZalba(String id) throws JAXBException, XMLDBException {
        //id_zalbe = tip/id
        String tip = id.split("/")[0];
        String idZalbe = id.split("/")[1];
        boolean isUpdated;

        if (tip.equals("cutanje")) {
            ZalbaCutanje zalbaCutanje = zalbaCutanjeService.getOne(idZalbe);
            isUpdated = zalbaCutanjeService.update(zalbaCutanje, "ponistena");
        } else {
            ZalbaOdluka zalbaOdluka = zalbaOdlukaService.getOne(idZalbe);
            isUpdated = zalbaOdlukaService.update(zalbaOdluka, "ponistena");
        }

        return isUpdated;
    }

    public boolean sendEmail(String info, User user) {
        //info = "zalba/id email"
        String zalba = info.split(" ")[0];
        String email = info.split(" ")[1];

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.project.poverenik.model.util.email.client");

        EmailClient emailClient = new EmailClient();
        emailClient.setDefaultUri("http://localhost:8095/ws");
        emailClient.setMarshaller(marshaller);
        emailClient.setUnmarshaller(marshaller);

        sendPlain sendPlain = new sendPlain();
        sendPlain.setEmail(new Tbody());
        sendPlain.getEmail().setTo(email);
        sendPlain.getEmail().setContent("Postovani, <br/><br/> Zamolio bih Vas da ponistite zalbu tip/id: " + zalba + " <br/><br/> Srdacno,  " + user.getName() + " " + user.getLastName());
        sendPlain.getEmail().setSubject("Ponistavanje zalbe: " + zalba);
        return emailClient.sentPlain(sendPlain);
    }
}
