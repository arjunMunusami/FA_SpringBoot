package com.fsd.assignment.taskmanager.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PROJECT")
public class ProjectEntity implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 931359485055502675L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PROJECT_ID")
	private Integer id;
	
	@Column(name="PROJECT_NAME")
	private String name;
	
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="END_DATE")
	private Date endDate;
	
	@Column(name="PRIORITY")
	private Integer priority;
	
	@Column(name="STATUS")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "MANAGER_ID",referencedColumnName = "USER_ID")
	private UserEntity manager;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	private List<TaskEntity> taskList;
	
	@Transient
	private Integer taskCnt;
	
	@Transient
	private Integer taskCmpCnt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserEntity getManager() {
		return manager;
	}

	public void setManager(UserEntity manager) {
		this.manager = manager;
	}

	public List<TaskEntity> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<TaskEntity> taskList) {
		this.taskList = taskList;
	}

	public Integer getTaskCnt() {
		return taskCnt;
	}

	public void setTaskCnt(Integer taskCnt) {
		this.taskCnt = taskCnt;
	}

	public Integer getTaskCmpCnt() {
		return taskCmpCnt;
	}

	public void setTaskCmpCnt(Integer taskCmpCnt) {
		this.taskCmpCnt = taskCmpCnt;
	}
	
	
}
