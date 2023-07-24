package com.medhead.security.configuration;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JsonWebTokenUtils {
    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";

    public String generateToken(User user) {
        int weekInMilliSeconds = 7 * 24 * 60 * 60 * 1000;
        String issuer = "com.medhead";
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + weekInMilliSeconds))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Boolean isTokenValid(String token) {
            try {
                Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
                return true;
            } catch (SignatureException ex) {
                log.error("Invalid JWT signature - {}", ex.getMessage());
            } catch (MalformedJwtException ex) {
                log.error("Invalid JWT token - {}", ex.getMessage());
            } catch (ExpiredJwtException ex) {
                log.error("Expired JWT token - {}", ex.getMessage());
            } catch (UnsupportedJwtException ex) {
                log.error("Unsupported JWT token - {}", ex.getMessage());
            } catch (IllegalArgumentException ex) {
                log.error("JWT claims string is empty - {}", ex.getMessage());
            }
            return false;
    }
}
