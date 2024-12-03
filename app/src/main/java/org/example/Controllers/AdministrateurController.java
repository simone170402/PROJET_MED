package org.example.Controllers;

import java.util.List;

import org.example.Entities.Administrateur;
import org.example.Services.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {
    @Autowired
    private AdministrateurService administrateurService;

    @GetMapping
    public List<Administrateur> getAllAdministrateurs() {
        return administrateurService.getAllAdministrateurs();
    }

    @GetMapping("/{id}")
    public Administrateur getAdministrateurById(@PathVariable Long id) {
        return administrateurService.getAdministrateurById(id);
    }

    @PostMapping
    public Administrateur createAdministrateur(@RequestBody Administrateur administrateur) {
        return administrateurService.createOrUpdateAdministrateur(administrateur);
    }

    @PutMapping("/{id}")
    public Administrateur updateAdministrateur(@PathVariable Long id, @RequestBody Administrateur administrateur) {
        administrateur.setId(id);
        return administrateurService.createOrUpdateAdministrateur(administrateur);
    }

    @DeleteMapping("/{id}")
    public void deleteAdministrateur(@PathVariable Long id) {
        administrateurService.deleteAdministrateur(id);
    }
}

