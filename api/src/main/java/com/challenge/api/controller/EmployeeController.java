package com.challenge.api.controller;

import com.challenge.api.exception.EmployeeNotCreatedException;
import com.challenge.api.exception.EmployeeNotFoundException;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import com.challenge.api.service.EmployeeService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

/**
 * Fill in the missing aspects of this Spring Web REST Controller. Don't forget to add a Service layer.
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Retrieves a list of all employees.
     *
     * @implNote The README says that this endpoint should return the full list of employees unfiltered and
     * the docstring says this endpoint returns one or more employees, so I implemented this endpoint with
     * the assumption that an empty list should never be returned.
     * @return One or more Employees.
     * @throws EmployeeNotFoundException if no employees are in the database
     */
    @GetMapping
    public List<EmployeeImpl> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    /**
     * Retrieves an employee by UUID.
     *
     * @param uuid Employee UUID
     * @return Requested Employee if exists
     * @throws EmployeeNotFoundException if no employee exists with the given UUID
     */
    @GetMapping("/{uuid}")
    public Employee getEmployeeByUuid(@PathVariable("uuid") UUID uuid) {
        return employeeService.findEmployeeByUUID(uuid);
    }

    /**
     * Adds an employee.
     *
     * @param requestBody EmployeeImpl
     * @return Newly created Employee
     * @throws EmployeeNotCreatedException if employee is not created
     */
    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeImpl requestBody) {
        return employeeService.addEmployee(requestBody);
    }
}
