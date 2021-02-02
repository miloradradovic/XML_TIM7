package com.project.poverenik.api;

import com.project.poverenik.client.EmailClient;
import com.project.poverenik.model.user.User;
import com.project.poverenik.model.util.email.Tbody;
import com.project.poverenik.model.util.email.client.sendAttach;
import com.project.poverenik.model.util.email.client.sendPlain;
import com.project.poverenik.model.util.lists.UserList;
import com.project.poverenik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import javax.xml.bind.JAXBException;

@CrossOrigin(origins = "https://localhost:4201")
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_XML_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ROLE_POVERENIK') || hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user) throws XMLDBException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_POVERENIK");
        if (userService.create(user)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER' || 'ROLE_POVERENIK')")
    @RequestMapping( method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<UserList> getUsers() throws XMLDBException, JAXBException {
        UserList users = userService.getAll();

        if(users != null)
            return new ResponseEntity(users, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_POVERENIK')")
    @RequestMapping(value="/{email}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getUser(@PathVariable String email) throws XMLDBException, JAXBException {
        User user = userService.getOne(email);
        if(user != null)
            return new ResponseEntity(user, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    @RequestMapping(value="/{email}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity delete(@PathVariable String email) throws XMLDBException, JAXBException {
        boolean isDeleted = userService.delete(email);
        if(isDeleted)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity update(@RequestBody User user) throws XMLDBException, JAXBException {
        boolean isUpdated = userService.update(user);
        if(isUpdated)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/ponisti", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity sendEmail(@RequestBody String info){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

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
        sendPlain.getEmail().setFile("");
        if(emailClient.sentPlain(sendPlain)){
            return new ResponseEntity<>(HttpStatus.OK);
        };
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


}
