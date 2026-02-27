package com.challenge.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.challenge.api.exception.EmployeeNotCreatedException;
import com.challenge.api.exception.EmployeeNotFoundException;
import com.challenge.api.model.EmployeeImpl;
import com.challenge.api.repository.EmployeeRepository;
import com.challenge.api.service.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void shouldRaiseExceptionWhenNoEmployeesExist() {
        List<EmployeeImpl> employees = new ArrayList<>();

        when(employeeRepository.findAll()).thenReturn(employees);

        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class, () -> employeeService.findAllEmployees());

        assertEquals("No employees exist in the database", ex.getMessage());
    }

    @Test
    void shouldRaiseExceptionWhenNoEmployeeIsFoundWithSpecifiedUuid() {
        UUID uuid = UUID.randomUUID();

        when(employeeRepository.findById(uuid)).thenReturn(Optional.empty());

        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployeeByUUID(uuid));

        assertEquals("Employee not found with UUID: " + uuid, ex.getMessage());
    }

    @Test
    void shouldRaiseExceptionWhenEmployeeIsNotCreated() {
        EmployeeImpl employee = new EmployeeImpl();
        when(employeeRepository.save(employee)).thenThrow(RuntimeException.class);

        assertThrows(EmployeeNotCreatedException.class, () -> employeeService.addEmployee(employee));
    }
}
