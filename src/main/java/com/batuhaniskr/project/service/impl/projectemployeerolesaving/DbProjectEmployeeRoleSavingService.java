package com.batuhaniskr.project.service.impl.projectemployeerolesaving;

import com.batuhaniskr.project.dto.NewProjectDto;
import com.batuhaniskr.project.model.*;
import com.batuhaniskr.project.repository.*;
import com.batuhaniskr.project.service.ProjectEmployeeRoleSavingService;
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
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeSecondPersonalWeightingFactor())));
            secondProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(secondProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(secondProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }

        if (!newProjectDto.getDbEmployeeSecondProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData secondProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            secondProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeSecondProfessionalCharacteristic()));
            secondProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeSecondProfessionalWeightingFactor())));
            secondProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(secondProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(secondProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }
    }

    private void saveFirstDbEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole dbProjectEmployeeRole) {
        if (!newProjectDto.getDbEmployeeFirstPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData firstProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            firstProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeFirstPersonalCharacteristic()));
            firstProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeFirstPersonalWeightingFactor())));
            firstProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(firstProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(firstProjectEmployeeRolePersonalData);
        }

        if (!newProjectDto.getDbEmployeeFirstProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData firstProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            firstProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeFirstProfessionalCharacteristic()));
            firstProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeFirstProfessionalWeightingFactor())));
            firstProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(firstProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(firstProjectEmployeeRoleProfessionalData);
        }
        projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
    }

    private void saveThirdDbEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole dbProjectEmployeeRole) {
        if (!newProjectDto.getDbEmployeeThirdPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData thirdProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            thirdProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeThirdPersonalCharacteristic()));
            thirdProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeThirdPersonalWeightingFactor())));
            thirdProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(thirdProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(thirdProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }

        if (!newProjectDto.getDbEmployeeThirdProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData thirdProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            thirdProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeThirdProfessionalCharacteristic()));
            thirdProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeThirdProfessionalWeightingFactor())));
            thirdProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(thirdProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(thirdProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }
    }

    private void saveFourthDbEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole dbProjectEmployeeRole) {
        if (!newProjectDto.getDbEmployeeFourthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fourthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fourthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeFourthPersonalCharacteristic()));
            fourthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeFourthPersonalWeightingFactor())));
            fourthProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fourthProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(fourthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }

        if (!newProjectDto.getDbEmployeeFourthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fourthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fourthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeFourthProfessionalCharacteristic()));
            fourthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeFourthProfessionalWeightingFactor())));
            fourthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fourthProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(fourthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }
    }

    private void saveFifthDbEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole dbProjectEmployeeRole) {
        if (!newProjectDto.getDbEmployeeFifthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fifthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fifthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeFifthPersonalCharacteristic()));
            fifthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeFifthPersonalWeightingFactor())));
            fifthProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fifthProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(fifthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }

        if (!newProjectDto.getDbEmployeeFifthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fifthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fifthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeFifthProfessionalCharacteristic()));
            fifthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeFifthProfessionalWeightingFactor())));
            fifthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fifthProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(fifthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }
    }

    private void saveSixthDbEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole dbProjectEmployeeRole) {
        if (!newProjectDto.getDbEmployeeSixthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData sixthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            sixthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeSixthPersonalCharacteristic()));
            sixthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeFirstPersonalWeightingFactor())));
            sixthProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(sixthProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(sixthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }

        if (!newProjectDto.getDbEmployeeSixthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData sixthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            sixthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeSixthProfessionalCharacteristic()));
            sixthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeSixthProfessionalWeightingFactor())));
            sixthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(sixthProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(sixthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }
    }

    private void saveSeventhDbEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole dbProjectEmployeeRole) {
        if (!newProjectDto.getDbEmployeeSeventhPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData seventhProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            seventhProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeSeventhPersonalCharacteristic()));
            seventhProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeSeventhPersonalWeightingFactor())));
            seventhProjectEmployeeRolePersonalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(seventhProjectEmployeeRolePersonalData);
            dbProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(seventhProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }

        if (!newProjectDto.getDbEmployeeSeventhProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData seventhProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            seventhProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getDbEmployeeSeventhProfessionalCharacteristic()));
            seventhProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getDbEmployeeSeventhProfessionalWeightingFactor())));
            seventhProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(dbProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(seventhProjectEmployeeRoleProfessionalData);
            dbProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(seventhProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(dbProjectEmployeeRole);
        }
    }
}