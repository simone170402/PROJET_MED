package org.example.Entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Administrateur extends Utilisateur {
    @ManyToMany
    private List<Centre> centres;
    // Getters and setters

    public void setId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }
}

