package org.example.Repositories;

import org.example.Entities.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
    Administrateur findByEmail(String email);
    Administrateur findByPhoneNumber(String phoneNumber);
    Administrateur findByName(String name);
    Administrateur findBySurname(String surname);
    Administrateur findByCity(String city);
    Administrateur findByAddress(String address);
}

