package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.CreateEmployeeRequestDto;

public interface IOwnerHandler {
    void saveEmployee(CreateEmployeeRequestDto createEmployeeRequestDto);
}
