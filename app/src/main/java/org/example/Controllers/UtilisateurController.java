package org.example.Controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Map;

import org.example.Entities.Role;
import org.example.Entities.Utilisateur;
import org.example.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getUtilisateurById(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        if (utilisateur == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Utilisateur non trouvé"));
        }
        return ResponseEntity.ok(utilisateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        utilisateur.setId(id);
        return ResponseEntity.ok(utilisateurService.createOrUpdateUtilisateur(utilisateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok(Map.of("message", "Utilisateur supprimé avec succès"));
    }

    @PostMapping("/creer")
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Map<String, Object> request) {
        String name = (String) request.get("name");
        String email = (String) request.get("email");
        String password = (String) request.get("password");
        List<String> roles = (List<String>) request.get("roles");

        if (name == null || email == null || password == null || roles == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Utilisateur utilisateur = utilisateurService.createUser(name, email, password, roles);
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateur);
    }

    @GetMapping("/by-email")
    public ResponseEntity<?> getUtilisateur(@RequestParam String email) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);
        if (utilisateur == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Utilisateur non trouvé"));
        }
        return ResponseEntity.ok(utilisateur);
    }

    @GetMapping("/by-email/roles")
    public ResponseEntity<?> getRolesByEmail(@RequestParam String email) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);
        if (utilisateur == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Utilisateur non trouvé"));
        }

        Set<String> roles = utilisateur.getRole().stream().map(Role::getName).collect(Collectors.toSet());
        return ResponseEntity.ok(Map.of("email", utilisateur.getEmail(), "roles", roles));
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<?> getUserRoles(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        if (utilisateur == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Utilisateur non trouvé"));
        }

        Set<String> roles = utilisateur.getRole().stream().map(Role::getName).collect(Collectors.toSet());
        return ResponseEntity.ok(Map.of("email", utilisateur.getEmail(), "roles", roles));
    }

    @GetMapping("/administrateurs")
    public List<Utilisateur> getAllAdministrateurs() {
        return utilisateurService.getAllUtilisateurs()
                .stream()
                .filter(user -> user.getRole().stream().anyMatch(role -> role.getId() == 1)) // Filtrer les admins
                .collect(Collectors.toList());
    }

}
