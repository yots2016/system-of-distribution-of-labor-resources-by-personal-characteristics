package com.distributionsystem.repository;

import com.distributionsystem.model.EmployeePersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePersonalDataRepository extends JpaRepository<EmployeePersonalData, Long> {
}
