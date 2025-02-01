package org.example.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Entities.Patient;
import org.example.Exceptions.PatientNotFoundException;
import org.example.Services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PatientRestControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientRestController patientRestController;

    private Patient patient;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(patientRestController)
                .build();
        objectMapper = new ObjectMapper();

        patient = new Patient();
        patient.setId(1L);
        patient.setName("Doe");
        patient.setSurname("John");
        patient.setEmail("john.doe@example.com");
    }

    @Test
    void getAllPatients_ShouldReturnListOfPatients() throws Exception {
        List<Patient> patients = Arrays.asList(patient, new Patient());
        when(patientService.getAllPatients()).thenReturn(patients);

        mockMvc.perform(get("/api/patients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Doe")))
                .andExpect(jsonPath("$[0].surname", is("John")))
                .andExpect(jsonPath("$[0].email", is("john.doe@example.com")));
    }

    @Test
    void getPatientById_ShouldReturnPatient() throws Exception {
        when(patientService.findOneById(1L)).thenReturn(patient);

        mockMvc.perform(get("/api/patients/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Doe")))
                .andExpect(jsonPath("$.surname", is("John")))
                .andExpect(jsonPath("$.email", is("john.doe@example.com")));
    }

    @Test
    void getPatientById_ShouldReturn404_WhenPatientNotFound() throws Exception {
        when(patientService.findOneById(99L)).thenThrow(new PatientNotFoundException("Patient not found"));

        mockMvc.perform(get("/api/patients/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createPatient_ShouldReturnCreatedPatient() throws Exception {
        when(patientService.savePatient(any(Patient.class))).thenReturn(patient);

        mockMvc.perform(post("/api/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Doe")))
                .andExpect(jsonPath("$.surname", is("John")))
                .andExpect(jsonPath("$.email", is("john.doe@example.com")));
    }

    @Test
    void updatePatient_ShouldReturnUpdatedPatient() throws Exception {
        when(patientService.update(anyLong(), any(Patient.class))).thenReturn(patient);

        mockMvc.perform(put("/api/patients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Doe")))
                .andExpect(jsonPath("$.surname", is("John")))
                .andExpect(jsonPath("$.email", is("john.doe@example.com")));
    }

    @Test
    void updatePatient_ShouldReturn404_WhenPatientNotFound() throws Exception {
        when(patientService.update(anyLong(), any(Patient.class)))
                .thenThrow(new PatientNotFoundException("Patient not found"));

        mockMvc.perform(put("/api/patients/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isNotFound());
    }
}
