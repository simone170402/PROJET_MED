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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:applicationTest.yaml")
public class CentreE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateCentre() throws Exception {
        String centreJson = """
            {
                "name": "Centre Medical Paris",
                "city": "Paris",
                "address": "123 Rue de la Santé",
                "phoneNumber": "0123456789"
            }
            """;

        mockMvc.perform(post("/api/centres")
                .content(centreJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Centre Medical Paris"));
    }
    @Test
    void testGetCentreById() throws Exception {
        // given
        String centreJson = """
            {
                "name": "Centre Hospitalier Universitaire de Bordeaux",
                "city": "Bordeaux",
                "address": "Place Amélie Raba Léon",
                "phone_number": "0556248394"
            }
            """;

        // Créer d'abord un centre
        String responseJson = mockMvc.perform(post("/api/centres")
                .content(centreJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        
        // Utiliser Jackson pour extraire l'ID du centre créé
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseJson);
        String id = jsonNode.get("id").asText();
        
        // when
        mockMvc.perform(get("/api/centres/" + id))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Centre Hospitalier Universitaire de Bordeaux"))
                .andExpect(jsonPath("$.city").value("Bordeaux"))
                .andExpect(jsonPath("$.address").value("Place Amélie Raba Léon"))
                .andExpect(jsonPath("$.phone_number").value("0556248394"));
    }
    @Test
    void testUpdateCentre() throws Exception {
        // First create a centre
        String centreJson = """
            {
                "name": "Centre Medical Paris",
                "city": "Paris",
                "address": "123 Rue de la Santé",
                "phoneNumber": "0123456789"
            }
            """;

        String response = mockMvc.perform(post("/api/centres")
                .content(centreJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        
        String id = response.split("\"id\":")[1].split(",")[0];

        // Update the centre
        String updatedCentreJson = """
            {
                "name": "Centre Medical Paris Updated",
                "city": "Paris",
                "address": "124 Rue de la Santé",
                "phoneNumber": "0123456789"
            }
            """;

        mockMvc.perform(put("/api/centres/" + id)
                .content(updatedCentreJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Centre Medical Paris Updated"));
    }

    @Test
    void testGetAllCentres() throws Exception {
        // Create multiple centres and test getting all of them
        String centreJson1 = """
            {
                "name": "Centre 1",
                "city": "Paris",
                "address": "Address 1",
                "phoneNumber": "0123456789"
            }
            """;

        String centreJson2 = """
            {
                "name": "Centre 2",
                "city": "Lyon",
                "address": "Address 2",
                "phoneNumber": "0987654321"
            }
            """;

        mockMvc.perform(post("/api/centres")
                .content(centreJson1)
                .contentType(MediaType.APPLICATION_JSON));
        
        mockMvc.perform(post("/api/centres")
                .content(centreJson2)
                .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/api/centres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists());
    }
    @Test
    void testGetNonExistentCentre() throws Exception {
        mockMvc.perform(get("/api/centres/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCentreWithInvalidData() throws Exception {
        String invalidCentreJson = """
            {
                "name": "",
                "city": "",
                "phoneNumber": "invalid"
            }
            """;

        mockMvc.perform(post("/api/centres")
                .content(invalidCentreJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
        
    @Test
    void testGetNonExistentPatient() throws Exception {
        mockMvc.perform(get("/api/patients/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreatePatientWithInvalidEmail() throws Exception {
        String invalidPatientJson = """
            {
                "name": "John",
                "surname": "Doe",
                "email": "invalid-email",
                "dateOfBirth": "1990-01-01"
            }
            """;

        mockMvc.perform(post("/api/patients")
                .content(invalidPatientJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
