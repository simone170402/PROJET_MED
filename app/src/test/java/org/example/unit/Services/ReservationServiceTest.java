package org.example.unit.Services;

import static org.junit.jupiter.api.Assertions.*;

import org.example.Entities.Medecin;
import org.example.Entities.Reservation;
import org.example.Exceptions.ReservationNotFoundException;
import org.example.Services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReservationServiceTest {
    @Autowired
    private ReservationService reservationService;

    private Medecin medecin;

    @BeforeEach
    void setUp() {
        medecin = new Medecin();
        medecin.setId(1L);
        medecin.setName("John");
        medecin.setSurname("Doe");
        medecin.setEmail("john.doe@example.com");
        medecin.setPhoneNumber("1234567890");
    }

    /**
     * Teste la méthode findOneById de ReservationService.
     * Vérifie qu'une réservation peut être trouvée par son ID.
     */
    @Test
    void testFindOneById() {
        // given
        Reservation reservation = new Reservation();
        reservation.setDateReservation(new String());
        reservation.setMedecin(medecin);
        reservationService.save(reservation);

        // when
        Reservation found = reservationService.findOneById(reservation.getId());

        // then
        assertNotNull(found, "Reservation should be found");
    }

    /**
     * Teste la méthode findAll de ReservationService.
     * Vérifie que toutes les réservations peuvent être trouvées.
     */
    @Test
    void testFindAll() {
        // given
        Reservation reservation1 = new Reservation();
        reservation1.setDateReservation(new String());
        reservation1.setMedecin(medecin);
        reservationService.save(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setDateReservation(new String());
        reservation2.setMedecin(medecin);
        reservationService.save(reservation2);

        // when
        int reservationCount = reservationService.findAll().size();

        // then
        assertEquals(2, reservationCount, "There should be two reservations");
    }

    /**
     * Teste la méthode save de ReservationService.
     * Vérifie qu'une réservation peut être sauvegardée.
     */
    @Test
    void testSave() {
        // given
        Reservation reservation = new Reservation();
        reservation.setDateReservation(new String());
        reservation.setMedecin(medecin);

        // when
        Reservation saved = reservationService.save(reservation);

        // then
        assertNotNull(saved.getId(), "Saved reservation should have an ID");
    }

    /**
     * Teste la méthode update de ReservationService.
     * Vérifie qu'une réservation peut être mise à jour.
     */
    @Test
    void testUpdate() {
        // given
        Reservation reservation = new Reservation();
        reservation.setDateReservation(new String());
        reservation.setMedecin(medecin);
        reservationService.save(reservation);

        // when
        String newDate = new String();
        reservation.setDateReservation(newDate);
        Reservation updated = reservationService.update(reservation.getId(), reservation);

        // then
        assertEquals(newDate, updated.getDateReservation(), "Reservation date should be updated");
    }

    /**
     * Teste la méthode delete de ReservationService.
     * Vérifie qu'une réservation peut être supprimée.
     */
    @Test
    void testDelete() {
        // given
        Reservation reservation = new Reservation();
        reservation.setDateReservation(new String());
        reservation.setMedecin(medecin);
        reservationService.save(reservation);

        // when
        reservationService.delete(reservation.getId());

        // then
        assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.findOneById(reservation.getId());
        }, "Reservation should be deleted");
    }
}