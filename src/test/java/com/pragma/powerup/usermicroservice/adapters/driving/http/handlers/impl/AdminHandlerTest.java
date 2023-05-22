package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.CreateOwnerRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.request.ICreateOwnerRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.request.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.response.IUserResponseMapperImpl;
import com.pragma.powerup.usermicroservice.domain.api.IAdminServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AdminHandlerTest {

    @InjectMocks
    AdminHandler adminHandler;
    @Mock
    IAdminServicePort adminServicePort;
    @Mock
    ICreateOwnerRequestMapper createOwnerRequestMapper;
    @Mock
    IUserRequestMapper userRequestMapper;
    @Mock
    IUserResponseMapperImpl userResponseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOwner() {
        User user = HttpData.obtainUser();
        CreateOwnerRequestDto userRequest = HttpData.obtainOwnerRequest();

        when(createOwnerRequestMapper.toUser(userRequest)).thenReturn(user);
        adminHandler.saveOwner(userRequest);

        verify(adminServicePort).saveOwner(user);
    }

    @Test
    void deleteUser() {
        User user = HttpData.obtainUser();
        UserRequestDto userRequest = HttpData.obtainUserRequest();

        when(userRequestMapper.toUser(userRequest)).thenReturn(user);
        adminHandler.deleteUser(userRequest);

        verify(adminServicePort).deleteUser(user);
    }
}