package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlData {
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

    public static UserEntity obtainUserEntity(){
        UserEntity user = new UserEntity();

        user.setId(1L);
        user.setName("Prada");
        user.setSurname("Gaja");
        user.setBirthdate(LocalDate.of(2000, 02, 24));
        user.setPhone("+57 3134456252");
        user.setEmail("gaja@gmail.com");
        user.setPassword("gaja");
        user.setDniNumber("123456");

        return user;
    }

    public static List<RoleEntity> obtainRoles() {
        List<RoleEntity> roleList = new ArrayList<>();

//        roleList.add(new RoleEntity(1L,
//                "ROLE_ADMIN",
//                "ROLE_ADMIN"));
//        roleList.add(new RoleEntity(2L,
//                "ROLE_OWNER",
//                "ROLE_OWNER"));

        return roleList;
    }
}
