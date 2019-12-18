package com.fsd.assignment.taskmanager.repository;

import java.util.List;

import com.fsd.assignment.taskmanager.entity.TaskEntity;
import com.fsd.assignment.taskmanager.model.TaskSearchVO;

public interface CustomTaskManagerDAO {
	
	public List<TaskEntity> fetchTaskDetails(TaskSearchVO taskSearch);

}
