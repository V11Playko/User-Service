package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.CreateEmployeeRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IOwnerHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.request.ICreateEmployeeRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.request.ICreateOwnerRequestMapper;
import com.pragma.powerup.usermicroservice.domain.api.IOwnerServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerHandler implements IOwnerHandler {
    private final IOwnerServicePort ownerServicePort;
    private final ICreateEmployeeRequestMapper createEmployeeRequestMapper;
    @Override
    public void saveEmployee(CreateEmployeeRequestDto createEmployeeRequestDto) {
        ownerServicePort.saveEmployee(createEmployeeRequestMapper.toUser(createEmployeeRequestDto));
    }
}
