package com.distributionsystem.service.impl;

import com.distributionsystem.model.Employee;
import com.distributionsystem.model.Project;
import com.distributionsystem.repository.ProjectRepository;
import com.distributionsystem.service.QuantityCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.distributionsystem.utils.CharacteristicsExtractingUtils.extractAllEmployeeCharacteristics;

@RequiredArgsConstructor
@Service
public class QuantityCalculationServiceImpl implements QuantityCalculationService {

    private final ProjectRepository projectRepository;

    @Override
    public double calculateProjectQuantity(Project project) {
        Set<Employee> employees = project.getEmployeesSet();
        return employees.stream()
                .flatMap(employee -> extractAllEmployeeCharacteristics(employee).values().stream())
                .mapToDouble(value -> value)
                .average()
                .orElse(0);
    }
}
