package org.example.Services;

import java.util.List;

import org.example.Entities.Centre;
import org.example.Entities.Medecin;
import org.example.Repositories.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentreService {
    @Autowired
    private CentreRepository centreRepository;

    public List<Centre> getAllCentres() {
        return centreRepository.findAll();
    }

    public Centre getCentreById(Long id) {
        return centreRepository.findById(id).orElse(null);
    }

    public Centre createOrUpdateCentre(Centre centre) {
        return centreRepository.save(centre);
    }

    public void deleteCentre(Long id) {
        centreRepository.deleteById(id);
    }

    public List<Centre> getCentresByCity(String city) {
        return centreRepository.findByCity(city);
    }

    public List<Medecin> getMedecinsByCentreId(Long centreId) {
        Centre centre = centreRepository.findById(centreId)
                .orElseThrow(() -> new RuntimeException("Centre not found with id " + centreId));
        return centre.getMedecins();
    } 
          
}

