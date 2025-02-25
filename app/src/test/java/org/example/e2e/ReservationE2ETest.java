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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:applicationTest.yaml")
public class ReservationE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateReservation() throws Exception {
        String reservationJson = """
            {
                "reservationStatus": "Disponible",
                "dateReservation": "2025-02-10",
                "datestart": "2025-02-10T09:00:00",
                "dateend": "2025-02-10T10:00:00",
                "title": "Consultation"
            }
            """;

        mockMvc.perform(post("/api/reservations")
                .content(reservationJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.reservationStatus").value("Disponible"));
    }

    @Test
    void testGetReservationById() throws Exception {
        // given
        String reservationJson = """
            {
                "reservationStatus": "Disponible",
                "dateReservation": "2025-02-10",
                "datestart": "2025-02-10T09:00:00",
                "dateend": "2025-02-10T10:00:00",
                "title": "Consultation"
            }
            """;

        // Create a reservation
        String responseJson = mockMvc.perform(post("/api/reservations")
                .content(reservationJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        
        // Use Jackson to extract the ID of the created reservation
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseJson);
        String id = jsonNode.get("id").asText();
        
        // when
        mockMvc.perform(get("/api/reservations/" + id))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationStatus").value("Disponible"))
                .andExpect(jsonPath("$.dateReservation").value("2025-02-10"))
                .andExpect(jsonPath("$.datestart").value("2025-02-10T09:00:00"))
                .andExpect(jsonPath("$.dateend").value("2025-02-10T10:00:00"))
                .andExpect(jsonPath("$.title").value("Consultation"));
    }

    @Test
    void testUpdateReservation() throws Exception {
        // First create a reservation
        String reservationJson = """
            {
                "reservationStatus": "Disponible",
                "dateReservation": "2025-02-10",
                "datestart": "2025-02-10T09:00:00",
                "dateend": "2025-02-10T10:00:00",
                "title": "Consultation"
            }
            """;

        String response = mockMvc.perform(post("/api/reservations")
                .content(reservationJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        
        String id = response.split("\"id\":")[1].split(",")[0];

        // Update the reservation
        String updatedReservationJson = """
            {
                "reservationStatus": "Reservé",
                "dateReservation": "2025-02-10",
                "datestart": "2025-02-10T09:00:00",
                "dateend": "2025-02-10T10:00:00",
                "title": "Consultation Urgente"
            }
            """;

        mockMvc.perform(put("/api/reservations/" + id)
                .content(updatedReservationJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationStatus").value("Reservé"));
    }

    @Test
    void testGetReservationsByDate() throws Exception {
        // Create a reservation
        String reservationJson = """
            {
                "reservationStatus": "Disponible",
                "dateReservation": "2025-02-10",
                "datestart": "2025-02-10T09:00:00",
                "dateend": "2025-02-10T10:00:00",
                "title": "Consultation"
            }
            """;

        mockMvc.perform(post("/api/reservations")
                .content(reservationJson)
                .contentType(MediaType.APPLICATION_JSON));

        // Get reservations by date
        mockMvc.perform(get("/api/reservations/date/2025-02-10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].dateReservation").value("2025-02-10"));
    }
    @Test
    void testGetNonExistentReservation() throws Exception {
        mockMvc.perform(get("/api/reservations/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateReservationWithInvalidDates() throws Exception {
        String invalidReservationJson = """
            {
                "datestart": "2025-02-10T11:00:00",
                "dateend": "2025-02-10T10:00:00",
                "title": "Invalid Time Range"
            }
            """;

        mockMvc.perform(post("/api/reservations")
                .content(invalidReservationJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateReservationWithPastDate() throws Exception {
        String pastDateReservationJson = """
            {
                "datestart": "2020-01-01T09:00:00",
                "dateend": "2020-01-01T10:00:00",
                "title": "Past Consultation"
            }
            """;

        mockMvc.perform(post("/api/reservations")
                .content(pastDateReservationJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}