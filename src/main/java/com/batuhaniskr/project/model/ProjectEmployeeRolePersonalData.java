package com.batuhaniskr.project.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(exclude = {"projectEmployeeRole", "weightingFactor", "commonPersonalData"})
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "project_employee_role_personal_data")
public class ProjectEmployeeRolePersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_employee_role_id", nullable = false)
    private ProjectEmployeeRole projectEmployeeRole;

    @ManyToOne
    @JoinColumn(name = "weighting_factor_id", nullable = false)
    private WeightingFactor weightingFactor;

    @ManyToOne
    @JoinColumn(name = "common_personal_data_id", nullable = false)
    private CommonPersonalData commonPersonalData;
}
