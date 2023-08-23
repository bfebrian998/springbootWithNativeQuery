package com.example.CRUDManagement.controller;

import com.example.CRUDManagement.model.ModulEmployee;
import com.example.CRUDManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("getAllEmployee")
    public ResponseEntity<List<ModulEmployee>> getAllEmployee() {
        List<ModulEmployee> employeeList = employeeService.getAllEmployees();
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<ModulEmployee> getEmployeeById(@PathVariable Long id) {
        Optional<ModulEmployee> employeeData = employeeService.getEmployeeById(id);
        return employeeData.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<ModulEmployee> addEmployee(@RequestBody ModulEmployee modulEmployee) {
        ModulEmployee employeeObj = employeeService.addEmployee(modulEmployee);
        return new ResponseEntity<>(employeeObj, HttpStatus.OK);
    }

    @PostMapping("/updateEmployeeById/{id}")
    public ResponseEntity<ModulEmployee> updateEmployeeById(@PathVariable Long id, @RequestBody ModulEmployee newEmployeeData) {
        Optional<ModulEmployee> employeeObj = employeeService.updateEmployeeById(id, newEmployeeData);
        return employeeObj.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/deleteEmployeeById/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
