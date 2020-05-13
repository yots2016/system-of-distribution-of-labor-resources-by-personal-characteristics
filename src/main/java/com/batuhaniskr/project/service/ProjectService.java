package com.batuhaniskr.project.service;

import com.batuhaniskr.project.model.Category;
import com.batuhaniskr.project.model.Project;
import com.batuhaniskr.project.repository.CategoryRepository;
import com.batuhaniskr.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, CategoryRepository categoryRepository) {
        this.projectRepository = projectRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public void saveProject(Project project) {
        Category category = categoryRepository.findByCategoryName(project.getCategory().getCategoryName());
        project.setCategory(category);
        projectRepository.save(project);
    }

    public void deleteProject(Integer id) {
        projectRepository.delete(id);
    }

    public Project getProjectById(Integer id) {
        return projectRepository.findOne(id);
    }
}
