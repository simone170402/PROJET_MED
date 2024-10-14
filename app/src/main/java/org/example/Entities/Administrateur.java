package org.example.Entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Administrateur extends Utilisateur {
    @ManyToMany
    private List<Centre> centres;

    // Getters and setters
    public List<Centre> getCentres() {
        return centres;
    }

    public void setCentres(List<Centre> centres) {
        this.centres = centres;
    }
}

