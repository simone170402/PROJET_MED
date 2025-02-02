package org.example.integration;

import org.example.Entities.Patient;
import org.example.Entities.Medecin;
import org.example.Entities.Reservation;
import org.example.Services.PatientService;
import org.example.Services.ReservationService;
import org.example.Services.MedecinService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PatientIntegrationTest {
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private MedecinService medecinService;

    @Test
    public void testPatientReservationHistory() {
        // given
        Patient patient = new Patient();
        patient.setName("Jane");
        patient.setSurname("Doe");
        patient.setEmail("jane.doe@example.com");
        Patient savedPatient = patientService.save(patient);

        Medecin medecin1 = new Medecin();
        medecin1.setName("Dr. House");
        Medecin savedMedecin1 = medecinService.save(medecin1);

        Medecin medecin2 = new Medecin();
        medecin2.setName("Dr. Wilson");
        Medecin savedMedecin2 = medecinService.save(medecin2);

        // Create multiple reservations
        createReservation(savedPatient, savedMedecin1, LocalDateTime.now().plusDays(1));
        createReservation(savedPatient, savedMedecin2, LocalDateTime.now().plusDays(2));

        // when
        List<Reservation> patientHistory = reservationService.findAll();
        patientHistory = patientHistory.stream()
            .filter(reservation -> reservation.getPatient().getId().equals(savedPatient.getId()))
            .toList();

        // then
        assertThat(patientHistory).hasSize(2);
        assertThat(patientHistory)
            .extracting("medecin.name")
            .contains("Dr. House", "Dr. Wilson");
    }

    private Reservation createReservation(Patient patient, Medecin medecin, LocalDateTime dateTime) {
        Reservation reservation = new Reservation();
        reservation.setPatient(patient);
        reservation.setMedecin(medecin);
        //reservation.setDateTime(dateTime);
        reservation.setReservationStatus("Disponible");
        return reservationService.reserveCreneau(reservation, patient);
    }
}