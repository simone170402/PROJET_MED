package org.example.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.example.Entities.Reservation;
import org.example.Exceptions.ReservationNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private Map<Long, Reservation> reservationMap = new HashMap<>();
    private Long currentId = 1L;

    public Reservation findOneById(Long id) {
        if (!reservationMap.containsKey(id)) {
            throw new ReservationNotFoundException("Reservation with id " + id + " not found.");
        }
        return reservationMap.get(id);
    }

    public List<Reservation> findAll() {
        return new ArrayList<>(reservationMap.values());
    }

    public Reservation save(Reservation reservation) {
        reservation.setId(currentId);
        reservationMap.put(currentId, reservation);
        currentId++;
        return reservation;
    }

    public Reservation update(Long id, Reservation reservation) {
        if (!reservationMap.containsKey(id)) {
            throw new ReservationNotFoundException("Reservation with id " + id + " not found.");
        }
        reservation.setId(id);
        reservationMap.put(id, reservation);
        return reservation;
    }

    public void delete(Long id) {
        if (!reservationMap.containsKey(id)) {
            throw new ReservationNotFoundException("Reservation with id " + id + " not found.");
        }
        reservationMap.remove(id);
    }

    public List<Reservation> findByPatientId(Long patientId) {
        List<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : reservationMap.values()) {
            if (reservation.getPatient().getId().equals(patientId)) {
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    public List<Reservation> findByCentreId(Long centreId) {
        List<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : reservationMap.values()) {
            if (reservation.getCentre().getId().equals(centreId)) {
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    @SuppressWarnings("unlikely-arg-type")
    public List<Reservation> findByDateReservation(String dateReservation) {
        List<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : reservationMap.values()) {
            if (reservation.getDate().equals(dateReservation)) {
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    public List<Reservation> findByReservationStatus(String reservationStatus) {
        List<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : reservationMap.values()) {
            if (reservation.isReservationStatus().equals(reservationStatus)) {
                reservations.add(reservation);
            }
        }
        return reservations;
    }
}
