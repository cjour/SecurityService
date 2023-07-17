package com.medhead.security.controller;

import com.medhead.security.configuration.JsonWebTokenUtils;
import com.medhead.security.entity.InternalUser;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JsonWebTokenUtils jsonWebTokenUtils;

    @PostMapping(value = "/auth")
    public ResponseEntity authenticateUser(@RequestBody InternalUser user) {

        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            User authenticatedUser = (User) authenticate.getPrincipal();
            log.info("[MEDHEAD] authenticated user is " + authenticatedUser.getUsername());

            String generatedToken = jsonWebTokenUtils.generateToken(authenticatedUser);
            log.info("[MEDHEAD] token is " + generatedToken);

            return ResponseEntity.status(HttpStatus.OK).header("Token", generatedToken).build();

        } catch (BadCredentialsException exception) {

            log.info("[MEDHEAD] user password is : " + user.getPassword());

            log.info("[MEDHEAD] user name is : " + user.getUsername());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
