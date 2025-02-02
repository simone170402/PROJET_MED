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
    
    @Test
    void testCreateMedecin() throws Exception {
        mockMvc.perform(post("/api/medecins")
                .content("""
                    {
                        "name": "Dr. House",
                        "surname": "Gregory",
                        "specialite": "Diagnostic"
                    }
                    """)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Dr. House"));
    }
    
    @Test
    void testGetMedecinById() throws Exception {
        // Créer d'abord un médecin
        String responseJson = mockMvc.perform(post("/api/medecins")
                .content("""
                    {
                        "name": "Dr. Wilson",
                        "surname": "James",
                        "specialite": "Oncologie"
                    }
                    """)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        
        // Extraire l'ID du médecin créé
        String id = responseJson.split("\"id\":")[1].split(",")[0];
        
        // Tester la récupération
        mockMvc.perform(get("/api/medecins/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. Wilson"));
    }
}