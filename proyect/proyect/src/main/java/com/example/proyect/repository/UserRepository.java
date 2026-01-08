package com.example.proyect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyect.model.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    
    UserModel findByUsername(String username);
}
