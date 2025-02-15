package org.example.unit.Controllers;

import org.example.Controllers.MedecinRestController;
import org.example.Repositories.MedecinRepository;
import org.example.Services.MedecinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class MedecinRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MedecinService medecinService;

    @MockBean
    private MedecinRepository medecinRepository;

    @InjectMocks
    private MedecinRestController medecinRestController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(medecinRestController)
                .build();
    }

    /**
     * Teste la méthode getMedecinsByCentre de MedecinRestController.
     * Vérifie que les médecins peuvent être trouvés par l'ID du centre.
     */
    @Test
    void getMedecinsByCentre_ShouldReturnMedecins() throws Exception {
        // given
        Object[] medecinData1 = {1L, "Dupont", "Jean", "0123456789", 1L, "Centre A"};
        Object[] medecinData2 = {2L, "Martin", "Pierre", "9876543210", 1L, "Centre A"};
        List<Object[]> medecinsList = Arrays.asList(medecinData1, medecinData2);

        when(medecinRepository.findMedecinsByCentreId(anyLong())).thenReturn(medecinsList);

        // when
        mockMvc.perform(get("/api/medecins/centre/1")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Dupont"))
                .andExpect(jsonPath("$[0].surname").value("Jean"))
                .andExpect(jsonPath("$[0].phoneNumber").value("0123456789"))
                .andExpect(jsonPath("$[0].centreId").value(1))
                .andExpect(jsonPath("$[0].centreName").value("Centre A"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Martin"));
    }

    /**
     * Teste la méthode getMedecinsByCentre de MedecinRestController.
     * Vérifie qu'une liste vide est retournée lorsqu'aucun médecin n'est trouvé.
     */
    @Test
    void getMedecinsByCentre_ShouldReturnEmptyList_WhenNoDoctorsFound() throws Exception {
        // given
        when(medecinRepository.findMedecinsByCentreId(anyLong())).thenReturn(Arrays.asList());

        // when
        mockMvc.perform(get("/api/medecins/centre/1")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    /**
     * Teste la gestion des exceptions dans MedecinRestController.
     * Vérifie qu'une réponse 404 est retournée lorsqu'un centre n'est pas trouvé.
     */
    @Test
    void handleMedecinNotFoundException_ShouldReturnNotFound() throws Exception {
        // given
        when(medecinRepository.findMedecinsByCentreId(99L))
                .thenThrow(new RuntimeException("Centre not found"));

        // when
        mockMvc.perform(get("/api/medecins/centre/99")
                .contentType(MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isNotFound());
    }
}