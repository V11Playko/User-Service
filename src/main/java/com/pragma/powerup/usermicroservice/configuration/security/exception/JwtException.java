package com.pragma.powerup.usermicroservice.configuration.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtException extends RuntimeException {
    public JwtException(String message) {
        super(message);
    }
}
