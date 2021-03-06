package com.distributionsystem.service.impl.projectemployeerolesaving;

import com.distributionsystem.dto.NewProjectDto;
import com.distributionsystem.model.*;
import com.distributionsystem.repository.*;
import com.distributionsystem.service.ProjectEmployeeRoleSavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(5)
@Service
@RequiredArgsConstructor
public class BeProjectEmployeeRoleSavingService implements ProjectEmployeeRoleSavingService {

    private static final String BACK_END_DEVELOPER = "Back-end developer";

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
        beProjectEmployeeRole.setEmployeeRole(BACK_END_DEVELOPER);
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
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeSecondPersonalWeightingFactor())));
            secondProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(secondProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalData(secondProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }

        if (!newProjectDto.getBeEmployeeSecondProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData secondProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            secondProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeSecondProfessionalCharacteristic()));
            secondProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeSecondProfessionalWeightingFactor())));
            secondProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(secondProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(secondProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }
    }

    private void saveFirstBeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole beProjectEmployeeRole) {
        if (!newProjectDto.getBeEmployeeFirstPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData firstProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            firstProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeFirstPersonalCharacteristic()));
            firstProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeFirstPersonalWeightingFactor())));
            firstProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(firstProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalData(firstProjectEmployeeRolePersonalData);
        }

        if (!newProjectDto.getBeEmployeeFirstProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData firstProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            firstProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeFirstProfessionalCharacteristic()));
            firstProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeFirstProfessionalWeightingFactor())));
            firstProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(firstProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(firstProjectEmployeeRoleProfessionalData);
        }
        projectEmployeeRoleRepository.save(beProjectEmployeeRole);
    }

    private void saveThirdBeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole beProjectEmployeeRole) {
        if (!newProjectDto.getBeEmployeeThirdPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData thirdProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            thirdProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeThirdPersonalCharacteristic()));
            thirdProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeThirdPersonalWeightingFactor())));
            thirdProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(thirdProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalData(thirdProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }

        if (!newProjectDto.getBeEmployeeThirdProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData thirdProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            thirdProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeThirdProfessionalCharacteristic()));
            thirdProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeThirdProfessionalWeightingFactor())));
            thirdProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(thirdProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(thirdProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }
    }

    private void saveFourthBeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole beProjectEmployeeRole) {
        if (!newProjectDto.getBeEmployeeFourthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fourthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fourthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeFourthPersonalCharacteristic()));
            fourthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeFourthPersonalWeightingFactor())));
            fourthProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fourthProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalData(fourthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }

        if (!newProjectDto.getBeEmployeeFourthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fourthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fourthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeFourthProfessionalCharacteristic()));
            fourthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeFourthProfessionalWeightingFactor())));
            fourthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fourthProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(fourthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }
    }

    private void saveFifthBeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole beProjectEmployeeRole) {
        if (!newProjectDto.getBeEmployeeFifthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fifthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fifthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeFifthPersonalCharacteristic()));
            fifthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeFifthPersonalWeightingFactor())));
            fifthProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fifthProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalData(fifthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }

        if (!newProjectDto.getBeEmployeeFifthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fifthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fifthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeFifthProfessionalCharacteristic()));
            fifthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeFifthProfessionalWeightingFactor())));
            fifthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fifthProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(fifthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }
    }

    private void saveSixthBeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole beProjectEmployeeRole) {
        if (!newProjectDto.getBeEmployeeSixthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData sixthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            sixthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeSixthPersonalCharacteristic()));
            sixthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeFirstPersonalWeightingFactor())));
            sixthProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(sixthProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalData(sixthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }

        if (!newProjectDto.getBeEmployeeSixthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData sixthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            sixthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeSixthProfessionalCharacteristic()));
            sixthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeSixthProfessionalWeightingFactor())));
            sixthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(sixthProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(sixthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }
    }

    private void saveSeventhBeEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole beProjectEmployeeRole) {
        if (!newProjectDto.getBeEmployeeSeventhPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData seventhProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            seventhProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeSeventhPersonalCharacteristic()));
            seventhProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeSeventhPersonalWeightingFactor())));
            seventhProjectEmployeeRolePersonalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(seventhProjectEmployeeRolePersonalData);
            beProjectEmployeeRole.setProjectEmployeeRolePersonalData(seventhProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }

        if (!newProjectDto.getBeEmployeeSeventhProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData seventhProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            seventhProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getBeEmployeeSeventhProfessionalCharacteristic()));
            seventhProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getBeEmployeeSeventhProfessionalWeightingFactor())));
            seventhProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(beProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(seventhProjectEmployeeRoleProfessionalData);
            beProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(seventhProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(beProjectEmployeeRole);
        }
    }
}
