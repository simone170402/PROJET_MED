package org.example.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.Entities.Medecin;
import org.example.Entities.Utilisateur;
import org.example.Entities.UtilisateurRole;
import org.example.Exceptions.MedecinNotFoundException;
import org.example.Repositories.MedecinRepository;
import org.example.Repositories.UtilisateurRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MedecinService {
    private Map<Long, Medecin> medecinMap = new HashMap<>();
    private Long currentId = 1L;
    private final MedecinRepository medecinRepository;
    private final UtilisateurRoleRepository utilisateurRoleRepository;


    @Autowired
    public MedecinService(MedecinRepository medecinRepository, UtilisateurRoleRepository utilisateurRoleRepository) {
        this.medecinRepository = medecinRepository;
        this.utilisateurRoleRepository = utilisateurRoleRepository;
    }

    public Medecin findOneById(Long id) {
        return medecinRepository.findById(id)
                .orElseThrow(() -> new MedecinNotFoundException("Medecin with ID " + id + " not found"));
    }

     public List<Medecin> getMedecinsByCentre(Long centreId) {
        // Trouver tous les utilisateurs avec un role_id = 2 (Médecin)
        List<UtilisateurRole> utilisateurRoles = utilisateurRoleRepository.findByRoleId(2L); // role_id 2 est pour les médecins
        List<Medecin> medecins = new ArrayList<>();

        for (UtilisateurRole utilisateurRole : utilisateurRoles) {
            Utilisateur utilisateur = utilisateurRole.getUtilisateur();
            // Vérifiez si l'utilisateur est associé au centre spécifié
            if (utilisateur instanceof Medecin && ((Medecin) utilisateur).getCentre().getId().equals(centreId)) {
                medecins.add((Medecin) utilisateur);
            }
        }

        return medecins;
    }
    

    public List<Medecin> findAll() {
        return new ArrayList<>(medecinMap.values());
    }

    public Medecin save(Medecin medecin) {
        medecin.setId(currentId);
        medecinMap.put(currentId, medecin);
        currentId++;
        return medecin;
    }

    public Medecin update(Long id, Medecin medecin) {
        if (!medecinMap.containsKey(id)) {
            throw new MedecinNotFoundException("Medecin with id " + id + " not found.");
        }
        medecin.setId(id);
        medecinMap.put(id, medecin);
        return medecin;
    }

    public void delete(Long id) {
        if (!medecinMap.containsKey(id)) {
            throw new MedecinNotFoundException("Medecin with id " + id + " not found.");
        }
        medecinMap.remove(id);
    }
}



