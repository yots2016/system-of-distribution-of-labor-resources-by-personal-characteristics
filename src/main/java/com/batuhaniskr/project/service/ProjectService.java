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

        if (newProjectDto.getPmEmployeeNumber() > 0) {
            savePmProjectEmployeeRole(newProjectDto, project);
        }
        if (newProjectDto.getBaEmployeeNumber() > 0) {
            saveBaProjectEmployeeRole(newProjectDto, project);
        }
        //TODO 01.06.2020 Implement
        if (newProjectDto.getSaEmployeeNumber() > 0) {
            saveSaProjectEmployeeRole(newProjectDto, project);
        }
        //TODO 01.06.2020 Implement
        if (newProjectDto.getFeEmployeeNumber() > 0) {
            saveFeProjectEmployeeRole(newProjectDto, project);
        }
        //TODO 01.06.2020 Implement
        if (newProjectDto.getBeEmployeeNumber() > 0) {
            saveBeProjectEmployeeRole(newProjectDto, project);
        }
        //TODO 01.06.2020 Implement
        if (newProjectDto.getFsEmployeeNumber() > 0) {
            saveFsProjectEmployeeRole(newProjectDto, project);
        }
        //TODO 01.06.2020 Implement
        if (newProjectDto.getInfEmployeeNumber() > 0) {
            saveDbProjectEmployeeRole(newProjectDto, project);
        }
        //TODO 01.06.2020 Implement
        saveInfProjectEmployeeRole(newProjectDto, project);


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

    private void saveInfProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {

    }

    private void saveDbProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {

    }

    private void saveFsProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {

    }

    private void saveBeProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {

    }

    private void saveFeProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {

    }

    private void saveSaProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {

    }

    private void saveBaProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        ProjectEmployeeRole baProjectEmployeeRole = new ProjectEmployeeRole();
        baProjectEmployeeRole.setEmployeeRole("BA");
        baProjectEmployeeRole.setEmployeesNumber(Long.valueOf(newProjectDto.getBaEmployeeNumber()));
        baProjectEmployeeRole.setProject(project);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        saveFirstBaEmployeeCharacterisctics(newProjectDto, baProjectEmployeeRole);
        saveSecondBaEmployeeCharacterisctics(newProjectDto, baProjectEmployeeRole);
        saveThirdBaEmployeeCharacterisctics(newProjectDto, baProjectEmployeeRole);
        saveFourthBaEmployeeCharacterisctics(newProjectDto, baProjectEmployeeRole);
        saveFifthBaEmployeeCharacterisctics(newProjectDto, baProjectEmployeeRole);
        saveSixthBaEmployeeCharacterisctics(newProjectDto, baProjectEmployeeRole);
        saveSeventhBaEmployeeCharacterisctics(newProjectDto, baProjectEmployeeRole);

        project.addProjectEmployeeRole(baProjectEmployeeRole);
        projectRepository.save(project);
    }

    private void saveSeventhBaEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData seventhProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        seventhProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeSeventhPersonalCharacteristic()));
        seventhProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeSeventhPersonalWeightingFactor())));
        seventhProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(seventhProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(seventhProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData seventhProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        seventhProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeSeventhProfessionalCharacteristic()));
        seventhProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeSeventhProfessionalWeightingFactor())));
        seventhProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(seventhProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(seventhProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }

    private void saveSixthBaEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData sixthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        sixthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeSixthPersonalCharacteristic()));
        sixthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeSixthPersonalWeightingFactor())));
        sixthProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(sixthProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(sixthProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData sixthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        sixthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeSixthProfessionalCharacteristic()));
        sixthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeSixthProfessionalWeightingFactor())));
        sixthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(sixthProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(sixthProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }

    private void saveFifthBaEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData fifthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        fifthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeFifthPersonalCharacteristic()));
        fifthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeFifthPersonalWeightingFactor())));
        fifthProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(fifthProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(fifthProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData fifthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        fifthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeFifthProfessionalCharacteristic()));
        fifthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeFifthProfessionalWeightingFactor())));
        fifthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(fifthProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(fifthProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }

    private void saveFourthBaEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData fourthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        fourthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeFourthPersonalCharacteristic()));
        fourthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeFirstPersonalWeightingFactor())));
        fourthProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(fourthProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(fourthProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData fourthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        fourthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeFirstProfessionalCharacteristic()));
        fourthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeFirstProfessionalWeightingFactor())));
        fourthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(fourthProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(fourthProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }

    private void saveThirdBaEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData thirdProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        thirdProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeThirdPersonalCharacteristic()));
        thirdProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeThirdPersonalWeightingFactor())));
        thirdProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(thirdProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(thirdProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData thirdProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        thirdProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeThirdProfessionalCharacteristic()));
        thirdProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeThirdProfessionalWeightingFactor())));
        thirdProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(thirdProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(thirdProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }

    private void saveSecondBaEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData secondProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        secondProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeSecondPersonalCharacteristic()));
        secondProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeSecondPersonalWeightingFactor())));
        secondProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(secondProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(secondProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData secondProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        secondProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeSecondProfessionalCharacteristic()));
        secondProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeSecondProfessionalWeightingFactor())));
        secondProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(secondProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(secondProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }

    private void saveFirstBaEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData firstProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        firstProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeFirstPersonalCharacteristic()));
        firstProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeFirstPersonalWeightingFactor())));
        firstProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(firstProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(firstProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData firstProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        firstProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeFirstProfessionalCharacteristic()));
        firstProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeFirstProfessionalWeightingFactor())));
        firstProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(firstProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(firstProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }

    private void savePmProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        ProjectEmployeeRole pmProjectEmployeeRole = new ProjectEmployeeRole();
        pmProjectEmployeeRole.setEmployeeRole("PM");
        pmProjectEmployeeRole.setEmployeesNumber(Long.valueOf(newProjectDto.getPmEmployeeNumber()));
        pmProjectEmployeeRole.setProject(project);
        projectEmployeeRoleRepository.save(pmProjectEmployeeRole);

        saveFirstPmEmployeeCharacterisctics(newProjectDto, pmProjectEmployeeRole);
        saveSecondPmEmployeeCharacterisctics(newProjectDto, pmProjectEmployeeRole);
        saveThirdPmEmployeeCharacterisctics(newProjectDto, pmProjectEmployeeRole);
        saveFourthPmEmployeeCharacterisctics(newProjectDto, pmProjectEmployeeRole);
        saveFifthPmEmployeeCharacterisctics(newProjectDto, pmProjectEmployeeRole);
        saveSixthPmEmployeeCharacterisctics(newProjectDto, pmProjectEmployeeRole);
        saveSeventhPmEmployeeCharacterisctics(newProjectDto, pmProjectEmployeeRole);

        project.addProjectEmployeeRole(pmProjectEmployeeRole);
        projectRepository.save(project);
    }

    private void saveSeventhPmEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeSeventhPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData seventhProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            seventhProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeSeventhPersonalCharacteristic()));
            seventhProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeSeventhPersonalWeightingFactor())));
            seventhProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(seventhProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(seventhProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }

        if (!newProjectDto.getPmEmployeeSeventhProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData seventhProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            seventhProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeSeventhProfessionalCharacteristic()));
            seventhProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeSeventhProfessionalWeightingFactor())));
            seventhProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(seventhProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(seventhProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }
    }

    private void saveSixthPmEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeSixthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData sixthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            sixthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeSixthPersonalCharacteristic()));
            sixthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeFirstPersonalWeightingFactor())));
            sixthProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(sixthProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(sixthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }

        if (!newProjectDto.getPmEmployeeSixthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData sixthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            sixthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeSixthProfessionalCharacteristic()));
            sixthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeSixthProfessionalWeightingFactor())));
            sixthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(sixthProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(sixthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }
    }

    private void saveFifthPmEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeFifthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fifthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fifthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeFifthPersonalCharacteristic()));
            fifthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeFifthPersonalWeightingFactor())));
            fifthProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fifthProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(fifthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }

        if (!newProjectDto.getPmEmployeeFifthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fifthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fifthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeFifthProfessionalCharacteristic()));
            fifthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeFifthProfessionalWeightingFactor())));
            fifthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fifthProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(fifthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }
    }

    private void saveFourthPmEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeFourthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fourthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fourthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeFourthPersonalCharacteristic()));
            fourthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeFourthPersonalWeightingFactor())));
            fourthProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fourthProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(fourthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }

        if (!newProjectDto.getPmEmployeeFourthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fourthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fourthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeFourthProfessionalCharacteristic()));
            fourthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeFourthProfessionalWeightingFactor())));
            fourthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fourthProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(fourthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }
    }

    private void saveThirdPmEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeThirdPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData thirdProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            thirdProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeThirdPersonalCharacteristic()));
            thirdProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeThirdPersonalWeightingFactor())));
            thirdProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(thirdProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(thirdProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }

        if (!newProjectDto.getPmEmployeeThirdProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData thirdProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            thirdProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeThirdProfessionalCharacteristic()));
            thirdProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeThirdProfessionalWeightingFactor())));
            thirdProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(thirdProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(thirdProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }
    }

    private void saveSecondPmEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeSecondPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData secondProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            secondProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeSecondPersonalCharacteristic()));
            secondProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeSecondPersonalWeightingFactor())));
            secondProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(secondProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(secondProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }

        if (!newProjectDto.getPmEmployeeSecondProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData secondProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            secondProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeSecondProfessionalCharacteristic()));
            secondProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeSecondProfessionalWeightingFactor())));
            secondProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(secondProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(secondProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }
    }

    private void saveFirstPmEmployeeCharacterisctics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeFirstPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData firstProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            firstProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeFirstPersonalCharacteristic()));
            firstProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeFirstPersonalWeightingFactor())));
            firstProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(firstProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(firstProjectEmployeeRolePersonalData);
        }

        if (!newProjectDto.getPmEmployeeFirstProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData firstProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            firstProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeFirstProfessionalCharacteristic()));
            firstProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getPmEmployeeFirstProfessionalWeightingFactor())));
            firstProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(firstProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(firstProjectEmployeeRoleProfessionalData);
        }
        projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
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
