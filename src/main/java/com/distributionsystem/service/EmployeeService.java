package com.distributionsystem.service;

import com.distributionsystem.dto.NewEmployeeDto;
import com.distributionsystem.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface EmployeeService {

    Page<Employee> getAllEmployees(Pageable pageable);

    List<Employee> getAllEmployees();

    void saveEmployee(NewEmployeeDto newEmployeeDto);

    void saveEmployee(Employee employee);

    void deleteEmployee(Long id);

    Optional<Employee> getEmployeeById(Long employeeId);
}
