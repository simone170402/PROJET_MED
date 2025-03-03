package org.example.Repositories;
import java.util.Optional;

import org.example.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
@Query("SELECT r FROM Role r WHERE UPPER(r.name) = UPPER(:name)")
Optional<Role> findByName(@Param("name") String name);

}

