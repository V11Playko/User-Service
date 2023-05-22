package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DomainData {
    public static User obtainUserValid(){
        return new User(
                1L,
                "Halbert",
                "Jese",
                "6454654",
                "+57 3134647582",
                LocalDate.of(2000, 10, 24),
                "jese@gmail.com",
                "Jese",
                new Role(
                        2L,
                        "ROLE_OWNER",
                        "ROLE_OWNER"
                )
        );
    }
    public static User obtainUserInvalid(){
        return new User(
                1L,
                "Halbert",
                "Jese",
                "6454654",
                "+57 3134647582",
                LocalDate.of(2010, 02, 24),
                "jese@gmail.com",
                "Jese",
                new Role(
                        2L,
                        "ROLE_OWNER",
                        "ROLE_OWNER"
                )
        );
    }

    public static List<Role> obtainRoles() {
        List<Role> roleList = new ArrayList<>();

        roleList.add(new Role(1L,
                "ADMIN",
                "ADMIN"));
        roleList.add(new Role(2L,
                "OWNER",
                "OWNER"));

        return roleList;
    }

}
