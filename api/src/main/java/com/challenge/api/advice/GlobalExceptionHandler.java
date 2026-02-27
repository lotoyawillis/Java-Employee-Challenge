package com.challenge.api.advice;

import com.challenge.api.exception.EmployeeNotCreatedException;
import com.challenge.api.exception.EmployeeNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles EmployeeNotFoundException.
     *
     * @param ex the custom exception
     * @return a 404 NOT FOUND ERROR response with error message
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(EmployeeNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Handles path variable errors.
     *
     * @param ex the exception
     * @return a 400 BAD REQUEST ERROR response with error message
     */
    @ExceptionHandler({MissingPathVariableException.class, IllegalArgumentException.class})
    public ResponseEntity<Map<String, String>> handleInvalidUUID(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Handles EmployeeNotCreatedException.
     *
     * @param ex the custom exception
     * @return a 500 INTERNAL SERVER ERROR response with error message
     */
    @ExceptionHandler(EmployeeNotCreatedException.class)
    public ResponseEntity<Map<String, String>> handleNotCreated(EmployeeNotCreatedException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
