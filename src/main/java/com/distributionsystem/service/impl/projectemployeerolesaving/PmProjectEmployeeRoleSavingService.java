package com.distributionsystem.service.impl.projectemployeerolesaving;

import com.distributionsystem.dto.NewProjectDto;
import com.distributionsystem.model.*;
import com.distributionsystem.repository.*;
import com.distributionsystem.service.ProjectEmployeeRoleSavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(1)
@Service
@RequiredArgsConstructor
public class PmProjectEmployeeRoleSavingService implements ProjectEmployeeRoleSavingService {

    private final ProjectRepository projectRepository;
    private final CommonPersonalDataRepository commonPersonalDataRepository;
    private final CommonProfessionalDataRepository commonProfessionalDataRepository;
    private final WeightingFactorRepository weightingFactorRepository;
    private final ProjectEmployeeRoleRepository projectEmployeeRoleRepository;
    private final ProjectEmployeeRolePersonalDataRepository projectEmployeeRolePersonalDataRepository;
    private final ProjectEmployeeRoleProfessionalDataRepository projectEmployeeRoleProfessionalDataRepository;

    @Override
    public void saveProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        if (newProjectDto.getPmEmployeeNumber() > 0) {
            savePmProjectEmployeeRole(newProjectDto, project);
        }
    }

    private void savePmProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        ProjectEmployeeRole pmProjectEmployeeRole = new ProjectEmployeeRole();
        pmProjectEmployeeRole.setEmployeeRole("PM");
        pmProjectEmployeeRole.setEmployeesNumber(Long.valueOf(newProjectDto.getPmEmployeeNumber()));
        pmProjectEmployeeRole.setProject(project);
        projectEmployeeRoleRepository.save(pmProjectEmployeeRole);

        saveFirstPmEmployeeCharacteristics(newProjectDto, pmProjectEmployeeRole);
        saveSecondPmEmployeeCharacteristics(newProjectDto, pmProjectEmployeeRole);
        saveThirdPmEmployeeCharacteristics(newProjectDto, pmProjectEmployeeRole);
        saveFourthPmEmployeeCharacteristics(newProjectDto, pmProjectEmployeeRole);
        saveFifthPmEmployeeCharacteristics(newProjectDto, pmProjectEmployeeRole);
        saveSixthPmEmployeeCharacteristics(newProjectDto, pmProjectEmployeeRole);
        saveSeventhPmEmployeeCharacteristics(newProjectDto, pmProjectEmployeeRole);

        project.addProjectEmployeeRole(pmProjectEmployeeRole);
        projectRepository.save(project);
    }

    private void saveSecondPmEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeSecondPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData secondProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            secondProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeSecondPersonalCharacteristic()));
            secondProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeSecondPersonalWeightingFactor())));
            secondProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(secondProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalData(secondProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }

        if (!newProjectDto.getPmEmployeeSecondProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData secondProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            secondProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeSecondProfessionalCharacteristic()));
            secondProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeSecondProfessionalWeightingFactor())));
            secondProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(secondProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(secondProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }
    }

    private void saveFirstPmEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeFirstPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData firstProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            firstProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeFirstPersonalCharacteristic()));
            firstProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeFirstPersonalWeightingFactor())));
            firstProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(firstProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalData(firstProjectEmployeeRolePersonalData);
        }

        if (!newProjectDto.getPmEmployeeFirstProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData firstProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            firstProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeFirstProfessionalCharacteristic()));
            firstProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeFirstProfessionalWeightingFactor())));
            firstProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(firstProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(firstProjectEmployeeRoleProfessionalData);
        }
        projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
    }

    private void saveThirdPmEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeThirdPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData thirdProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            thirdProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeThirdPersonalCharacteristic()));
            thirdProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeThirdPersonalWeightingFactor())));
            thirdProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(thirdProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalData(thirdProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }

        if (!newProjectDto.getPmEmployeeThirdProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData thirdProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            thirdProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeThirdProfessionalCharacteristic()));
            thirdProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeThirdProfessionalWeightingFactor())));
            thirdProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(thirdProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(thirdProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }
    }

    private void saveFourthPmEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeFourthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fourthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fourthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeFourthPersonalCharacteristic()));
            fourthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeFourthPersonalWeightingFactor())));
            fourthProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fourthProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalData(fourthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }

        if (!newProjectDto.getPmEmployeeFourthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fourthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fourthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeFourthProfessionalCharacteristic()));
            fourthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeFourthProfessionalWeightingFactor())));
            fourthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fourthProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(fourthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }
    }

    private void saveFifthPmEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeFifthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fifthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fifthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeFifthPersonalCharacteristic()));
            fifthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeFifthPersonalWeightingFactor())));
            fifthProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fifthProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalData(fifthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }

        if (!newProjectDto.getPmEmployeeFifthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fifthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fifthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeFifthProfessionalCharacteristic()));
            fifthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeFifthProfessionalWeightingFactor())));
            fifthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fifthProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(fifthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }
    }

    private void saveSixthPmEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeSixthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData sixthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            sixthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeSixthPersonalCharacteristic()));
            sixthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeFirstPersonalWeightingFactor())));
            sixthProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(sixthProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalData(sixthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }

        if (!newProjectDto.getPmEmployeeSixthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData sixthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            sixthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeSixthProfessionalCharacteristic()));
            sixthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeSixthProfessionalWeightingFactor())));
            sixthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(sixthProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(sixthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }
    }

    private void saveSeventhPmEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole pmProjectEmployeeRole) {
        if (!newProjectDto.getPmEmployeeSeventhPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData seventhProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            seventhProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeSeventhPersonalCharacteristic()));
            seventhProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeSeventhPersonalWeightingFactor())));
            seventhProjectEmployeeRolePersonalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(seventhProjectEmployeeRolePersonalData);
            pmProjectEmployeeRole.setProjectEmployeeRolePersonalData(seventhProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }

        if (!newProjectDto.getPmEmployeeSeventhProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData seventhProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            seventhProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getPmEmployeeSeventhProfessionalCharacteristic()));
            seventhProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Float.valueOf(newProjectDto.getPmEmployeeSeventhProfessionalWeightingFactor())));
            seventhProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(pmProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(seventhProjectEmployeeRoleProfessionalData);
            pmProjectEmployeeRole.setProjectEmployeeRoleProfessionalData(seventhProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(pmProjectEmployeeRole);
        }
    }
}
