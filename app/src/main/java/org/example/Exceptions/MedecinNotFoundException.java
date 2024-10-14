package org.example.Exceptions;

public class MedecinNotFoundException extends RuntimeException {
    public MedecinNotFoundException(String message) {
        super(message);
    }
}

