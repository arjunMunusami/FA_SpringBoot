package com.fsd.assignment.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fsd.assignment.taskmanager.entity.ProjectEntity;
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
		return daoRepo.findAll(Sort.by(Sort.Direction.ASC, orderField));
	}
	
	public void suspendProject(Integer projectId) {
		daoRepo.suspendProject(projectId);
	}
	
	public ProjectEntity loadProjectDetail(Integer projectId) throws BusinessException {
		return daoRepo.getOne(projectId);
	}
}
