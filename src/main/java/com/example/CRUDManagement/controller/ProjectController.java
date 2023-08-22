package com.example.CRUDManagement.controller;
import com.example.CRUDManagement.model.ModulProject;
import com.example.CRUDManagement.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    ProjectRepo projectRepo;
    @GetMapping("getAllProject")
    public ResponseEntity<List<ModulProject>> getAllProject() {
        try{
            List<ModulProject>  projectList = new ArrayList<>();
            projectRepo.findAll().forEach(projectList::add);
            if (projectList.isEmpty()){
                return  new ResponseEntity<>( HttpStatus.NO_CONTENT);
            }
            return  new ResponseEntity<>(projectList, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getProjectById/{id}")
    public  ResponseEntity<ModulProject> getProjectById(@PathVariable Long id){
        Optional<ModulProject> projectData = projectRepo.findById(id);

        if (projectData.isPresent()){
            return  new ResponseEntity<>(projectData.get(), HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addProject")
    public ResponseEntity<ModulProject> addProject(@RequestBody ModulProject modulProject){
        try {
            ModulProject projectObj = projectRepo.save(modulProject);
            return new ResponseEntity<>(projectObj, HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping("/updateProjectById/{id}")
    public  ResponseEntity<ModulProject> updateEmployeeById(@PathVariable Long id, @RequestBody ModulProject newEProjectData){
        Optional<ModulProject> oldProjectData = projectRepo.findById(id);
        if (oldProjectData.isPresent()){
            ModulProject updateProjectData = oldProjectData.get();
            updateProjectData.setListProject(newEProjectData.getListProject());
            updateProjectData.setCompany(newEProjectData.getCompany());

            ModulProject projectObj = projectRepo.save(updateProjectData);
            return new ResponseEntity<>(projectObj, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
