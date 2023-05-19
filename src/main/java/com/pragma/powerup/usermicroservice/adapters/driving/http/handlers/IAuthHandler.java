package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.LoginRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.JwtTokenResponseDto;

public interface IAuthHandler {
    JwtTokenResponseDto loginUser(LoginRequestDto loginRequestDto);

}
