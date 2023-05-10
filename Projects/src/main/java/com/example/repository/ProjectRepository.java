package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
	
	List<Project> findByProjectClientName(String projectClientName);
	List<Project> findByEmployeeId(String employeeId);
	
}
