package com.fsd.assignment.taskmanager.model;

import java.io.Serializable;
import java.util.Date;

public class TaskSearchVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4301533199376481884L;

	private String taskName;
	
	private Integer taskMinPriority;
	
	private Integer taskMaxPriority;
	
	private String parentTask;
	
	private Date taskStartDate;
	
	private Date taskEndDate;
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaskMinPriority() {
		return taskMinPriority;
	}

	public void setTaskMinPriority(Integer taskMinPriority) {
		this.taskMinPriority = taskMinPriority;
	}

	public Integer getTaskMaxPriority() {
		return taskMaxPriority;
	}

	public void setTaskMaxPriority(Integer taskMaxPriority) {
		this.taskMaxPriority = taskMaxPriority;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

	public Date getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public Date getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

}
