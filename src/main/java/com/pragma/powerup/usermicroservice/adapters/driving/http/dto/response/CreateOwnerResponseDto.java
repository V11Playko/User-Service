package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateOwnerResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
}
