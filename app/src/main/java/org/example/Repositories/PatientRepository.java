package org.example.Repositories;

import org.example.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);
    Patient findByPhoneNumber(String phoneNumber);
    Patient findByName(String name);
    Patient findBySurname(String surname);
    Patient findByCity(String city);
    Patient findByAddress(String address);
}

