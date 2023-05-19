package com.pragma.powerup.usermicroservice.domain.spi;

public interface IAuthPasswordEncoderPort {
    String encodePassword(String decodedPassword);

    String decodePassword(String encodedPassword);
}
