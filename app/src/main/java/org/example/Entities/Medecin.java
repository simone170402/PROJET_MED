package org.example.Entities;

import java.util.List;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
public class Medecin extends Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "centre_id", nullable = true)
    private Centre centre;

    @JsonManagedReference
    @OneToMany(mappedBy = "medecin", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Patient> patients;

    // Getters et Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Centre getCentre() { return centre; }
    public void setCentre(Centre centre) { this.centre = centre; }

    public List<Patient> getPatients() { return patients; }
    public void setPatients(List<Patient> patients) { this.patients = patients; }
}
