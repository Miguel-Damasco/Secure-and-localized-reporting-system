package com.example.proyect.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.proyect.dto.response.ApiResponse;
import com.example.proyect.dto.response.ApiResponses;
import com.example.proyect.dto.user.LoginUserRequestDTO;
import com.example.proyect.dto.user.LoginUserResponseDTO;
import com.example.proyect.dto.user.RegisterUserRequestDTO;
import com.example.proyect.dto.user.RegisterUserResponseDTO;
import com.example.proyect.service.AuthenticationService;
import com.example.proyect.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class AuthenticationController {
    
    private final UserService userService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(UserService pUserService, AuthenticationService pAuthenticationService) {
        this.userService = pUserService;
        this.authenticationService = pAuthenticationService;
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterUserResponseDTO>> register(@RequestBody RegisterUserRequestDTO pRequest) {

        RegisterUserResponseDTO response = this.userService.registerUser(pRequest);

        URI location = ServletUriComponentsBuilder
                                            .fromCurrentRequest()
                                            .path("/{id}")
                                            .buildAndExpand(response.id())
                                            .toUri();

        return ResponseEntity.created(location)
                                        .body(ApiResponses.success(response, 
                                                    201, 
                                                    "User successfully register!", 
                                                    location.getPath()));
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginUserResponseDTO>> login(@RequestBody LoginUserRequestDTO request) {

        LoginUserResponseDTO response = this.authenticationService.login(request);

        String path = ServletUriComponentsBuilder
                                                .fromCurrentRequest()
                                                .build()
                                                .getPath();

        return ResponseEntity.ok(ApiResponses.success(response, 
                                    200, "Log in successfully!", path));
    }

}
