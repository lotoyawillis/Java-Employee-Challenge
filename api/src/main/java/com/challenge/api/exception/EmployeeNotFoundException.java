package com.challenge.api.exception;

/**
 * Thrown when at least one employee is requested but no matching
 * record exists in the database.
 * Automatically handled by GlobalExceptionHandler - returns status code 404 with the message.
 */
public class EmployeeNotFoundException extends RuntimeException {
    /**
     * Constructs a new exception with the specified message.
     *
     * @param message the exception details
     */
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
