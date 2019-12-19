package com.fsd.assignment.taskmanager.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.assignment.taskmanager.entity.ProjectEntity;
import com.fsd.assignment.taskmanager.exception.BusinessException;
import com.fsd.assignment.taskmanager.model.ProjectResultVO;
import com.fsd.assignment.taskmanager.service.ProjectServiceImpl;

@RestController
@RequestMapping("/project/")
public class ProjectController {
	
	@Autowired
	private ProjectServiceImpl service;
	
	@RequestMapping(path = {"/addProject","/updateProject"},method = RequestMethod.POST)
	public ProjectResultVO saveProjectDetails(@RequestBody ProjectEntity project) {
		ProjectResultVO projectResult = new ProjectResultVO();
		try {
			project = service.saveProjectDetails(project);
			projectResult.setData(project);
		} catch (BusinessException e) {
			projectResult.setData(project);
			projectResult.setErrorMsg(e.getMessage());
		}
		return projectResult;
	}
	
	@RequestMapping(value = "/searchProject/{order}", method = {RequestMethod.GET})
	public ProjectResultVO searchTask(Model model,
			@PathParam("orderField") String orderField) {
		ProjectResultVO projectResult = new ProjectResultVO();
		
		try {
			List<ProjectEntity> userDetails = service.fetchProjectDetails(orderField);
			projectResult.setProjectList(userDetails);
		} catch (RuntimeException e) {
			projectResult.setErrorMsg(e.getMessage());
		}
		return projectResult;
	}
	
	@RequestMapping(value = "/suspendProject/{projectId}", method = {RequestMethod.GET})
	public ProjectResultVO suspendProject(Model model,
			@PathParam("projectId") Integer projectId) {
		ProjectResultVO projectResult = new ProjectResultVO();
		try {
			service.suspendProject(projectId);
		} catch (RuntimeException e) {
			projectResult.setErrorMsg(e.getMessage());
		}
		return projectResult;
	}

}
