package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IAdminServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.IsOlder;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserNotFound;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IAuthPasswordEncoderPort;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.validations.UserValid;

import java.util.List;

public class AdminUseCase implements IAdminServicePort {
    private final IAuthPasswordEncoderPort authEncoderPort;
    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;

    public AdminUseCase(IAuthPasswordEncoderPort authEncoderPort, IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort) {
        this.authEncoderPort = authEncoderPort;
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public void saveOwner(User user) {
        Role role = this.rolePersistencePort.getRole(Constants.OWNER_ROLE_ID);

        user.setRole(role);
        user.setPassword(authEncoderPort.encodePassword(user.getPassword()));
        if (UserValid.isOlder(user)) {
            userPersistencePort.saveUser(user);
        } else {
            throw new IsOlder();
        }
    }

    @Override
    public void deleteUser(User user) {
        userPersistencePort.deleteUser(user);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userPersistencePort.getUserByEmail(email).orElse(null);
        if (user == null) {
            throw new UserNotFound();
        }
        return user;
    }

    @Override
    public List<User> getAllProviders(int page) {
        return userPersistencePort.getAllProviders(page);
    }

    @Override
    public User getProvider(Long id) {
        return userPersistencePort.getProvider(id);
    }

    @Override
    public User getOwner(Long id) {
        return userPersistencePort.getOwner(id);
    }

    @Override
    public User getEmployee(Long id) {
        return userPersistencePort.getEmployee(id);
    }
}
