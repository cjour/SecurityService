package com.medhead.security.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Component
public class JsonWebTokenUtils {

    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";
    private final String issuer = "com.medhead";
    private final Integer weekInMilliSeconds = 7 * 24 * 60 * 60 * 1000;

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + weekInMilliSeconds))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
        ;
    }
}
