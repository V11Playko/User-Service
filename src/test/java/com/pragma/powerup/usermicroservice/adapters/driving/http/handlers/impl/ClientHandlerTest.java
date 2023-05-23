package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.CreateClientRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.request.ICreateClientRequestMapper;
import com.pragma.powerup.usermicroservice.domain.api.IClientServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClientHandlerTest {
    @InjectMocks
    ClientHandler clientHandler;

    @Mock
    IClientServicePort clientServicePort;

    @Mock
    ICreateClientRequestMapper createClientRequestMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveClient() {
        User user = HttpData.obtainUser();
        CreateClientRequestDto userRequest = HttpData.obtainClientRequest();

        when(createClientRequestMapper.toUser(userRequest)).thenReturn(user);
        clientHandler.saveClient(userRequest);

        verify(clientServicePort).saveClient(user);
    }
}