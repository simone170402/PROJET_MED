package org.example.Entities;
import org.springframework.stereotype.Entity;
@Entity
public class Centre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String address;
    private String phoneNumber;
    // Getters and setters
}

