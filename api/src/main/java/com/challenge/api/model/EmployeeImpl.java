package com.challenge.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
public class EmployeeImpl implements Employee {
    /**
     * ID used for EmployeeImpl
     * Set by the Service.
     */
    @Id
    private UUID uuid;

    private String firstName;
    private String lastName;
    private String fullName;

    private Integer salary;

    private Integer age;
    private String jobTitle;

    private String email;

    private Instant contractHireDate;

    /**
     * Null if Employee has not been terminated.
     */
    private Instant contractTerminationDate;
}
