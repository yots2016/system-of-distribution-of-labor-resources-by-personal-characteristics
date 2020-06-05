package com.batuhaniskr.project.service.impl.projectemployeerolesaving;

import com.batuhaniskr.project.dto.NewProjectDto;
import com.batuhaniskr.project.model.*;
import com.batuhaniskr.project.repository.*;
import com.batuhaniskr.project.service.ProjectEmployeeRoleSavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Order(6)
@Service
@RequiredArgsConstructor
public class FsProjectEmployeeRoleSavingService implements ProjectEmployeeRoleSavingService {

    private final ProjectRepository projectRepository;
    private final CommonPersonalDataRepository commonPersonalDataRepository;
    private final CommonProfessionalDataRepository commonProfessionalDataRepository;
    private final WeightingFactorRepository weightingFactorRepository;
    private final ProjectEmployeeRoleRepository projectEmployeeRoleRepository;
    private final ProjectEmployeeRolePersonalDataRepository projectEmployeeRolePersonalDataRepository;
    private final ProjectEmployeeRoleProfessionalDataRepository projectEmployeeRoleProfessionalDataRepository;

    @Override
    public void saveProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        if (newProjectDto.getFsEmployeeNumber() > 0) {
            saveFsProjectEmployeeRole(newProjectDto, project);
        }
    }

    private void saveFsProjectEmployeeRole(NewProjectDto newProjectDto, Project project) {
        ProjectEmployeeRole fsProjectEmployeeRole = new ProjectEmployeeRole();
        fsProjectEmployeeRole.setEmployeeRole("FS");
        fsProjectEmployeeRole.setEmployeesNumber(Long.valueOf(newProjectDto.getFsEmployeeNumber()));
        fsProjectEmployeeRole.setProject(project);
        projectEmployeeRoleRepository.save(fsProjectEmployeeRole);

        saveFirstFsEmployeeCharacteristics(newProjectDto, fsProjectEmployeeRole);
        saveSecondFsEmployeeCharacteristics(newProjectDto, fsProjectEmployeeRole);
        saveThirdFsEmployeeCharacteristics(newProjectDto, fsProjectEmployeeRole);
        saveFourthFsEmployeeCharacteristics(newProjectDto, fsProjectEmployeeRole);
        saveFifthFsEmployeeCharacteristics(newProjectDto, fsProjectEmployeeRole);
        saveSixthFsEmployeeCharacteristics(newProjectDto, fsProjectEmployeeRole);
        saveSeventhFsEmployeeCharacteristics(newProjectDto, fsProjectEmployeeRole);

        project.addProjectEmployeeRole(fsProjectEmployeeRole);
        projectRepository.save(project);
    }

    private void saveSecondFsEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole fsProjectEmployeeRole) {
        if (!newProjectDto.getFsEmployeeSecondPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData secondProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            secondProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeSecondPersonalCharacteristic()));
            secondProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeSecondPersonalWeightingFactor())));
            secondProjectEmployeeRolePersonalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(secondProjectEmployeeRolePersonalData);
            fsProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(secondProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(fsProjectEmployeeRole);
        }

        if (!newProjectDto.getFsEmployeeSecondProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData secondProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            secondProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeSecondProfessionalCharacteristic()));
            secondProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeSecondProfessionalWeightingFactor())));
            secondProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(secondProjectEmployeeRoleProfessionalData);
            fsProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(secondProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(fsProjectEmployeeRole);
        }
    }

    private void saveFirstFsEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole fsProjectEmployeeRole) {
        if (!newProjectDto.getFsEmployeeFirstPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData firstProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            firstProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeFirstPersonalCharacteristic()));
            firstProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeFirstPersonalWeightingFactor())));
            firstProjectEmployeeRolePersonalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(firstProjectEmployeeRolePersonalData);
            fsProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(firstProjectEmployeeRolePersonalData);
        }

        if (!newProjectDto.getFsEmployeeFirstProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData firstProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            firstProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeFirstProfessionalCharacteristic()));
            firstProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeFirstProfessionalWeightingFactor())));
            firstProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(firstProjectEmployeeRoleProfessionalData);
            fsProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(firstProjectEmployeeRoleProfessionalData);
        }
        projectEmployeeRoleRepository.save(fsProjectEmployeeRole);
    }

    private void saveThirdFsEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole fsProjectEmployeeRole) {
        if (!newProjectDto.getFsEmployeeThirdPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData thirdProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            thirdProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeThirdPersonalCharacteristic()));
            thirdProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeThirdPersonalWeightingFactor())));
            thirdProjectEmployeeRolePersonalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(thirdProjectEmployeeRolePersonalData);
            fsProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(thirdProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(fsProjectEmployeeRole);
        }

        if (!newProjectDto.getFsEmployeeThirdProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData thirdProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            thirdProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeThirdProfessionalCharacteristic()));
            thirdProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeThirdProfessionalWeightingFactor())));
            thirdProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(thirdProjectEmployeeRoleProfessionalData);
            fsProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(thirdProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(fsProjectEmployeeRole);
        }
    }

    private void saveFourthFsEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole fsProjectEmployeeRole) {
        if (!newProjectDto.getFsEmployeeFourthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fourthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fourthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeFourthPersonalCharacteristic()));
            fourthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeFourthPersonalWeightingFactor())));
            fourthProjectEmployeeRolePersonalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fourthProjectEmployeeRolePersonalData);
            fsProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(fourthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(fsProjectEmployeeRole);
        }

        if (!newProjectDto.getFsEmployeeFourthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fourthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fourthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeFourthProfessionalCharacteristic()));
            fourthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeFourthProfessionalWeightingFactor())));
            fourthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fourthProjectEmployeeRoleProfessionalData);
            fsProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(fourthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(fsProjectEmployeeRole);
        }
    }

    private void saveFifthFsEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole fsProjectEmployeeRole) {
        if (!newProjectDto.getFsEmployeeFifthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData fifthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            fifthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeFifthPersonalCharacteristic()));
            fifthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeFifthPersonalWeightingFactor())));
            fifthProjectEmployeeRolePersonalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(fifthProjectEmployeeRolePersonalData);
            fsProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(fifthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(fsProjectEmployeeRole);
        }

        if (!newProjectDto.getFsEmployeeFifthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData fifthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            fifthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeFifthProfessionalCharacteristic()));
            fifthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeFifthProfessionalWeightingFactor())));
            fifthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(fifthProjectEmployeeRoleProfessionalData);
            fsProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(fifthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(fsProjectEmployeeRole);
        }
    }

    private void saveSixthFsEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole fsProjectEmployeeRole) {
        if (!newProjectDto.getFsEmployeeSixthPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData sixthProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            sixthProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeSixthPersonalCharacteristic()));
            sixthProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeFirstPersonalWeightingFactor())));
            sixthProjectEmployeeRolePersonalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(sixthProjectEmployeeRolePersonalData);
            fsProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(sixthProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(fsProjectEmployeeRole);
        }

        if (!newProjectDto.getFsEmployeeSixthProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData sixthProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            sixthProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeSixthProfessionalCharacteristic()));
            sixthProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeSixthProfessionalWeightingFactor())));
            sixthProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(sixthProjectEmployeeRoleProfessionalData);
            fsProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(sixthProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(fsProjectEmployeeRole);
        }
    }

    private void saveSeventhFsEmployeeCharacteristics(NewProjectDto newProjectDto, ProjectEmployeeRole fsProjectEmployeeRole) {
        if (!newProjectDto.getFsEmployeeSeventhPersonalCharacteristic().isEmpty()) {
            ProjectEmployeeRolePersonalData seventhProjectEmployeeRolePersonalData = new ProjectEmployeeRolePersonalData();
            seventhProjectEmployeeRolePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeSeventhPersonalCharacteristic()));
            seventhProjectEmployeeRolePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeSeventhPersonalWeightingFactor())));
            seventhProjectEmployeeRolePersonalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRolePersonalDataRepository.save(seventhProjectEmployeeRolePersonalData);
            fsProjectEmployeeRole.setProjectEmployeeRolePersonalDataSet(seventhProjectEmployeeRolePersonalData);
            projectEmployeeRoleRepository.save(fsProjectEmployeeRole);
        }

        if (!newProjectDto.getFsEmployeeSeventhProfessionalCharacteristic().isEmpty()) {
            ProjectEmployeeRoleProfessionalData seventhProjectEmployeeRoleProfessionalData = new ProjectEmployeeRoleProfessionalData();
            seventhProjectEmployeeRoleProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newProjectDto.getFsEmployeeSeventhProfessionalCharacteristic()));
            seventhProjectEmployeeRoleProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newProjectDto.getFsEmployeeSeventhProfessionalWeightingFactor())));
            seventhProjectEmployeeRoleProfessionalData.setProjectEmployeeRole(fsProjectEmployeeRole);
            projectEmployeeRoleProfessionalDataRepository.save(seventhProjectEmployeeRoleProfessionalData);
            fsProjectEmployeeRole.setProjectEmployeeRoleProfessionalDataSet(seventhProjectEmployeeRoleProfessionalData);
            projectEmployeeRoleRepository.save(fsProjectEmployeeRole);
        }
    }
}
