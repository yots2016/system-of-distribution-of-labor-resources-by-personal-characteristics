package com.batuhaniskr.project.service;

import com.batuhaniskr.project.model.CommonPersonalData;
import com.batuhaniskr.project.repository.CommonPersonalDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommonPersonalDataService {

    private final CommonPersonalDataRepository commonPersonalDataRepository;

    public List<CommonPersonalData> getAllCommonPersonalData() {
        return commonPersonalDataRepository.findAll();
    }

    public Page<CommonPersonalData> getAllEmployees(Pageable pageable) {
        return commonPersonalDataRepository.findAll(pageable);
    }
}
