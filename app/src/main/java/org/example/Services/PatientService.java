package org.example.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.Entities.Patient;
import org.example.Exceptions.PatientNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private Map<Long, Patient> patientMap = new HashMap<>();
    private Long currentId = 1L;

    public Patient findOneById(Long id) {
        if (!patientMap.containsKey(id)) {
            throw new PatientNotFoundException("Patient with id " + id + " not found.");
        }
        return patientMap.get(id);
    }

    public List<Patient> findAll() {
        return new ArrayList<>(patientMap.values());
    }

    public Patient save(Patient patient) {
        patient.setId(currentId);
        patientMap.put(currentId, patient);
        currentId++;
        return patient;
    }

    public Patient update(Long id, Patient patient) {
        if (!patientMap.containsKey(id)) {
            throw new PatientNotFoundException("Patient with id " + id + " not found.");
        }
        patient.setId(id);
        patientMap.put(id, patient);
        return patient;
    }

    public void delete(Long id) {
        if (!patientMap.containsKey(id)) {
            throw new PatientNotFoundException("Patient with id " + id + " not found.");
        }
        patientMap.remove(id);
    }

    public List<Patient> findByNameStartsWith(String name) {
        List<Patient> patients = new ArrayList<>();
        for (Patient patient : patientMap.values()) {
            if (patient.getName().startsWith(name)) {
                patients.add(patient);
            }
        }
        return patients;
    }
}
