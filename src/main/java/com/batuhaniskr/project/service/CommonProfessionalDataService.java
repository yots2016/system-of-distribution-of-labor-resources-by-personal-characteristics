package com.batuhaniskr.project.service;

import com.batuhaniskr.project.model.CommonProfessionalData;
import com.batuhaniskr.project.repository.CommonProfessionalDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommonProfessionalDataService {

    private final CommonProfessionalDataRepository commonProfessionalDataRepository;

    public List<CommonProfessionalData> getAllCommonProfessionalData() {
        return commonProfessionalDataRepository.findAll();

    }
}
