package com.fsd.assignment.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fsd.assignment.taskmanager.entity.ProjectEntity;
import com.fsd.assignment.taskmanager.entity.UserEntity;
import com.fsd.assignment.taskmanager.exception.BusinessException;
import com.fsd.assignment.taskmanager.repository.UserRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userDao;
	
	public UserEntity saveUserDetails(UserEntity userEntity) throws BusinessException {
		return userDao.save(userEntity);
	}
	
	public List<UserEntity> fetchUserDetails(String orderField) {
		return userDao.findAll(Sort.by(Sort.Direction.ASC, orderField));
	}
	
	public void deleteUser(Integer userId) {
		userDao.deleteById(userId);
	}
	
	public UserEntity loadUserDetail(Integer userId) throws BusinessException {
		return userDao.getOne(userId);
	}
}
