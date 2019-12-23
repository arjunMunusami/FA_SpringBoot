package com.fsd.assignment.taskmanager.model;

import java.util.List;

import com.fsd.assignment.taskmanager.entity.ParentTaskEntity;
import com.fsd.assignment.taskmanager.entity.ProjectEntity;

public class ParentTaskResultVO {
	
	private ParentTaskEntity data;
	
	private String errorMsg;
	
	private List<ParentTaskEntity> projectList;

	public ParentTaskEntity getData() {
		return data;
	}

	public void setData(ParentTaskEntity data) {
		this.data = data;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<ParentTaskEntity> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<ParentTaskEntity> projectList) {
		this.projectList = projectList;
	}
	
	

}
