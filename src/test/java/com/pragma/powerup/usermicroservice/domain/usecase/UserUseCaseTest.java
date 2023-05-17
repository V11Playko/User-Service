package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.exceptions.IsOlder;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.validations.UserValid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private UserValid userValid;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUserAdult() {
        User user = DomainData.obtainUserValid();

        assertEquals("ADMIN", user.getRole().getName());
        assertDoesNotThrow(() ->userUseCase.saveUser(user));
    }

    @Test
    void exceptionForInvalidUserDueToAge(){
        User user = DomainData.obtainUserInvalid();

        userValid.isOlder(user);

        assertThrows(IsOlder.class, () -> {
            userUseCase.saveUser(user);
        });
    }

    @Test
    void deleteUser() {
        User user = DomainData.obtainUserValid();
        userUseCase.deleteUser(user);

        verify(userPersistencePort).deleteUser(user);
    }

    @Test
    void roleFindByUserIdTest() {
        Long id = 1L;
        User user = DomainData.obtainUserValid();

        when(userPersistencePort.getProvider(id)).thenReturn(user);

        assertEquals(1, id);
    }
}