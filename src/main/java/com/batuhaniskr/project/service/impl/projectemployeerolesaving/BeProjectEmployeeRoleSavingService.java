package com.batuhaniskr.project.service.impl.projectemployeerolesaving;

import com.batuhaniskr.project.dto.NewProjectDto;
import com.batuhaniskr.project.model.*;
import com.batuhaniskr.project.repository.*;
import com.batuhaniskr.project.service.ProjectEmployeeRoleSavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(5)
@Service
@RequiredArgsConstructor
public class BeProjectEmployeeRoleSavingService implements ProjectEmployeeRoleSavingService {

    private final ProjectRepository projectRepository;
    private final CommonPersonalDataRepository commonPersonalDataRepository;
    private final CommonProfessionalDataRepository commonProfessionalDataRepository;
    private final WeightingFactorRepository weightingFactorRepository;
    private final ProjectEmployeeRoleRepository projectEmployeeRoleRepository;
    private final ProjectEmployeeRolePersonalDataRepository projectEmployeeRolePersonalDataRepository;
    private final ProjectEmployeeRoleProfessionalDataRepository projectEmployeeRoleProfessionalDataRepository;

    @Override
    public void saveProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        if (newProjectDto.getBeEmployeeNumber() > 0) {
            saveBeProjectEmployeeRole(newProjectDto, project);
        }
    }

    private void saveBeProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        ProjectEmployeeRole beProjectEmployeeRole = new ProjectEmployeeRole();
        beProjectEmployeeRole.setEmployeeRole("BE");
        beProjectEmployeeRole.setEmployeesNumber(Long.valueOf(newProjectDto.getBeEmployeeNumber()));
        beProjectEmployeeRole.setProject(project);
        projectEmployeeRoleRepository.save(beProjectEmployeeRole);

        saveFirstBeEmployeeCharacteristics(newProjectDto, beProjectEmployeeRole);
        saveSecondBeEmployeeCharacteristics(newProjectDto, beProjectEmployeeRole);
        saveThirdBeEmployeeCharacteristics(newProjectDto, beProjectEmployeeRole);
        saveFourthBeEmployeeCharacteristics(newProjectDto, beProjectEmployeeRole);
        saveFifthBeEmployeeCharacteristics(newProjectDto, beProjectEmployeeRole);
        saveSixthBeEmployeeCharacteristics(newProjectDto, beProjectEmployeeRole);
        saveSeventhBeEmployeeCharacteristics(newProjectDto, beProjectEmployeeRole);

        project.addProjectEmployeeRole(beProjectEmployeeRole);
        projectRepository.save(project);
    }

    private void saveSecondBeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole beProjectEmployeeRole) {
        if (!newProjectDto.getBeEmployeeSecondPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData secondProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            secondProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeSecondPersonalCharacteristic()));
            secondProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeSecondPersonalWeightingFactor())));
            secondProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(secondProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(secondProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }

        if (!newProjectDto.getBeEmployeeSecondProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData secondProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            secondProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeSecondProfessionalCharacteristic()));
            secondProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeSecondProfessionalWeightingFactor())));
            secondProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(secondProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(secondProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }
    }

    private void saveFirstBeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole beProjectEmployeeRole) {
        if (!newProjectDto.getBeEmployeeFirstPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData firstProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            firstProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeFirstPersonalCharacteristic()));
            firstProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeFirstPersonalWeightingFactor())));
            firstProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(firstProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(firstProjectEmployeeRolePersonalData);
        }

        if (!newProjectDto.getBeEmployeeFirstProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData firstProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            firstProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeFirstProfessionalCharacteristic()));
            firstProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeFirstProfessionalWeightingFactor())));
            firstProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(firstProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(firstProjectEmployeeRoleProfessionalData);
        }
        projectEmployeeRoleRepository.save(beProjectEmployeeRole);
    }

    private void saveThirdBeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole beProjectEmployeeRole) {
        if (!newProjectDto.getBeEmployeeThirdPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData thirdProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            thirdProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeThirdPersonalCharacteristic()));
            thirdProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeThirdPersonalWeightingFactor())));
            thirdProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(thirdProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(thirdProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }

        if (!newProjectDto.getBeEmployeeThirdProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData thirdProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            thirdProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeThirdProfessionalCharacteristic()));
            thirdProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeThirdProfessionalWeightingFactor())));
            thirdProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(thirdProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(thirdProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }
    }

    private void saveFourthBeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole beProjectEmployeeRole) {
        if (!newProjectDto.getBeEmployeeFourthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fourthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fourthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeFourthPersonalCharacteristic()));
            fourthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeFourthPersonalWeightingFactor())));
            fourthProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fourthProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(fourthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }

        if (!newProjectDto.getBeEmployeeFourthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fourthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fourthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeFourthProfessionalCharacteristic()));
            fourthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeFourthProfessionalWeightingFactor())));
            fourthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fourthProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(fourthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }
    }

    private void saveFifthBeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole beProjectEmployeeRole) {
        if (!newProjectDto.getBeEmployeeFifthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fifthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fifthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeFifthPersonalCharacteristic()));
            fifthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeFifthPersonalWeightingFactor())));
            fifthProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fifthProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(fifthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }

        if (!newProjectDto.getBeEmployeeFifthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fifthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fifthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeFifthProfessionalCharacteristic()));
            fifthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeFifthProfessionalWeightingFactor())));
            fifthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fifthProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(fifthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }
    }

    private void saveSixthBeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole beProjectEmployeeRole) {
        if (!newProjectDto.getBeEmployeeSixthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData sixthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            sixthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeSixthPersonalCharacteristic()));
            sixthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeFirstPersonalWeightingFactor())));
            sixthProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(sixthProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(sixthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }

        if (!newProjectDto.getBeEmployeeSixthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData sixthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            sixthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeSixthProfessionalCharacteristic()));
            sixthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeSixthProfessionalWeightingFactor())));
            sixthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(sixthProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(sixthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }
    }

    private void saveSeventhBeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole beProjectEmployeeRole) {
        if (!newProjectDto.getBeEmployeeSeventhPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData seventhProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            seventhProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeSeventhPersonalCharacteristic()));
            seventhProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeSeventhPersonalWeightingFactor())));
            seventhProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(seventhProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(seventhProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }

        if (!newProjectDto.getBeEmployeeSeventhProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData seventhProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            seventhProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeSeventhProfessionalCharacteristic()));
            seventhProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getBeEmployeeSeventhProfessionalWeightingFactor())));
            seventhProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(seventhProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(seventhProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }
    }
}
