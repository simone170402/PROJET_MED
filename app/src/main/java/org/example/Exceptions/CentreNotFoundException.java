package org.example.Exceptions;

public class CentreNotFoundException extends RuntimeException {
    public CentreNotFoundException(String message) {
        super(message);
    }
}
