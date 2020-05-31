package com.batuhaniskr.project.service;

import com.batuhaniskr.project.dto.NewProjectDto;
import com.batuhaniskr.project.model.*;
import com.batuhaniskr.project.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final CategoryRepository categoryRepository;
    private final CommonPersonalDataRepository commonPersonalDataRepository;
    private final CommonProfessionalDataRepository commonProfessionalDataRepository;
    private final WeightingFactorRepository weightingFactorRepository;
    private final ProjectEmployeeRoleRepository projectEmployeeRoleRepository;
    private final ProjectEmployeeRolePersonalDataRepository projectEmployeeRolePersonalDataRepository;
    private final ProjectEmployeeRoleProfessionalDataRepository projectEmployeeRoleProfessionalDataRepository;
    private final EmployeeRepository employeeRepository;

    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public void saveProject(NewProjectDto newProjectDto) {
        Project project = new Project();
        project.setName(newProjectDto.getName());
        project.setPrice(newProjectDto.getPrice());
        project.setQuantity(newProjectDto.getQuantity());
        Category category = categoryRepository.findByCategoryName(newProjectDto.getCategoryName());
        project.setCategory(category);
        projectRepository.save(project);
        category.addProject(project);

        ProjectEmployeeRole pmProjectEmployeeRole = new ProjectEmployeeRole();
        pmProjectEmployeeRole.setEmployeeRole("PM");
        pmProjectEmployeeRole.setEmployeesNumber(Long.valueOf(newProjectDto.getPmEmployeeNumber()));
        pmProjectEmployeeRole.setProject(project);
        projectEmployeeRoleRepository.save(pmProjectEmployeeRole);

        ProjectEmployeeRolePersonalData firstProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        firstProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository.findByDescription(newProjectDto.getPmEmployeeFirstPersonalCharacteristic()));
        firstProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository.findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeFirstPersonalWeightingFactor())));
        firstProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(firstProjectEmployeeRolePersonalData);
        pmProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(firstProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(pmProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData firstProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        firstProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository.findByDescription(newProjectDto.getPmEmployeeFirstProfessionalCharacteristic()));
        firstProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository.findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeFirstProfessionalWeightingFactor())));
        firstProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(firstProjectEmployeeRoleProfessionalData);
        pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(firstProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(pmProjectEmployeeRole);

//        ProjectEmployeeRolePersonalData secondProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
//        secondProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository.findByDescription(newProjectDto.getPmEmployeeSecondPersonalCharacteristic()));
//        secondProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository.findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeSecondPersonalWeightingFactor())));
//        secondProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
//        projectEmployeeRolePersonalDataRepository.save(secondProjectEmployeeRolePersonalData);

        project.addProjectEmployeeRole(pmProjectEmployeeRole);


        Set<ProjectEmployeeRole> projectEmployeeRoleSet = project.getProjectEmployeeRoleSet();
        projectEmployeeRoleSet.stream()
                .filter(projectEmployeeRole -> projectEmployeeRole != null)
                .filter(projectEmployeeRole -> projectEmployeeRole.getEmployeesNumber() > 0)
                .forEach(projectEmployeeRole -> {
                    for (int i = 0; i < projectEmployeeRole.getEmployeesNumber(); i++) {
                        findCorrespondingEmployee(project, projectEmployeeRole);
                    }
                });


        List<Project> all = projectRepository.findAll();
    }

    private void findCorrespondingEmployee(Project project, ProjectEmployeeRole projectEmployeeRole) {
        System.out.println();
        Map<String, Short> personal = projectEmployeeRole.getProjectEmployeeRolePersonalDataSet().stream()
                .map(projectEmployeeRolePersonalData -> Pair.of(projectEmployeeRolePersonalData.getCommonPersonalData().getDescription(), projectEmployeeRolePersonalData.getWeightingFactor().getWeightingFactor()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
        Map<String, Short> professional = projectEmployeeRole.getProjectEmployeeRoleProfessionalDataSet().stream()
                .map(data -> Pair.of(data.getCommonProfessionalData().getDescription(), data.getWeightingFactor().getWeightingFactor()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
        Map<String, Short> characteristics = Stream.concat(personal.entrySet().stream(), professional.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        employeeRepository
                .findAll().stream()
                .filter(employee -> {
                    Map<String, Short> personalCharacteristics = employee.getEmployeePersonalDataSet().stream()
                            .map(employeePersonalData -> Pair.of(employeePersonalData.getCommonPersonalData().getDescription(), employeePersonalData.getWeightingFactor().getWeightingFactor()))
                            .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
                    Map<String, Short> professionalCharacteristics = employee.getEmployeeProfessionalDataSet().stream()
                            .map(employeeProfessionalData -> Pair.of(employeeProfessionalData.getCommonProfessionalData().getDescription(), employeeProfessionalData.getWeightingFactor().getWeightingFactor()))
                            .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
                    Map<String, Short> emplCharact = Stream.concat(professionalCharacteristics.entrySet().stream(), personalCharacteristics.entrySet().stream())
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    return checkMatch(emplCharact, characteristics);
                })
                .findFirst()
//                .filter(employee -> employee.getProject() == null)
                .ifPresent(employee -> {
                    employee.setProject(project);
                    employeeRepository.save(employee);
                    project.addEmployee(employee);
                });

    }

    private boolean checkMatch(Map<String, Short> emplCharact, Map<String, Short> characteristics) {
        boolean allMatch = emplCharact.entrySet().stream()
                .allMatch(entry -> {
                    boolean containsKey = characteristics.containsKey(entry.getKey());
                    if (containsKey) {
                        Short weightingFactor = characteristics.get(entry.getKey());
                        return entry.getValue() >= weightingFactor;
                    } else {
                        return false;
                    }
                });
        return allMatch;
    }

    public void deleteProject(Long id) {
        projectRepository.delete(id);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findOne(id);
    }
}
