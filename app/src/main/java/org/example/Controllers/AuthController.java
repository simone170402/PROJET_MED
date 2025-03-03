package org.example.Controllers;

import org.example.Entities.Role;
import org.example.Entities.Utilisateur;
import org.example.Repositories.UtilisateurRepository;
import org.example.Services.UtilisateurService;
import org.example.Utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;
    private final JwtUtils jwtUtils;
    private final UtilisateurService utilisateurService;

    public AuthController(AuthenticationManager authenticationManager,
                          UtilisateurRepository utilisateurRepository,
                          JwtUtils jwtUtils,
                          UtilisateurService utilisateurService) {
        this.authenticationManager = authenticationManager;
        this.utilisateurRepository = utilisateurRepository;
        this.jwtUtils = jwtUtils;
        this.utilisateurService = utilisateurService;
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header reçu : " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Authorization header manquant ou invalide"));
        }

        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        System.out.println("Base64 Credentials : " + base64Credentials);

        try {
            // Utilisez le décodeur standard
            String fixedBase64Credentials = base64Credentials.replace("-", "+").replace("_", "/");
            String credentials = new String(Base64.getDecoder().decode(fixedBase64Credentials));

            // Vérifiez les credentials pour les caractères interdits
            if (!Pattern.matches("^[A-Za-z0-9:@.]+$", credentials)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Credentials contiennent des caractères interdits"));
            }

            String[] values = credentials.split(":", 2);
            String email = values[0];
            String password = values[1];

            System.out.println("Tentative de connexion pour : " + email);

            Utilisateur user = utilisateurRepository.findByEmailWithRoles(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Identifiants incorrects"));
            }

            // Comparaison correcte avec BCrypt
            if (!BCrypt.checkpw(password, user.getPassword())) {
                System.out.println("Mot de passe incorrect !");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Identifiants incorrects"));
            }

            // Retrieve roles properly
            List<String> roles = user.getUtilisateurRoles().stream()
                .map(ur -> ur.getRole().getName())
                .collect(Collectors.toList());

            String token = jwtUtils.generateToken(user);

            return ResponseEntity.ok(Map.of(
                "token", token,
                "roles", roles,
                "user", user
            ));
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur de décodage Base64 : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Invalid Base64 encoding"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Erreur interne du serveur"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        String name = (String) userMap.get("name");
        List<String> roles = (List<String>) userMap.get("roles");

        if (email == null || password == null || name == null || roles == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Tous les champs sont obligatoires"));
        }

        if (utilisateurRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email déjà utilisé"));
        }

        // Hash le mot de passe avant de le sauvegarder
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("Mot de passe haché lors de l'inscription : " + hashedPassword);

        Utilisateur newUser = utilisateurService.createUser(name, email, hashedPassword, roles);

        return ResponseEntity.ok(Map.of(
            "message", "Inscription réussie",
            "user", newUser
        ));
    }
}
