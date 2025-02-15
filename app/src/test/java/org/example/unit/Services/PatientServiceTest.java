package org.example.unit.Services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.example.Entities.Patient;
import org.example.Exceptions.PatientNotFoundException;
import org.example.Services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    /**
     * Teste la méthode findOneById de PatientService.
     * Vérifie qu'un patient peut être trouvé par son ID.
     */
    @Test
    void testFindOneById() {
        // given
        Patient patient = new Patient();
        patient.setName("John");
        patientService.save(patient);

        // when
        Patient found = patientService.findOneById(patient.getId());

        // then
        assertNotNull(found, "Patient should be found");
    }

    /**
     * Teste la méthode findAll de PatientService.
     * Vérifie que tous les patients peuvent être trouvés.
     */
    @Test
    void testFindAll() {
        // given
        Patient patient1 = new Patient();
        patient1.setName("John");
        patientService.save(patient1);

        Patient patient2 = new Patient();
        patient2.setName("Jane");
        patientService.save(patient2);

        // when
        int patientCount = patientService.getAllPatients().size();

        // then
        assertEquals(2, patientCount, "There should be two patients");
    }

    /**
     * Teste la méthode save de PatientService.
     * Vérifie qu'un patient peut être sauvegardé.
     */
    @Test
    void testSave() {
        // given
        Patient patient = new Patient();
        patient.setName("John");

        // when
        Patient saved = patientService.save(patient);

        // then
        assertNotNull(saved.getId(), "Saved patient should have an ID");
    }

    /**
     * Teste la méthode update de PatientService.
     * Vérifie qu'un patient peut être mis à jour.
     */
    @Test
    void testUpdate() {
        // given
        Patient patient = new Patient();
        patient.setName("John");
        patientService.save(patient);

        // when
        patient.setName("John Updated");
        Patient updated = patientService.update(patient.getId(), patient);

        // then
        assertEquals("John Updated", updated.getName(), "Patient name should be updated");
    }

    /**
     * Teste la méthode delete de PatientService.
     * Vérifie qu'un patient peut être supprimé.
     */
    @Test
    void testDelete() {
        // given
        Patient patient = new Patient();
        patient.setName("John");
        patientService.save(patient);

        // when
        patientService.delete(patient.getId());

        // then
        assertThrows(PatientNotFoundException.class, () -> {
            patientService.findOneById(patient.getId());
        }, "Patient should be deleted");
    }

    /**
     * Teste la méthode findByNameStartsWith de PatientService.
     * Vérifie qu'un patient peut être trouvé par le début de son nom.
     */
    @Test
    void testFindByNameStartsWith() {
        // given
        Patient patient = new Patient();
        patient.setName("John");
        patientService.save(patient);

        // when
        List<Patient> patients = patientService.findByNameStartsWith("Jo");

        // then
        assertEquals(1, patients.size(), "There should be one patient with name starting with 'Jo'");
    }
}