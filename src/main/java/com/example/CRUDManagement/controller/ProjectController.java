package com.example.CRUDManagement.controller;

import com.example.CRUDManagement.model.ModulProject;
import com.example.CRUDManagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("getAllProject")
    public ResponseEntity<List<ModulProject>> getAllProject() {
        List<ModulProject> projectList = projectService.getAllProjects();
        if (projectList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @GetMapping("/getProjectById/{id}")
    public ResponseEntity<ModulProject> getProjectById(@PathVariable Long id) {
        Optional<ModulProject> projectData = projectService.getProjectById(id);
        return projectData.map(project -> new ResponseEntity<>(project, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/addProject")
    public ResponseEntity<ModulProject> addProject(@RequestBody ModulProject modulProject) {
        ModulProject projectObj = projectService.addProject(modulProject);
        return new ResponseEntity<>(projectObj, HttpStatus.OK);
    }

    @PostMapping("/updateProjectById/{id}")
    public ResponseEntity<ModulProject> updateProjectById(@PathVariable Long id, @RequestBody ModulProject newProjectData) {
        Optional<ModulProject> projectObj = projectService.updateProjectById(id, newProjectData);
        return projectObj.map(project -> new ResponseEntity<>(project, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
