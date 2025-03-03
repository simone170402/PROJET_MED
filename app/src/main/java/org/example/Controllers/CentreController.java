package org.example.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.Entities.Centre;
import org.example.Entities.Medecin;
import org.example.Services.CentreService;
import org.example.Repositories.CentreRepository;
import org.example.Repositories.MedecinRepository;
import org.example.Repositories.PatientRepository;
import org.example.Exceptions.CentreNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/centres")
public class CentreController {
    @Autowired
    private CentreService centreService;

    @Autowired
    private CentreRepository centreRepository;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public List<Centre> getAllCentres() {
        return centreService.getAllCentres();
    }

    @GetMapping("/search")
    public List<Centre> getCentresByCity(@RequestParam String city) {
        return centreService.getCentresByCity(city);
    }

    @GetMapping("/{id}")
    public Centre getCentreById(@PathVariable Long id) {
        return centreService.getCentreById(id)
                .orElseThrow(() -> new CentreNotFoundException("Centre not found with id " + id));
    }

    
    @GetMapping("/{id}/medecins")
    public List<Medecin> getMedecinsByCentreId(@PathVariable Long id) {
        return centreService.getMedecinsByCentreId(id);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Centre> createCentre(@RequestBody @Validated Centre centre) {
        Centre savedCentre = centreService.save(centre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCentre);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Centre> updateCentre(@PathVariable Long id, @RequestBody Centre centre) {
        Optional<Centre> existingCentreOpt = centreRepository.findById(id);
        
        if (existingCentreOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Centre existingCentre = existingCentreOpt.get();

        // Mise à jour des champs basiques
        existingCentre.setName(centre.getName());
        existingCentre.setCity(centre.getCity());
        existingCentre.setAddress(centre.getAddress());
        existingCentre.setPhoneNumber(centre.getPhoneNumber());

        //  Correction de la mise à jour des médecins
        if (centre.getMedecins() != null && !centre.getMedecins().isEmpty()) {
            List<Medecin> medecinsToUpdate = new ArrayList<>();
            for (Medecin medecin : centre.getMedecins()) {
                if (medecin.getId() != null) { // Vérifier que l'ID est bien présent
                    medecinRepository.findById(medecin.getId()).ifPresent(medecinsToUpdate::add);
                }
            }
            existingCentre.setMedecins(medecinsToUpdate);
        } else {
            existingCentre.setMedecins(new ArrayList<>()); // Si aucun médecin n'est envoyé, on vide la liste
        }

        return ResponseEntity.ok(centreRepository.save(existingCentre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCentre(@PathVariable Long id) {
        if (centreService.existsById(id)) {
            centreService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new CentreNotFoundException("Centre not found with id " + id);
    }
}