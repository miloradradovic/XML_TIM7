package com.project.organ_vlasti.api;

import com.project.organ_vlasti.model.user.User;
import com.project.organ_vlasti.model.util.UserTokenStateDTO;
import com.project.organ_vlasti.security.TokenUtils;
import com.project.organ_vlasti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.servlet.http.HttpServletResponse;

//123qweASD
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_XML_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    public AuthenticationController() {

    }

    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, value = "/sign-in")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest,
                                                       HttpServletResponse response) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User person = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(person.getEmail(), person.getRole());

        return ResponseEntity.ok(new UserTokenStateDTO(jwt));
    }

    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, value = "/sign-up")
    public ResponseEntity<?> createUser(@RequestBody User user) throws XMLDBException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        if (userService.create(user)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
