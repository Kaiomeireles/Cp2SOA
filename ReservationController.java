package com.hotelreservation.controller;

import com.hotelreservation.model.Reservation;
import com.hotelreservation.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation create(@RequestBody Reservation reservation) throws Exception {
        return reservationService.createReservation(reservation);
    }

    @PostMapping("/{id}/checkin")
    public Reservation checkIn(@PathVariable Long id) throws Exception {
        return reservationService.checkIn(id);
    }

    @PostMapping("/{id}/checkout")
    public Reservation checkOut(@PathVariable Long id) throws Exception {
        return reservationService.checkOut(id);
    }

    @PostMapping("/{id}/cancel")
    public Reservation cancel(@PathVariable Long id) throws Exception {
        return reservationService.cancel(id);
    }
}
