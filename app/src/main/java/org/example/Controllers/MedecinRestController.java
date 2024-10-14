package org.example.Controllers;

import java.util.List;

import org.example.Entities.Medecin;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedecinRestController {
    @Autowired
    private MedecinService medecinService;

    @GetMapping("/medecins")
    public List<Medecin> getAllMedecins() {
        return medecinService.findAll();
    }

    @GetMapping("/medecin/{id}")
    public Medecin getMedecinById(@PathVariable Long id) {
        return medecinService.findOneById(id);
    }

    @PostMapping("/medecins")
    @ResponseStatus(HttpStatus.CREATED)
    public Medecin createMedecin(@RequestBody Medecin medecin) {
        return medecinService.save(medecin);
    }

    @PutMapping("/medecin/{id}")
    public Medecin updateMedecin(@PathVariable Long id, @RequestBody Medecin medecin) {
        return medecinService.update(id, medecin);
    }

    @DeleteMapping("/medecin/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedecin(@PathVariable Long id) {
        medecinService.delete(id);
    }

    @ExceptionHandler(MedecinNotFoundException.class)
    public ResponseEntity<String> handle(MedecinNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}


