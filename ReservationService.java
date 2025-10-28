package com.hotelreservation.service;

import com.hotelreservation.model.Reservation;
import com.hotelreservation.model.Room;
import com.hotelreservation.repository.ReservationRepository;
import com.hotelreservation.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepo;
    private final RoomRepository roomRepo;

    public ReservationService(ReservationRepository reservationRepo, RoomRepository roomRepo) {
        this.reservationRepo = reservationRepo;
        this.roomRepo = roomRepo;
    }

    public Reservation createReservation(Reservation reservation) throws Exception {
        Room room = roomRepo.findById(reservation.getRoom().getId())
                .orElseThrow(() -> new Exception("Quarto não encontrado"));

        // Verifica disponibilidade
        List<Reservation> overlapping = reservationRepo.findByRoomIdAndStatusInAndCheckinDateLessThanEqualAndCheckoutDateGreaterThanEqual(
                room.getId(),
                Arrays.asList("CREATED", "CHECKED_IN"),
                reservation.getCheckoutDate(),
                reservation.getCheckinDate()
        );

        if (!overlapping.isEmpty()) {
            throw new Exception("Quarto indisponível nas datas selecionadas");
        }

        reservation.setStatus("CREATED");
        return reservationRepo.save(reservation);
    }

    public Reservation checkIn(Long reservationId) throws Exception {
        Reservation r = reservationRepo.findById(reservationId)
                .orElseThrow(() -> new Exception("Reserva não encontrada"));
        r.setStatus("CHECKED_IN");
        return reservationRepo.save(r);
    }

    public Reservation checkOut(Long reservationId) throws Exception {
        Reservation r = reservationRepo.findById(reservationId)
                .orElseThrow(() -> new Exception("Reserva não encontrada"));
        long days = ChronoUnit.DAYS.between(r.getCheckinDate(), r.getCheckoutDate());
        r.setTotalValue(days * r.getRoom().getDailyRate());
        r.setStatus("CHECKED_OUT");
        return reservationRepo.save(r);
    }

    public Reservation cancel(Long reservationId) throws Exception {
        Reservation r = reservationRepo.findById(reservationId)
                .orElseThrow(() -> new Exception("Reserva não encontrada"));
        r.setStatus("CANCELED");
        return reservationRepo.save(r);
    }
}
