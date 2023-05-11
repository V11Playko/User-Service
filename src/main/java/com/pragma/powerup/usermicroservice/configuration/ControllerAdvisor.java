package com.pragma.powerup.usermicroservice.configuration;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.DataRequired;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.IsOlder;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.OwnerNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.RoleNotAllowedForCreationException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.RoleNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.pragma.powerup.usermicroservice.configuration.Constants.DATA_REQUIRED;
import static com.pragma.powerup.usermicroservice.configuration.Constants.IS_OLDER;
import static com.pragma.powerup.usermicroservice.configuration.Constants.MAIL_ALREADY_EXISTS_MESSAGE;
import static com.pragma.powerup.usermicroservice.configuration.Constants.NO_DATA_FOUND_MESSAGE;
import static com.pragma.powerup.usermicroservice.configuration.Constants.OWNER_NOT_FOUND_MESSAGE;
import static com.pragma.powerup.usermicroservice.configuration.Constants.RESPONSE_ERROR_MESSAGE_KEY;
import static com.pragma.powerup.usermicroservice.configuration.Constants.ROLE_NOT_ALLOWED_MESSAGE;
import static com.pragma.powerup.usermicroservice.configuration.Constants.ROLE_NOT_FOUND_MESSAGE;
import static com.pragma.powerup.usermicroservice.configuration.Constants.USER_ALREADY_EXISTS_MESSAGE;
import static com.pragma.powerup.usermicroservice.configuration.Constants.USER_NOT_FOUND_MESSAGE;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(NoDataFoundException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NO_DATA_FOUND_MESSAGE));
    }

    @ExceptionHandler(MailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleMailAlreadyExistsException(
            MailAlreadyExistsException mailAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, MAIL_ALREADY_EXISTS_MESSAGE));
    }
    @ExceptionHandler(RoleNotAllowedForCreationException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotAllowedForCreationException(
            RoleNotAllowedForCreationException roleNotAllowedForCreationException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ROLE_NOT_ALLOWED_MESSAGE));
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(
            UserAlreadyExistsException userAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_ALREADY_EXISTS_MESSAGE));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(
            UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_NOT_FOUND_MESSAGE));
    }
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotFoundException(
            RoleNotFoundException roleNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ROLE_NOT_FOUND_MESSAGE));
    }
    @ExceptionHandler(DataRequired.class)
    public ResponseEntity<Map<String, String>> handleUseCaseDataRequiredNotFoundException(
            DataRequired dataRequired) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, DATA_REQUIRED));
    }
    @ExceptionHandler(IsOlder.class)
    public ResponseEntity<Map<String, String>> mustBeOfLegalAgeExceptionNotFound(
            IsOlder isHolder) {
        // Valorar y argumentar el status
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, IS_OLDER));
    }
    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<Map<String, String>> ownerNotFound(
            OwnerNotFoundException ownerNotFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, OWNER_NOT_FOUND_MESSAGE));
    }
}
