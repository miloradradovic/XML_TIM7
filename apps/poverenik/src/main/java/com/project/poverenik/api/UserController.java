package com.project.poverenik.api;

import com.project.poverenik.model.user.User;
import com.project.poverenik.model.util.lists.UserList;
import com.project.poverenik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import javax.xml.bind.JAXBException;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_XML_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user) throws XMLDBException {
        if (userService.create(user)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping( method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<UserList> getUsers() throws XMLDBException, JAXBException {
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/{username}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getUser(@PathVariable String username) throws XMLDBException, JAXBException {
        User user = userService.getOne(username);
        if(user != null)
            return new ResponseEntity(user, HttpStatus.OK);

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


}
