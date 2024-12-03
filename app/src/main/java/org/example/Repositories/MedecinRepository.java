package org.example.Repositories;

import org.example.Entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findByEmail(String email);
    Medecin findByPhoneNumber(String phoneNumber);
    Medecin findByName(String name);
    Medecin findBySurname(String surname);
    Medecin findByCity(String city);
    Medecin findByAddress(String address);

    @Query("SELECT u.id, u.name, u.surname, u.phoneNumber, c.id as centreId, c.name as centreName " +
           "FROM Utilisateur u " +
           "JOIN u.utilisateurRoles ur " +
           "JOIN ur.role r " +
           "LEFT JOIN Medecin m ON u.id = m.id " +
           "LEFT JOIN m.centre c " +
           "WHERE r.id = 2 AND c.id = :centreId")
    List<Object[]> findMedecinsByCentreId(@Param("centreId") Long centreId);
}