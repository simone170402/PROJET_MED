package org.example.Repositories;

import org.example.Entities.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CentreRepository extends JpaRepository<Centre, Long> {
    List<Centre> findByCity(String city);
    List<Centre> findByName(String name);
    List<Centre> findByAddress(String address);
    List<Centre> findByPhoneNumber(String phoneNumber);
}
