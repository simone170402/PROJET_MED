package org.example.Controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.Entities.Centre;
import org.example.Entities.Medecin;
import org.example.Entities.Patient;
import org.example.Entities.Reservation;
import org.example.Exceptions.ReservationNotFoundException;
import org.example.Services.ReservationService;
import org.example.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "*")
public class ReservationRestController {
    
    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/medecin/{medecinId}")
    public ResponseEntity<List<Reservation>> getCreneauxByMedecin(@PathVariable Long medecinId) {
        List<Reservation> reservations = reservationService.getCreneauxByMedecin(medecinId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/medecin/{medecinId}/available")
    public ResponseEntity<List<Reservation>> getAvailableCreneauxByMedecin(@PathVariable Long medecinId) {
        List<Reservation> availableReservations = reservationService.getAvailableCreneauxByMedecin(medecinId);
        return ResponseEntity.ok(availableReservations);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.findOneById(id);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation savedReservation = reservationService.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationService.update(id, reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable Long id) {
        reservationService.delete(id);
    }

    @PostMapping("/reserve")
    public ResponseEntity<?> reserveCreneau(@RequestBody Map<String, Object> payload) {
        try {
            // Validate and extract "patient" and "reservation" keys
            Map<String, Object> patientMap = (Map<String, Object>) payload.get("patient");
            Map<String, Object> reservationMap = (Map<String, Object>) payload.get("reservation");

            // Check for null values
            if (patientMap == null) {
                return ResponseEntity.badRequest().body("Missing 'patient' details in the payload.");
            }
            if (reservationMap == null) {
                return ResponseEntity.badRequest().body("Missing 'reservation' details in the payload.");
            }

            // Extract and map patient details
            Patient patient = new Patient();
            patient.setName((String) patientMap.get("name"));
            patient.setSurname((String) patientMap.get("surname"));
            patient.setPhoneNumber((String) patientMap.get("phoneNumber"));
            patient.setEmail((String) patientMap.get("email"));
            patient.setDateOfBirth((String) patientMap.get("dateOfBirth"));  
            patient.setAdresse((String) patientMap.get("adresse")); 

            // Check if patient already exists
            Optional<Patient> existingPatient = patientRepository.findByEmail(patient.getEmail());
            if (existingPatient.isPresent()) {
                patient = existingPatient.get(); // Use the existing patient
            } else {
                // Get the centre ID from the reservation and set it for the patient as well
                Centre centre = new Centre();
                Long centreId = Long.valueOf(((Map<String, Object>) reservationMap.get("centre")).get("id").toString());
                centre.setId(centreId);
                patient.setCentre(centre); // Set the centre for the patient

                patient = patientRepository.save(patient); // Save the new patient
            }

            // Extract reservation details
            Reservation reservation = new Reservation();
            reservation.setDateReservation((String) reservationMap.get("dateReservation"));  
            reservation.setDatestart((String) reservationMap.get("datestart"));  
            reservation.setDateend((String) reservationMap.get("dateend"));  
            reservation.setTitle((String) reservationMap.get("title"));

            // Set centre and medecin for the reservation
            Centre centre = new Centre();
            centre.setId(Long.valueOf(((Map<String, Object>) reservationMap.get("centre")).get("id").toString()));
            Medecin medecin = new Medecin();
            medecin.setId(Long.valueOf(((Map<String, Object>) reservationMap.get("medecin")).get("id").toString()));

            reservation.setCentre(centre);
            reservation.setMedecin(medecin);
            reservation.setPatient(patient); // Link the patient to the reservation

            // Save the reservation
            Reservation reservedSlot = reservationService.save(reservation);

            return ResponseEntity.ok(reservedSlot);
        } catch (ReservationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Reservation>> searchReservations(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String status) {
        
        List<Reservation> reservations;
        if (date != null && status != null) {
            reservations = reservationService.findByDateAndStatus(date, status);
        } else if (date != null) {
            reservations = reservationService.findByDate(date);
        } else if (status != null) {
            reservations = reservationService.findByStatus(status);
        } else {
            reservations = reservationService.findAll();
        }
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Reservation> updateReservationStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> statusUpdate) {
        
        String newStatus = statusUpdate.get("reservationStatus");
        if (newStatus == null) {
            return ResponseEntity.badRequest().build();
        }

        Reservation reservation = reservationService.findOneById(id);
        reservation.setReservationStatus(newStatus);
        Reservation updatedReservation = reservationService.update(id, reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable Long id) {
        Reservation cancelledReservation = reservationService.cancelReservation(id);
        return ResponseEntity.ok(cancelledReservation);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleReservationNotFoundException(ReservationNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
