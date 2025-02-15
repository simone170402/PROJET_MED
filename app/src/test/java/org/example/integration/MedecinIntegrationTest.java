package org.example.integration;

import org.example.Entities.Medecin;
import org.example.Exceptions.MedecinNotFoundException;
import org.example.Entities.Centre;
import org.example.Services.MedecinService;
import org.example.Services.CentreService;
import org.example.Repositories.MedecinRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:applicationTest.yaml")
class MedecinIntegrationTest {
    
    @Autowired
    private MedecinService medecinService;
    
    @Autowired
    private CentreService centreService;
    
    @Autowired
    private MedecinRepository medecinRepository;

    /**
     * Teste l'intégration d'un médecin avec un centre.
     * Vérifie qu'un médecin peut être sauvegardé et récupéré par son centre.
     */
    @Test
    public void testMedecinIntegrationWithCentre() {
        // given
        Centre centre = new Centre();
        centre.setName("Hôpital Central");
        Centre savedCentre = centreService.createOrUpdateCentre(centre);
    
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        medecin.setSurname("Gregory");
        medecin.setCentre(savedCentre);
        
        // when
        Medecin savedMedecin = medecinService.save(medecin);
        
        // then
        assertThat(savedMedecin.getCentre().getName()).isEqualTo("Hôpital Central");
        List<Medecin> medecinsByCentre = medecinService.getMedecinsByCentre(savedCentre.getId());
        assertThat(medecinsByCentre)
            .hasSize(1)
            .extracting("name")
            .contains("Dr. House");
    
        // Utiliser medecinRepository pour vérifier les données
        Medecin foundMedecin = medecinRepository.findById(savedMedecin.getId()).orElse(null);
        assertThat(foundMedecin).isNotNull();
        assertThat(foundMedecin.getName()).isEqualTo("Dr. House");
    }

    /**
     * Teste la récupération de tous les médecins.
     * Vérifie que tous les médecins peuvent être trouvés.
     */
    @Test
    public void testFindAllMedecins() {
        // given
        Medecin medecin1 = new Medecin();
        medecin1.setName("Dr. House");
        medecin1.setSurname("Gregory");
        medecinService.save(medecin1);

        Medecin medecin2 = new Medecin();
        medecin2.setName("Dr. Wilson");
        medecin2.setSurname("James");
        medecinService.save(medecin2);

        // when
        List<Medecin> medecins = medecinService.findAll();

        // then
        assertThat(medecins).hasSize(2);
        assertThat(medecins).extracting(Medecin::getName).contains("Dr. House", "Dr. Wilson");
    }

    /**
     * Teste la mise à jour d'un médecin.
     * Vérifie qu'un médecin peut être mis à jour.
     */
    @Test
    public void testUpdateMedecin() {
        // given
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        medecin.setSurname("Gregory");
        Medecin savedMedecin = medecinService.save(medecin);

        // when
        savedMedecin.setName("Dr. House Updated");
        Medecin updatedMedecin = medecinService.update(savedMedecin.getId(), savedMedecin);

        // then
        Medecin retrievedMedecin = medecinService.findOneById(updatedMedecin.getId());
        assertThat(retrievedMedecin.getName()).isEqualTo("Dr. House Updated");
    }

    /**
     * Teste la suppression d'un médecin.
     * Vérifie qu'un médecin peut être supprimé.
     */
    @Test
    public void testDeleteMedecin() {
        // given
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        medecin.setSurname("Gregory");
        Medecin savedMedecin = medecinService.save(medecin);

        // when
        medecinService.delete(savedMedecin.getId());

        // then
        assertThatThrownBy(() -> medecinService.findOneById(savedMedecin.getId()))
                .isInstanceOf(MedecinNotFoundException.class);
    }
}