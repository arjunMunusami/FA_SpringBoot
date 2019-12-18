package com.fsd.assignment.taskmanager.model;

import java.io.Serializable;
import java.util.List;

import com.fsd.assignment.taskmanager.entity.TaskEntity;

public class TaskResultVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8671253649406263217L;

	private TaskEntity taskEntity;
	
	private List<TaskEntity> taskList;
	
	private String errMsg;

	public TaskEntity getTaskEntity() {
		return taskEntity;
	}

	public void setTaskEntity(TaskEntity taskEntity) {
		this.taskEntity = taskEntity;
	}

	public List<TaskEntity> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskEntity> taskList) {
		this.taskList = taskList;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
