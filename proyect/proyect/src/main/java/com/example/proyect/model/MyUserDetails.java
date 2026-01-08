package com.example.proyect.model;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {


    private UserModel userModel;

    public MyUserDetails(UserModel pUserModel) {
        this.userModel = pUserModel;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        
        return this.userModel.getPassword();
    }

    @Override
    public String getUsername() {
      
        return this.userModel.getUsername();
    }
    
}
