package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;


import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.ProvidedNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.OwnerNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.EmployeeNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.ClientNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.pragma.powerup.usermicroservice.configuration.Constants.CLIENT_ROLE_ID;
import static com.pragma.powerup.usermicroservice.configuration.Constants.EMPLOYEE_ROLE_ID;
import static com.pragma.powerup.usermicroservice.configuration.Constants.MAX_PAGE_SIZE;
import static com.pragma.powerup.usermicroservice.configuration.Constants.OWNER_ROLE_ID;
import static com.pragma.powerup.usermicroservice.configuration.Constants.PROVIDER_ROLE_ID;

@RequiredArgsConstructor
@Transactional
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    /**
     * Saves a user in the user's table on the database
     *
     * @param user - data of the user
     * @throws UserAlreadyExistsException - A user already exists with the provider role
     * @throws MailAlreadyExistsException - The email already exists
     * */
    @Override
    public void saveUser(User user) {
        if (userRepository.findByDniNumber(user.getDniNumber()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if (userRepository.existsByEmail(user.getEmail())){
            throw new MailAlreadyExistsException();
        }
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public void deleteUser(User user) {
        if (userRepository.findByIdAndRoleEntityId(user.getId(), user.getRole().getId()).isPresent()) {
            userRepository.deleteByIdAndRoleEntityId(user.getId(), user.getRole().getId());
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return Optional.ofNullable(userEntityMapper.toUser(user));
    }

    @Override
    public List<User> getAllProviders(int page) {
        Pageable pagination = PageRequest.of(page, MAX_PAGE_SIZE);
        List<UserEntity> userEntityList = userRepository.findAllByRoleEntityId(PROVIDER_ROLE_ID, pagination);
        if (userEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public User getProvider(Long id) {
        UserEntity userEntity = userRepository.findByIdAndRoleEntityId(id, PROVIDER_ROLE_ID).orElseThrow(ProvidedNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }

    @Override
    public User getOwner(Long id) {
        UserEntity userEntity = userRepository.findByIdAndRoleEntityId(id, OWNER_ROLE_ID).orElseThrow(OwnerNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }

    @Override
    public User getEmployee(Long id) {
        UserEntity userEntity = userRepository.findByIdAndRoleEntityId(id, EMPLOYEE_ROLE_ID).orElseThrow(EmployeeNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }

    @Override
    public User getClient(Long id) {
        UserEntity userEntity = userRepository.findByIdAndRoleEntityId(id, CLIENT_ROLE_ID).orElseThrow(ClientNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }
}
