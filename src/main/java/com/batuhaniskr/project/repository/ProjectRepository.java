package com.batuhaniskr.project.repository;

import com.batuhaniskr.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
