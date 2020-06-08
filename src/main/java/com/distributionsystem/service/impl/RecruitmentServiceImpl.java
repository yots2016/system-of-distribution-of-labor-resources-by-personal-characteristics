package com.distributionsystem.service.impl;

import com.distributionsystem.model.*;
import com.distributionsystem.repository.EmployeeRepository;
import com.distributionsystem.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Map<String, Float> extractAllEmployeeCharacteristics(Employee employee) {
        Map<String, Float> personalCharacteristics = employee.getEmployeePersonalDataSet().stream()
                .map(employeePersonalData -> Pair.of(employeePersonalData.getCommonPersonalData().getDescription(),
                        employeePersonalData.getWeightingFactor().getWeightingFactor()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond, this::mergeEmployeesCharacteristics));
        Map<String, Float> professionalCharacteristics = employee.getEmployeeProfessionalDataSet().stream()
                .map(employeeProfessionalData -> Pair.of(
                        employeeProfessionalData.getCommonProfessionalData().getDescription(),
                        employeeProfessionalData.getWeightingFactor().getWeightingFactor()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond, this::mergeEmployeesCharacteristics));
        return Stream.concat(professionalCharacteristics.entrySet().stream(),
                personalCharacteristics.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<String, Float> extractAllRoleCharacteristics(ProjectEmployeeRole projectEmployeeRole) {
        Map<String, Float> rolePersonalCharacteristics = projectEmployeeRole.getProjectEmployeeRolePersonalDataSet()
                .stream()
                .map(projectEmployeeRolePersonalData -> Pair.of(
                        projectEmployeeRolePersonalData.getCommonPersonalData().getDescription(),
                        projectEmployeeRolePersonalData.getWeightingFactor().getWeightingFactor()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond,
                        this::mergeProjectEmployeeRolesCharacteristics));
        Map<String, Float> roleProfessionalCharacteristics =
                projectEmployeeRole.getProjectEmployeeRoleProfessionalDataSet().stream()
                        .map(data -> Pair.of(data.getCommonProfessionalData().getDescription(),
                                data.getWeightingFactor().getWeightingFactor()))
                        .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond,
                                this::mergeProjectEmployeeRolesCharacteristics));
        return Stream.concat(rolePersonalCharacteristics.entrySet().stream(), roleProfessionalCharacteristics.entrySet()
                .stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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

    private Float mergeEmployeesCharacteristics(Float float1, Float float2) {
        return float1 > float2 ? float1 : float2;
    }

    private Float mergeProjectEmployeeRolesCharacteristics(Float float1, Float float2) {
        return float1 > float2 ? float2 : float1;
    }
}
