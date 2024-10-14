package org.example.Services;

import java.util.List;

import org.example.Entities.Administrateur;
import org.example.Repositories.AdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrateurService {
    @Autowired
    private AdministrateurRepository administrateurRepository;

    public List<Administrateur> getAllAdministrateurs() {
        return administrateurRepository.findAll();
    }

    public Administrateur getAdministrateurById(Long id) {
        return administrateurRepository.findById(id).orElse(null);
    }

    public Administrateur createOrUpdateAdministrateur(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    public void deleteAdministrateur(Long id) {
        administrateurRepository.deleteById(id);
    }
}

