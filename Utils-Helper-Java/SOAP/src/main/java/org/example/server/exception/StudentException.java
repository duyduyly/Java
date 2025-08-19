package org.example.server.exception;

public class StudentException extends RuntimeException {
    private String message;

    public StudentException(String message) {
        super(message);
        this.message = message;
    }
}
