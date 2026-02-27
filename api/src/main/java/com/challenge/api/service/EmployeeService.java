package com.challenge.api.service;

import com.challenge.api.exception.EmployeeNotCreatedException;
import com.challenge.api.exception.EmployeeNotFoundException;
import com.challenge.api.model.EmployeeImpl;
import com.challenge.api.repository.EmployeeRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository; // The database of EmployeeImpl records

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Returns a list of all employees.
     *
     * @return One or more Employees.
     * @throws EmployeeNotFoundException if no employees are in the database
     */
    public List<EmployeeImpl> findAllEmployees() {
        List<EmployeeImpl> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees exist in the database");
        }
        return employees;
    }

    /**
     * Returns an employee by UUID.
     *
     * @param uuid Employee UUID
     * @return EmployeeImpl if exists
     * @throws EmployeeNotFoundException if no employee exists with the given UUID
     */
    public EmployeeImpl findEmployeeByUUID(UUID uuid) {

        return employeeRepository
                .findById(uuid)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with UUID: " + uuid));
    }

    /**
     * Creates and returns a created employee.
     *
     * @implNote Need not be concerned with an actual persistence layer. Generate mock Employee model as necessary.
     * @param employee EmployeeImpl
     * @return Newly created EmployeeImpl
     * @throws EmployeeNotCreatedException if employee is not created
     */
    public EmployeeImpl addEmployee(EmployeeImpl employee) {
        employee.setUuid(UUID.randomUUID());
        try {
            return employeeRepository.save(employee);
        } catch (Exception ex) {
            throw new EmployeeNotCreatedException(ex.getMessage());
        }
    }
}
