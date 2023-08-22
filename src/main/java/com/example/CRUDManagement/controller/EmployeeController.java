package com.example.CRUDManagement.controller;

import com.example.CRUDManagement.model.ModulEmployee;
import com.example.CRUDManagement.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
     EmployeeRepo employeeRepo;


    @GetMapping("getAllEmployee")
    public ResponseEntity<List<ModulEmployee>> getAllEmployee() {
    try{
        List<ModulEmployee> employeeList = new ArrayList<>();
        employeeRepo.findAll().forEach(employeeList::add);
        if (employeeList.isEmpty()){
            return  new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(employeeList, HttpStatus.OK);
    } catch (Exception ex){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @GetMapping("/getEmployeeById/{id}")
    public  ResponseEntity<ModulEmployee> getEmployeeById(@PathVariable Long id){
        Optional<ModulEmployee> employeeData = employeeRepo.findById(id);

        if (employeeData.isPresent()){
            return  new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<ModulEmployee> addEmployee(@RequestBody ModulEmployee modulEmployee){
        try {
            ModulEmployee employeeObj = employeeRepo.save(modulEmployee);
            return new ResponseEntity<>(employeeObj, HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/updateEmployeeById/{id}")
    public  ResponseEntity<ModulEmployee> updateEmployeeById(@PathVariable Long id, @RequestBody ModulEmployee newEmployeeData){
    Optional<ModulEmployee> oldEmployeeData = employeeRepo.findById(id);
    if (oldEmployeeData.isPresent()){
        ModulEmployee updateEmployeeData = oldEmployeeData.get();
        updateEmployeeData.setDevisi(newEmployeeData.getDevisi());
        updateEmployeeData.setName(newEmployeeData.getName());

        ModulEmployee employeeObj = employeeRepo.save(updateEmployeeData);
        return new ResponseEntity<>(employeeObj, HttpStatus.OK);
       }
    return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteEmployeeById{id}")
    public  ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable Long id){
        employeeRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
