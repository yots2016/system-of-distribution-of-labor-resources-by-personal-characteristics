package com.batuhaniskr.project.repository;

import com.batuhaniskr.project.model.EmployeePersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePersonalDataRepository extends JpaRepository<EmployeePersonalData, Long> {
}
