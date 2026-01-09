package com.example.proyect.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.proyect.dto.user.LoginUserRequestDTO;
import com.example.proyect.dto.user.LoginUserResponseDTO;
import com.example.proyect.dto.user.RegisterUserRequestDTO;
import com.example.proyect.dto.user.RegisterUserResponseDTO;
import com.example.proyect.exception.domain.UserAlreadyExistsException;
import com.example.proyect.model.UserModel;
import com.example.proyect.repository.UserRepository;

@Service
public class UserService {
    
    private final JWTService jwtService;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    public UserService(JWTService pJwtService, AuthenticationManager pAuthenticationManager , UserRepository pUserRepository, PasswordEncoder pPasswordEncoder) {
        this.jwtService = pJwtService;
        this.userRepository = pUserRepository;
        this.authenticationManager = pAuthenticationManager;
        this.encoder = pPasswordEncoder;
    }

    public RegisterUserResponseDTO registerUser(RegisterUserRequestDTO pRequest) {

        this.userRepository.findByUsername(pRequest.username()).ifPresent(user -> {
            throw new UserAlreadyExistsException(pRequest.username());
        });

        UserModel myUser = new UserModel();
        myUser.setUsername(pRequest.username());
        myUser.setPassword(this.encoder.encode(pRequest.password()));

        try {

            UserModel userSaved = this.userRepository.save(myUser);
            return new RegisterUserResponseDTO(userSaved.getId(), userSaved.getUsername());
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException(pRequest.username());
        }

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
