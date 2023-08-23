package com.example.CRUDManagement.service;

import com.example.CRUDManagement.model.ModulEmployee;
import com.example.CRUDManagement.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public List<ModulEmployee> getAllEmployees() {
        List<ModulEmployee> employeeList = new ArrayList<>();
        employeeRepo.findAll().forEach(employeeList::add);
        return employeeList;
    }

    @Override
    public Optional<ModulEmployee> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    @Override
    public ModulEmployee addEmployee(ModulEmployee modulEmployee) {
        return employeeRepo.save(modulEmployee);
    }

    @Override
    public Optional<ModulEmployee> updateEmployeeById(Long id, ModulEmployee newEmployeeData) {
        Optional<ModulEmployee> oldEmployeeData = employeeRepo.findById(id);
        if (oldEmployeeData.isPresent()) {
            ModulEmployee updateEmployeeData = oldEmployeeData.get();
            updateEmployeeData.setDevisi(newEmployeeData.getDevisi());
            updateEmployeeData.setName(newEmployeeData.getName());
            return Optional.of(employeeRepo.save(updateEmployeeData));
        }
        return Optional.empty();
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepo.deleteById(id);
    }
}
