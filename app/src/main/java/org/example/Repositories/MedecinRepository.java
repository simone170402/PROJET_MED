package org.example.Repositories;

import org.example.Entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findByEmail(String email);
    Medecin findByPhoneNumber(String phoneNumber);
    Medecin findByName(String name);
    Medecin findBySurname(String surname);
    Medecin findBySpeciality(String speciality);
    Medecin findByCity(String city);
    Medecin findByAddress(String address);
}

