package org.example.Controllers;

import java.util.List;

import org.example.Entities.Reservation;
import org.example.Exceptions.ReservationNotFoundException;
import org.example.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return reservationService.findAll();
    }

    @GetMapping("/reservation/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.findOneById(id);
    }

    @PostMapping("/reservations")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.save(reservation);
    }

    @PutMapping("/reservation/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.update(id, reservation);
    }

    @DeleteMapping("/reservation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable Long id) {
        reservationService.delete(id);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String> handle(ReservationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    
}

