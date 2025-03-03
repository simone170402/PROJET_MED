package org.example.Repositories;

import java.util.List;

import org.example.Entities.Administrateur;
import org.example.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

