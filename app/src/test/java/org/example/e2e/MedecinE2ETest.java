package org.example.e2e;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
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
}