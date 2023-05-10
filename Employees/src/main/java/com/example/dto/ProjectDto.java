package com.example.dto;

import java.time.LocalDate;
import java.util.List;

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
public class ProjectDto {
	private String projectId;
	private String projectName;
	private String projectClientName;
	private String projectDescription;
	private boolean projectStatus;
	private LocalDate projectStartDate;
	private LocalDate projectExpiryDate;
	private List<String> projectTechStacks;
}
