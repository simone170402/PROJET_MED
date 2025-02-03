
package org.example.e2e;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:applicationTest.yaml")
public class PatientE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreatePatient() throws Exception {
        String patientJson = """
            {
                "name": "John",
                "surname": "Doe",
                "phoneNumber": "0123456789",
                "email": "john.doe@email.com",
                "dateOfBirth": "1990-01-01",
                "vaccinationStatus": "Completed",
                "adresse": "456 Rue du Patient"
            }
            """;

        mockMvc.perform(post("/api/patients")
                .content(patientJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testGetPatientById() throws Exception {
        // First create a patient
        String patientJson = """
            {
                "name": "John",
                "surname": "Doe",
                "phoneNumber": "0123456789",
                "email": "john.doe@email.com",
                "dateOfBirth": "1990-01-01",
                "vaccinationStatus": "Completed",
                "adresse": "456 Rue du Patient"
            }
            """;

        String response = mockMvc.perform(post("/api/patients")
                .content(patientJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        
        String id = response.split("\"id\":")[1].split(",")[0];

        // Get the patient by ID
        mockMvc.perform(get("/api/patients/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"))
                .andExpect(jsonPath("$.phoneNumber").value("0123456789"))
                .andExpect(jsonPath("$.email").value("john.doe@email.com"))
                .andExpect(jsonPath("$.dateOfBirth").value("1990-01-01"))
                .andExpect(jsonPath("$.vaccinationStatus").value("Completed"))
                .andExpect(jsonPath("$.adresse").value("456 Rue du Patient"));
    }
    @Test
    void testUpdatePatient() throws Exception {
        // First create a patient
        String patientJson = """
            {
                "name": "John",
                "surname": "Doe",
                "phoneNumber": "0123456789",
                "email": "john.doe@email.com",
                "dateOfBirth": "1990-01-01",
                "vaccinationStatus": "Pending"
            }
            """;

        String response = mockMvc.perform(post("/api/patients")
                .content(patientJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        
        String id = response.split("\"id\":")[1].split(",")[0];

        // Update the patient
        String updatedPatientJson = """
            {
                "name": "John",
                "surname": "Doe",
                "phoneNumber": "0123456789",
                "email": "john.doe@email.com",
                "dateOfBirth": "1990-01-01",
                "vaccinationStatus": "Completed"
            }
            """;

        mockMvc.perform(put("/api/patients/" + id)
                .content(updatedPatientJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vaccinationStatus").value("Completed"));
    }

    @Test
    void testDeletePatient() throws Exception {
        // given
        String patientJson = """
            {
                "name": "John",
                "surname": "Doe",
                "email": "john.doe@example.com"
            }
            """;

        // Créer d'abord un patient
        String responseJson = mockMvc.perform(post("/api/patients")
                .content(patientJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        
        // Utiliser Jackson pour extraire l'ID du patient créé
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseJson);
        String id = jsonNode.get("id").asText();
        
        // Supprimer le patient
        mockMvc.perform(delete("/api/patients/" + id))
                .andExpect(status().isNoContent());
        
        // Vérifier que le patient a été supprimé
        mockMvc.perform(get("/api/patients/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllPatients() throws Exception {
        // given
        String patientJson1 = """
            {
                "name": "John",
                "surname": "Doe",
                "email": "john.doe@example.com"
            }
            """;

        String patientJson2 = """
            {
                "name": "Jane",
                "surname": "Doe",
                "email": "jane.doe@example.com"
            }
            """;

        // Créer les patients
        mockMvc.perform(post("/api/patients")
                .content(patientJson1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/patients")
                .content(patientJson2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        // when
        mockMvc.perform(get("/api/patients"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].surname").value("Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[1].name").value("Jane"))
                .andExpect(jsonPath("$[1].surname").value("Doe"))
                .andExpect(jsonPath("$[1].email").value("jane.doe@example.com"));
    }
}

