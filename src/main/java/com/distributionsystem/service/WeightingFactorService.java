package com.distributionsystem.service;

import com.distributionsystem.model.WeightingFactor;
import com.distributionsystem.repository.WeightingFactorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WeightingFactorService {

    private final WeightingFactorRepository weightingFactorRepository;

    public List<WeightingFactor> getAllWeightingFactor() {
        return weightingFactorRepository.findAll();
    }

    public Page<WeightingFactor> getAllEmployees(Pageable pageable) {
        return weightingFactorRepository.findAll(pageable);
    }
}
