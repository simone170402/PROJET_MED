package org.example.e2e;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.yaml")
public class ReservationE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateAndGetReservation() throws Exception {
        // Create a reservation
        String reservationJson = """
            {
                "reservationStatus": "Disponible",
                "dateReservation": "2025-02-20",
                "datestart": "2025-02-20T10:00:00",
                "dateend": "2025-02-20T11:00:00",
                "title": "Consultation"
            }
            """;

        String response = mockMvc.perform(post("/api/reservations")
                .content(reservationJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        // Extract the ID
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        String id = jsonNode.get("id").asText();

        // Get the reservation by ID
        mockMvc.perform(get("/api/reservations/reservation/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationStatus").value("Disponible"))
                .andExpect(jsonPath("$.dateReservation").value("2025-02-20"));
    }

    @Test
    void testGetAllReservations() throws Exception {
        // Create a reservation first
        String reservationJson = """
            {
                "reservationStatus": "Disponible",
                "dateReservation": "2025-02-20",
                "datestart": "2025-02-20T10:00:00",
                "dateend": "2025-02-20T11:00:00",
                "title": "Consultation"
            }
            """;

        mockMvc.perform(post("/api/reservations")
                .content(reservationJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        // Get all reservations
        mockMvc.perform(get("/api/reservations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].reservationStatus").value("Disponible"));
    }

    @Test
    void testUpdateReservation() throws Exception {
        // Create a reservation
        String reservationJson = """
            {
                "reservationStatus": "Disponible",
                "dateReservation": "2025-02-20",
                "datestart": "2025-02-20T10:00:00",
                "dateend": "2025-02-20T11:00:00",
                "title": "Consultation"
            }
            """;

        String response = mockMvc.perform(post("/api/reservations")
                .content(reservationJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        String id = jsonNode.get("id").asText();

        // Update the reservation
        String updatedJson = """
            {
                "reservationStatus": "Réservé",
                "dateReservation": "2025-02-20",
                "datestart": "2025-02-20T10:00:00",
                "dateend": "2025-02-20T11:00:00",
                "title": "Consultation modifiée"
            }
            """;

        mockMvc.perform(put("/api/reservations/" + id)
                .content(updatedJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationStatus").value("Réservé"))
                .andExpect(jsonPath("$.title").value("Consultation modifiée"));
    }

    @Test
    void testDeleteReservation() throws Exception {
        // Create a reservation
        String reservationJson = """
            {
                "reservationStatus": "Disponible",
                "dateReservation": "2025-02-20",
                "datestart": "2025-02-20T10:00:00",
                "dateend": "2025-02-20T11:00:00",
                "title": "Consultation"
            }
            """;

        String response = mockMvc.perform(post("/api/reservations")
                .content(reservationJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        String id = jsonNode.get("id").asText();

        // Delete the reservation
        mockMvc.perform(delete("/api/reservations/" + id))
                .andExpect(status().isNoContent());

        // Verify it's deleted
        mockMvc.perform(get("/api/reservations/reservation/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void testReserveCreneauWithPatient() throws Exception {
        String payload = """
            {
                "patient": {
                    "name": "John",
                    "surname": "Doe",
                    "email": "john.doe@example.com",
                    "phoneNumber": "0123456789"
                },
                "reservation": {
                    "dateReservation": "2025-02-20",
                    "datestart": "2025-02-20T10:00:00",
                    "dateend": "2025-02-20T11:00:00",
                    "reservationStatus": "Disponible",
                    "title": "Consultation"
                }
            }
            """;

        mockMvc.perform(post("/api/reservations/reserve")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patient.name").value("John"))
                .andExpect(jsonPath("$.reservationStatus").value("Réservé"));
    }
}