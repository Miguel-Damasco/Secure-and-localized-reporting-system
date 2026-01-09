package com.example.proyect.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.proyect.dto.user.LoginUserRequestDTO;
import com.example.proyect.dto.user.LoginUserResponseDTO;

@Service
public class AuthenticationService {
    
    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(JWTService pJwtService, AuthenticationManager pAuthenticationManager) {
        this.jwtService = pJwtService;
        this.authenticationManager = pAuthenticationManager;
    }

    public LoginUserResponseDTO login(LoginUserRequestDTO pRequest) {

        Authentication authentication = this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(pRequest.username(), 
                                                    pRequest.password())
        );

        String token = this.jwtService.generateToken(authentication.getName());

        return new LoginUserResponseDTO(token);
    }
}
