package com.example.proyect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyect.dto.RegisterUserDTO;
import com.example.proyect.model.UserModel;
import com.example.proyect.service.JWTService;
import com.example.proyect.service.UserService;

@RestController
public class LogInController {
    
    private final UserService userService;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    public LogInController(UserService pUserService, JWTService pJwtService, AuthenticationManager pAuthenticationManager) {
        this.userService = pUserService;
        this.jwtService = pJwtService;
        this.authenticationManager = pAuthenticationManager;
    }


    @PostMapping("/register")
    public UserModel register(@RequestBody RegisterUserDTO pRequest) {

        return this.userService.registerUser(pRequest);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegisterUserDTO request) {

        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
            )
        );

        String jwt = jwtService.generateToken(auth.getName());

        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/test")
    public String test() {

        return "Hola!";
    }


}
