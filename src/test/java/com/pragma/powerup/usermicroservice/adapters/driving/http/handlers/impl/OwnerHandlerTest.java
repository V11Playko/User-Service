package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.CreateEmployeeRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IAdminHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IOwnerHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.request.ICreateEmployeeRequestMapper;
import com.pragma.powerup.usermicroservice.domain.api.IOwnerServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OwnerHandlerTest {
    @InjectMocks
    OwnerHandler ownerHandler;
    @Mock
    IOwnerServicePort ownerServicePort;
    @Mock
    ICreateEmployeeRequestMapper createEmployeeRequestMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveEmployee() {
        User user = HttpData.obtainUser();
        CreateEmployeeRequestDto userRequest = HttpData.obtainEmployeeRequest();

        when(createEmployeeRequestMapper.toUser(userRequest)).thenReturn(user);
        ownerHandler.saveEmployee(userRequest);

        verify(ownerServicePort).saveEmployee(user);
    }
}