package com.distributionsystem.service;

import com.distributionsystem.dto.NewEmployeeDto;
import com.distributionsystem.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface EmployeeService {

    Page<Employee> getAllEmployees(Pageable pageable);

    List<Employee> getAllEmployees();

    void saveEmployee(NewEmployeeDto newEmployeeDto);

    void deleteEmployee(Long id);
}
