package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IOwnerServicePort {
    void saveEmployee(User user);
}
