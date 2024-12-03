package org.example.Services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.example.Entities.Utilisateur;
import org.example.Repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.Entities.Role;
import org.example.Entities.UtilisateurRole;
import org.example.Repositories.RoleRepository;
import org.example.Repositories.UtilisateurRoleRepository;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final UtilisateurRoleRepository utilisateurRoleRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository,
                              RoleRepository roleRepository,
                              UtilisateurRoleRepository utilisateurRoleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.utilisateurRoleRepository = utilisateurRoleRepository;
    }

    public Utilisateur createUtilisateur(String nom, String prenom, String email, String motDePasse, String roleName) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setName(nom);
        utilisateur.setSurname(prenom);
        utilisateur.setEmail(email);
        utilisateur.setPassword(motDePasse);

        Role role = roleRepository.findByName(roleName)
                                  .orElseThrow(() -> new RuntimeException("Role non trouvé"));

        UtilisateurRole utilisateurRole = new UtilisateurRole();
        utilisateurRole.setUtilisateur(utilisateur);
        utilisateurRole.setRole(role);

        utilisateur.getUtilisateurRoles().add(utilisateurRole);
        utilisateurRepository.save(utilisateur);

        return utilisateur;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur createOrUpdateUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }
    
    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                                     .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    public Utilisateur UpdateUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
    
    public Set<Role> getRoles(Utilisateur utilisateur) {
        return utilisateur.getUtilisateurRoles().stream()
                          .map(UtilisateurRole::getRole)
                          .collect(Collectors.toSet());
    }
}


