package com.project.organ_vlasti.initialization;

import com.project.organ_vlasti.model.user.ObjectFactory;
import com.project.organ_vlasti.model.user.User;
import com.project.organ_vlasti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.xmldb.api.base.XMLDBException;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;


@Component
public class Initialize {

    private static final Logger LOG = Logger.getLogger(com.project.organ_vlasti.initialization.Initialize.class.getName());

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() throws XMLDBException {
        LOG.info("Executing operation Initialize");
        if(userService.hasRoleOrganVlasti() == 0){
            ObjectFactory of = new ObjectFactory();
            User user = of.createUser();
            user.setRole("ROLE_ORGAN_VLASTI");
            user.setPassword(passwordEncoder.encode("12345678"));
            user.setEmail("email@email.com");
            user.setLastName("Sluzbenik");
            user.setName("Gosn");
            userService.create(user);
            LOG.info("Predefined user added");
        }
    }
}
