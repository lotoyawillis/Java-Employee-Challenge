package com.challenge.api.repository;

import com.challenge.api.model.CompanyEmployee;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<CompanyEmployee, UUID> {}
