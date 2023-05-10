package com.example.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.example.dto.EmployeeDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Project {
	@Id
	private String projectId;
	private String projectName;
	private String projectClientName;
	private String projectDescription;
	@Column(name = "is_project_completed")
	private boolean projectStatus;
	private LocalDate projectStartDate;
	private LocalDate projectExpiryDate;
	@ElementCollection
	private List<String> projectTechStacks = new ArrayList<>();
	@ElementCollection
	private List<String> employeeId = new ArrayList<>();
	@Transient
	private List<EmployeeDto> employee = new ArrayList<>();
}
