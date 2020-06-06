package com.distributionsystem.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(exclude = {"employee", "weightingFactor", "commonPersonalData"})
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "employee_personal_data")
public class EmployeePersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "weighting_factor_id", nullable = false)
    private WeightingFactor weightingFactor;

    @ManyToOne
    @JoinColumn(name = "common_personal_data_id", nullable = false)
    private CommonPersonalData commonPersonalData;
}
