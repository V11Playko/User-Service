package com.pragma.powerup.usermicroservice.domain.validations;


import com.pragma.powerup.usermicroservice.domain.model.User;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class UserValid {

    public static boolean isOlder(User user) {
        String fechaNacimiento = String.valueOf(user.getBirthdate());
        try {
            LocalDate fecha = LocalDate.parse(fechaNacimiento);
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(fecha, ahora);
            int edad = periodo.getYears();
            return edad >= 18;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
