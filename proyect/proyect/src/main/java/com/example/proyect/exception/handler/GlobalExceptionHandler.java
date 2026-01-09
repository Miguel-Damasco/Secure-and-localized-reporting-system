package com.example.proyect.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.proyect.dto.response.ApiResponse;
import com.example.proyect.dto.response.ApiResponses;
import com.example.proyect.exception.domain.UserAlreadyExistsException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserAlreadyExists(UserAlreadyExistsException exception, 
                                                                                HttpServletRequest request) {


        String path = request.getRequestURI();

        return ResponseEntity
                            .status(HttpStatus.CONFLICT)
                            .body(ApiResponses.error(exception.getMessage(), 
                                                                "USER_ALREADY_EXISTS",
                                                                HttpStatus.CONFLICT.value(),
                                                                "Request failed", 
                                                                path));

    }
}
