package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.CreateClientRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;

public interface IClientHandler {
    void saveClient(CreateClientRequestDto createClientRequestDto);
    UserResponseDto getClient(Long id);
}
