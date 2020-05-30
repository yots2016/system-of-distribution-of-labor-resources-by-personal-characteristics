package com.batuhaniskr.project.model;

import lombok.*;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "weightingFactor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeePersonalData> employeePersonalDataSet;

    @Column(nullable = false)
    @OneToMany(mappedBy = "weightingFactor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeeProfessionalData> employeeProfessionalDataSet;

    @Column(nullable = false)
    @OneToMany(mappedBy = "weightingFactor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectEmployeeRolePersonalData> projectEmployeeRolePersonalDataSet;

    @Column(nullable = false)
    @OneToMany(mappedBy = "weightingFactor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectEmployeeRoleProfessionalData> projectEmployeeRoleProfessionalDataSet;
}
