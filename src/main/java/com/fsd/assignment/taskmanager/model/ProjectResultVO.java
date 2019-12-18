package com.fsd.assignment.taskmanager.model;

import java.util.List;

import com.fsd.assignment.taskmanager.entity.ProjectEntity;

public class ProjectResultVO {
	
	private ProjectEntity data;
	
	private String errorMsg;
	
	private List<ProjectEntity> projectList;

	public ProjectEntity getData() {
		return data;
	}

	public void setData(ProjectEntity data) {
		this.data = data;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<ProjectEntity> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<ProjectEntity> projectList) {
		this.projectList = projectList;
	}
	
	

}
