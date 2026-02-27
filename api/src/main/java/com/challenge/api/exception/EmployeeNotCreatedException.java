package com.challenge.api.exception;

/**
 * Thrown when an employee record fails to be added to the database.
 * Automatically handled by GlobalExceptionHandler - returns status code 500 with the message.
 */
public class EmployeeNotCreatedException extends RuntimeException {
    /**
     * Constructs a new exception with the specified message.
     *
     * @param message the exception details
     */
    public EmployeeNotCreatedException(String message) {
        super(message);
    }
}
