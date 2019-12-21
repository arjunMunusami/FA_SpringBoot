package com.fsd.assignment.taskmanager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.assignment.taskmanager.entity.ParentTaskEntity;
import com.fsd.assignment.taskmanager.entity.ProjectEntity;
import com.fsd.assignment.taskmanager.entity.TaskEntity;
import com.fsd.assignment.taskmanager.entity.UserEntity;
import com.fsd.assignment.taskmanager.exception.BusinessException;
import com.fsd.assignment.taskmanager.model.TaskResultVO;
import com.fsd.assignment.taskmanager.repository.TaskManagerDAO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
public class TaskControllerTest {
	
	 private static final ObjectMapper om = new ObjectMapper();

	 @Autowired
	 private TestRestTemplate restTemplate;

	 @MockBean
	 private TaskManagerDAO taskMgrRepo;
	 
	 @Test
	    public void saveTaskEntityTest() throws JSONException {

	       TaskEntity taskEntity = buildTaskEntity();
	       when(taskMgrRepo.save(Mockito.any())).thenReturn(taskEntity);

	        ResponseEntity<TaskEntity> response = restTemplate.postForEntity("/task/addTask", taskEntity, TaskEntity.class);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());

	    }
	 
	 @Test
	 public void saveTaskEntityTestFailure() throws JSONException {
	       when(taskMgrRepo.save(Mockito.any())).thenThrow(BusinessException.class);
	        ResponseEntity<TaskEntity> response = restTemplate.postForEntity("/task/addTask", new TaskEntity(), TaskEntity.class);
	        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	 }
	 
	 @Test
	 public void searchTaskTest() throws JSONException {
		 
		 TaskEntity taskEntity = buildTaskEntity();
		 List<TaskEntity> taskEntityList = new ArrayList<>();
		 taskEntityList.add(taskEntity);
		 
	       when(taskMgrRepo.fetchTaskDetails(Mockito.any())).thenReturn(taskEntityList);
	        ResponseEntity<TaskResultVO> response = restTemplate.postForEntity("/task/searchTask", new TaskResultVO(), TaskResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	 }
	 
	 @Test
	 public void searchTaskFailureTest() throws JSONException {
		 
		 TaskEntity taskEntity = buildTaskEntity();
		 List<TaskEntity> taskEntityList = new ArrayList<>();
		 taskEntityList.add(taskEntity);
		 
	       when(taskMgrRepo.fetchTaskDetails(Mockito.any())).thenThrow(BusinessException.class);
	        ResponseEntity<TaskResultVO> response = restTemplate.postForEntity("/task/searchTask", new TaskResultVO(), TaskResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	 }
	 
	 @Test
	 public void loadTaskTest() throws JSONException {
		 
		 TaskEntity taskEntity = buildTaskEntity();
		 
		 Optional<TaskEntity> taskEntityOpt = Optional.of(taskEntity);
	      
		 when(taskMgrRepo.findById(Mockito.any())).thenReturn(taskEntityOpt);
	        ResponseEntity<TaskResultVO> response = restTemplate.postForEntity("/task/loadTask/1", new TaskResultVO(), TaskResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	 }
	 
	 @Test
	 public void endTaskTest() throws JSONException {
		 
		 TaskEntity taskEntity = buildTaskEntity();
		 
	        ResponseEntity<TaskResultVO> response = restTemplate.postForEntity("/task/loadTask/1", new TaskResultVO(), TaskResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
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
