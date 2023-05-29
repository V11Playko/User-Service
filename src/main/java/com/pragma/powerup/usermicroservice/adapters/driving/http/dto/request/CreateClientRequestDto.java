package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateClientRequestDto {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 150, message = "Name must be between 1 and 150 characters")
    private String name;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 1, max = 150, message = "Last name must be between 1 and 150 characters")
    private String surname;

    @Pattern(regexp = "^[0-9]*$", message = "Field 'identificationNumber' must be only numbers")
    @NotBlank(message = "Field 'identificationNumber' it's required")
    @Column(unique = true, nullable = false, length = 11)
    private String dniNumber;

    @Pattern(regexp = "^\\+?57\\s(3[0-2]|7[0-1])\\d{8}$", message = "Pattern not allowed")
    @NotBlank(message = "Phone number cannot be blank")
    private String phone;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Field 'email' must be a valid email direction. Enter the format name@example.com")
    private String email;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 4, max = 150, message = "Password must be between 4 and 150 characters")
    private String password;
}
