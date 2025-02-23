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

        // when
        Reservation reservation = new Reservation();
        reservation.setPatient(savedPatient);
        reservation.setMedecin(savedMedecin);
        reservation.setDateReservation("2025-02-20");
        reservation.setDatestart("2025-02-20T10:00:00");
        reservation.setDateend("2025-02-20T11:00:00");
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

        // Create first reservation
        Reservation reservation1 = new Reservation();
        reservation1.setPatient(savedPatient1);
        reservation1.setMedecin(savedMedecin);
        reservation1.setDateReservation("2025-02-20");
        reservation1.setDatestart("2025-02-20T10:00:00");
        reservation1.setDateend("2025-02-20T11:00:00");
        reservation1.setReservationStatus("Disponible");
        reservationService.reserveCreneau(reservation1, savedPatient1);

        // Try to create second reservation at the same time
        Reservation reservation2 = new Reservation();
        reservation2.setPatient(savedPatient2);
        reservation2.setMedecin(savedMedecin);
        reservation2.setDateReservation("2025-02-20");
        reservation2.setDatestart("2025-02-20T10:00:00");
        reservation2.setDateend("2025-02-20T11:00:00");
        reservation2.setReservationStatus("Disponible");

        // then
        assertThatThrownBy(() -> reservationService.reserveCreneau(reservation2, savedPatient2))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Créneau déjà réservé");
    }

    /**
     * Teste la validation des créneaux horaires.
     */
    @Test
    public void testTimeSlotValidation() {
        // given
        Patient patient = new Patient();
        patient.setName("John");
        patient.setSurname("Doe");
        patient.setEmail("john.doe@example.com");
        Patient savedPatient = patientService.save(patient);

        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        Medecin savedMedecin = medecinService.save(medecin);

        // when - créer une réservation avec des heures invalides
        Reservation reservation = new Reservation();
        reservation.setPatient(savedPatient);
        reservation.setMedecin(savedMedecin);
        reservation.setDateReservation("2025-02-20");
        reservation.setDatestart("2025-02-20T18:00:00"); // Heure de fin de journée
        reservation.setDateend("2025-02-20T19:00:00");   // Hors heures de consultation
        reservation.setReservationStatus("Disponible");

        // then
        assertThatThrownBy(() -> reservationService.reserveCreneau(reservation, savedPatient))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("horaires de consultation");
    }

    /**
     * Teste l'annulation d'une réservation.
     */
    @Test
    public void testReservationCancellation() {
        // given
        Patient patient = new Patient();
        patient.setName("John");
        patient.setSurname("Doe");
        patient.setEmail("john.doe@example.com");
        Patient savedPatient = patientService.save(patient);

        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        Medecin savedMedecin = medecinService.save(medecin);

        Reservation reservation = new Reservation();
        reservation.setPatient(savedPatient);
        reservation.setMedecin(savedMedecin);
        reservation.setDateReservation("2025-02-20");
        reservation.setDatestart("2025-02-20T10:00:00");
        reservation.setDateend("2025-02-20T11:00:00");
        reservation.setReservationStatus("Disponible");
        Reservation savedReservation = reservationService.reserveCreneau(reservation, savedPatient);

        // when
        Reservation cancelledReservation = reservationService.cancelReservation(savedReservation.getId());

        // then
        assertThat(cancelledReservation.getReservationStatus()).isEqualTo("Annulé");
    }

    /**
     * Teste la recherche de créneaux disponibles.
     */
    @Test
    public void testFindAvailableTimeSlots() {
        // given
        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        Medecin savedMedecin = medecinService.save(medecin);

        // Créer quelques réservations
        Reservation reservation1 = new Reservation();
        reservation1.setMedecin(savedMedecin);
        reservation1.setDateReservation("2025-02-20");
        reservation1.setDatestart("2025-02-20T09:00:00");
        reservation1.setDateend("2025-02-20T10:00:00");
        reservation1.setReservationStatus("Disponible");
        reservationService.save(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setMedecin(savedMedecin);
        reservation2.setDateReservation("2025-02-20");
        reservation2.setDatestart("2025-02-20T11:00:00");
        reservation2.setDateend("2025-02-20T12:00:00");
        reservation2.setReservationStatus("Disponible");
        reservationService.save(reservation2);

        // when
        List<Reservation> availableSlots = reservationService.getAvailableTimeSlots(savedMedecin.getId(), "2025-02-20");

        // then
        assertThat(availableSlots).hasSize(2);
        assertThat(availableSlots).extracting("datestart")
            .containsExactlyInAnyOrder("2025-02-20T09:00:00", "2025-02-20T11:00:00");
    }

    /**
     * Teste la validation des dates de réservation.
     */
    @Test
    public void testDateValidation() {
        // given
        Patient patient = new Patient();
        patient.setName("John");
        patient.setSurname("Doe");
        patient.setEmail("john.doe@example.com");
        Patient savedPatient = patientService.save(patient);

        Medecin medecin = new Medecin();
        medecin.setName("Dr. House");
        Medecin savedMedecin = medecinService.save(medecin);

        // Create a reservation
        Reservation reservation = new Reservation();
        reservation.setPatient(savedPatient);
        reservation.setMedecin(savedMedecin);
        reservation.setDateReservation("2025-02-20");
        reservation.setDatestart("2025-02-20T10:00:00");
        reservation.setDateend("2025-02-20T11:00:00");
        reservation.setReservationStatus("Disponible");
        reservationService.save(reservation);

        // when
        List<Reservation> availableReservations = reservationService.findByStatus("Disponible");

        // then
        assertThat(availableReservations).hasSize(1);
        assertThat(availableReservations.get(0).getReservationStatus()).isEqualTo("Disponible");
    }
}