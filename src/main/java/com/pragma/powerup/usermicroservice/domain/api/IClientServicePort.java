package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IClientServicePort {
    void saveClient(User user);
    User getClient(Long id);
}
