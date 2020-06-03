package com.batuhaniskr.project.service;

import com.batuhaniskr.project.dto.NewEmployeeDto;
import com.batuhaniskr.project.model.*;
import com.batuhaniskr.project.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeePersonalDataRepository employeePersonalDataRepository;
    private final EmployeeProfessionalDataRepository employeeProfessionalDataRepository;
    private final CommonPersonalDataRepository commonPersonalDataRepository;
    private final CommonProfessionalDataRepository commonProfessionalDataRepository;
    private final WeightingFactorRepository weightingFactorRepository;

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void saveEmployee(NewEmployeeDto newEmployeeDto) {
        Employee employee = new Employee();
        employee.setFirstName(newEmployeeDto.getFirstName());
        employee.setLastName(newEmployeeDto.getLastName());
        employee.setEmail(newEmployeeDto.getEmail());
        employee.setPosition(newEmployeeDto.getPosition());
        employeeRepository.save(employee);

        saveFirstEmployeeCharasterictics(newEmployeeDto, employee);
        saveSecondEmployeeCharasterictics(newEmployeeDto, employee);
        saveThirdEmployeeCharasterictics(newEmployeeDto, employee);
        saveFourthEmployeeCharasterictics(newEmployeeDto, employee);
        saveFifthEmployeeCharasterictics(newEmployeeDto, employee);
        saveSixthEmployeeCharasterictics(newEmployeeDto, employee);
        saveSeventhEmployeeCharasterictics(newEmployeeDto, employee);
    }

    private void saveSeventhEmployeeCharasterictics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeSeventhPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData seventhEmployeePersonalData = new EmployeePersonalData();
            seventhEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeSeventhPersonalCharacteristic()));
            seventhEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeSeventhPersonalWeightingFactor())));
            seventhEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(seventhEmployeePersonalData);
            employee.setEmployeePersonalDataSet(seventhEmployeePersonalData);
            employeeRepository.save(employee);
        }

        if (!newEmployeeDto.getEmployeeSeventhProfessionalCharacteristic().isEmpty()) {
            EmployeeProfessionalData seventhEmployeeProfessionalData = new EmployeeProfessionalData();
            seventhEmployeeProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeSeventhProfessionalCharacteristic()));
            seventhEmployeeProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeSeventhProfessionalWeightingFactor())));
            seventhEmployeeProfessionalData.setEmployee(employee);
            employeeProfessionalDataRepository.save(seventhEmployeeProfessionalData);
            employee.setEmployeeProfessionalDataSet(seventhEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    private void saveSixthEmployeeCharasterictics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeSixthPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData sixthEmployeePersonalData = new EmployeePersonalData();
            sixthEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeSixthPersonalCharacteristic()));
            sixthEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeSixthPersonalWeightingFactor())));
            sixthEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(sixthEmployeePersonalData);
            employee.setEmployeePersonalDataSet(sixthEmployeePersonalData);
            employeeRepository.save(employee);
        }

        if (!newEmployeeDto.getEmployeeSixthProfessionalCharacteristic().isEmpty()) {
            EmployeeProfessionalData sixthEmployeeProfessionalData = new EmployeeProfessionalData();
            sixthEmployeeProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeSixthProfessionalCharacteristic()));
            sixthEmployeeProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeSixthProfessionalWeightingFactor())));
            sixthEmployeeProfessionalData.setEmployee(employee);
            employeeProfessionalDataRepository.save(sixthEmployeeProfessionalData);
            employee.setEmployeeProfessionalDataSet(sixthEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    private void saveFifthEmployeeCharasterictics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeFifthPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData fifthEmployeePersonalData = new EmployeePersonalData();
            fifthEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeFifthPersonalCharacteristic()));
            fifthEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeFifthPersonalWeightingFactor())));
            fifthEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(fifthEmployeePersonalData);
            employee.setEmployeePersonalDataSet(fifthEmployeePersonalData);
            employeeRepository.save(employee);
        }

        if (!newEmployeeDto.getEmployeeFifthProfessionalCharacteristic().isEmpty()) {
            EmployeeProfessionalData fifthEmployeeProfessionalData = new EmployeeProfessionalData();
            fifthEmployeeProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeFifthProfessionalCharacteristic()));
            fifthEmployeeProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeFifthProfessionalWeightingFactor())));
            fifthEmployeeProfessionalData.setEmployee(employee);
            employeeProfessionalDataRepository.save(fifthEmployeeProfessionalData);
            employee.setEmployeeProfessionalDataSet(fifthEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    private void saveFourthEmployeeCharasterictics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeFourthPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData fourthEmployeePersonalData = new EmployeePersonalData();
            fourthEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeFourthPersonalCharacteristic()));
            fourthEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeFourthPersonalWeightingFactor())));
            fourthEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(fourthEmployeePersonalData);
            employee.setEmployeePersonalDataSet(fourthEmployeePersonalData);
            employeeRepository.save(employee);
        }

        if (!newEmployeeDto.getEmployeeFourthProfessionalCharacteristic().isEmpty()) {
            EmployeeProfessionalData fourthEmployeeProfessionalData = new EmployeeProfessionalData();
            fourthEmployeeProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeFourthProfessionalCharacteristic()));
            fourthEmployeeProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeFourthProfessionalWeightingFactor())));
            fourthEmployeeProfessionalData.setEmployee(employee);
            employeeProfessionalDataRepository.save(fourthEmployeeProfessionalData);
            employee.setEmployeeProfessionalDataSet(fourthEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    private void saveThirdEmployeeCharasterictics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeThirdPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData thirdEmployeePersonalData = new EmployeePersonalData();
            thirdEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeThirdPersonalCharacteristic()));
            thirdEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeThirdPersonalWeightingFactor())));
            thirdEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(thirdEmployeePersonalData);
            employee.setEmployeePersonalDataSet(thirdEmployeePersonalData);
            employeeRepository.save(employee);
        }

        if (!newEmployeeDto.getEmployeeThirdProfessionalCharacteristic().isEmpty()) {
            EmployeeProfessionalData thirdEmployeeProfessionalData = new EmployeeProfessionalData();
            thirdEmployeeProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeThirdProfessionalCharacteristic()));
            thirdEmployeeProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeThirdProfessionalWeightingFactor())));
            thirdEmployeeProfessionalData.setEmployee(employee);
            employeeProfessionalDataRepository.save(thirdEmployeeProfessionalData);
            employee.setEmployeeProfessionalDataSet(thirdEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    private void saveSecondEmployeeCharasterictics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeSecondPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData secondEmployeePersonalData = new EmployeePersonalData();
            secondEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeSecondPersonalCharacteristic()));
            secondEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeSecondPersonalWeightingFactor())));
            secondEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(secondEmployeePersonalData);
            employee.setEmployeePersonalDataSet(secondEmployeePersonalData);
            employeeRepository.save(employee);
        }

        if (!newEmployeeDto.getEmployeeSecondProfessionalCharacteristic().isEmpty()) {
            EmployeeProfessionalData secondEmployeeProfessionalData = new EmployeeProfessionalData();
            secondEmployeeProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeSecondProfessionalCharacteristic()));
            secondEmployeeProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeSecondProfessionalWeightingFactor())));
            secondEmployeeProfessionalData.setEmployee(employee);
            employeeProfessionalDataRepository.save(secondEmployeeProfessionalData);
            employee.setEmployeeProfessionalDataSet(secondEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    private void saveFirstEmployeeCharasterictics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeFirstPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData firstEmployeePersonalData = new EmployeePersonalData();
            firstEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeFirstPersonalCharacteristic()));
            firstEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeFirstPersonalWeightingFactor())));
            firstEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(firstEmployeePersonalData);
            employee.setEmployeePersonalDataSet(firstEmployeePersonalData);
            employeeRepository.save(employee);
        }

        if (!newEmployeeDto.getEmployeeFirstProfessionalCharacteristic().isEmpty()) {
            EmployeeProfessionalData firstEmployeeProfessionalData = new EmployeeProfessionalData();
            firstEmployeeProfessionalData.setCommonProfessionalData(commonProfessionalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeFirstProfessionalCharacteristic()));
            firstEmployeeProfessionalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeFirstProfessionalWeightingFactor())));
            firstEmployeeProfessionalData.setEmployee(employee);
            employeeProfessionalDataRepository.save(firstEmployeeProfessionalData);
            employee.setEmployeeProfessionalDataSet(firstEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.delete(id);
    }
}
