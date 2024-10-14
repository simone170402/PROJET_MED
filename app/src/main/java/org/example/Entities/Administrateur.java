package org.example.Entities;

import java.util.List;

import org.checkerframework.checker.units.qual.g;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Administrateur extends Utilisateur {
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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

