package com.project.email.api;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.email.service.EmailService;


@RestController
@RequestMapping(value = "/email", produces = MediaType.APPLICATION_XML_VALUE)
public class EmailController {

	@Autowired
    EmailService emailService;
	
	@RequestMapping(value="/{email}", method= RequestMethod.GET)
    public ResponseEntity<?> createAdministrator(@PathVariable String email) {

        emailService.sendMail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
