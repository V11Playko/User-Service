package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.CreateClientRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IClientHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.request.ICreateClientRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.response.IUserResponseMapper;
import com.pragma.powerup.usermicroservice.domain.api.IClientServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientHandler implements IClientHandler {
    private final IClientServicePort clientServicePort;
    private final ICreateClientRequestMapper createClientRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    @Override
    public void saveClient(CreateClientRequestDto createClientRequestDto) {
        clientServicePort.saveClient(createClientRequestMapper.toUser(createClientRequestDto));
    }

    @Override
    public UserResponseDto getClient(Long id) {
        return userResponseMapper.toResponse(clientServicePort.getClient(id));
    }

}
