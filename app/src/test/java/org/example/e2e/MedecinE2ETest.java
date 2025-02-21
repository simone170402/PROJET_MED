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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:applicationTest.yaml")
public class MedecinE2ETest {
    
    @Autowired
    private MockMvc mockMvc;
    
    /**
     * Teste la création d'un médecin via l'API.
     * Vérifie qu'un médecin peut être créé et retourné avec les bonnes informations.
     */
    @Test
    void testCreateMedecin() throws Exception {
        // given
        String medecinJson = """
            {
                "name": "Dr. House",
                "surname": "Gregory",
                "specialite": "Diagnostic"
            }
            """;

        // when
        mockMvc.perform(post("/api/medecins")
                .content(medecinJson)
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Dr. House"));
    }
    
    /**
     * Teste la récupération d'un médecin par son ID via l'API.
     * Vérifie qu'un médecin peut être récupéré avec les bonnes informations.
     */
    @Test
    void testGetMedecinById() throws Exception {
        // given
        String medecinJson = """
            {
                "name": "Dr. Wilson",
                "surname": "James",
                "specialite": "Oncologie"
            }
            """;

        // Créer d'abord un médecin
        String responseJson = mockMvc.perform(post("/api/medecins")
                .content(medecinJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        
        // Extraire l'ID du médecin créé
        String id = responseJson.split("\"id\":")[1].split(",")[0];
        
        // when
        mockMvc.perform(get("/api/medecins/" + id))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. Wilson"));
    }
    @Test
    void testUpdateMedecin() throws Exception {
        // First create a medecin
        String medecinJson = """
            {
                "name": "Dr. House",
                "surname": "Gregory",
                "specialite": "Diagnostic"
            }
            """;

        String response = mockMvc.perform(post("/api/medecins")
                .content(medecinJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        
        String id = response.split("\"id\":")[1].split(",")[0];

        // Update the medecin
        String updatedMedecinJson = """
            {
                "name": "Dr. House",
                "surname": "Gregory",
                "specialite": "Immunologie"
            }
            """;

        mockMvc.perform(put("/api/medecins/" + id)
                .content(updatedMedecinJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.specialite").value("Immunologie"));
    }

    @Test
    void testDeleteMedecin() throws Exception {
        // First create a medecin
        String medecinJson = """
            {
                "name": "Dr. House",
                "surname": "Gregory",
                "specialite": "Diagnostic"
            }
            """;

        String response = mockMvc.perform(post("/api/medecins")
                .content(medecinJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        
        String id = response.split("\"id\":")[1].split(",")[0];

        // Delete the medecin
        mockMvc.perform(delete("/api/medecins/" + id))
                .andExpect(status().isOk());

        // Verify deletion
        mockMvc.perform(get("/api/medecins/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllMedecins() throws Exception {
        // Create first medecin
        String medecinJson1 = """
            {
                "name": "Dr. House",
                "surname": "Gregory",
                "specialite": "Diagnostic"
            }
            """;

        // Create second medecin
        String medecinJson2 = """
            {
                "name": "Dr. Wilson",
                "surname": "James",
                "specialite": "Oncologie"
            }
            """;

        mockMvc.perform(post("/api/medecins")
                .content(medecinJson1)
                .contentType(MediaType.APPLICATION_JSON));
        
        mockMvc.perform(post("/api/medecins")
                .content(medecinJson2)
                .contentType(MediaType.APPLICATION_JSON));

        // Get all medecins
        mockMvc.perform(get("/api/medecins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists())
                .andExpect(jsonPath("$[2]").doesNotExist());
    }

    @Test
    void testCreateMedecinBadRequest() throws Exception {
        String invalidMedecinJson = """
            {
                "name": "",
                "surname": "",
                "specialite": ""
            }
            """;

        mockMvc.perform(post("/api/medecins")
                .content(invalidMedecinJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    @Test
void testGetNonExistentMedecin() throws Exception {
    mockMvc.perform(get("/api/medecins/999"))
            .andExpect(status().isNotFound());
}

    @Test
    void testUpdateNonExistentMedecin() throws Exception {
        String medecinJson = """
            {
                "name": "Dr. House",
                "surname": "Gregory",
                "specialite": "Diagnostic"
            }
            """;

        mockMvc.perform(put("/api/medecins/999")
                .content(medecinJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}