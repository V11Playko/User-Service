package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

import java.util.List;

public interface IAdminServicePort {
    void saveOwner(User user);
    void deleteUser(User user);
    User getUserByEmail(String mail);
    List<User> getAllProviders(int page);
    User getProvider(Long id);
    User getOwner(Long id);
    User getEmployee(Long id);
}
