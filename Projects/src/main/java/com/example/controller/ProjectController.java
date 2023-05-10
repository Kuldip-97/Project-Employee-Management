package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EmployeeDto;
import com.example.dto.ProjectDto;
import com.example.service.ProjectService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
	@Autowired
	ProjectService projectService;
	
	
	@GetMapping
	public ResponseEntity<List<ProjectDto>> getAllProjects(){
		List<ProjectDto> allProjects = projectService.getAllProjectsFromDB();
		return new ResponseEntity<List<ProjectDto>>(allProjects, HttpStatus.OK);
	}
	
	@GetMapping("/{projectId}")
	//@CircuitBreaker(name = "projectDtoCircuitBreaker", fallbackMethod = "projectDtoFallbackMethod")
	//@Retry(name = "projectDtoRetry", fallbackMethod = "projectDtoFallbackMethod")
	@RateLimiter(name = "projectDtoRateLimiter", fallbackMethod = "projectDtoFallbackMethod")
	public ResponseEntity<ProjectDto> getSpecificProject(@PathVariable String projectId){
		ProjectDto projectByID = projectService.getProjectByIDfromDB(projectId);
		return new ResponseEntity<ProjectDto>(projectByID, HttpStatus.OK);
	}
	
	@GetMapping("/name/{projectClientName}")
	public ResponseEntity<List<ProjectDto>> getAllProjects(@PathVariable String projectClientName){
		List<ProjectDto> allProjectsByClientName = projectService.getAllProjectsByClientName(projectClientName);
		return new ResponseEntity<List<ProjectDto>>(allProjectsByClientName, HttpStatus.OK);
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<List<ProjectDto>> getAllProjectsOfUser(@PathVariable String employeeId){
		List<ProjectDto> allProjectsOfSpecificEmployee = projectService.getAllProjectsByEmployeeId(employeeId);
		return new ResponseEntity<List<ProjectDto>>(allProjectsOfSpecificEmployee, HttpStatus.OK);
	}
	
	@GetMapping("/reqemp/{projectId}")
	public ResponseEntity<List<EmployeeDto>> getRequiredEmployeeForProject(@PathVariable String projectId){
		List<EmployeeDto> empByReqSkill = projectService.getEmpByReqSkill(projectId);
		return new ResponseEntity<List<EmployeeDto>>(empByReqSkill, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto){
		ProjectDto projectInserted = projectService.insertProjectInDB(projectDto);
		return new ResponseEntity<ProjectDto>(projectInserted, HttpStatus.CREATED);
	}
	
	@PutMapping("/{projectId}")
	public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto, @PathVariable String projectId){
		ProjectDto projectUpdated = projectService.updateProjectInDB(projectDto, projectId);
		return new ResponseEntity<ProjectDto>(projectUpdated, HttpStatus.OK);
	}
	
	@PutMapping("/{projectId}/employee")
	public ResponseEntity<ProjectDto> assignEmployeeToProject(@RequestBody ProjectDto projectDto, @PathVariable String projectId){
		ProjectDto employeeAssigned = projectService.assignEmployeeToProject(projectDto, projectId);
		return new ResponseEntity<ProjectDto>(employeeAssigned, HttpStatus.OK);
	}
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<String> deleteProject(@PathVariable String projectId){
		String projectDeleted = projectService.deleteProjectFromDB(projectId);
		return new ResponseEntity<String>(projectDeleted, HttpStatus.OK);
	}
	
	public ResponseEntity<ProjectDto> projectDtoFallbackMethod(Exception ex){
		ex.getMessage();
		ProjectDto projDto = projectService.noProject();
		ex.printStackTrace();
		return new ResponseEntity<ProjectDto>(projDto, HttpStatus.FAILED_DEPENDENCY);
	}
}
