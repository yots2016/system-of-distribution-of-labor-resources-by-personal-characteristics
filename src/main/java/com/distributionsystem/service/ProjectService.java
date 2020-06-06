package com.distributionsystem.service;

import com.distributionsystem.dto.NewProjectDto;
import com.distributionsystem.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProjectService {

    Optional<Project> getProjectById(Long id);

    Page<Project> getAllProjects(Pageable pageable);

    void saveProject(NewProjectDto newProjectDto);

    void saveProject(Project project);

    void deleteProject(Long id);
}
