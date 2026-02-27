package com.challenge.api;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.challenge.api.controller.EmployeeController;
import com.challenge.api.model.EmployeeImpl;
import com.challenge.api.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void shouldReturnAllEmployeesWhenMinimumOneEmployeeExists() throws Exception {
        UUID uuid = UUID.randomUUID();

        EmployeeImpl employee1 = new EmployeeImpl();
        employee1.setUuid(uuid);
        employee1.setFirstName("Leon");
        employee1.setLastName("Kennedy");
        employee1.setFullName("Leon Kennedy");
        employee1.setSalary(40000);
        employee1.setAge(48);
        employee1.setJobTitle("Project Manager");
        employee1.setEmail("leon@example.com");
        employee1.setContractHireDate(Instant.now());
        employee1.setContractTerminationDate(null);

        List<EmployeeImpl> employees = new ArrayList<>();
        employees.add(employee1);

        when(employeeService.findAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/api/v1/employee"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].firstName").value(hasItem("Leon")));
    }

    @Test
    void shouldReturnAllEmployeesWhenMultipleEmployeesExist() throws Exception {
        UUID uuid = UUID.randomUUID();

        EmployeeImpl employee1 = new EmployeeImpl();
        employee1.setUuid(uuid);
        employee1.setFirstName("Leon");
        employee1.setLastName("Kennedy");
        employee1.setFullName("Leon Kennedy");
        employee1.setSalary(40000);
        employee1.setAge(48);
        employee1.setJobTitle("Project Manager");
        employee1.setEmail("leon@example.com");
        employee1.setContractHireDate(Instant.now());
        employee1.setContractTerminationDate(null);

        uuid = UUID.randomUUID();

        EmployeeImpl employee2 = new EmployeeImpl();
        employee2.setUuid(uuid);
        employee2.setFirstName("Ada");
        employee2.setLastName("Wong");
        employee2.setFullName("Ada Wong");
        employee2.setSalary(50000);
        employee2.setAge(32);
        employee2.setJobTitle("Software Engineer");
        employee2.setEmail("ada@example.com");
        employee2.setContractHireDate(Instant.now());
        employee2.setContractTerminationDate(null);

        List<EmployeeImpl> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        when(employeeService.findAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/api/v1/employee"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].firstName").value(hasItem("Leon")))
                .andExpect(jsonPath("$[*].firstName").value(hasItem("Ada")));
    }

    @Test
    void shouldReturnEmployeeWhenValidUuidIsGiven() throws Exception {
        UUID uuid = UUID.randomUUID();

        EmployeeImpl employee = new EmployeeImpl();
        employee.setUuid(uuid);
        employee.setFirstName("Leon");
        employee.setLastName("Kennedy");
        employee.setFullName("Leon Kennedy");
        employee.setSalary(40000);
        employee.setAge(48);
        employee.setJobTitle("Project Manager");
        employee.setEmail("leon@example.com");
        employee.setContractHireDate(Instant.now());
        employee.setContractTerminationDate(null);

        when(employeeService.findEmployeeByUUID(uuid)).thenReturn(employee);

        mockMvc.perform(get("/api/v1/employee/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Leon"))
                .andExpect(jsonPath("$.lastName").value("Kennedy"))
                .andExpect(jsonPath("$.fullName").value("Leon Kennedy"))
                .andExpect(jsonPath("$.jobTitle").value("Project Manager"))
                .andExpect(jsonPath("$.salary").value(40000))
                .andExpect(jsonPath("$.age").value(48))
                .andExpect(jsonPath("$.email").value("leon@example.com"));
    }

    @Test
    void shouldReturnEmployeeWhenCreateSucceeds() throws Exception {
        UUID uuid = UUID.randomUUID();

        EmployeeImpl employee = new EmployeeImpl();
        employee.setUuid(uuid);
        employee.setFirstName("Leon");
        employee.setLastName("Kennedy");
        employee.setFullName("Leon Kennedy");
        employee.setSalary(40000);
        employee.setAge(48);
        employee.setJobTitle("Project Manager");
        employee.setEmail("leon@example.com");
        employee.setContractHireDate(Instant.now());
        employee.setContractTerminationDate(null);

        when(employeeService.addEmployee(employee)).thenReturn(employee);

        String employeeJson = objectMapper.writeValueAsString(employee);

        mockMvc.perform(post("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Leon"))
                .andExpect(jsonPath("$.lastName").value("Kennedy"))
                .andExpect(jsonPath("$.fullName").value("Leon Kennedy"))
                .andExpect(jsonPath("$.jobTitle").value("Project Manager"))
                .andExpect(jsonPath("$.salary").value(40000))
                .andExpect(jsonPath("$.age").value(48))
                .andExpect(jsonPath("$.email").value("leon@example.com"));
    }
}
