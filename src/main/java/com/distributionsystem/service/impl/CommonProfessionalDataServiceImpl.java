package com.distributionsystem.service.impl;

import com.distributionsystem.model.CommonProfessionalData;
import com.distributionsystem.repository.CommonProfessionalDataRepository;
import com.distributionsystem.service.CommonProfessionalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommonProfessionalDataServiceImpl implements CommonProfessionalDataService {

    private final CommonProfessionalDataRepository commonProfessionalDataRepository;

    @Override
    public List<CommonProfessionalData> getAllCommonProfessionalData() {
        return commonProfessionalDataRepository.findAll();
    }

    @Override
    public Page<CommonProfessionalData> getAllCommonProfessionalData(Pageable pageable) {
        return commonProfessionalDataRepository.findAll(pageable);
    }
}
