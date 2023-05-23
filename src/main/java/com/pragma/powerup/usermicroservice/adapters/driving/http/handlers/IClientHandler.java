package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.CreateClientRequestDto;

public interface IClientHandler {
    void saveClient(CreateClientRequestDto createClientRequestDto);
}
