package com.distributionsystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = {"project", "projectEmployeeRolePersonalDataSet",
        "projectEmployeeRoleProfessionalDataSet", "projectEmployeeSet"})
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

    @OneToMany(mappedBy = "projectEmployeeRole", cascade = CascadeType.ALL)
    private Set<ProjectEmployeeRolePersonalData> projectEmployeeRolePersonalDataSet = new HashSet<>();

    @OneToMany(mappedBy = "projectEmployeeRole", cascade = CascadeType.ALL)
    private Set<ProjectEmployeeRoleProfessionalData> projectEmployeeRoleProfessionalDataSet = new HashSet<>();

    @OneToMany(mappedBy = "projectEmployeeRole")
    private Set<Employee> projectEmployeeSet = new HashSet<>();

    public void setEmployee(Employee employee) {
        this.projectEmployeeSet.add(employee);
    }

    public void setProjectEmployeeRolePersonalData(ProjectEmployeeRolePersonalData projectEmployeeRolePersonalData) {
        this.projectEmployeeRolePersonalDataSet.add(projectEmployeeRolePersonalData);
    }

    public void setProjectEmployeeRoleProfessionalData(ProjectEmployeeRoleProfessionalData projectEmployeeRoleProfessionalData) {
        this.projectEmployeeRoleProfessionalDataSet.add(projectEmployeeRoleProfessionalData);
    }
}
