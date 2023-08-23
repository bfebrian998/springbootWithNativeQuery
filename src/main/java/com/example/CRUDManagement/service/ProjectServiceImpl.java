package com.example.CRUDManagement.service;
import com.example.CRUDManagement.model.ModulProject;
import com.example.CRUDManagement.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Override
    public List<ModulProject> getAllProjects() {
        return projectRepo.findAll();
    }

    @Override
    public Optional<ModulProject> getProjectById(Long id) {
        return projectRepo.findById(id);
    }

    @Override
    public ModulProject addProject(ModulProject modulProject) {
        return projectRepo.save(modulProject);
    }

    @Override
    public Optional<ModulProject> updateProjectById(Long id, ModulProject newProjectData) {
        Optional<ModulProject> oldProjectData = projectRepo.findById(id);
        if (oldProjectData.isPresent()) {
            ModulProject updateProjectData = oldProjectData.get();
            updateProjectData.setListProject(newProjectData.getListProject());
            updateProjectData.setCompany(newProjectData.getCompany());
            return Optional.of(projectRepo.save(updateProjectData));
        }
        return Optional.empty();
    }
}
