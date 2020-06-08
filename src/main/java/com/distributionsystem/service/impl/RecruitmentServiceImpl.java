package com.distributionsystem.service.impl;

import com.distributionsystem.model.*;
import com.distributionsystem.repository.EmployeeRepository;
import com.distributionsystem.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

import static com.distributionsystem.utils.CharacteristicsExtractingUtils.extractAllEmployeeCharacteristics;
import static com.distributionsystem.utils.CharacteristicsExtractingUtils.extractAllRoleCharacteristics;

@Service
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {

    private final EmployeeRepository employeeRepository;

    @Override
    public void selectEmployeesForProject(Project project) {
        Set<ProjectEmployeeRole> projectEmployeeRoleSet = project.getProjectEmployeeRoleSet();
        projectEmployeeRoleSet.stream()
                .filter(projectEmployeeRole -> projectEmployeeRole.getEmployeesNumber() > 0)
                .forEach(projectEmployeeRole -> findCorrespondingEmployees(project, projectEmployeeRole));
    }

    private void findCorrespondingEmployees(Project project, ProjectEmployeeRole projectEmployeeRole) {
        for (int i = 0; i < projectEmployeeRole.getEmployeesNumber(); i++) {
            findCorrespondingEmployee(project, projectEmployeeRole);
        }
    }

    private void findCorrespondingEmployee(Project project, ProjectEmployeeRole projectEmployeeRole) {
        Map<String, Float> roleCharacteristics = extractAllRoleCharacteristics(projectEmployeeRole);

        employeeRepository
                .findAll().stream()
                .filter(employee -> employee.getProject() == null)
                .filter(employee -> checkEmployeeAndRoleMatch(roleCharacteristics, employee))
                .findFirst()
                .ifPresent(employee -> saveEmployee(project, employee, projectEmployeeRole));

    }

    private boolean checkEmployeeAndRoleMatch(Map<String, Float> roleCharacteristics, Employee employee) {
        Map<String, Float> employeeCharacteristics = extractAllEmployeeCharacteristics(employee);
        return checkMatch(employeeCharacteristics, roleCharacteristics);
    }

    private void saveEmployee(Project project, Employee employee, ProjectEmployeeRole projectEmployeeRole) {
        employee.setProjectEmployeeRole(projectEmployeeRole);
        employee.setProject(project);
        employeeRepository.save(employee);
        projectEmployeeRole.setEmployee(employee);
        project.addEmployee(employee);
    }

    private boolean checkMatch(Map<String, Float> employeeCharacteristics, Map<String, Float> roleCharacteristics) {
        return roleCharacteristics.entrySet().stream()
                .allMatch(entry -> {
                    boolean containsKey = employeeCharacteristics.containsKey(entry.getKey());
                    if (containsKey) {
                        Float weightingFactor = employeeCharacteristics.get(entry.getKey());
                        return entry.getValue() <= weightingFactor;
                    } else {
                        return false;
                    }
                });
    }
}
