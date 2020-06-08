package com.distributionsystem.utils;

import com.distributionsystem.model.Employee;
import com.distributionsystem.model.ProjectEmployeeRole;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CharacteristicsExtractingUtils {

    public static Map<String, Float> extractAllEmployeeCharacteristics(Employee employee) {
        Map<String, Float> personalCharacteristics = employee.getEmployeePersonalDataSet().stream()
                .map(employeePersonalData -> Pair.of(employeePersonalData.getCommonPersonalData().getDescription(),
                        employeePersonalData.getWeightingFactor().getWeightingFactor()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond,
                        CharacteristicsExtractingUtils::mergeEmployeesCharacteristics));
        Map<String, Float> professionalCharacteristics = employee.getEmployeeProfessionalDataSet().stream()
                .map(employeeProfessionalData -> Pair.of(
                        employeeProfessionalData.getCommonProfessionalData().getDescription(),
                        employeeProfessionalData.getWeightingFactor().getWeightingFactor()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond, CharacteristicsExtractingUtils::mergeEmployeesCharacteristics));
        return Stream.concat(professionalCharacteristics.entrySet().stream(),
                personalCharacteristics.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, Float> extractAllRoleCharacteristics(ProjectEmployeeRole projectEmployeeRole) {
        Map<String, Float> rolePersonalCharacteristics = projectEmployeeRole.getProjectEmployeeRolePersonalDataSet()
                .stream()
                .map(projectEmployeeRolePersonalData -> Pair.of(
                        projectEmployeeRolePersonalData.getCommonPersonalData().getDescription(),
                        projectEmployeeRolePersonalData.getWeightingFactor().getWeightingFactor()))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond,
                        CharacteristicsExtractingUtils::mergeProjectEmployeeRolesCharacteristics));
        Map<String, Float> roleProfessionalCharacteristics =
                projectEmployeeRole.getProjectEmployeeRoleProfessionalDataSet().stream()
                        .map(data -> Pair.of(data.getCommonProfessionalData().getDescription(),
                                data.getWeightingFactor().getWeightingFactor()))
                        .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond,
                                CharacteristicsExtractingUtils::mergeProjectEmployeeRolesCharacteristics));
        return Stream.concat(rolePersonalCharacteristics.entrySet().stream(), roleProfessionalCharacteristics.entrySet()
                .stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static float mergeEmployeesCharacteristics(float float1, float float2) {
        return Math.max(float1, float2);
    }

    private static float mergeProjectEmployeeRolesCharacteristics(float float1, float float2) {
        return Math.min(float1, float2);
    }
}
