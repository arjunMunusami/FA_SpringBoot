package com.fsd.assignment.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.assignment.taskmanager.entity.ParentTaskEntity;
import com.fsd.assignment.taskmanager.entity.TaskEntity;
import com.fsd.assignment.taskmanager.exception.BusinessException;
import com.fsd.assignment.taskmanager.model.TaskSearchVO;
import com.fsd.assignment.taskmanager.repository.ParentTaskRepository;
import com.fsd.assignment.taskmanager.repository.TaskManagerDAO;

@Service
public class TaskManagerServiceImpl {
	
	@Autowired
	public TaskManagerDAO daoRepo;
	
	@Autowired
	public ParentTaskRepository prtTaskRepo;
	
	public TaskEntity saveTaskDetails(TaskEntity taskEntity) throws BusinessException {
		
		if(!taskEntity.isPrtTask()) {
			taskEntity = daoRepo.save(taskEntity);
		} else {
			ParentTaskEntity parentTask = new ParentTaskEntity();
			parentTask.setName(taskEntity.getTaskName());
			
			parentTask = prtTaskRepo.save(parentTask);
			taskEntity.setParentTask(parentTask);
		}
				
		return taskEntity;
	}
	
	public List<TaskEntity> fetchTaskDetails(TaskSearchVO searchTaskVO) {
		
		return daoRepo.fetchTaskDetails(searchTaskVO);
	}
	
	public TaskEntity fetchTaskEntity(Integer taskId) {
		return daoRepo.findById(taskId).get();
	}
	
	public List<ParentTaskEntity> fetchParentTask() {
		return prtTaskRepo.findAll();
	}
	
	public void endTask(Integer taskId) {
		daoRepo.endTaskDetail("CMP", taskId);
	}
	
}
