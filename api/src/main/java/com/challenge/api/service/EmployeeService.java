package com.challenge.api.service;

import com.challenge.api.model.CompanyEmployee;
import com.challenge.api.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<CompanyEmployee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<CompanyEmployee> findEmployeeByUUID(UUID uuid) {
        return employeeRepository.findById(uuid);
    }

    public CompanyEmployee addEmployee(CompanyEmployee employee) {
        if (employee.getUuid() == null) {
            employee.setUuid(UUID.randomUUID());
        }
        employeeRepository.save(employee);
        return employee;
    }
}
