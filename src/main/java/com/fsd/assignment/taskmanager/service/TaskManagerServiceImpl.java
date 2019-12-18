package com.fsd.assignment.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.assignment.taskmanager.entity.TaskEntity;
import com.fsd.assignment.taskmanager.exception.BusinessException;
import com.fsd.assignment.taskmanager.model.TaskSearchVO;
import com.fsd.assignment.taskmanager.repository.TaskManagerDAO;

@Service
public class TaskManagerServiceImpl {
	
	@Autowired
	public TaskManagerDAO daoRepo;
	
	public TaskEntity saveTaskDetails(TaskEntity taskEntity) throws BusinessException {
		
		if(null!=taskEntity.getParentTask()) {
			TaskEntity parentTask = daoRepo.findByTaskName(taskEntity.getParentTask().getTaskName());
			if(null==parentTask) {
				throw new BusinessException("Parent Task is not defined in System!");
			} else {
				taskEntity.setParentTask(parentTask);
			}
		}
		taskEntity = daoRepo.save(taskEntity);
		
		return taskEntity;
	}
	
	public List<TaskEntity> fetchTaskDetails(TaskSearchVO searchTaskVO) {
		
		return daoRepo.fetchTaskDetails(searchTaskVO);
	}
	
	public TaskEntity fetchTaskEntity(Integer taskId) {
		return daoRepo.findById(taskId).get();
	}
	
	public void endTask(Integer taskId) {
		daoRepo.endTaskDetail("CMP", taskId);
	}
	
}
