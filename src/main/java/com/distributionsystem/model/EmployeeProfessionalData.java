package com.distributionsystem.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(exclude = {"employee", "commonProfessionalData", "weightingFactor"})
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "employee_professional_data")
public class EmployeeProfessionalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "common_professional_data_id", nullable = false)
    private CommonProfessionalData commonProfessionalData;

    @ManyToOne
    @JoinColumn(name = "weighting_factor_id", nullable = false)
    private WeightingFactor weightingFactor;
}
