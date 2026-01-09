package com.example.proyect.exception.domain;

public class UserAlreadyExistsException extends RuntimeException {
    
    public UserAlreadyExistsException(String pUsername) {
        super("User already exists: " + pUsername);
    }
}
