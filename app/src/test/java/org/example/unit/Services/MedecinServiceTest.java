package org.example.unit.Services;

import static org.junit.jupiter.api.Assertions.*;

import org.example.Entities.Medecin;
import org.example.Exceptions.MedecinNotFoundException;
import org.example.Services.MedecinService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MedecinServiceTest {
    @Autowired
    private MedecinService medecinService;

    /**
     * Teste la méthode findOneById de MedecinService.
     * Vérifie qu'un médecin peut être trouvé par son ID.
     */
    @Test
    void testFindOneById() {
        // given
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        medecinService.save(medecin);

        // when
        Medecin found = medecinService.findOneById(medecin.getId());

        // then
        assertNotNull(found, "Medecin should be found");
    }

    /**
     * Teste la méthode findAll de MedecinService.
     * Vérifie que tous les médecins peuvent être trouvés.
     */
    @Test
    void testFindAll() {
        // given
        Medecin medecin1 = new Medecin();
        medecin1.setName("Dr. House");
        medecinService.save(medecin1);

        Medecin medecin2 = new Medecin();
        medecin2.setName("Dr. Grey");
        medecinService.save(medecin2);

        // when
        int medecinCount = medecinService.findAll().size();

        // then
        assertEquals(2, medecinCount, "There should be two medecins");
    }

    /**
     * Teste la méthode save de MedecinService.
     * Vérifie qu'un médecin peut être sauvegardé.
     */
    @Test
    void testSave() {
        // given
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");

        // when
        Medecin saved = medecinService.save(medecin);

        // then
        assertNotNull(saved.getId(), "Saved medecin should have an ID");
    }

    /**
     * Teste la méthode update de MedecinService.
     * Vérifie qu'un médecin peut être mis à jour.
     */
    @Test
    void testUpdate() {
        // given
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        medecinService.save(medecin);

        // when
        medecin.setName("Dr. House Updated");
        Medecin updated = medecinService.update(medecin.getId(), medecin);

        // then
        assertEquals("Dr. House Updated", updated.getName(), "Medecin name should be updated");
    }

    /**
     * Teste la méthode delete de MedecinService.
     * Vérifie qu'un médecin peut être supprimé.
     */
    @Test
    void testDelete() {
        // given
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        medecinService.save(medecin);

        // when
        medecinService.delete(medecin.getId());

        // then
        assertThrows(MedecinNotFoundException.class, () -> {
            medecinService.findOneById(medecin.getId());
        }, "Medecin should be deleted");
    }
}