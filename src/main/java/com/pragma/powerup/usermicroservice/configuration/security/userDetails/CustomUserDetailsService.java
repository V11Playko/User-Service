package com.pragma.powerup.usermicroservice.configuration.security.userDetails;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findByEmail(email);

        List<UserEntity> userEntity = userRepository.findAllById(user.getId());

        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("Invalid email or password");
        }

        List<RoleEntity> roles = new ArrayList<>();

        for (UserEntity usuario : userEntity) {
            roles.add(user.getRoleEntity());
        }
        return CustomUserDetails.build(user, roles);
    }
}
