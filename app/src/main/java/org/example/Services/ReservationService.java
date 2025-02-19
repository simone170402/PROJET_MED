package org.example.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.example.Entities.Patient;
import org.example.Entities.Reservation;
import org.example.Exceptions.ReservationNotFoundException;
import org.example.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.Repositories.PatientRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PatientRepository patientRepository;

    public Reservation findOneById(Long id) {
        return reservationRepository.findById(id)
            .orElseThrow(() -> new ReservationNotFoundException("Reservation with id " + id + " not found."));
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation save(Reservation reservation) {
        validateReservationDate(reservation);
        validateReservationTime(reservation);
        return reservationRepository.save(reservation);
    }

    public Reservation update(Long id, Reservation reservation) {
        if (!reservationRepository.existsById(id)) {
            throw new ReservationNotFoundException("Reservation with id " + id + " not found.");
        }
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }

    public void delete(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new ReservationNotFoundException("Reservation with id " + id + " not found.");
        }
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getCreneauxByMedecin(Long medecinId) {
        return reservationRepository.findByMedecinId(medecinId);
    }

    public List<Reservation> getAvailableCreneauxByMedecin(Long medecinId) {
        return reservationRepository.findByMedecinIdAndReservationStatus(medecinId, "Disponible");
    }

    public Reservation reserveCreneau(Reservation reservationData, Patient patientData) {
        // Save or retrieve the patient
        Patient patient = patientRepository.findByEmail(patientData.getEmail())
                .orElseGet(() -> patientRepository.save(patientData));
    
        // Check if the slot is available
        if (!"Disponible".equals(reservationData.getReservationStatus())) {
            throw new RuntimeException("Créneau déjà réservé.");
        }
    
        // Link the patient to the reservation
        reservationData.setPatient(patient);
        reservationData.setReservationStatus("Réservé");
    
        // Save and return the reservation
        return reservationRepository.save(reservationData);
    }

    public List<Reservation> findByDate(String date) {
        return reservationRepository.findByDateReservation(date);
    }

    public List<Reservation> findByStatus(String status) {
        return reservationRepository.findByReservationStatus(status);
    }

    public List<Reservation> findByDateAndStatus(String date, String status) {
        return reservationRepository.findByDateReservationAndReservationStatus(date, status);
    }

    public Reservation cancelReservation(Long id) {
        Reservation reservation = findOneById(id);
        if (reservation == null) {
            throw new ReservationNotFoundException("Reservation not found with id: " + id);
        }
        
        reservation.setReservationStatus("Annulé");
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAvailableTimeSlots(Long medecinId, String date) {
        return reservationRepository.findByMedecinIdAndDateReservationAndReservationStatus(
            medecinId, date, "Disponible");
    }

    public void validateReservationTime(Reservation reservation) {
        String startTime = reservation.getDatestart();
        if (startTime == null) {
            throw new IllegalArgumentException("L'heure de début est requise");
        }

        // Convertir l'heure au format 24h
        int hour = Integer.parseInt(startTime.split("T")[1].split(":")[0]);
        
        // Vérifier si l'heure est dans les heures de consultation (9h-17h)
        if (hour < 9 || hour >= 17) {
            throw new IllegalArgumentException("Les réservations ne sont possibles qu'entre 9h et 17h");
        }
    }

    public void validateReservationDate(Reservation reservation) {
        String dateReservation = reservation.getDateReservation();
        if (dateReservation == null) {
            throw new IllegalArgumentException("La date de réservation est requise");
        }

        // Vérifier si la date est dans le futur
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date reservationDate = sdf.parse(dateReservation);
            Date today = new Date();
            
            if (reservationDate.before(today)) {
                throw new IllegalArgumentException("La date de réservation doit être dans le futur");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Format de date invalide");
        }
    }
}
