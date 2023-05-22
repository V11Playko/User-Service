package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.CreateOwnerRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HttpData {

    public static User obtainUser(){
        return new User(
                1L,
                "Halbert",
                "Jese",
                "6454654",
                "+57 3134647582",
                LocalDate.of(2000, 02, 24),
                "jese@gmail.com",
                "Jese",
                new Role(
                        1L,
                        "ADMIN",
                        "ADMIN"
                )
        );
    }

    public static UserRequestDto obtainUserRequest(){
        return new UserRequestDto(
                "Halbert",
                "Jese",
                "6454654",
                "+57 3134647582",
                LocalDate.of(2000, 02, 24),
                "jese@gmail.com",
                "Jese",
                1L
        );
    }
    public static CreateOwnerRequestDto obtainOwnerRequest(){
        return new CreateOwnerRequestDto(
                "Halbert",
                "Jese",
                "6454654",
                "+57 3134647582",
                LocalDate.of(2000, 02, 24),
                "jese@gmail.com",
                "Jese"
        );
    }

    public static List<Role> obtainRoles() {
        List<Role> roleList = new ArrayList<>();

//        roleList.add(new Role(1L,
//                "ROLE_ADMIN",
//                "ROLE_ADMIN"));
//        roleList.add(new Role(2L,
//                "ROLE_OWNER",
//                "ROLE_OWNER"));

        return roleList;
    }
}
