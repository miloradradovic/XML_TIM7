package com.project.organ_vlasti.api;

import com.project.organ_vlasti.model.user.User;
import com.project.organ_vlasti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_XML_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('ROLE_ORGAN_VLASTI')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user) throws XMLDBException {
        if (userService.create(user, "ROLE_ORGAN_VLASTI")) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
