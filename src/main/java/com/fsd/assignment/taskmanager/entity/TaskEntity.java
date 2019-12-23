package com.fsd.assignment.taskmanager.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity(name="TASK")
public class TaskEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1094820529356437361L;

	@Id
	@Column(name="TASK_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskId;
	
	@Column(name="TASK_NAME")
	private String taskName;
	
	@Column(name="PRIORITY")
	private Integer taskPriority;
	
	@Column(name="START_DATE")
	private Date taskStartDt;
	
	@Column(name="END_DATE")
	private Date taskEndDt;
	
	@ManyToOne
	@JoinColumn(name="P_TSK_ID", referencedColumnName="TASK_ID")
	private ParentTaskEntity parentTask;
	
	@ManyToOne
	@JoinColumn(name="USR_ID", referencedColumnName="USER_ID")
	private UserEntity user;	
	
	@ManyToOne
	@JoinColumn(name="PRJ_ID", referencedColumnName="PROJECT_ID")
	private ProjectEntity project;
	
	@Column(name="STATUS")
	private String status;
	
	@Transient
	private boolean prtTask;

	public boolean isPrtTask() {
		return prtTask;
	}

	public void setPrtTask(boolean prtTask) {
		this.prtTask = prtTask;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(Integer taskPriority) {
		this.taskPriority = taskPriority;
	}

	public Date getTaskStartDt() {
		return taskStartDt;
	}

	public void setTaskStartDt(Date taskStartDt) {
		this.taskStartDt = taskStartDt;
	}

	public Date getTaskEndDt() {
		return taskEndDt;
	}

	public void setTaskEndDt(Date taskEndDt) {
		this.taskEndDt = taskEndDt;
	}

	public ParentTaskEntity getParentTask() {
		return parentTask;
	}

	public void setParentTask(ParentTaskEntity parentTask) {
		this.parentTask = parentTask;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
