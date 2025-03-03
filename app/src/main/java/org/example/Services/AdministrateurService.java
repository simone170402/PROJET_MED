package org.example.Services;

import java.util.List;
import java.util.Optional;
import org.example.Entities.Administrateur;
import org.example.Entities.Centre;
import org.example.Entities.Utilisateur;
import org.example.Repositories.AdministrateurRepository;
import org.example.Repositories.CentreRepository;
import org.example.Repositories.RoleRepository;
import org.example.Repositories.UtilisateurRepository;
import org.example.Entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrateurService {
    private final AdministrateurRepository administrateurRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final CentreRepository centreRepository;

    @Autowired
    public AdministrateurService(AdministrateurRepository administrateurRepository, 
                                 UtilisateurRepository utilisateurRepository, 
                                 RoleRepository roleRepository,
                                 CentreRepository centreRepository) {
        this.administrateurRepository = administrateurRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.centreRepository = centreRepository;
    }

    public List<Administrateur> getAllAdmins() {
        return administrateurRepository.findAll();
    }

    public Optional<Administrateur> getAdminById(Long id) {
        return administrateurRepository.findById(id);
    }

    public Administrateur createAdministrateur(String name, String email, String password, Long centreId) {
        Administrateur admin = new Administrateur();
        admin.setName(name);
        admin.setEmail(email);
        admin.setPassword(password); // Ajouter un encodage BCrypt dans une prochaine version

        // Attribution du rôle ADMIN_CENTRE
        Optional<Role> adminRole = roleRepository.findById(1L); // ID correct pour ADMIN_CENTRE
        adminRole.ifPresent(role -> admin.getRole().add(role));

        // Attribution du centre
        if (centreId != null) {
            Centre centre = centreRepository.findById(centreId)
                    .orElseThrow(() -> new RuntimeException("Centre introuvable"));
            admin.setCentre(centre);
        }

        return administrateurRepository.save(admin);
    }

    public void deleteAdministrateur(Long id) {
        administrateurRepository.deleteById(id);
    }

    public Administrateur assignAdminToCentre(Long adminId, Long centreId) {
        Administrateur admin = administrateurRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrateur introuvable"));

        Centre centre = centreRepository.findById(centreId)
                .orElseThrow(() -> new RuntimeException("Centre introuvable"));

        admin.setCentre(centre);
        return administrateurRepository.save(admin);
    }
}
