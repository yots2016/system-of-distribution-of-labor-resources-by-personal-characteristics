package com.batuhaniskr.project.service;

import com.batuhaniskr.project.model.Employee;
import com.batuhaniskr.project.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //TODO 30.05.2020 Implement
    public void saveEmployee(Employee employee) {
        employee.setProject(null);
        employeeRepository.save(employee);
    }
}
