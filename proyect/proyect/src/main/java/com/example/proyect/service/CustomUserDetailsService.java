package com.example.proyect.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.proyect.model.MyUserDetails;
import com.example.proyect.model.UserModel;
import com.example.proyect.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository pUserRepository) {
        this.userRepository = pUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserModel myUser = userRepository.findByUsername(username);

        if(myUser == null) {

            System.out.println("My user not found");
            throw new UsernameNotFoundException("User not found");
        }

        return new MyUserDetails(myUser);
    }
    
}
