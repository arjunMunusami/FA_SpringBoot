package com.fsd.assignment.taskmanager.service;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fsd.assignment.taskmanager.entity.ProjectEntity;
import com.fsd.assignment.taskmanager.entity.TaskEntity;
import com.fsd.assignment.taskmanager.exception.BusinessException;
import com.fsd.assignment.taskmanager.repository.ProjectRepository;

@Service
public class ProjectServiceImpl {
	
	@Autowired
	public ProjectRepository daoRepo;

	public ProjectEntity saveProjectDetails(ProjectEntity projectEntity) throws BusinessException {
		return daoRepo.save(projectEntity);
	}
	
	public List<ProjectEntity> fetchProjectDetails(String orderField) {
		List<ProjectEntity> projectList;
		if(StringUtils.equals(orderField, "Completed")) {
			projectList = daoRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
			if(!CollectionUtils.isEmpty(projectList)) {
				projectList = updateTaskCnt(projectList);				
				projectList.sort((a,b)->a.getTaskCmpCnt().compareTo(b.getTaskCmpCnt()));
			}
		} else {
			projectList = daoRepo.findAll(Sort.by(Sort.Direction.ASC, orderField));
			projectList = updateTaskCnt(projectList);
		}
		return projectList;
	}
	
	public void suspendProject(Integer projectId) {
		daoRepo.suspendProject(projectId);
	}
	
	public ProjectEntity loadProjectDetail(Integer projectId) throws BusinessException {
		return daoRepo.findById(projectId).get();
	}
	
	private List<ProjectEntity> updateTaskCnt(List<ProjectEntity> projectList){
		projectList.forEach(project->{					
			int taskInPrg = 0;
			int taskCompleted = 0;					
			if(!CollectionUtils.isEmpty(project.getTaskList())) {
				for(TaskEntity task : project.getTaskList()) {
					if(StringUtils.equals(task.getStatus(), "INP")) {
						taskInPrg++;
					} else {
						taskCompleted++;
					}
				}				
			}	
			project.setTaskCnt(taskInPrg);
			project.setTaskCmpCnt(taskCompleted);
		});
		return projectList;
	}
}
