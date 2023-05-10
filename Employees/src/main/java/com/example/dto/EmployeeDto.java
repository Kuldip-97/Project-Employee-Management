package com.example.dto;

import java.util.ArrayList;
import java.util.List;

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
public class EmployeeDto {
	private String employeeId;
	private String employeeName;
	private String employeeEmail;
	private List<SkillsDto> employeeSkills;
	private List<ProjectDto> project = new ArrayList<>();
}
