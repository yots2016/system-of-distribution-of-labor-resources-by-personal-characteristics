package com.distributionsystem.service.impl;

import com.distributionsystem.model.CommonPersonalData;
import com.distributionsystem.repository.CommonPersonalDataRepository;
import com.distributionsystem.service.CommonPersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommonPersonalDataServiceImpl implements CommonPersonalDataService {

    private final CommonPersonalDataRepository commonPersonalDataRepository;

    public Page<CommonPersonalData> getAllCommonPersonalData(Pageable pageable) {
        return commonPersonalDataRepository.findAll(pageable);
    }
}
