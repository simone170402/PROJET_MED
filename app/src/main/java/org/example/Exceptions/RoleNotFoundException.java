package org.example.Exceptions;


public class RoleNotFoundException extends RuntimeException  {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
