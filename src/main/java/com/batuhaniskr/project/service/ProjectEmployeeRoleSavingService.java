package com.batuhaniskr.project.service;

import com.batuhaniskr.project.dto.NewProjectDto;
import com.batuhaniskr.project.model.Project;

public interface ProjectEmployeeRoleSavingService {

    public void saveProjectEmployeeRole(NewProjectDto newProjectDto, Project project);

}
