package org.example.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.example.Entities.Medecin;
import org.example.Entities.Utilisateur;
import org.example.Exceptions.MedecinNotFoundException;
import org.example.Services.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;

import org.example.Repositories.MedecinRepository;
import org.example.Repositories.UtilisateurRepository;


@RestController
@RequestMapping("/api/medecins")
public class MedecinRestController {
    private final MedecinService medecinService;
    private final MedecinRepository medecinRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public MedecinRestController(MedecinService medecinService, MedecinRepository medecinRepository, UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.medecinService = medecinService;
        this.medecinRepository = medecinRepository;
    }

    @GetMapping
    public List<Utilisateur> getAllMedecins() {
        List<Object[]> results = utilisateurRepository.findMedecinsRaw();

        List<Utilisateur> medecins = results.stream().map(row -> {
            Utilisateur medecin = new Utilisateur();
            medecin.setId(((Number) row[0]).longValue());
            medecin.setName((String) row[1]);
            medecin.setEmail((String) row[2]);
            medecin.setPhoneNumber((String) row[3]);
            medecin.setCity((String) row[4]);
            medecin.setAddress((String) row[5]);
            return medecin;
        }).collect(Collectors.toList());

        System.out.println("Nombre de médecins trouvés : " + medecins.size());
        medecins.forEach(medecin -> 
            System.out.println("Médecin : " + medecin.getName() + " - " + medecin.getEmail())
        );

        return medecins;
    }


    @GetMapping("/centre/{centreId}")
    public List<Map<String, Object>> getMedecinsByCentre(@PathVariable Long centreId) {
        List<Object[]> results = medecinRepository.findMedecinsByCentreId(centreId);
        List<Map<String, Object>> medecins = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> medecin = new HashMap<>();
            medecin.put("id", result[0]);
            medecin.put("name", result[1]);
            medecin.put("surname", result[2]);
            medecin.put("phoneNumber", result[3]);
            medecin.put("centreId", result[4]);
            medecin.put("centreName", result[5]);
            medecins.add(medecin);
        }

        return medecins;
    }

    @GetMapping("/{id}")
    public Medecin getMedecinById(@PathVariable Long id) {
        return medecinService.findOneById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Medecin createMedecin(@RequestBody Medecin medecin) {
        return medecinService.save(medecin);
    }


    @PutMapping("/{id}")
    public Medecin updateMedecin(@PathVariable Long id, @RequestBody Medecin medecin) {
        return medecinService.update(id, medecin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        medecinService.deleteMedecin(id);
        return ResponseEntity.noContent().build();
}


    @ExceptionHandler(MedecinNotFoundException.class)
    public ResponseEntity<String> handle(MedecinNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}


