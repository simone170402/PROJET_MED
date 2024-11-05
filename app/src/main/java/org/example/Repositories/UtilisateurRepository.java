package org.example.Repositories;

import org.example.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
    Utilisateur findByPhoneNumber(String phoneNumber);
    Utilisateur findByName(String name);
    Utilisateur findBySurname(String surname);
    Utilisateur findByCity(String city);
    Utilisateur findByAddress(String address);
}

