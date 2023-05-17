package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserServicePort {
    void saveUser(User user);
    void deleteUser(User user);
    User getUserByMail(String mail);
    List<User> getAllProviders(int page);
    User getProvider(Long id);
    User getOwner(Long id);
    User getEmployee(Long id);
    User getClient(Long id);
}
