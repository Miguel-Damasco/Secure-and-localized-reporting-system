package com.example.proyect.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.proyect.dto.RegisterUserDTO;
import com.example.proyect.model.UserModel;
import com.example.proyect.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository pUserRepository, PasswordEncoder pPasswordEncoder) {
        this.userRepository = pUserRepository;
        this.encoder = pPasswordEncoder;
    }

    public UserModel registerUser(RegisterUserDTO pNewUser) {

        UserModel myNewUser = new UserModel();
        myNewUser.setUsername(pNewUser.username());
        myNewUser.setPassword(encoder.encode(pNewUser.password()));

        userRepository.save(myNewUser);

        return myNewUser;

    }
}
