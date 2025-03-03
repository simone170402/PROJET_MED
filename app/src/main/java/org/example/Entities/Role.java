package org.example.Entities;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Utilisateur> utilisateurs = new HashSet<>();

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Utilisateur> getUtilisateurs() { return utilisateurs; }
    public void setUtilisateurs(Set<Utilisateur> utilisateurs) { this.utilisateurs = utilisateurs; }
}
