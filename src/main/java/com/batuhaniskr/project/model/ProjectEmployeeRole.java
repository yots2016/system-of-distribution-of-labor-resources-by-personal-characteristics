package com.batuhaniskr.project.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(exclude = {"project", "projectEmployeeRolePersonalDataSet", "projectEmployeeRoleProfessionalDataSet"})
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "project_employee_role")
public class ProjectEmployeeRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_role", nullable = false, length = 400)
    private String employeeRole;

    @Column(name = "employees_number", nullable = false)
    private Long employeesNumber;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    @OneToMany(mappedBy = "projectEmployeeRole", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectEmployeeRolePersonalData> projectEmployeeRolePersonalDataSet;

    @Column(nullable = false)
    @OneToMany(mappedBy = "projectEmployeeRole", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectEmployeeRoleProfessionalData> projectEmployeeRoleProfessionalDataSet;
}
