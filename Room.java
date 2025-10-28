package com.hotelreservation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String number;

    @NotBlank
    private String type; // STANDARD, DELUXE, SUITE

    @Min(1)
    private int capacity;

    @DecimalMin("0.0")
    private double dailyRate;

    @NotBlank
    private String status; // ATIVO / INATIVO

    // getters e setters
}
