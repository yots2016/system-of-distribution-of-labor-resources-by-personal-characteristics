package com.distributionsystem.service;

import com.distributionsystem.dto.NewEmployeeDto;
import com.distributionsystem.model.*;
import com.distributionsystem.repository.*;
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

        saveAllEmployeeCharacteristics(newEmployeeDto, employee);
    }

    private void saveAllEmployeeCharacteristics(NewEmployeeDto newEmployeeDto, Employee employee) {
        saveFirstEmployeeCharacteristics(newEmployeeDto, employee);
        saveSecondEmployeeCharacteristics(newEmployeeDto, employee);
        saveThirdEmployeeCharacteristics(newEmployeeDto, employee);
        saveFourthEmployeeCharacteristics(newEmployeeDto, employee);
        saveFifthEmployeeCharacteristics(newEmployeeDto, employee);
        saveSixthEmployeeCharacteristics(newEmployeeDto, employee);
        saveSeventhEmployeeCharacteristics(newEmployeeDto, employee);
    }

    private void saveFirstEmployeeCharacteristics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeFirstPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData firstEmployeePersonalData = new EmployeePersonalData();
            firstEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeFirstPersonalCharacteristic()));
            firstEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeFirstPersonalWeightingFactor())));
            firstEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(firstEmployeePersonalData);
            employee.setEmployeePersonalData(firstEmployeePersonalData);
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
            employee.setEmployeeProfessionalData(firstEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    private void saveSecondEmployeeCharacteristics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeSecondPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData secondEmployeePersonalData = new EmployeePersonalData();
            secondEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeSecondPersonalCharacteristic()));
            secondEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeSecondPersonalWeightingFactor())));
            secondEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(secondEmployeePersonalData);
            employee.setEmployeePersonalData(secondEmployeePersonalData);
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
            employee.setEmployeeProfessionalData(secondEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    private void saveThirdEmployeeCharacteristics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeThirdPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData thirdEmployeePersonalData = new EmployeePersonalData();
            thirdEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeThirdPersonalCharacteristic()));
            thirdEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeThirdPersonalWeightingFactor())));
            thirdEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(thirdEmployeePersonalData);
            employee.setEmployeePersonalData(thirdEmployeePersonalData);
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
            employee.setEmployeeProfessionalData(thirdEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    private void saveFourthEmployeeCharacteristics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeFourthPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData fourthEmployeePersonalData = new EmployeePersonalData();
            fourthEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeFourthPersonalCharacteristic()));
            fourthEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeFourthPersonalWeightingFactor())));
            fourthEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(fourthEmployeePersonalData);
            employee.setEmployeePersonalData(fourthEmployeePersonalData);
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
            employee.setEmployeeProfessionalData(fourthEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    private void saveFifthEmployeeCharacteristics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeFifthPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData fifthEmployeePersonalData = new EmployeePersonalData();
            fifthEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeFifthPersonalCharacteristic()));
            fifthEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeFifthPersonalWeightingFactor())));
            fifthEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(fifthEmployeePersonalData);
            employee.setEmployeePersonalData(fifthEmployeePersonalData);
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
            employee.setEmployeeProfessionalData(fifthEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    private void saveSixthEmployeeCharacteristics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeSixthPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData sixthEmployeePersonalData = new EmployeePersonalData();
            sixthEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeSixthPersonalCharacteristic()));
            sixthEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeSixthPersonalWeightingFactor())));
            sixthEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(sixthEmployeePersonalData);
            employee.setEmployeePersonalData(sixthEmployeePersonalData);
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
            employee.setEmployeeProfessionalData(sixthEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    private void saveSeventhEmployeeCharacteristics(NewEmployeeDto newEmployeeDto, Employee employee) {
        if (!newEmployeeDto.getEmployeeSeventhPersonalCharacteristic().isEmpty()) {
            EmployeePersonalData seventhEmployeePersonalData = new EmployeePersonalData();
            seventhEmployeePersonalData.setCommonPersonalData(commonPersonalDataRepository
                    .findByDescription(newEmployeeDto.getEmployeeSeventhPersonalCharacteristic()));
            seventhEmployeePersonalData.setWeightingFactor(weightingFactorRepository
                    .findByWeightingFactor(Short.valueOf(newEmployeeDto.getEmployeeSeventhPersonalWeightingFactor())));
            seventhEmployeePersonalData.setEmployee(employee);
            employeePersonalDataRepository.save(seventhEmployeePersonalData);
            employee.setEmployeePersonalData(seventhEmployeePersonalData);
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
            employee.setEmployeeProfessionalData(seventhEmployeeProfessionalData);
            employeeRepository.save(employee);
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
