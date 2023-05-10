package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.example.dto.ProjectDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Employee {
	@Id
	private String employeeId;
	private String employeeName;
	private String employeeEmail;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Skills> employeeSkills;
	@Transient
	private List<ProjectDto> project = new ArrayList<>();
	@ElementCollection
	private List<String> projectId = new ArrayList<>();
}
