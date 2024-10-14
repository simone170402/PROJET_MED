package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.Entities.Medecin;
import org.example.Exceptions.MedecinNotFoundException;
import org.example.Services.MedecinService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedecinServiceTest {
    @Autowired
    private MedecinService medecinService;

    @Test
    void testFindOneById() {
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        medecinService.save(medecin);

        Medecin found = medecinService.findOneById(medecin.getId());
        assertNotNull(found, "Medecin should be found");
    }

    @Test
    void testFindAll() {
        Medecin medecin1 = new Medecin();
        medecin1.setName("Dr. House");
        medecinService.save(medecin1);

        Medecin medecin2 = new Medecin();
        medecin2.setName("Dr. Grey");
        medecinService.save(medecin2);

        assertEquals(2, medecinService.findAll().size(), "There should be two medecins");
    }

    @Test
    void testSave() {
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        Medecin saved = medecinService.save(medecin);

        assertNotNull(saved.getId(), "Saved medecin should have an ID");
    }

    @Test
    void testUpdate() {
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        medecinService.save(medecin);

        medecin.setName("Dr. House Updated");
        Medecin updated = medecinService.update(medecin.getId(), medecin);

        assertEquals("Dr. House Updated", updated.getName(), "Medecin name should be updated");
    }

    @Test
    void testDelete() {
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        medecinService.save(medecin);

        medecinService.delete(medecin.getId());
        assertThrows(MedecinNotFoundException.class, () -> {
            medecinService.findOneById(medecin.getId());
        }, "Medecin should be deleted");
    }
}

