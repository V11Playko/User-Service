package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.DataRequired;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.CreateOwnerRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IAdminHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.request.ICreateOwnerRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.request.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.response.IUserResponseMapper;
import com.pragma.powerup.usermicroservice.domain.api.IAdminServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserNotFound;
import com.pragma.powerup.usermicroservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminHandler implements IAdminHandler {

    private final IAdminServicePort adminServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final ICreateOwnerRequestMapper createOwnerRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public void saveOwner(CreateOwnerRequestDto createOwnerRequestDto) {
        adminServicePort.saveOwner(createOwnerRequestMapper.toUser(createOwnerRequestDto));
    }

    @Override
    public void deleteUser(UserRequestDto userRequestDto) {
        adminServicePort.deleteUser(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        User user;
        try {
            user = adminServicePort.getUserByEmail(email);
        } catch (UserNotFound e) {
            throw new DataRequired();
        }
        return userResponseMapper.toResponse(user);
    }

    @Override
    public List<UserResponseDto> getProvider(Integer page) {
        return userResponseMapper.toResponseList(adminServicePort.getAllProviders(page));
    }

    @Override
    public UserResponseDto getProvider(Long id) {
        return userResponseMapper.toResponse(adminServicePort.getProvider(id));
    }

    @Override
    public UserResponseDto getOwner(Long id) {
        return userResponseMapper.toResponse(adminServicePort.getOwner(id));
    }

    @Override
    public UserResponseDto getEmployee(Long id) {
        return userResponseMapper.toResponse(adminServicePort.getEmployee(id));
    }

}

