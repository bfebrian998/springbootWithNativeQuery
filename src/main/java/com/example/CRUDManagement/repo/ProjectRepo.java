package com.example.CRUDManagement.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.CRUDManagement.model.ModulProject;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<ModulProject, Long>{
}
