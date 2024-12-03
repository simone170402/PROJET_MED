package org.example.Entities;

import java.util.Date;

import org.checkerframework.checker.units.qual.m;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reservationStatus;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Centre centre;

    @Column(name = "date_reservation")
    private Date dateReservation;
    private Date Datestart;
    private Date Dateend;   
    private String title;

    @ManyToOne
    private Medecin doctor;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String isReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String ReservationStatus) {
        reservationStatus = ReservationStatus;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public Date getDate() {
        return dateReservation;
    }

    public void setDate(Date date) {
        this.dateReservation = date;
    }

    public Date getDatestart() {
        return Datestart;
    }

    public void setDatestart(Date datestart) {
        Datestart = datestart;
    }

    public Date getDateend() {
        return Dateend;
    }

    public void setDateend(Date dateend) {
        Dateend = dateend;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Medecin getDoctor() {
        return doctor;
    }

    public void setDoctor(Medecin doctor) {
        this.doctor = doctor;
    }

}

