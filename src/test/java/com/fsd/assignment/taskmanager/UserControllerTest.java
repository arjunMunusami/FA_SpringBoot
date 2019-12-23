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
import com.fsd.assignment.taskmanager.model.UserResultVO;
import com.fsd.assignment.taskmanager.repository.ParentTaskRepository;
import com.fsd.assignment.taskmanager.repository.ProjectRepository;
import com.fsd.assignment.taskmanager.repository.TaskManagerDAO;
import com.fsd.assignment.taskmanager.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // for restTemplate
public class UserControllerTest {
	
	 private static final ObjectMapper om = new ObjectMapper();

	 @Autowired
	 private TestRestTemplate restTemplate;

	 @MockBean
	 private UserRepository userRepository;
	 
	 @Test
	    public void saveUserTest() throws JSONException {
		 
		 UserEntity userEntity = new UserEntity();
		 userEntity.setUserId(1);
		 userEntity.setEmplID("1234");
		 userEntity.setFirstName("asdfg");
		 userEntity.setLastName("qewrr");

	       when(userRepository.save(Mockito.any())).thenReturn(userEntity);

	        ResponseEntity<UserResultVO> response = restTemplate.postForEntity("/user/addUser", userEntity, UserResultVO.class);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());

	    }
	 
	 @Test
	    public void saveUserFailTest() {
		 
		 UserEntity userEntity = new UserEntity();
		 userEntity.setUserId(1);
		 userEntity.setEmplID("1234");
		 userEntity.setFirstName("asdfg");
		 userEntity.setLastName("qewrr");

	     when(userRepository.save(Mockito.any())).thenThrow(MockitoException.class);

	      ResponseEntity<UserResultVO> response = restTemplate.postForEntity("/user/addUser", userEntity, UserResultVO.class);
	        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());

	    }
	 
	 @Test
	 public void sortUserTest() throws JSONException {
		 UserEntity userEntity = new UserEntity();
		 userEntity.setUserId(1);
		 userEntity.setEmplID("1234");
		 userEntity.setFirstName("asdfg");
		 userEntity.setLastName("qewrr");
		 
		 List<UserEntity> userList = new ArrayList<>();
		 userList.add(userEntity);
		 
	       when(userRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"))).thenReturn(userList);
	        ResponseEntity<UserResultVO> response = restTemplate.getForEntity("/user/sortUser/firstName", UserResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	 }
	 
	 @Test
	 public void sortUserFailureTest() throws JSONException {
		 UserEntity userEntity = new UserEntity();
		 userEntity.setUserId(1);
		 userEntity.setEmplID("1234");
		 userEntity.setFirstName("asdfg");
		 userEntity.setLastName("qewrr");
		 
		 List<UserEntity> userList = new ArrayList<>();
		 userList.add(userEntity);
		 
	       when(userRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"))).thenThrow(MockitoException.class);
	        ResponseEntity<UserResultVO> response = restTemplate.getForEntity("/user/sortUser/firstName", UserResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	 }
	 
	 @Test
	 public void suspendUserTest() throws JSONException {
		 
	        ResponseEntity<UserResultVO> response = restTemplate.getForEntity("/user/deleteUser/1", UserResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	        
	 }
	 
	 @Test
	 public void suspendUserFailTest() throws JSONException {
		 
		 Mockito.doThrow(MockitoException.class).when(userRepository).deleteById(Mockito.anyInt());
		 
	        ResponseEntity<UserResultVO> response = restTemplate.getForEntity("/user/deleteUser/1", UserResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	        
	 }
	 
	 @Test
	 public void loadProjectTest() throws JSONException {
		 
		 UserEntity userEntity = new UserEntity();
		 userEntity.setUserId(1);
		 userEntity.setEmplID("1234");
		 userEntity.setFirstName("asdfg");
		 userEntity.setLastName("qewrr");
		 
		 Optional<UserEntity> userEntityOpt = Optional.of(userEntity);
	      
		 when(userRepository.findById(Mockito.any())).thenReturn(userEntityOpt);
	        ResponseEntity<UserResultVO> response = restTemplate.getForEntity("/user/loadUser/1",  UserResultVO.class);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	 }
	 
	 @Test
	 public void loadProjectFailTest() throws JSONException {
		 
		
		 
		 when(userRepository.findById(Mockito.any())).thenThrow(MockitoException.class);
	        ResponseEntity<UserResultVO> response = restTemplate.getForEntity("/user/loadUser/1",  UserResultVO.class);
	        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
	 }
	 
	 

}
