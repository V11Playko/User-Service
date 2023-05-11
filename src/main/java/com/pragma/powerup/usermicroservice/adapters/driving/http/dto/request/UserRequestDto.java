package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserRequestDto {
    @NotBlank(message = "Field 'name' it's required")
    private String name;
    @NotBlank(message = "Field 'surname' it's required")
    private String surname;
    @Pattern(regexp = "^[0-9]*$", message = "Field 'identificationNumber' must be only numbers")
    @NotBlank(message = "Field 'identificationNumber' it's required")
    @Column(unique = true, nullable = false, length = 11)
    private String dniNumber;
    @Pattern(regexp = "^\\+?57\\s(3[0-2]|7[0-1])\\d{8}$", message = "Field 'numberPhone' must be a valid number phone. Enter the format +57 3...")
    @NotBlank(message = "Field 'phoneNumber' it's required")
    private String phone;
    @NotNull
    private LocalDate birthdate;
    @NotBlank(message = "Field 'email' it's required")
    @Email(message = "Field 'email' must be a valid email direction. Enter the format name@example.com")
    private String mail;
    @NotBlank(message = "Field 'password' it's required")
    private String password;
    @NotNull
    @Min(value = 1, message = "This value is invalid")
    @Max(value = 4, message = "This value is invalid")
    private Long idRole;
}
