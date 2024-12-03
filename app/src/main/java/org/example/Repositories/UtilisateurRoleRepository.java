package org.example.Repositories;

import org.springframework.stereotype.Repository;
import org.example.Entities.UtilisateurRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



@Repository
public interface UtilisateurRoleRepository extends JpaRepository<UtilisateurRole, Long> {
    List<UtilisateurRole> findByRoleId(Long roleId);
}


