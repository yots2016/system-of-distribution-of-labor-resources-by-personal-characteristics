package com.distributionsystem.service;

import com.distributionsystem.model.CommonProfessionalData;
import com.distributionsystem.repository.CommonProfessionalDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommonProfessionalDataService {

    private final CommonProfessionalDataRepository commonProfessionalDataRepository;

    public List<CommonProfessionalData> getAllCommonProfessionalData() {
        return commonProfessionalDataRepository.findAll();
    }

    public Page<CommonProfessionalData> getAllEmployees(Pageable pageable) {
        return commonProfessionalDataRepository.findAll(pageable);
    }
}
