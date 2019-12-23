package com.fsd.assignment.taskmanager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.assignment.taskmanager.entity.ParentTaskEntity;
import com.fsd.assignment.taskmanager.entity.ProjectEntity;
import com.fsd.assignment.taskmanager.entity.TaskEntity;
import com.fsd.assignment.taskmanager.entity.UserEntity;
import com.fsd.assignment.taskmanager.model.ParentTaskResultVO;
import com.fsd.assignment.taskmanager.model.ProjectResultVO;
import com.fsd.assignment.taskmanager.model.TaskResultVO;
import com.fsd.assignment.taskmanager.model.TaskSearchVO;
import com.fsd.assignment.taskmanager.repository.ParentTaskRepository;
import com.fsd.assignment.taskmanager.repository.ProjectRepository;
import com.fsd.assignment.taskmanager.repository.TaskManagerDAO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
public class ProjectControllerTest {
	
	 private static final ObjectMapper om = new ObjectMapper();

	 @Autowired
	 private TestRestTemplate restTemplate;

	 @MockBean
	 private TaskManagerDAO taskMgrRepo;
	 
	 @MockBean
	 private ProjectRepository projectRepo;
	 
	 @MockBean
		public ParentTaskRepository prtTaskRepo;
	 
	 @Test
	    public void saveProjectTest() throws JSONException {

	       ProjectEntity project = buildProjectEntity();
	       when(projectRepo.save(Mockito.any())).thenReturn(project);

	        ResponseEntity<ProjectResultVO> response = restTemplate.postForEntity("/project/addProject", project, ProjectResultVO.class);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());

	    }
	 
	 @Test
	    public void saveProjectFailTest() {

	       ProjectEntity project = buildProjectEntity();
	       when(projectRepo.save(Mockito.any())).thenThrow(MockitoException.class);

	        ResponseEntity<ProjectResultVO> response = restTemplate.postForEntity("/project/addProject", project, ProjectResultVO.class);

	        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());

	    }
	 
	 @Test
	 public void sortProjectTest() throws JSONException {
		 ProjectEntity project = buildProjectEntity();
		 List<ProjectEntity> projectList = new ArrayList<>();
		 projectList.add(project);
	       when(projectRepo.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(projectList);
	        ResponseEntity<ProjectResultVO> response = restTemplate.getForEntity("/project/sortProject/Completed", ProjectResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	        
	       response = restTemplate.getForEntity("/project/sortProject/name", ProjectResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	 }
	 
	 @Test
	 public void sortProjectFailTest() throws JSONException {
		 ProjectEntity project = buildProjectEntity();
		 List<ProjectEntity> projectList = new ArrayList<>();
		 projectList.add(project);
		 when(projectRepo.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenThrow(MockitoException.class);
	        ResponseEntity<ProjectResultVO> response = restTemplate.getForEntity("/project/sortProject/Completed", ProjectResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	        
	 }
	 
	 @Test
	 public void suspendProjectTest() throws JSONException {
		 
	        ResponseEntity<ProjectResultVO> response = restTemplate.getForEntity("/project/suspendProject/1", ProjectResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	        
	 }
	 
	 @Test
	 public void suspendProjectFailTest() throws JSONException {
		 
		 Mockito.doThrow(MockitoException.class).when(projectRepo).suspendProject(Mockito.anyInt());
		 
	        ResponseEntity<ProjectResultVO> response = restTemplate.getForEntity("/project/suspendProject/1", ProjectResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	        
	 }
	 
	 @Test
	 public void loadProjectTest() throws JSONException {
		 
		 ProjectEntity projectEntity =  buildProjectEntity();		 
		 Optional<ProjectEntity> projectOpt = Optional.of(projectEntity);
	      
		 when(projectRepo.findById(Mockito.any())).thenReturn(projectOpt);
	        ResponseEntity<ProjectResultVO> response = restTemplate.getForEntity("/project/loadProject/1",  ProjectResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	 }
	 
	 @Test
	 public void loadProjectFailTest() throws JSONException {
		 
		 when(taskMgrRepo.findById(Mockito.any())).thenThrow(MockitoException.class);
	        ResponseEntity<ProjectResultVO> response = restTemplate.getForEntity("/project/loadProject/1",  ProjectResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	 }
	 
	 private ProjectEntity buildProjectEntity() {
		 
		 UserEntity userEntity = new UserEntity();		     
	       userEntity.setUserId(1);
		 
		 ProjectEntity project = new ProjectEntity();
		 project.setStartDate(new Date());
		 project.setEndDate(new Date());
		 project.setName("project");
		 project.setPriority(1);
		 project.setManager(userEntity);
		 
	      List<TaskEntity> taskList = new ArrayList<>();
	      
	      project.setTaskList(taskList);
	      
	       
	       return project;
		 
	 }
	 
	 private TaskEntity buildTaskEntity() {
		 
		 TaskEntity taskEntity = new TaskEntity();
	       taskEntity.setTaskId(1);
	       taskEntity.setTaskName("taskName");
	       taskEntity.setTaskStartDt(new Date());
	       taskEntity.setTaskEndDt(new Date());
	       taskEntity.setTaskPriority(1);
	       
	       UserEntity userEntity = new UserEntity();		     
	       userEntity.setUserId(1);
	       taskEntity.setUser(userEntity);
	       
	       ProjectEntity projEntity = new ProjectEntity();	       
	       projEntity.setId(1);
	       taskEntity.setProject(projEntity); 
	       
	       ParentTaskEntity prtTskEntity = new ParentTaskEntity();
	       prtTskEntity.setId(1);
	       taskEntity.setParentTask(prtTskEntity);
	       
	       return taskEntity;
		 
	 }

}
