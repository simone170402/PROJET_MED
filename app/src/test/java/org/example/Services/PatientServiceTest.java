package org.example.Services;

import org.example.Entities.Patient;
import org.example.Exceptions.PatientNotFoundException;
import org.example.Repositories.PatientRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setName("Doe");
        patient.setSurname("John");
        patient.setEmail("john.doe@example.com");
    }

    @Test
    void findOneById_ShouldReturnPatient_WhenPatientExists() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Patient found = patientService.findOneById(1L);

        assertNotNull(found);
        assertEquals(patient.getId(), found.getId());
        assertEquals(patient.getName(), found.getName());
        verify(patientRepository).findById(1L);
    }

    @Test
    void findOneById_ShouldThrowException_WhenPatientNotFound() {
        when(patientRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> {
            patientService.findOneById(99L);
        });
        verify(patientRepository).findById(99L);
    }

    @Test
    void getAllPatients_ShouldReturnListOfPatients() {
        List<Patient> patients = Arrays.asList(patient, new Patient());
        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> found = patientService.getAllPatients();

        assertNotNull(found);
        assertEquals(2, found.size());
        verify(patientRepository).findAll();
    }

    @Test
    void savePatient_ShouldReturnExistingPatient_WhenEmailExists() {
        when(patientRepository.findByEmail(patient.getEmail())).thenReturn(Optional.of(patient));

        Patient saved = patientService.savePatient(patient);

        assertNotNull(saved);
        assertEquals(patient.getEmail(), saved.getEmail());
        verify(patientRepository).findByEmail(patient.getEmail());
        verify(patientRepository, never()).save(any(Patient.class));
    }

    @Test
    void savePatient_ShouldSaveNewPatient_WhenEmailNotExists() {
        when(patientRepository.findByEmail(patient.getEmail())).thenReturn(Optional.empty());
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient saved = patientService.savePatient(patient);

        assertNotNull(saved);
        assertEquals(patient.getEmail(), saved.getEmail());
        verify(patientRepository).findByEmail(patient.getEmail());
        verify(patientRepository).save(patient);
    }

    @Test
    void save_ShouldSavePatient() {
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient saved = patientService.save(patient);

        assertNotNull(saved);
        assertEquals(patient.getId(), saved.getId());
        verify(patientRepository).save(patient);
    }

    @Test
    void update_ShouldUpdatePatient_WhenPatientExists() {
        when(patientRepository.existsById(1L)).thenReturn(true);
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient updated = patientService.update(1L, patient);

        assertNotNull(updated);
        assertEquals(patient.getId(), updated.getId());
        verify(patientRepository).existsById(1L);
        verify(patientRepository).save(patient);
    }

    @Test
    void update_ShouldThrowException_WhenPatientNotFound() {
        when(patientRepository.existsById(99L)).thenReturn(false);

        assertThrows(PatientNotFoundException.class, () -> {
            patientService.update(99L, patient);
        });
        verify(patientRepository).existsById(99L);
        verify(patientRepository, never()).save(any(Patient.class));
    }
}
