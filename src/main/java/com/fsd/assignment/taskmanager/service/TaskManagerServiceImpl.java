package com.fsd.assignment.taskmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.assignment.taskmanager.entity.ParentTaskEntity;
import com.fsd.assignment.taskmanager.entity.ProjectEntity;
import com.fsd.assignment.taskmanager.entity.TaskEntity;
import com.fsd.assignment.taskmanager.exception.BusinessException;
import com.fsd.assignment.taskmanager.model.TaskSearchVO;
import com.fsd.assignment.taskmanager.repository.ParentTaskRepository;
import com.fsd.assignment.taskmanager.repository.ProjectRepository;
import com.fsd.assignment.taskmanager.repository.TaskManagerDAO;

@Service
public class TaskManagerServiceImpl {
	
	@Autowired
	public TaskManagerDAO daoRepo;
	
	@Autowired
	public ParentTaskRepository prtTaskRepo;
	
	@Autowired
	public ProjectRepository projectRepo;
	
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
	
	public List<TaskEntity> fetchTaskDetails(TaskSearchVO searchTaskVO,String orderField) {
		List<TaskEntity> taskList = new ArrayList<>();
		Optional<ProjectEntity> optPrj =  projectRepo.findById(searchTaskVO.getProjectId());
		
		if(optPrj.isPresent()) {
			taskList = optPrj.get().getTaskList();
			if(StringUtils.equals(orderField, "startDate")) {
				taskList.sort((a,b)->a.getTaskStartDt().compareTo(b.getTaskStartDt()));
			} else if (StringUtils.equals(orderField, "endDate")) {
				taskList.sort((a,b)->a.getTaskEndDt().compareTo(b.getTaskEndDt()));
			} else if(StringUtils.equals(orderField, "priority")) {
				taskList.sort((a,b)->a.getTaskPriority().compareTo(b.getTaskPriority()));
			} else if(StringUtils.equals(orderField, "status")) {
				taskList.sort((a,b)->a.getStatus().compareTo(b.getStatus()));
			}
		}
		
		return taskList;
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
