package com.project.poverenik.initialization;

import com.project.poverenik.model.user.ObjectFactory;
import com.project.poverenik.model.user.User;
import com.project.poverenik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.base.XMLDBException;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import java.util.logging.Logger;


@Component
public class Initialize {

    private static final Logger LOG = Logger.getLogger(Initialize.class.getName());

    @Autowired
    UserService userService;

    @PostConstruct
    public void init() throws XMLDBException, JAXBException {
        LOG.info("Executing operation Initialize");
        if (userService.hasRolePoverenik() == 0) {
            ObjectFactory of = new ObjectFactory();
            User user = of.createUser();
            user.setPassword("12345678");
            user.setEmail("email@email.com");
            user.setLastName("Poverenik");
            user.setName("Gosn");
            userService.create(user, "ROLE_POVERENIK");
            LOG.info("Predefined user added");
        }
    }
}
