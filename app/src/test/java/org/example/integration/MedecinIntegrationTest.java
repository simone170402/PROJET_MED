package org.example.integration;

import org.example.Entities.Medecin;
import org.example.Services.MedecinService;
import org.example.Repositories.MedecinRepository;
import org.example.Exceptions.MedecinNotFoundException;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedecinIntegrationTest {
    
    @Autowired
    MedecinService medecinService;
    
    @Autowired
    MedecinRepository medecinRepository;
    
    /**
     * Teste la méthode save et findOneById de MedecinService.
     * Vérifie qu'un médecin peut être sauvegardé et récupéré par son ID.
     */
    @Test
    public void testSaveAndRetrieveMedecin() {
        // given
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        medecin.setSurname("Gregory");
        
        // when
        Medecin savedMedecin = medecinService.save(medecin);
        
        // then
        Medecin retrievedMedecin = medecinService.findOneById(savedMedecin.getId());
        Assertions.assertThat(retrievedMedecin.getName()).isEqualTo("Dr. House");
        Assertions.assertThat(retrievedMedecin.getSurname()).isEqualTo("Gregory");
    }

    /**
     * Teste la méthode findAll de MedecinService.
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
        Assertions.assertThat(medecins).hasSize(2);
        Assertions.assertThat(medecins).extracting(Medecin::getName).contains("Dr. House", "Dr. Wilson");
    }

    /**
     * Teste la méthode update de MedecinService.
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
        Assertions.assertThat(retrievedMedecin.getName()).isEqualTo("Dr. House Updated");
    }

    /**
     * Teste la méthode delete de MedecinService.
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
        Assertions.assertThatThrownBy(() -> medecinService.findOneById(savedMedecin.getId()))
                .isInstanceOf(MedecinNotFoundException.class);
    }
}