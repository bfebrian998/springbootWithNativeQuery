package com.example.CRUDManagement.service;

import com.example.CRUDManagement.model.ModulEmployee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<ModulEmployee> getAllEmployees();
    Optional<ModulEmployee> getEmployeeById(Long id);
    ModulEmployee addEmployee(ModulEmployee modulEmployee);
    Optional<ModulEmployee> updateEmployeeById(Long id, ModulEmployee newEmployeeData);
    void deleteEmployeeById(Long id);
}
