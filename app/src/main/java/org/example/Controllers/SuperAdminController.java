package org.example.Controllers;

import org.example.Entities.Utilisateur;
import org.example.Services.UtilisateurService;
import org.example.Repositories.UtilisateurRepository;
import org.example.Entities.Role;
import org.example.Repositories.RoleRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/superadmin")
public class SuperAdminController {
    private final UtilisateurService utilisateurService;
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;

    public SuperAdminController(UtilisateurService utilisateurService, UtilisateurRepository utilisateurRepository, RoleRepository roleRepository) {
        this.utilisateurService = utilisateurService;
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public List<Utilisateur> getAllSuperAdmins() {
        return utilisateurRepository.findAll().stream()
                .filter(user -> user.getRole().stream().anyMatch(role -> role.getName().equals("SUPER_ADMIN")))
                .toList();
    }

    @PostMapping
    public ResponseEntity<Utilisateur> createSuperAdmin(@RequestBody Utilisateur utilisateur) {
        Optional<Role> superAdminRole = roleRepository.findById(1L);
        if (superAdminRole.isPresent()) {
            utilisateur.getRole().add(superAdminRole.get());
            Utilisateur savedUser = utilisateurRepository.save(utilisateur);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateSuperAdmin(@PathVariable Long id, @RequestBody Utilisateur updatedUser) {
        return utilisateurRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            utilisateurRepository.save(user);
            return ResponseEntity.ok(user);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuperAdmin(@PathVariable Long id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
