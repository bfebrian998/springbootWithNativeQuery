package com.example.CRUDManagement.service;

import com.example.CRUDManagement.model.ModulEmployee;
import com.example.CRUDManagement.repo.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public List<ModulEmployee> getAllEmployees() {
        List<ModulEmployee> employeeList = employeeRepo.findAll();
        List<ModulEmployee> objEmployee = new ArrayList<>();
        try {
            employeeList.forEach(data -> objEmployee.add(data));
        }
        catch (Exception e) {
            log.error("data not found", e);
        }
        return employeeList;
    }

    @Override
    public Optional<ModulEmployee> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    @Override
    public ModulEmployee addEmployee(ModulEmployee modulEmployee) {
        return employeeRepo.saveAndFlush(modulEmployee);
    }

    @Override
    public Optional<ModulEmployee> updateEmployeeById(Long id, ModulEmployee newEmployeeData) {
        Optional<ModulEmployee> oldEmployeeData = employeeRepo.findById(id);
        if (oldEmployeeData.isPresent()) {
            ModulEmployee updateEmployeeData = ModulEmployee.builder()
                    .id(id)
                    .devisi(newEmployeeData.getDevisi())
                    .name(newEmployeeData.getName())
                    .build();
            return Optional.of(employeeRepo.saveAndFlush(updateEmployeeData));
        }
        return Optional.empty();
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public  ModulEmployee getMaxEmployee (){
        ModulEmployee dataMax = employeeRepo.findLatestEmployee();
        return  dataMax;
    }
}
