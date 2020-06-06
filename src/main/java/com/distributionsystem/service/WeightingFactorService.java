package com.distributionsystem.service;

import com.distributionsystem.model.WeightingFactor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WeightingFactorService {

    List<WeightingFactor> getAllWeightingFactor();

    Page<WeightingFactor> getAllEmployees(Pageable pageable);
}
