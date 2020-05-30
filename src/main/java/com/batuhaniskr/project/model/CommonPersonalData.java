package com.batuhaniskr.project.model;

import lombok.*;

import javax.persistence.*;
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

    @Column(nullable = false)
    @OneToMany(mappedBy = "commonPersonalData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeePersonalData> employeePersonalDataSet;

    @Column(nullable = false)
    @OneToMany(mappedBy = "commonPersonalData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectEmployeeRolePersonalData> projectEmployeeRolePersonalDataSet;
}
