package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IRoleResponseMapper;
import com.pragma.powerup.usermicroservice.domain.api.IRoleServicePort;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleHandlerImplTest {
    @InjectMocks
    RoleHandlerImpl roleHandler;
    @Mock
    IRoleServicePort roleServicePort;
    @Mock
    IRoleResponseMapper roleResponseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllRoles() {
        List<Role> roleList = HttpData.obtainRoles();

        when(roleServicePort.getAllRoles()).thenReturn(roleList);
        assertEquals(roleHandler.getAllRoles(), roleList);
    }
}