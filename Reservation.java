package com.hotelreservation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Room room;

    @NotBlank
    private String guestName;

    @NotNull
    private LocalDate checkinDate;

    @NotNull
    private LocalDate checkoutDate;

    @NotBlank
    private String status; // CREATED, CHECKED_IN, CHECKED_OUT, CANCELED

    private Double totalValue;

    // getters e setters
}
