package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IClientHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/v1/employee")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class EmployeeRestController {


    private final IClientHandler clientHandler;
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
