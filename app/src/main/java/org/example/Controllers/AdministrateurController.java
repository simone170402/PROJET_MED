package org.example.Controllers;

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
        return utilisateurRepository.findAll().stream()
                .filter(user -> user.getRole().stream()
                        .anyMatch(role -> role.getId() == 1)) //  Récupère les admins de centre uniquement
                .toList();
    }


    @PostMapping
    public ResponseEntity<Utilisateur> createAdministrateur(@RequestBody Utilisateur utilisateur) {
        Optional<Role> adminRole = roleRepository.findById(2L);
        if (adminRole.isPresent()) {
            utilisateur.getRole().add(adminRole.get());
            Utilisateur savedUser = utilisateurRepository.save(utilisateur);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Long id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
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

            //  Convertir la liste en Set pour correspondre à setRole(Set<Role>)
            Set<Role> adminRoles = utilisateur.getRole().stream()
                    .filter(role -> role.getId() == 1) // On ne garde que ADMIN_CENTRE
                    .collect(Collectors.toSet());  // 💡 Convertir en Set<Role>

            admin.setRole(adminRoles);

            return ResponseEntity.ok(utilisateurRepository.save(admin));
        }
        return ResponseEntity.notFound().build();
    }

}
