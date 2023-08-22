package com.example.CRUDManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.CRUDManagement.model.ModulEmployee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<ModulEmployee, Long> {

}
