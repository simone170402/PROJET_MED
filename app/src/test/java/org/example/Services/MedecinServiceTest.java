package org.example.Services;

import org.example.Entities.Centre;
import org.example.Entities.Medecin;
import org.example.Entities.Utilisateur;
import org.example.Entities.UtilisateurRole;
import org.example.Exceptions.MedecinNotFoundException;
import org.example.Repositories.MedecinRepository;
import org.example.Repositories.UtilisateurRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedecinServiceTest {

    @Mock
    private MedecinRepository medecinRepository;

    @Mock
    private UtilisateurRoleRepository utilisateurRoleRepository;

    @InjectMocks
    private MedecinService medecinService;

    private Medecin medecin;
    private Centre centre;
    private UtilisateurRole utilisateurRole;

    @BeforeEach
    void setUp() {
        centre = new Centre();
        centre.setId(1L);
        centre.setName("Centre Test");

        medecin = new Medecin();
        medecin.setId(1L);
        medecin.setName("Dr. Test");
        medecin.setSurname("Jean");
        medecin.setCentre(centre);

        utilisateurRole = new UtilisateurRole();
        utilisateurRole.setUtilisateur(medecin);
    }

    @Test
    void findOneById_ShouldReturnMedecin_WhenMedecinExists() {
        when(medecinRepository.findById(1L)).thenReturn(Optional.of(medecin));

        Medecin found = medecinService.findOneById(1L);

        assertNotNull(found);
        assertEquals(medecin.getId(), found.getId());
        assertEquals(medecin.getName(), found.getName());
        assertEquals(medecin.getSurname(), found.getSurname());
        verify(medecinRepository).findById(1L);
    }

    @Test
    void findOneById_ShouldThrowException_WhenMedecinNotFound() {
        when(medecinRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(MedecinNotFoundException.class, () -> {
            medecinService.findOneById(99L);
        });
        verify(medecinRepository).findById(99L);
    }

    @Test
    void getMedecinsByCentre_ShouldReturnMedecins_WhenCentreExists() {
        List<UtilisateurRole> utilisateurRoles = Arrays.asList(utilisateurRole);
        when(utilisateurRoleRepository.findByRoleId(2L)).thenReturn(utilisateurRoles);

        List<Medecin> medecins = medecinService.getMedecinsByCentre(1L);

        assertNotNull(medecins);
        assertFalse(medecins.isEmpty());
        assertEquals(1, medecins.size());
        assertEquals(medecin.getId(), medecins.get(0).getId());
        verify(utilisateurRoleRepository).findByRoleId(2L);
    }

    @Test
    void getMedecinsByCentre_ShouldReturnEmptyList_WhenNoDoctorsInCentre() {
        Centre autreCentre = new Centre();
        autreCentre.setId(2L);
        medecin.setCentre(autreCentre);

        List<UtilisateurRole> utilisateurRoles = Arrays.asList(utilisateurRole);
        when(utilisateurRoleRepository.findByRoleId(2L)).thenReturn(utilisateurRoles);

        List<Medecin> medecins = medecinService.getMedecinsByCentre(1L);

        assertTrue(medecins.isEmpty());
        verify(utilisateurRoleRepository).findByRoleId(2L);
    }
}
