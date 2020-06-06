package com.distributionsystem.service;

import com.distributionsystem.dto.NewProjectDto;
import com.distributionsystem.model.Project;

public interface ProjectEmployeeRoleSavingService {

    public void saveProjectEmployeeRole(NewProjectDto newProjectDto, Project project);

}
