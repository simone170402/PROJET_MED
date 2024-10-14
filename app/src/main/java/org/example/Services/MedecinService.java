package org.example.Services;

import java.util.List;

import org.example.Entities.Medecin;
import org.example.Repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedecinService {
    @Autowired
    private MedecinRepository medecinRepository;

    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    public Medecin getMedecinById(Long id) {
        return medecinRepository.findById(id).orElse(null);
    }

    public Medecin createOrUpdateMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    public void deleteMedecin(Long id) {
        medecinRepository.deleteById(id);
    }

    public List<Medecin> searchMedecinsByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchMedecinsByName'");
    }
}

