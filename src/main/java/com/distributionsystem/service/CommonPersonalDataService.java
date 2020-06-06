package com.distributionsystem.service;

import com.distributionsystem.model.CommonPersonalData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonPersonalDataService {

    Page<CommonPersonalData> getAllCommonPersonalData(Pageable pageable);

    List<CommonPersonalData> getAllCommonPersonalData();
}
