package org.example.Repositories;

import java.util.Date;
import java.util.List;

import org.example.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByPatientId(Long patientId);
    List<Reservation> findByCentreId(Long centreId);
    List<Reservation> findByDateReservation(String dateReservation);
    List<Reservation> findByReservationStatus(String reservationStatus);
    List<Reservation> findByMedecinId(Long medecinId);
    List<Reservation> findByMedecinIdAndReservationStatus(Long medecinId, String reservationStatus);
}

