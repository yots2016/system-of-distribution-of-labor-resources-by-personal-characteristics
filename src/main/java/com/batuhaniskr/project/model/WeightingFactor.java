package com.batuhaniskr.project.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = {"employeePersonalDataSet", "employeeProfessionalDataSet",
        "projectEmployeeRolePersonalDataSet", "projectEmployeeRoleProfessionalDataSet"})
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "weighting_factor")
public class WeightingFactor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 4000)
    private String name;

    @Column(name = "weighting_factor", nullable = false)
    private Short weightingFactor;

    @Column(nullable = false)
    @OneToMany(mappedBy = "weightingFactor", cascade = CascadeType.ALL)
    private Set<EmployeePersonalData> employeePersonalDataSet;

    @Column(nullable = false)
    @OneToMany(mappedBy = "weightingFactor", cascade = CascadeType.ALL)
    private Set<EmployeeProfessionalData> employeeProfessionalDataSet;

    @Column(nullable = false)
    @OneToMany(mappedBy = "weightingFactor", cascade = CascadeType.ALL)
    private Set<ProjectEmployeeRolePersonalData> projectEmployeeRolePersonalDataSet;

    @Column(nullable = false)
    @OneToMany(mappedBy = "weightingFactor", cascade = CascadeType.ALL)
    private Set<ProjectEmployeeRoleProfessionalData> projectEmployeeRoleProfessionalDataSet;

    public void setEmployeePersonalDataSet(EmployeePersonalData employeePersonalData) {
        if (employeePersonalDataSet == null) {
            employeePersonalDataSet = new HashSet<>();
        }
        this.employeePersonalDataSet.add(employeePersonalData);
    }

    public void setEmployeeProfessionalDataSet(EmployeeProfessionalData employeeProfessionalData) {
        if (employeeProfessionalDataSet == null) {
            employeeProfessionalDataSet = new HashSet<>();
        }
        this.employeeProfessionalDataSet.add(employeeProfessionalData);
    }

    public void setProjectEmployeeRolePersonalDataSet(ProjectEmployeeRolePersonalData projectEmployeeRolePersonalData) {
        if (projectEmployeeRolePersonalDataSet == null) {
            projectEmployeeRolePersonalDataSet = new HashSet<>();
        }
        this.projectEmployeeRolePersonalDataSet.add(projectEmployeeRolePersonalData);
    }

    public void setProjectEmployeeRoleProfessionalDataSet(ProjectEmployeeRoleProfessionalData projectEmployeeRoleProfessionalData) {
        if (projectEmployeeRoleProfessionalDataSet == null) {
            projectEmployeeRoleProfessionalDataSet = new HashSet<>();
        }
        this.projectEmployeeRoleProfessionalDataSet.add(projectEmployeeRoleProfessionalData);
    }
}
