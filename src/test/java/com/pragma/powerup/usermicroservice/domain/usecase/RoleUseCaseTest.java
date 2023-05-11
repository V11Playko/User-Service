package com.pragma.powerup.usermicroservice.domain.usecase;


import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoleUseCaseTest {
    @InjectMocks
    private RoleUseCase roleUseCase;
    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Test
    void getAllRoles() {
        List<Role> roleList = DomainData.obtainRoles();

        when(rolePersistencePort.getAllRoles()).thenReturn(roleList);
        assertEquals(roleUseCase.getAllRoles(), roleList);
    }
}