package com.distributionsystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = {"employeeProfessionalDataSet", "projectEmployeeRoleProfessionalDataSet"})
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Data
@Entity
@Table(name = "common_professional_data")
public class CommonProfessionalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, length = 4000)
    private String description;

    @OneToMany(mappedBy = "commonProfessionalData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeeProfessionalData> employeeProfessionalDataSet;

    @OneToMany(mappedBy = "commonProfessionalData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectEmployeeRoleProfessionalData> projectEmployeeRoleProfessionalDataSet;

    public void setEmployeeProfessionalDataSet(EmployeeProfessionalData employeeProfessionalData) {
        if (employeeProfessionalDataSet == null) {
            employeeProfessionalDataSet = new HashSet<>();
        }
        employeeProfessionalDataSet.add(employeeProfessionalData);
    }

    public void setProjectEmployeeRoleProfessionalDataSet(ProjectEmployeeRoleProfessionalData projectEmployeeRoleProfessionalData) {
        if (projectEmployeeRoleProfessionalDataSet == null) {
            projectEmployeeRoleProfessionalDataSet = new HashSet<>();
        }
        projectEmployeeRoleProfessionalDataSet.add(projectEmployeeRoleProfessionalData);
    }
}
