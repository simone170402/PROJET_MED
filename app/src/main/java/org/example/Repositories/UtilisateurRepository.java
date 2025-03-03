package org.example.Repositories;

import org.example.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Query("SELECT u FROM Utilisateur u JOIN FETCH u.utilisateurRoles ur JOIN FETCH ur.role WHERE u.email = :email")
    Optional<Utilisateur> findByEmail(@Param("email") String email);
    Utilisateur findByPhoneNumber(String phoneNumber);
    Optional<Utilisateur> findByName(String name);
    Utilisateur findBySurname(String surname);
    Utilisateur findByCity(String city);
    Utilisateur findByAddress(String address);
    @Query(value = "SELECT u.id, u.name, u.email " +
               "FROM utilisateur u " +
               "JOIN utilisateur_role ur ON u.id = ur.utilisateur_id " +
               "JOIN role r ON ur.role_id = r.id " +
               "WHERE r.name = 'ADMIN_CENTRE'", nativeQuery = true)
    List<Object[]> findAdministrateursRaw();


    @Query(value = "SELECT u.id, u.name, u.email, u.phone_number, u.city, u.address " +
               "FROM utilisateur u " +
               "JOIN utilisateur_role ur ON u.id = ur.utilisateur_id " +
               "JOIN role r ON ur.role_id = r.id " +
               "WHERE r.name = 'MEDECIN'", nativeQuery = true)
    List<Object[]> findMedecinsRaw();

    @Query("SELECT u FROM Utilisateur u JOIN FETCH u.utilisateurRoles ur JOIN FETCH ur.role WHERE u.email = :email")
    Utilisateur findByEmailWithRoles(@Param("email") String email);


}

