package com.distributionsystem.repository;

import com.distributionsystem.model.CommonProfessionalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonProfessionalDataRepository extends JpaRepository<CommonProfessionalData, Long> {

    CommonProfessionalData findByDescription(String description);
}
