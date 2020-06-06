package com.distributionsystem.repository;

import com.distributionsystem.model.CommonPersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonPersonalDataRepository extends JpaRepository<CommonPersonalData, Long> {

    CommonPersonalData findByDescription(String description);
}
