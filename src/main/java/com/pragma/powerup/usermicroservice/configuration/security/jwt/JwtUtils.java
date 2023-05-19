package com.pragma.powerup.usermicroservice.configuration.security.jwt;


import com.pragma.powerup.usermicroservice.configuration.security.userDetails.CustomUserDetails;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private final String jwtSecret = "4qhq8LrEBfYcaRHxhdb9zURb2rf8e7UdSECRETaklsdIJi289JKLASD";
    private final static Long jwtExpirationMinutes = 2_592_000L;

    public String generateJwtToken(Authentication authentication) {

        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + ( jwtExpirationMinutes * 1000)))
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            throw new JwtException("Invalid Jwt signature");
        } catch (MalformedJwtException e) {
            throw new JwtException("Invalid Jwt token");
        } catch (ExpiredJwtException e) {
            throw new JwtException("JWT token is expired");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("JWT token is unsupported");
        } catch (IllegalArgumentException e) {
            throw new JwtException("JWT claims string is empty");
        }
    }
}
