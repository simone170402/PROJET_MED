package org.example.Repositories;

import org.example.Entities.Centre;
import org.example.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Méthodes JPA standard
    Optional<Patient> findByEmail(String email);
    Patient findByPhoneNumber(String phoneNumber);
    Patient findByName(String name);
    Patient findBySurname(String surname);
    Patient findByDateOfBirth(String dateOfBirth);
    Patient findByVaccinationStatus(String vaccinationStatus);
    // Recherche des patients dont le nom commence par une certaine chaîne
    List<Patient> findByNameStartingWith(String name);
    List<Patient> findByCentre(Centre centre);
    
}


