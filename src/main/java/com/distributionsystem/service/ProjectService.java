package com.distributionsystem.service;

import com.distributionsystem.dto.NewProjectDto;
import com.distributionsystem.model.Category;
import com.distributionsystem.model.Project;
import com.distributionsystem.repository.CategoryRepository;
import com.distributionsystem.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final CategoryRepository categoryRepository;

    private final RecruitmentService recruitmentService;
    private final List<ProjectEmployeeRoleSavingService> projectEmployeeRoleSavingServices;

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public void saveProject(NewProjectDto newProjectDto) {
        Project project = constructProject(newProjectDto);
        Category category = categoryRepository.findByCategoryName(newProjectDto.getCategoryName());
        project.setCategory(category);
        projectRepository.save(project);
        category.addProject(project);

        saveAllEmployeesRoles(newProjectDto, project);

        this.recruitmentService.selectEmployeesForProject(project);
    }

    private Project constructProject(NewProjectDto newProjectDto) {
        Project project = new Project();
        project.setName(newProjectDto.getName());
        project.setPrice(newProjectDto.getPrice());
        project.setQuantity(newProjectDto.getQuantity());
        return project;
    }

    private void saveAllEmployeesRoles(NewProjectDto newProjectDto, Project project) {
        projectEmployeeRoleSavingServices.forEach(projectEmployeeRoleSavingService ->
                projectEmployeeRoleSavingService.saveProjectEmployeeRole(newProjectDto, project));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}