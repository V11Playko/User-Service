package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.LoginRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.JwtTokenResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IAdminHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IAuthHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IClientHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final IAdminHandler adminHandler;
    private final IAuthHandler authHandler;
    private final IClientHandler clientHandler;

    @Operation(summary = "Login into the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized for bad credentials", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponseDto> loginUser(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(this.authHandler.loginUser(loginRequestDto), HttpStatus.OK);
    }

    @Operation(summary = "Find a user by its email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/getUser")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(this.adminHandler.getUserByEmail(email), HttpStatus.OK);
    }





    @Operation(summary = "Get a client user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Client user returned",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRequestDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found with client role",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/client/{id}")
    public ResponseEntity<UserResponseDto> getClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientHandler.getClient(id));
    }

}
