package org.example.Entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Medecin extends Utilisateur {
    @ManyToOne
    private Centre centre;
    @OneToMany(mappedBy = "medecin")
    private List<Patient> patients;
    // Getters and setters
    public void setId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }
}

