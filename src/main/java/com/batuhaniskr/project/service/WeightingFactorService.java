package com.batuhaniskr.project.service;

import com.batuhaniskr.project.model.WeightingFactor;
import com.batuhaniskr.project.repository.WeightingFactorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WeightingFactorService {

    private final WeightingFactorRepository weightingFactorRepository;

    public List<WeightingFactor> getAllWeightingFactor() {
        return weightingFactorRepository.findAll();
    }
}