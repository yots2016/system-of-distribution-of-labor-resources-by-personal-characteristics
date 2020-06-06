package com.distributionsystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = {"employeePersonalDataSet", "projectEmployeeRolePersonalDataSet"})
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "common_personal_data")
public class CommonPersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, length = 4000)
    private String description;

    @OneToMany(mappedBy = "commonPersonalData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeePersonalData> employeePersonalDataSet;

    @OneToMany(mappedBy = "commonPersonalData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectEmployeeRolePersonalData> projectEmployeeRolePersonalDataSet;

    public void setEmployeePersonalDataSet(EmployeePersonalData employeePersonalData) {
        if (employeePersonalDataSet == null) {
            employeePersonalDataSet = new HashSet<>();
        }
        employeePersonalDataSet.add(employeePersonalData);
    }

    public void setProjectEmployeeRolePersonalDataSet(ProjectEmployeeRolePersonalData projectEmployeeRolePersonalData) {
        if (projectEmployeeRolePersonalDataSet == null) {
            projectEmployeeRolePersonalDataSet = new HashSet<>();
        }
        projectEmployeeRolePersonalDataSet.add(projectEmployeeRolePersonalData);
    }
}
