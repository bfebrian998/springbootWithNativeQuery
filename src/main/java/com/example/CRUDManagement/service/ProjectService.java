package com.example.CRUDManagement.service;
import com.example.CRUDManagement.model.ModulProject;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<ModulProject> getAllProjects();
    Optional<ModulProject> getProjectById(Long id);
    ModulProject addProject(ModulProject modulProject);
    Optional<ModulProject> updateProjectById(Long id, ModulProject newProjectData);
}
