package com.distributionsystem.repository;

import com.distributionsystem.model.WeightingFactor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightingFactorRepository extends JpaRepository<WeightingFactor, Long> {

    WeightingFactor findByWeightingFactor(Float weightingFactor);
}
