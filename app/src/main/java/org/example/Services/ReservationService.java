package org.example.Services;

import java.util.List;

import org.example.Entities.Patient;
import org.example.Entities.Reservation;
import org.example.Exceptions.ReservationNotFoundException;
import org.example.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.Repositories.PatientRepository;
import java.util.Date;

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

    // Méthode pour récupérer les réservations par centre
    public List<Reservation> getReservationsByCentre(Long centreId) {
        return reservationRepository.findByCentreId(centreId);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation save(Reservation reservation) {
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
    
}
