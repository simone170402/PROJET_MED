package org.example.Controllers;

import org.example.Entities.Administrateur;
import org.example.Entities.Role;
import org.example.Entities.Utilisateur;
import org.example.Repositories.UtilisateurRepository;
import org.example.Repositories.RoleRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Set;

@RestController
@RequestMapping("/api/administrateurs")
public class AdministrateurController {
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;

    public AdministrateurController(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public List<Utilisateur> getAllAdministrateurs() {
        List<Object[]> results = utilisateurRepository.findAdministrateursRaw();

        List<Utilisateur> admins = results.stream().map(row -> {
            Utilisateur admin = new Utilisateur();
            admin.setId(((Number) row[0]).longValue());
            admin.setName((String) row[1]);
            admin.setEmail((String) row[2]);
            return admin;
        }).collect(Collectors.toList());

        System.out.println("Nombre d'administrateurs trouvés : " + admins.size());
        admins.forEach(admin -> 
            System.out.println("Admin : " + admin.getName() + " - " + admin.getEmail())
        );

        return admins;
    }

    

    @PostMapping
    public ResponseEntity<Utilisateur> createAdministrateur(@RequestBody Utilisateur utilisateur) {
        Optional<Role> adminRole = roleRepository.findById(1L);
        if (adminRole.isPresent()) {
            utilisateur.getRole().add(adminRole.get());
            Utilisateur savedUser = utilisateurRepository.save(utilisateur);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Long id) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);

        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();

            // Supprimer toutes les relations avant suppression
            utilisateur.removeRoles(); // Nettoie les rôles et utilisateurRoles
            utilisateurRepository.save(utilisateur); // Sauvegarde les changements
            
            if (utilisateur instanceof Administrateur) {
                Administrateur admin = (Administrateur) utilisateur;
                admin.setCentre(null);
                utilisateurRepository.save(admin);
            }

            // Supprimer l'utilisateur
            utilisateurRepository.delete(utilisateur);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateAdministrateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> existingUser = utilisateurRepository.findById(id);
        
        if (existingUser.isPresent()) {
            Utilisateur admin = existingUser.get();
            admin.setName(utilisateur.getName());
            admin.setEmail(utilisateur.getEmail());

            // Supprimer les anciens rôles pour éviter les doublons
            admin.getRole().clear();

            //Filtrer uniquement les rôles ADMIN_CENTRE
            Set<Role> adminRoles = utilisateur.getRole().stream()
                    .filter(role -> role.getId() == 1) 
                    .collect(Collectors.toSet());  

            admin.setRole(adminRoles);

            return ResponseEntity.ok(utilisateurRepository.save(admin));
        }
        return ResponseEntity.notFound().build();
    }


}
