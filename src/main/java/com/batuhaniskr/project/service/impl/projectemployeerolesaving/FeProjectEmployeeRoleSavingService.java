package com.batuhaniskr.project.service.impl.projectemployeerolesaving;

import com.batuhaniskr.project.dto.NewProjectDto;
import com.batuhaniskr.project.model.*;
import com.batuhaniskr.project.repository.*;
import com.batuhaniskr.project.service.ProjectEmployeeRoleSavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(4)
@Service
@RequiredArgsConstructor
public class FeProjectEmployeeRoleSavingService implements ProjectEmployeeRoleSavingService {

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
        feProjectEmployeeRole.setEmployeeRole("FE");
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
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeSecondPersonalWeightingFactor())));
            secondProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(secondProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(secondProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }

        if (!newProjectDto.getFeEmployeeSecondProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData secondProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            secondProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeSecondProfessionalCharacteristic()));
            secondProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeSecondProfessionalWeightingFactor())));
            secondProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(secondProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(secondProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }
    }

    private void saveFirstFeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole feProjectEmployeeRole) {
        if (!newProjectDto.getFeEmployeeFirstPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData firstProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            firstProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeFirstPersonalCharacteristic()));
            firstProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeFirstPersonalWeightingFactor())));
            firstProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(firstProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(firstProjectEmployeeRolePersonalData);
        }

        if (!newProjectDto.getFeEmployeeFirstProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData firstProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            firstProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeFirstProfessionalCharacteristic()));
            firstProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeFirstProfessionalWeightingFactor())));
            firstProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(firstProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(firstProjectEmployeeRoleProfessionalData);
        }
        projectEmployeeRoleRepository.save(feProjectEmployeeRole);
    }

    private void saveThirdFeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole feProjectEmployeeRole) {
        if (!newProjectDto.getFeEmployeeThirdPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData thirdProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            thirdProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeThirdPersonalCharacteristic()));
            thirdProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeThirdPersonalWeightingFactor())));
            thirdProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(thirdProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(thirdProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }

        if (!newProjectDto.getFeEmployeeThirdProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData thirdProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            thirdProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeThirdProfessionalCharacteristic()));
            thirdProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeThirdProfessionalWeightingFactor())));
            thirdProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(thirdProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(thirdProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }
    }

    private void saveFourthFeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole feProjectEmployeeRole) {
        if (!newProjectDto.getFeEmployeeFourthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fourthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fourthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeFourthPersonalCharacteristic()));
            fourthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeFourthPersonalWeightingFactor())));
            fourthProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fourthProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(fourthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }

        if (!newProjectDto.getFeEmployeeFourthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fourthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fourthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeFourthProfessionalCharacteristic()));
            fourthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeFourthProfessionalWeightingFactor())));
            fourthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fourthProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(fourthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }
    }

    private void saveFifthFeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole feProjectEmployeeRole) {
        if (!newProjectDto.getFeEmployeeFifthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fifthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fifthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeFifthPersonalCharacteristic()));
            fifthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeFifthPersonalWeightingFactor())));
            fifthProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fifthProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(fifthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }

        if (!newProjectDto.getFeEmployeeFifthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fifthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fifthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeFifthProfessionalCharacteristic()));
            fifthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeFifthProfessionalWeightingFactor())));
            fifthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fifthProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(fifthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }
    }

    private void saveSixthFeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole feProjectEmployeeRole) {
        if (!newProjectDto.getFeEmployeeSixthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData sixthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            sixthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeSixthPersonalCharacteristic()));
            sixthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeFirstPersonalWeightingFactor())));
            sixthProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(sixthProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(sixthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }

        if (!newProjectDto.getFeEmployeeSixthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData sixthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            sixthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeSixthProfessionalCharacteristic()));
            sixthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeSixthProfessionalWeightingFactor())));
            sixthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(sixthProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(sixthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }
    }

    private void saveSeventhFeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole feProjectEmployeeRole) {
        if (!newProjectDto.getFeEmployeeSeventhPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData seventhProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            seventhProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeSeventhPersonalCharacteristic()));
            seventhProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeSeventhPersonalWeightingFactor())));
            seventhProjectEmployeeRolePersonalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(seventhProjectEmployeeRolePersonalData);
            feProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(seventhProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }

        if (!newProjectDto.getFeEmployeeSeventhProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData seventhProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            seventhProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFeEmployeeSeventhProfessionalCharacteristic()));
            seventhProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFeEmployeeSeventhProfessionalWeightingFactor())));
            seventhProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(feProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(seventhProjectEmployeeRoleProfessionalData);
            feProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(seventhProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(feProjectEmployeeRole);
        }
    }
}
