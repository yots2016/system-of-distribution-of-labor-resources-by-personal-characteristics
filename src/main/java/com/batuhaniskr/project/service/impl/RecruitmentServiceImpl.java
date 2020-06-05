package com.batuhaniskr.project.service.impl;

import com.batuhaniskr.project.model.*;
import com.batuhaniskr.project.repository.EmployeeRepository;
import com.batuhaniskr.project.service.RecruitmentService;
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
        Map<String, Short> roleCharacteristics = extractAllRoleCharacteristics(projectEmployeeRole);

        employeeRepository
                .findAll().stream()
                .filter(employee -> employee.getProject() == null)
                .filter(employee -> checkEmployeeAndRoleMatch(roleCharacteristics, employee))
                .findFirst()
                .ifPresent(employee -> saveEmployee(project, employee, projectEmployeeRole));

    }

    private boolean checkEmployeeAndRoleMatch(Map<String, Short> roleCharacteristics, Employee employee) {
        Map<String, Short> employeeCharacteristics = extractAllEmployeeCharacteristics(employee);
        return checkMatch(employeeCharacteristics, roleCharacteristics);
    }

    private void saveEmployee(Project project, Employee employee, ProjectEmployeeRole projectEmployeeRole) {
        employee.setProjectEmployeeRole(projectEmployeeRole);
        employee.setProject(project);
        employeeRepository.save(employee);
        projectEmployeeRole.setEmployee(employee);
        project.addEmployee(employee);
    }

    private Map<String, Short> extractAllEmployeeCharacteristics(Employee employee) {
        Map<String, Short> personalCharacteristics = employee.getEmployeePersonalDataSet().stream()
                .map(employeePersonalData -> Pair.of(employeePersonalData.getCommonPersonalData().getDescription(),
                        employeePersonalData.getWeightingFactor().getWeightingFactor()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond, this::mergeEmployees));
        Map<String, Short> professionalCharacteristics = employee.getEmployeeProfessionalDataSet().stream()
                .map(employeeProfessionalData -> Pair.of(employeeProfessionalData.getCommonProfessionalData().getDescription(),
                        employeeProfessionalData.getWeightingFactor().getWeightingFactor()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond, this::mergeEmployees));
        return Stream.concat(professionalCharacteristics.entrySet().stream(),
                personalCharacteristics.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<String, Short> extractAllRoleCharacteristics(ProjectEmployeeRole projectEmployeeRole) {
        Map<String, Short> rolePersonalCharacteristics = projectEmployeeRole.getProjectEmployeeRolePersonalDataSet().stream()
                .map(projectEmployeeRolePersonalData -> Pair.of(projectEmployeeRolePersonalData.getCommonPersonalData().getDescription(),
                        projectEmployeeRolePersonalData.getWeightingFactor().getWeightingFactor()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond, this::mergeProjectEmployeeRoles));
        Map<String, Short> roleProfessionalCharacteristics = projectEmployeeRole.getProjectEmployeeRoleProfessionalDataSet().stream()
                .map(data -> Pair.of(data.getCommonProfessionalData().getDescription(), data.getWeightingFactor().getWeightingFactor()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond, this::mergeProjectEmployeeRoles));
        return Stream.concat(rolePersonalCharacteristics.entrySet().stream(), roleProfessionalCharacteristics.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private boolean checkMatch(Map<String, Short> employeeCharacteristics, Map<String, Short> roleCharacteristics) {
        return roleCharacteristics.entrySet().stream()
                .allMatch(entry -> {
                    boolean containsKey = employeeCharacteristics.containsKey(entry.getKey());
                    if (containsKey) {
                        Short weightingFactor = employeeCharacteristics.get(entry.getKey());
                        return entry.getValue() <= weightingFactor;
                    } else {
                        return false;
                    }
                });
    }

    private Short mergeEmployees(Short short1, Short short2) {
        return short1 > short2 ? short1 : short2;
    }

    private Short mergeProjectEmployeeRoles(Short short1, Short short2) {
        return short1 > short2 ? short2 : short1;
    }
}
