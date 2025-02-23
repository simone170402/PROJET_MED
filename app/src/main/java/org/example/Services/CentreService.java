package org.example.Services;

import java.util.List;
import java.util.Optional;

import org.example.Entities.Centre;
import org.example.Entities.Medecin;
import org.example.Entities.Patient;
import org.example.Entities.Reservation;
import org.example.Exceptions.CentreNotFoundException;
import org.example.Repositories.CentreRepository;
import org.example.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CentreService {
    @Autowired
    private CentreRepository centreRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Centre> getAllCentres() {
        return centreRepository.findAll();
    }

    public Optional<Centre> getCentreById(Long id) {
        return centreRepository.findById(id);
    }

    /**
     * Crée ou met à jour un centre
     * @param centre Le centre à créer ou mettre à jour
     * @return Le centre sauvegardé
     */
    public Centre createOrUpdateCentre(Centre centre) {
        return centreRepository.save(centre);
    }

    public Centre save(Centre centre) {
        return centreRepository.save(centre);
    }

    public void deleteById(Long id) {
    Centre centre = centreRepository.findById(id)
            .orElseThrow(() -> new CentreNotFoundException("Centre not found with id " + id));

    // Détacher les médecins
    for (Medecin medecin : centre.getMedecins()) {
        medecin.setCentre(null);
    }

    // Détacher les réservations
    for (Reservation reservation : centre.getReservations()) {
        reservation.setCentre(null);
    }

    // Détacher l'administrateur
    if (centre.getAdministrateur() != null) {
        centre.getAdministrateur().setCentre(null);
    }

    // Détacher les patients
    List<Patient> patients = patientRepository.findByCentre(centre);
    for (Patient patient : patients) {
        patient.setCentre(null);
    }
    
    patientRepository.saveAll(patients); // Mise à jour en base
    centreRepository.save(centre); // Mise à jour en base

    centreRepository.deleteById(id); // Suppression du Centre
}



    public List<Centre> getCentresByCity(String city) {
        return centreRepository.findByCity(city);
    }

    public List<Medecin> getMedecinsByCentreId(Long centreId) {
        Centre centre = centreRepository.findById(centreId)
                .orElseThrow(() -> new RuntimeException("Centre not found with id " + centreId));
        return centre.getMedecins();
    }

    public boolean existsById(Long id) {
        return centreRepository.existsById(id);
    }
}