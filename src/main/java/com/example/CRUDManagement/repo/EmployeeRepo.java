package com.example.CRUDManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.CRUDManagement.model.ModulEmployee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<ModulEmployee, Long> {

    @Query(value = "select *from employees  order by id desc limit 1 ", nativeQuery = true)
    public ModulEmployee  findLatestEmployee();
}
