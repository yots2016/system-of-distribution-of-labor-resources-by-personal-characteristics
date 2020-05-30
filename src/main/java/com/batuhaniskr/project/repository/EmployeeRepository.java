package com.batuhaniskr.project.repository;

import com.batuhaniskr.project.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
