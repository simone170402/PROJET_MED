package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

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

    @Test
    void testFindOneById() {
        Reservation reservation = new Reservation();
        reservation.setDateReservation(new String());
        reservation.setMedecin(medecin);
        reservationService.save(reservation);

        Reservation found = reservationService.findOneById(reservation.getId());
        assertNotNull(found, "Reservation should be found");
    }

    @Test
    void testFindAll() {
        Reservation reservation1 = new Reservation();
        reservation1.setDateReservation(new String());
        reservation1.setMedecin(medecin);
        reservationService.save(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setDateReservation(new String());
        reservation2.setMedecin(medecin);
        reservationService.save(reservation2);

        assertEquals(2, reservationService.findAll().size(), "There should be two reservations");
    }

    @Test
    void testSave() {
        Reservation reservation = new Reservation();
        reservation.setDateReservation(new String());
        reservation.setMedecin(medecin);
        Reservation saved = reservationService.save(reservation);

        assertNotNull(saved.getId(), "Saved reservation should have an ID");
    }

    @Test
    void testUpdate() {
        Reservation reservation = new Reservation();
        reservation.setDateReservation(new String());
        reservation.setMedecin(medecin);
        reservationService.save(reservation);

        String newDate = new String();
        reservation.setDateReservation(newDate);
        Reservation updated = reservationService.update(reservation.getId(), reservation);

        assertEquals(newDate, updated.getDateReservation(), "Reservation date should be updated");
    }

    @Test
    void testDelete() {
        Reservation reservation = new Reservation();
        reservation.setDateReservation(new String());
        reservation.setMedecin(medecin);
        reservationService.save(reservation);

        reservationService.delete(reservation.getId());
        assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.findOneById(reservation.getId());
        }, "Reservation should be deleted");
    }
}