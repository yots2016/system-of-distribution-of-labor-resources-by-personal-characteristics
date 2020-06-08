package com.distributionsystem.service.impl.projectemployeerolesaving;

import com.distributionsystem.dto.NewProjectDto;
import com.distributionsystem.model.*;
import com.distributionsystem.repository.*;
import com.distributionsystem.service.ProjectEmployeeRoleSavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(7)
@Service
@RequiredArgsConstructor
public class DbProjectEmployeeRoleSavingService implements ProjectEmployeeRoleSavingService {

    private final ProjectRepository projectRepository;
    private final CommonPersonalDataRepository commonPersonalDataRepository;
    private final CommonProfessionalDataRepository commonProfessionalDataRepository;
    private final WeightingFactorRepository weightingFactorRepository;
    private final ProjectEmployeeRoleRepository projectEmployeeRoleRepository;
    private final ProjectEmployeeRolePersonalDataRepository projectEmployeeRolePersonalDataRepository;
    private final ProjectEmployeeRoleProfessionalDataRepository projectEmployeeRoleProfessionalDataRepository;

    @Override
    public void saveProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        if (newProjectDto.getDbEmployeeNumber() > 0) {
            saveDbProjectEmployeeRole(newProjectDto, project);
        }
    }

    private void saveDbProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        ProjectEmployeeRole dbProjectEmployeeRole = new ProjectEmployeeRole();
        dbProjectEmployeeRole.setEmployeeRole("DB");
        dbProjectEmployeeRole.setEmployeesNumber(Long.valueOf(newProjectDto.getDbEmployeeNumber()));
        dbProjectEmployeeRole.setProject(project);
        projectEmployeeRoleRepository.save(dbProjectEmployeeRole);

        saveFirstDbEmployeeCharacteristics(newProjectDto, dbProjectEmployeeRole);
        saveSecondDbEmployeeCharacteristics(newProjectDto, dbProjectEmployeeRole);
        saveThirdDbEmployeeCharacteristics(newProjectDto, dbProjectEmployeeRole);
        saveFourthDbEmployeeCharacteristics(newProjectDto, dbProjectEmployeeRole);
        saveFifthDbEmployeeCharacteristics(newProjectDto, dbProjectEmployeeRole);
        saveSixthDbEmployeeCharacteristics(newProjectDto, dbProjectEmployeeRole);
        saveSeventhDbEmployeeCharacteristics(newProjectDto, dbProjectEmployeeRole);

        project.addProjectEmployeeRole(dbProjectEmployeeRole);
        projectRepository.save(project);
    }

    private void saveSecondDbEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole dbProjectEmployeeRole) {
        if (!newProjectDto.getDbEmployeeSecondPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData secondProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            secondProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeSecondPersonalCharacteristic()));
            secondProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeSecondPersonalWeightingFactor())));
            secondProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(secondProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalData(secondProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }

        if (!newProjectDto.getDbEmployeeSecondProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData secondProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            secondProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeSecondProfessionalCharacteristic()));
            secondProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeSecondProfessionalWeightingFactor())));
            secondProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(secondProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(secondProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }
    }

    private void saveFirstDbEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole dbProjectEmployeeRole) {
        if (!newProjectDto.getDbEmployeeFirstPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData firstProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            firstProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeFirstPersonalCharacteristic()));
            firstProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeFirstPersonalWeightingFactor())));
            firstProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(firstProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalData(firstProjectEmployeeRolePersonalData);
        }

        if (!newProjectDto.getDbEmployeeFirstProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData firstProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            firstProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeFirstProfessionalCharacteristic()));
            firstProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeFirstProfessionalWeightingFactor())));
            firstProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(firstProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(firstProjectEmployeeRoleProfessionalData);
        }
        projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
    }

    private void saveThirdDbEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole dbProjectEmployeeRole) {
        if (!newProjectDto.getDbEmployeeThirdPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData thirdProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            thirdProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeThirdPersonalCharacteristic()));
            thirdProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeThirdPersonalWeightingFactor())));
            thirdProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(thirdProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalData(thirdProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }

        if (!newProjectDto.getDbEmployeeThirdProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData thirdProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            thirdProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeThirdProfessionalCharacteristic()));
            thirdProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeThirdProfessionalWeightingFactor())));
            thirdProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(thirdProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(thirdProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }
    }

    private void saveFourthDbEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole dbProjectEmployeeRole) {
        if (!newProjectDto.getDbEmployeeFourthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fourthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fourthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeFourthPersonalCharacteristic()));
            fourthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeFourthPersonalWeightingFactor())));
            fourthProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fourthProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalData(fourthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }

        if (!newProjectDto.getDbEmployeeFourthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fourthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fourthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeFourthProfessionalCharacteristic()));
            fourthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeFourthProfessionalWeightingFactor())));
            fourthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fourthProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(fourthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }
    }

    private void saveFifthDbEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole dbProjectEmployeeRole) {
        if (!newProjectDto.getDbEmployeeFifthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fifthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fifthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeFifthPersonalCharacteristic()));
            fifthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeFifthPersonalWeightingFactor())));
            fifthProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fifthProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalData(fifthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }

        if (!newProjectDto.getDbEmployeeFifthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fifthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fifthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeFifthProfessionalCharacteristic()));
            fifthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeFifthProfessionalWeightingFactor())));
            fifthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fifthProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(fifthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }
    }

    private void saveSixthDbEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole dbProjectEmployeeRole) {
        if (!newProjectDto.getDbEmployeeSixthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData sixthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            sixthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeSixthPersonalCharacteristic()));
            sixthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeFirstPersonalWeightingFactor())));
            sixthProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(sixthProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalData(sixthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }

        if (!newProjectDto.getDbEmployeeSixthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData sixthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            sixthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeSixthProfessionalCharacteristic()));
            sixthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeSixthProfessionalWeightingFactor())));
            sixthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(sixthProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(sixthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }
    }

    private void saveSeventhDbEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole dbProjectEmployeeRole) {
        if (!newProjectDto.getDbEmployeeSeventhPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData seventhProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            seventhProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeSeventhPersonalCharacteristic()));
            seventhProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeSeventhPersonalWeightingFactor())));
            seventhProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(seventhProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalData(seventhProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }

        if (!newProjectDto.getDbEmployeeSeventhProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData seventhProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            seventhProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeSeventhProfessionalCharacteristic()));
            seventhProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getDbEmployeeSeventhProfessionalWeightingFactor())));
            seventhProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(seventhProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(seventhProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }
    }
}