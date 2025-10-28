package com.hotelreservation.repository;

import com.hotelreservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRoomIdAndStatusInAndCheckinDateLessThanEqualAndCheckoutDateGreaterThanEqual(
        Long roomId, List<String> statuses, LocalDate end, LocalDate start);
}
