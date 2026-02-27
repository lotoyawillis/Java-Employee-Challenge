package com.challenge.api.repository;

import com.challenge.api.model.EmployeeImpl;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeImpl, UUID> {}
