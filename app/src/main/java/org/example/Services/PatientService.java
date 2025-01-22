package org.example.Services;


import java.util.List;
import java.util.Optional;

import org.example.Entities.Patient;
import org.example.Exceptions.PatientNotFoundException;
import org.springframework.stereotype.Service;
import org.example.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository; // Utilisation de JPA pour la gestion des patients

    public Patient findOneById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with id " + id + " not found."));
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient savePatient(Patient patient) {
        // Vérifier si un patient avec le même email existe
        Optional<Patient> existingPatientOpt = patientRepository.findByEmail(patient.getEmail());
    
        if (existingPatientOpt.isPresent()) {
            // Si le patient existe déjà, retourner l'existant
            return existingPatientOpt.get();
            
            // Optionnel : Si vous souhaitez lever une exception à la place :
            // throw new IllegalArgumentException("Patient already exists.");
        }
    
        // Si le patient n'existe pas, sauvegarder le nouveau
        return patientRepository.save(patient);
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient update(Long id, Patient patient) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient with id " + id + " not found.");
        }
        patient.setId(id);
        return patientRepository.save(patient);
    }

    public void delete(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient with id " + id + " not found.");
        }
        patientRepository.deleteById(id);
    }

    public List<Patient> findByNameStartsWith(String name) {
        return patientRepository.findByNameStartingWith(name); // Utilisation de la méthode JPA
    }
}

