package com.distributionsystem.service;

import com.distributionsystem.model.CommonPersonalData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonPersonalDataService {

    Page<CommonPersonalData> getAllCommonPersonalData(Pageable pageable);

}
