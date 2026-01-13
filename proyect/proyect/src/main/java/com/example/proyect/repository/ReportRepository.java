package com.example.proyect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyect.model.ReportModel;

@Repository
public interface ReportRepository extends JpaRepository<ReportModel, Long>{
    
}
