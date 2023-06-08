package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IClientServicePort;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IAuthPasswordEncoderPort;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;

public class ClientUseCase implements IClientServicePort {
    private final IAuthPasswordEncoderPort authEncoderPort;
    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;

    public ClientUseCase(IAuthPasswordEncoderPort authEncoderPort, IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort) {
        this.authEncoderPort = authEncoderPort;
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public void saveClient(User user) {
        Role role = rolePersistencePort.getRole(Constants.CLIENT_ROLE_ID);

        user.setRole(role);
        user.setPassword(authEncoderPort.encodePassword(user.getPassword()));

        userPersistencePort.saveUser(user);
    }

    @Override
    public User getClient(Long id) {
        return userPersistencePort.getClient(id);
    }
}
