package com.distributionsystem.service.impl.projectemployeerolesaving;

import com.distributionsystem.dto.NewProjectDto;
import com.distributionsystem.model.*;
import com.distributionsystem.repository.*;
import com.distributionsystem.service.ProjectEmployeeRoleSavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(4)
@Service
@RequiredArgsConstructor
public class FeProjectEmployeeRoleSavingService implements ProjectEmployeeRoleSavingService {

    private static final String FRONT_END_DEVELOPER = "Front-end developer";

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
            saveFeProjectEmployeeRole(newProjectDto, project);
        }
    }

    private void saveFeProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        ProjectEmployeeRole feProjectEmployeeRole = new ProjectEmployeeRole();
        feProjectEmployeeRole.setEmployeeRole(FRONT_END_DEVELOPER);
        feProjectEmployeeRole.setEmployeesNumber(Long.valueOf(newProjectDto.getFeEmployeeNumber()));
        feProjectEmployeeRole.setProject(project);
        projectEmployeeRoleRepository.save(feProjectEmployeeRole);

        saveFirstFeEmployeeCharacteristics(newProjectDto, feProjectEmployeeRole);
        saveSecondFeEmployeeCharacteristics(newProjectDto, feProjectEmployeeRole);
        saveThirdFeEmployeeCharacteristics(newProjectDto, feProjectEmployeeRole);
        saveFourthFeEmployeeCharacteristics(newProjectDto, feProjectEmployeeRole);
        saveFifthFeEmployeeCharacteristics(newProjectDto, feProjectEmployeeRole);
        saveSixthFeEmployeeCharacteristics(newProjectDto, feProjectEmployeeRole);
        saveSeventhFeEmployeeCharacteristics(newProjectDto, feProjectEmployeeRole);

        project.addProjectEmployeeRole(feProjectEmployeeRole);
        projectRepository.save(project);
    }

    private void saveSecondFeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole feProjectEmployeeRole) {
        if (!newProjectDto.getFeEmployeeSecondPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData secondProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            secondProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeSecondPersonalCharacteristic()));
            secondProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeSecondPersonalWeightingFactor())));
            secondProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(secondProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalData(secondProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }

        if (!newProjectDto.getFeEmployeeSecondProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData secondProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            secondProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeSecondProfessionalCharacteristic()));
            secondProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeSecondProfessionalWeightingFactor())));
            secondProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(secondProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(secondProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }
    }

    private void saveFirstFeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole feProjectEmployeeRole) {
        if (!newProjectDto.getFeEmployeeFirstPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData firstProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            firstProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeFirstPersonalCharacteristic()));
            firstProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeFirstPersonalWeightingFactor())));
            firstProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(firstProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalData(firstProjectEmployeeRolePersonalData);
        }

        if (!newProjectDto.getFeEmployeeFirstProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData firstProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            firstProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeFirstProfessionalCharacteristic()));
            firstProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeFirstProfessionalWeightingFactor())));
            firstProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(firstProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(firstProjectEmployeeRoleProfessionalData);
        }
        projectEmployeeRoleRepository.save(feProjectEmployeeRole);
    }

    private void saveThirdFeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole feProjectEmployeeRole) {
        if (!newProjectDto.getFeEmployeeThirdPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData thirdProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            thirdProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeThirdPersonalCharacteristic()));
            thirdProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeThirdPersonalWeightingFactor())));
            thirdProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(thirdProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalData(thirdProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }

        if (!newProjectDto.getFeEmployeeThirdProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData thirdProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            thirdProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeThirdProfessionalCharacteristic()));
            thirdProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeThirdProfessionalWeightingFactor())));
            thirdProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(thirdProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(thirdProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }
    }

    private void saveFourthFeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole feProjectEmployeeRole) {
        if (!newProjectDto.getFeEmployeeFourthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fourthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fourthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeFourthPersonalCharacteristic()));
            fourthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeFourthPersonalWeightingFactor())));
            fourthProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fourthProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalData(fourthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }

        if (!newProjectDto.getFeEmployeeFourthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fourthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fourthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeFourthProfessionalCharacteristic()));
            fourthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeFourthProfessionalWeightingFactor())));
            fourthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fourthProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(fourthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }
    }

    private void saveFifthFeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole feProjectEmployeeRole) {
        if (!newProjectDto.getFeEmployeeFifthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fifthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fifthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeFifthPersonalCharacteristic()));
            fifthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeFifthPersonalWeightingFactor())));
            fifthProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fifthProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalData(fifthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }

        if (!newProjectDto.getFeEmployeeFifthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fifthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fifthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeFifthProfessionalCharacteristic()));
            fifthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeFifthProfessionalWeightingFactor())));
            fifthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fifthProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(fifthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }
    }

    private void saveSixthFeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole feProjectEmployeeRole) {
        if (!newProjectDto.getFeEmployeeSixthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData sixthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            sixthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeSixthPersonalCharacteristic()));
            sixthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeFirstPersonalWeightingFactor())));
            sixthProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(sixthProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalData(sixthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }

        if (!newProjectDto.getFeEmployeeSixthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData sixthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            sixthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeSixthProfessionalCharacteristic()));
            sixthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeSixthProfessionalWeightingFactor())));
            sixthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(sixthProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(sixthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }
    }

    private void saveSeventhFeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole feProjectEmployeeRole) {
        if (!newProjectDto.getFeEmployeeSeventhPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData seventhProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            seventhProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeSeventhPersonalCharacteristic()));
            seventhProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeSeventhPersonalWeightingFactor())));
            seventhProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(seventhProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalData(seventhProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }

        if (!newProjectDto.getFeEmployeeSeventhProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData seventhProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            seventhProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeSeventhProfessionalCharacteristic()));
            seventhProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getFeEmployeeSeventhProfessionalWeightingFactor())));
            seventhProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(seventhProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(seventhProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }
    }
}
