package com.distributionsystem.service.impl.projectemployeerolesaving;

import com.distributionsystem.dto.NewProjectDto;
import com.distributionsystem.model.*;
import com.distributionsystem.repository.*;
import com.distributionsystem.service.ProjectEmployeeRoleSavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(8)
@Service
@RequiredArgsConstructor
public class InfProjectEmployeeRoleSavingService  implements ProjectEmployeeRoleSavingService {

    private static final String INFORMATION_DEVELOPER = "Information developer";

    private final ProjectRepository projectRepository;
    private final CommonPersonalDataRepository commonPersonalDataRepository;
    private final CommonProfessionalDataRepository commonProfessionalDataRepository;
    private final WeightingFactorRepository weightingFactorRepository;
    private final ProjectEmployeeRoleRepository projectEmployeeRoleRepository;
    private final ProjectEmployeeRolePersonalDataRepository projectEmployeeRolePersonalDataRepository;
    private final ProjectEmployeeRoleProfessionalDataRepository projectEmployeeRoleProfessionalDataRepository;

    @Override
    public void saveProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        if (newProjectDto.getInfEmployeeNumber() > 0) {
            saveInfProjectEmployeeRole(newProjectDto, project);
        }
    }

    private void saveInfProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        ProjectEmployeeRole infProjectEmployeeRole = new ProjectEmployeeRole();
        infProjectEmployeeRole.setEmployeeRole(INFORMATION_DEVELOPER);
        infProjectEmployeeRole.setEmployeesNumber(Long.valueOf(newProjectDto.getInfEmployeeNumber()));
        infProjectEmployeeRole.setProject(project);
        projectEmployeeRoleRepository.save(infProjectEmployeeRole);

        saveFirstInfEmployeeCharacteristics(newProjectDto, infProjectEmployeeRole);
        saveSecondInfEmployeeCharacteristics(newProjectDto, infProjectEmployeeRole);
        saveThirdInfEmployeeCharacteristics(newProjectDto, infProjectEmployeeRole);
        saveFourthInfEmployeeCharacteristics(newProjectDto, infProjectEmployeeRole);
        saveFifthInfEmployeeCharacteristics(newProjectDto, infProjectEmployeeRole);
        saveSixthInfEmployeeCharacteristics(newProjectDto, infProjectEmployeeRole);
        saveSeventhInfEmployeeCharacteristics(newProjectDto, infProjectEmployeeRole);

        project.addProjectEmployeeRole(infProjectEmployeeRole);
        projectRepository.save(project);
    }

    private void saveSecondInfEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole infProjectEmployeeRole) {
        if (!newProjectDto.getInfEmployeeSecondPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData secondProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            secondProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeSecondPersonalCharacteristic()));
            secondProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeSecondPersonalWeightingFactor())));
            secondProjectEmployeeRolePersonalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(secondProjectEmployeeRolePersonalData);
            infProjectEmployeeRole.setProjectEmployeeRolePersonalData(secondProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(infProjectEmployeeRole);
        }

        if (!newProjectDto.getInfEmployeeSecondProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData secondProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            secondProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeSecondProfessionalCharacteristic()));
            secondProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeSecondProfessionalWeightingFactor())));
            secondProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(secondProjectEmployeeRoleProfessionalData);
            infProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(secondProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(infProjectEmployeeRole);
        }
    }

    private void saveFirstInfEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole infProjectEmployeeRole) {
        if (!newProjectDto.getInfEmployeeFirstPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData firstProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            firstProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeFirstPersonalCharacteristic()));
            firstProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeFirstPersonalWeightingFactor())));
            firstProjectEmployeeRolePersonalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(firstProjectEmployeeRolePersonalData);
            infProjectEmployeeRole.setProjectEmployeeRolePersonalData(firstProjectEmployeeRolePersonalData);
        }

        if (!newProjectDto.getInfEmployeeFirstProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData firstProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            firstProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeFirstProfessionalCharacteristic()));
            firstProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeFirstProfessionalWeightingFactor())));
            firstProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(firstProjectEmployeeRoleProfessionalData);
            infProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(firstProjectEmployeeRoleProfessionalData);
        }
        projectEmployeeRoleRepository.save(infProjectEmployeeRole);
    }

    private void saveThirdInfEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole infProjectEmployeeRole) {
        if (!newProjectDto.getInfEmployeeThirdPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData thirdProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            thirdProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeThirdPersonalCharacteristic()));
            thirdProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeThirdPersonalWeightingFactor())));
            thirdProjectEmployeeRolePersonalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(thirdProjectEmployeeRolePersonalData);
            infProjectEmployeeRole.setProjectEmployeeRolePersonalData(thirdProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(infProjectEmployeeRole);
        }

        if (!newProjectDto.getInfEmployeeThirdProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData thirdProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            thirdProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeThirdProfessionalCharacteristic()));
            thirdProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeThirdProfessionalWeightingFactor())));
            thirdProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(thirdProjectEmployeeRoleProfessionalData);
            infProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(thirdProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(infProjectEmployeeRole);
        }
    }

    private void saveFourthInfEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole infProjectEmployeeRole) {
        if (!newProjectDto.getInfEmployeeFourthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fourthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fourthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeFourthPersonalCharacteristic()));
            fourthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeFourthPersonalWeightingFactor())));
            fourthProjectEmployeeRolePersonalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fourthProjectEmployeeRolePersonalData);
            infProjectEmployeeRole.setProjectEmployeeRolePersonalData(fourthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(infProjectEmployeeRole);
        }

        if (!newProjectDto.getInfEmployeeFourthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fourthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fourthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeFourthProfessionalCharacteristic()));
            fourthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeFourthProfessionalWeightingFactor())));
            fourthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fourthProjectEmployeeRoleProfessionalData);
            infProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(fourthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(infProjectEmployeeRole);
        }
    }

    private void saveFifthInfEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole infProjectEmployeeRole) {
        if (!newProjectDto.getInfEmployeeFifthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fifthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fifthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeFifthPersonalCharacteristic()));
            fifthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeFifthPersonalWeightingFactor())));
            fifthProjectEmployeeRolePersonalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fifthProjectEmployeeRolePersonalData);
            infProjectEmployeeRole.setProjectEmployeeRolePersonalData(fifthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(infProjectEmployeeRole);
        }

        if (!newProjectDto.getInfEmployeeFifthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fifthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fifthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeFifthProfessionalCharacteristic()));
            fifthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeFifthProfessionalWeightingFactor())));
            fifthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fifthProjectEmployeeRoleProfessionalData);
            infProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(fifthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(infProjectEmployeeRole);
        }
    }

    private void saveSixthInfEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole infProjectEmployeeRole) {
        if (!newProjectDto.getInfEmployeeSixthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData sixthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            sixthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeSixthPersonalCharacteristic()));
            sixthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeFirstPersonalWeightingFactor())));
            sixthProjectEmployeeRolePersonalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(sixthProjectEmployeeRolePersonalData);
            infProjectEmployeeRole.setProjectEmployeeRolePersonalData(sixthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(infProjectEmployeeRole);
        }

        if (!newProjectDto.getInfEmployeeSixthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData sixthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            sixthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeSixthProfessionalCharacteristic()));
            sixthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeSixthProfessionalWeightingFactor())));
            sixthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(sixthProjectEmployeeRoleProfessionalData);
            infProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(sixthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(infProjectEmployeeRole);
        }
    }

    private void saveSeventhInfEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole infProjectEmployeeRole) {
        if (!newProjectDto.getInfEmployeeSeventhPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData seventhProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            seventhProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeSeventhPersonalCharacteristic()));
            seventhProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeSeventhPersonalWeightingFactor())));
            seventhProjectEmployeeRolePersonalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(seventhProjectEmployeeRolePersonalData);
            infProjectEmployeeRole.setProjectEmployeeRolePersonalData(seventhProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(infProjectEmployeeRole);
        }

        if (!newProjectDto.getInfEmployeeSeventhProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData seventhProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            seventhProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getInfEmployeeSeventhProfessionalCharacteristic()));
            seventhProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getInfEmployeeSeventhProfessionalWeightingFactor())));
            seventhProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(infProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(seventhProjectEmployeeRoleProfessionalData);
            infProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(seventhProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(infProjectEmployeeRole);
        }
    }
}
