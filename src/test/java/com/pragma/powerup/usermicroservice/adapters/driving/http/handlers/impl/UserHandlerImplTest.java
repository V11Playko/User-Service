package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserResponseMapperImpl;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class UserHandlerImplTest {

    @InjectMocks
    UserHandlerImpl userHandler;
    @Mock
    IUserServicePort userServicePort;
    @Mock
    IUserRequestMapper userRequestMapper;
    @Mock
    IUserResponseMapperImpl userResponseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser() {
        User user = HttpData.obtainUser();
        UserRequestDto userRequest = HttpData.obtainUserRequest();

        when(userRequestMapper.toUser(userRequest)).thenReturn(user);
        userHandler.saveUser(userRequest);

        verify(userServicePort).saveUser(user);
    }

    @Test
    void deleteUser() {
        User user = HttpData.obtainUser();
        UserRequestDto userRequest = HttpData.obtainUserRequest();

        when(userRequestMapper.toUser(userRequest)).thenReturn(user);
        userHandler.deleteUser(userRequest);

        verify(userServicePort).deleteUser(user);
    }
}