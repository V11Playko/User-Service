package com.pragma.powerup.usermicroservice.configuration;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.RoleMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.UserMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRoleEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRoleRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.domain.api.IAdminServicePort;
import com.pragma.powerup.usermicroservice.domain.api.IClientServicePort;
import com.pragma.powerup.usermicroservice.domain.api.IOwnerServicePort;
import com.pragma.powerup.usermicroservice.domain.api.IRoleServicePort;
import com.pragma.powerup.usermicroservice.domain.spi.IAuthPasswordEncoderPort;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.usecase.AdminUseCase;
import com.pragma.powerup.usermicroservice.domain.usecase.ClientUseCase;
import com.pragma.powerup.usermicroservice.domain.usecase.OwnerUseCase;
import com.pragma.powerup.usermicroservice.domain.usecase.RoleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    @Bean
    public IAuthPasswordEncoderPort authPasswordEncoderPort() {
        return new AuthBcryptAdapter(encoder());
    }

    @Bean
    public IUserPersistencePort userPersistencePort(IUserRepository userRepository, IUserEntityMapper userEntityMapper) {
        return new UserMysqlAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public IRolePersistencePort rolePersistencePort(IRoleRepository roleRepository, IRoleEntityMapper roleEntityMapper) {
        return new RoleMysqlAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IAdminServicePort adminServicePort(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort) {
        return new AdminUseCase(authPasswordEncoderPort(), userPersistencePort, rolePersistencePort);
    }

    @Bean
    public IRoleServicePort roleServicePort(IRolePersistencePort rolePersistencePort) {
        return new RoleUseCase(rolePersistencePort);
    }

    @Bean
    public IOwnerServicePort ownerServicePort(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort) {
        return new OwnerUseCase(this.authPasswordEncoderPort(), userPersistencePort, rolePersistencePort);
    }

    @Bean
    public IClientServicePort clientServicePort(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort) {
        return new ClientUseCase(this.authPasswordEncoderPort(), userPersistencePort, rolePersistencePort);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
