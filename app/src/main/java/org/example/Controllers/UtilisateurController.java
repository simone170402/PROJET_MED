package org.example.Controllers;

import java.util.List;
import java.util.Set;

import org.example.Entities.Role;
import org.example.Entities.Utilisateur;
import org.example.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public Utilisateur getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @PutMapping("/{id}")
    public Utilisateur updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        utilisateur.setId(id);
        return utilisateurService.createOrUpdateUtilisateur(utilisateur);
    }

    @DeleteMapping("/{id}")
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }


    @PostMapping("/creer")
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestParam String nom,
                                                         @RequestParam String prenom,
                                                         @RequestParam String email,
                                                         @RequestParam String motDePasse,
                                                         @RequestParam String role) {
        Utilisateur utilisateur = utilisateurService.createUtilisateur(nom, prenom, email, motDePasse, role);
        return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable String email) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    }

    @GetMapping("/{email}/roles")
    public ResponseEntity<Set<Role>> getRoles(@PathVariable String email) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);
        Set<Role> roles = utilisateurService.getRoles(utilisateur);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}


