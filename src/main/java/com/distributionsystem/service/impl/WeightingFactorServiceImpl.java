package com.distributionsystem.service.impl;

import com.distributionsystem.model.WeightingFactor;
import com.distributionsystem.repository.WeightingFactorRepository;
import com.distributionsystem.service.WeightingFactorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WeightingFactorServiceImpl implements WeightingFactorService {

    private final WeightingFactorRepository weightingFactorRepository;

    @Override
    public List<WeightingFactor> getAllWeightingFactor() {
        return weightingFactorRepository.findAll();
    }

    @Override
    public Page<WeightingFactor> getAllEmployees(Pageable pageable) {
        return weightingFactorRepository.findAll(pageable);
    }
}
