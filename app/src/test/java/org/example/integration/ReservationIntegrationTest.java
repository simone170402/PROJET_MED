package org.example.integration;

import org.example.Entities.Patient;
import org.example.Entities.Medecin;
import org.example.Entities.Reservation;
import org.example.Services.ReservationService;
import org.example.Services.MedecinService;
import org.example.Services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.yaml")
class ReservationIntegrationTest {
    
    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private MedecinService medecinService;
    
    @Autowired
    private PatientService patientService;

    /**
     * Teste le processus complet de réservation.
     * Vérifie qu'une réservation peut être créée et récupérée par le médecin.
     */
    @Test
    public void testCompleteReservationProcess() {
        // given
        Patient patient = new Patient();
        patient.setName("John");
        patient.setSurname("Doe");
        patient.setEmail("john.doe@example.com");
        Patient savedPatient = patientService.save(patient);

        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        Medecin savedMedecin = medecinService.save(medecin);

        String appointmentDate = "2023-10-10";

        // when
        Reservation reservation = new Reservation();
        reservation.setPatient(savedPatient);
        reservation.setMedecin(savedMedecin);
        reservation.setDateReservation(appointmentDate);
        reservation.setReservationStatus("Disponible");
        Reservation savedReservation = reservationService.reserveCreneau(reservation, savedPatient);

        // then
        assertThat(savedReservation.getId()).isNotNull();
        List<Reservation> reservationsByMedecin = reservationService.getCreneauxByMedecin(savedMedecin.getId());
        assertThat(reservationsByMedecin)
            .hasSize(1)
            .extracting("patient.name")
            .contains("John");
    }

    /**
     * Teste les conflits de réservation.
     * Vérifie qu'une réservation ne peut pas être faite sur un créneau déjà réservé.
     */
    @Test
    public void testReservationConflicts() {
        // given
        Patient patient1 = new Patient();
        patient1.setName("John");
        patient1.setSurname("Doe");
        patient1.setEmail("john.doe@example.com");
        Patient savedPatient1 = patientService.save(patient1);

        Patient patient2 = new Patient();
        patient2.setName("Jane");
        patient2.setSurname("Doe");
        patient2.setEmail("jane.doe@example.com");
        Patient savedPatient2 = patientService.save(patient2);

        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        Medecin savedMedecin = medecinService.save(medecin);

        String appointmentDate = "2023-10-10";

        // when
        Reservation reservation1 = new Reservation();
        reservation1.setPatient(savedPatient1);
        reservation1.setMedecin(savedMedecin);
        reservation1.setDateReservation(appointmentDate);
        reservation1.setReservationStatus("Disponible");
        Reservation savedReservation1 = reservationService.reserveCreneau(reservation1, savedPatient1);

        Reservation reservation2 = new Reservation();
        reservation2.setPatient(savedPatient2);
        reservation2.setMedecin(savedMedecin);
        reservation2.setDateReservation(appointmentDate);
        reservation2.setReservationStatus("Disponible");

        // then
        assertThatThrownBy(() -> reservationService.reserveCreneau(reservation2, savedPatient2))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Créneau déjà réservé.");
    }
}