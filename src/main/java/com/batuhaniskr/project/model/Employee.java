package com.batuhaniskr.project.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = {"project", "employeePersonalDataSet", "employeeProfessionalDataSet"})
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "position", nullable = false)
    private String position;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(nullable = false)
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeePersonalData> employeePersonalDataSet;

    @Column(nullable = false)
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeeProfessionalData> employeeProfessionalDataSet;

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
}
