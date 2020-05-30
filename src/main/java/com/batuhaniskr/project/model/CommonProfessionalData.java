package com.batuhaniskr.project.model;

import lombok.*;

import javax.persistence.*;
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

    @Column(nullable = false)
    @OneToMany(mappedBy = "commonProfessionalData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeeProfessionalData> employeeProfessionalDataSet;

    @Column(nullable = false)
    @OneToMany(mappedBy = "commonProfessionalData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProjectEmployeeRoleProfessionalData> projectEmployeeRoleProfessionalDataSet;
}
