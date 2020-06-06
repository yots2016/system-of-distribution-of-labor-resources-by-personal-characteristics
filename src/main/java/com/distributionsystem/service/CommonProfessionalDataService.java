package com.distributionsystem.service;

import com.distributionsystem.model.CommonProfessionalData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonProfessionalDataService {

    public List<CommonProfessionalData> getAllCommonProfessionalData();

    public Page<CommonProfessionalData> getAllCommonProfessionalData(Pageable pageable);
}
