package com.batuhaniskr.project.service.impl.projectemployeerolesaving;

import com.batuhaniskr.project.dto.NewProjectDto;
import com.batuhaniskr.project.model.*;
import com.batuhaniskr.project.repository.*;
import com.batuhaniskr.project.service.ProjectEmployeeRoleSavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(2)
@Service
@RequiredArgsConstructor
public class BaProjectEmployeeRoleSavingService implements ProjectEmployeeRoleSavingService {

    private final ProjectRepository projectRepository;
    private final CommonPersonalDataRepository commonPersonalDataRepository;
    private final CommonProfessionalDataRepository commonProfessionalDataRepository;
    private final WeightingFactorRepository weightingFactorRepository;
    private final ProjectEmployeeRoleRepository projectEmployeeRoleRepository;
    private final ProjectEmployeeRolePersonalDataRepository projectEmployeeRolePersonalDataRepository;
    private final ProjectEmployeeRoleProfessionalDataRepository projectEmployeeRoleProfessionalDataRepository;

    @Override
    public void saveProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        if (newProjectDto.getBaEmployeeNumber() > 0) {
            saveBaProjectEmployeeRole(newProjectDto, project);
        }
    }

    private void saveBaProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        ProjectEmployeeRole baProjectEmployeeRole = new ProjectEmployeeRole();
        baProjectEmployeeRole.setEmployeeRole("BA");
        baProjectEmployeeRole.setEmployeesNumber(Long.valueOf(newProjectDto.getBaEmployeeNumber()));
        baProjectEmployeeRole.setProject(project);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        saveFirstBaEmployeeCharacteristics(newProjectDto, baProjectEmployeeRole);
        saveSecondBaEmployeeCharacteristics(newProjectDto, baProjectEmployeeRole);
        saveThirdBaEmployeeCharacteristics(newProjectDto, baProjectEmployeeRole);
        saveFourthBaEmployeeCharacteristics(newProjectDto, baProjectEmployeeRole);
        saveFifthBaEmployeeCharacteristics(newProjectDto, baProjectEmployeeRole);
        saveSixthBaEmployeeCharacteristics(newProjectDto, baProjectEmployeeRole);
        saveSeventhBaEmployeeCharacteristics(newProjectDto, baProjectEmployeeRole);

        project.addProjectEmployeeRole(baProjectEmployeeRole);
        projectRepository.save(project);
    }

    private void saveFirstBaEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData firstProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        firstProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeFirstPersonalCharacteristic()));
        firstProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeFirstPersonalWeightingFactor())));
        firstProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(firstProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalData(firstProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData firstProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        firstProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeFirstProfessionalCharacteristic()));
        firstProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeFirstProfessionalWeightingFactor())));
        firstProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(firstProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(firstProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }

    private void saveSecondBaEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData secondProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        secondProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeSecondPersonalCharacteristic()));
        secondProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeSecondPersonalWeightingFactor())));
        secondProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(secondProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalData(secondProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData secondProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        secondProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeSecondProfessionalCharacteristic()));
        secondProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeSecondProfessionalWeightingFactor())));
        secondProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(secondProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(secondProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }

    private void saveThirdBaEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData thirdProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        thirdProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeThirdPersonalCharacteristic()));
        thirdProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeThirdPersonalWeightingFactor())));
        thirdProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(thirdProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalData(thirdProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData thirdProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        thirdProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeThirdProfessionalCharacteristic()));
        thirdProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeThirdProfessionalWeightingFactor())));
        thirdProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(thirdProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(thirdProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }

    private void saveFourthBaEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData fourthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        fourthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeFourthPersonalCharacteristic()));
        fourthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeFirstPersonalWeightingFactor())));
        fourthProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(fourthProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalData(fourthProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData fourthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        fourthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeFirstProfessionalCharacteristic()));
        fourthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeFirstProfessionalWeightingFactor())));
        fourthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(fourthProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(fourthProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }

    private void saveFifthBaEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData fifthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        fifthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeFifthPersonalCharacteristic()));
        fifthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeFifthPersonalWeightingFactor())));
        fifthProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(fifthProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalData(fifthProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData fifthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        fifthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeFifthProfessionalCharacteristic()));
        fifthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeFifthProfessionalWeightingFactor())));
        fifthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(fifthProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(fifthProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }

    private void saveSixthBaEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData sixthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        sixthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeSixthPersonalCharacteristic()));
        sixthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeSixthPersonalWeightingFactor())));
        sixthProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(sixthProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalData(sixthProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData sixthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        sixthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeSixthProfessionalCharacteristic()));
        sixthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeSixthProfessionalWeightingFactor())));
        sixthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(sixthProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(sixthProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }

    private void saveSeventhBaEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole baProjectEmployeeRole) {
        ProjectEmployeeRolePersonalData seventhProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
        seventhProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeSeventhPersonalCharacteristic()));
        seventhProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeSeventhPersonalWeightingFactor())));
        seventhProjectEmployeeRolePersonalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRolePersonalDataRepository.save(seventhProjectEmployeeRolePersonalData);
        baProjectEmployeeRole.setProjectEmployeeRolePersonalData(seventhProjectEmployeeRolePersonalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);

        ProjectEmployeeRoleProfessionalData seventhProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
        seventhProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                .findByDescription(newProjectDto.getBaEmployeeSeventhProfessionalCharacteristic()));
        seventhProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                .findByWeightingFactor(Short.valueOf(newProjectDto.getBaEmployeeSeventhProfessionalWeightingFactor())));
        seventhProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(baProjectEmployeeRole);
        projectEmployeeRoleProfessionalDataRepository.save(seventhProjectEmployeeRoleProfessionalData);
        baProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(seventhProjectEmployeeRoleProfessionalData);
        projectEmployeeRoleRepository.save(baProjectEmployeeRole);
    }
}
