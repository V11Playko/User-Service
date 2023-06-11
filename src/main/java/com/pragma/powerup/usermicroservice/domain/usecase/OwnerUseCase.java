package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IOwnerServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.IsOlder;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IAuthPasswordEncoderPort;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.validations.UserValid;

public class OwnerUseCase implements IOwnerServicePort {

    private final IAuthPasswordEncoderPort authEncoderPort;
    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;

    public OwnerUseCase(IAuthPasswordEncoderPort authEncoderPort, IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort) {
        this.authEncoderPort = authEncoderPort;
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }

    /**
     * Create Employee
     *
     * @param user
     * @throws IsOlder - Check if the user is of legal age
     */
    @Override
    public void saveEmployee(User user) {
        Role role = this.rolePersistencePort.getRole(Constants.EMPLOYEE_ROLE_ID);

        user.setRole(role);
        user.setPassword(authEncoderPort.encodePassword(user.getPassword()));
        if (UserValid.isOlder(user)) {
            userPersistencePort.saveUser(user);
        } else {
            throw new IsOlder();
        }
    }
}
