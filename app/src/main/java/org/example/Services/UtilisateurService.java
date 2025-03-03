package org.example.Services;

import java.util.*;
import java.util.stream.Collectors;

import org.example.Entities.Utilisateur;
import org.example.Repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.Entities.Role;
import org.example.Repositories.RoleRepository;
import org.example.Utils.JwtUtils;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UtilisateurService {
    private static final Logger logger = LoggerFactory.getLogger(UtilisateurService.class);

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository,
                              RoleRepository roleRepository,
                              JwtUtils jwtUtils,
                              BCryptPasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Création d'un utilisateur avec hachage du mot de passe et affectation d'un rôle
     */
    public Utilisateur createUser(String name, String email, String password, List<String> roleNames) {
        logger.info("Création de l'utilisateur : {}", email);
    
        if (utilisateurRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Erreur : L'email est déjà utilisé !");
        }
    
        Set<Role> roles = roleNames.stream()
        .map(roleName -> {
            Optional<Role> roleOpt = roleRepository.findByName(roleName.toUpperCase());
            if (roleOpt.isEmpty()) {
                logger.error(" ERREUR : Le rôle '{}' n'existe pas en base !", roleName);
            }
            return roleOpt.orElseThrow(() -> new RuntimeException("Erreur : Le rôle " + roleName + " n'existe pas."));
        })
        .collect(Collectors.toSet());  
        
        Utilisateur user = new Utilisateur();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encodePasswordIfNecessary(password)); //  Corrigé ici
        user.setRole(roles);
    
        utilisateurRepository.save(user);
        logger.info("Utilisateur {} créé avec succès.", email);
    
        return user;
    }
    /**
     * Authentification de l'utilisateur avec vérification du mot de passe
     */
    public ResponseEntity<?> authenticateUser(String email, String password) {
        logger.info("Tentative de connexion pour : {}", email);
    
        Utilisateur user = utilisateurRepository.findByEmailWithRoles(email);
        if (user == null) {  
            logger.warn("Échec : Utilisateur introuvable !");
            return ResponseEntity.status(401).body(Map.of("error", "Identifiants incorrects"));
        }
    
        if (!passwordEncoder.matches(password, user.getPassword())) {
            logger.warn("Mot de passe incorrect !");
            return ResponseEntity.status(401).body(Map.of("error", "Mot de passe incorrect"));
        }
    
        //  Récupération sécurisée des rôles
        List<String> roles = (user.getUtilisateurRoles() != null) ? 
            user.getUtilisateurRoles().stream()
                .map(ur -> ur.getRole().getName())
                .collect(Collectors.toList())
            : new ArrayList<>();
    
        //  Vérifier que les rôles sont bien récupérés
        logger.info("Utilisateur '{}' a les rôles suivants : {}", email, roles);
    
        String token = jwtUtils.generateToken(user);
    
        return ResponseEntity.ok(Map.of(
            "token", token,
            "roles", roles,
            "user", user
        ));
    }
    
    

    /**
     * Vérifie et rehash les mots de passe si nécessaire
     */
    public void rehashPasswords() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        logger.info("Vérification et rehash des mots de passe...");

        for (Utilisateur user : utilisateurs) {
            if (!user.getPassword().startsWith("$2a$")) {
                logger.info("Rehashing password for: {}", user.getEmail());
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                utilisateurRepository.save(user);
            } else {
                logger.info("Mot de passe déjà encodé pour: {}", user.getEmail());
            }
        }

        logger.info("Tous les mots de passe ont été vérifiés et rehashés si nécessaire.");
    }

    @PostConstruct
    public void init() {
        rehashPasswords();
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID " + id));
    }

    public Utilisateur createOrUpdateUtilisateur(Utilisateur utilisateur) {
        utilisateur.setPassword(encodePasswordIfNecessary(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'email " + email));
    }

    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        utilisateur.setPassword(encodePasswordIfNecessary(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
        logger.info("Utilisateur supprimé avec l'ID : {}", id);
    }

    /**
     * Encode le mot de passe uniquement s'il n'est pas déjà encodé
     */
    private String encodePasswordIfNecessary(String password) {
        if (password == null || password.startsWith("$2a$")) {
            return password; // Déjà haché
        }
        return passwordEncoder.encode(password); // Hachage si nécessaire
    }    
}