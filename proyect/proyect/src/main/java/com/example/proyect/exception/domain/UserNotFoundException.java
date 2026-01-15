package com.example.proyect.exception.domain;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(long pId) {
        super("user with the id [" + pId +"] not found");
    }
}
