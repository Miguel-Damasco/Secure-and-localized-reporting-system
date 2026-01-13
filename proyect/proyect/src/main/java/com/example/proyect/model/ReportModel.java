package com.example.proyect.model;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Entity
@Table(name = "report")
public class ReportModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    @Column(name = "creation_date")
    private Instant creationDate;

    private String locale;


    public void setPrice(BigDecimal pPrice) {
        this.price = pPrice;
    }

    public void setCreationDate(Instant pCreationDate) {
        this.creationDate = pCreationDate;
    }

    public void setLocale(String pLocale) {
        this.locale = pLocale;
    }

}
