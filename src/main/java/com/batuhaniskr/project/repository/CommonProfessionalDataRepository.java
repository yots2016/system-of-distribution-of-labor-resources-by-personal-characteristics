package com.batuhaniskr.project.repository;

import com.batuhaniskr.project.model.CommonProfessionalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonProfessionalDataRepository extends JpaRepository<CommonProfessionalData, Long> {

    CommonProfessionalData findByDescription(String description);
}
