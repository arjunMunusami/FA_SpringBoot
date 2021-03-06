package com.fsd.assignment.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.assignment.taskmanager.entity.UserEntity;
import com.fsd.assignment.taskmanager.exception.BusinessException;
import com.fsd.assignment.taskmanager.model.UserResultVO;
import com.fsd.assignment.taskmanager.service.UserServiceImpl;

@RestController
@RequestMapping(value = "/user/")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value = {"/addUser","/updateUser"}, method = {RequestMethod.POST})
	public UserResultVO saveUserData(Model model,@RequestBody UserEntity userData) {
		UserResultVO userResult = new UserResultVO();
		try {
			userData = userService.saveUserDetails(userData);
			userResult.setUserData(userData);
		} catch (BusinessException e) {
			userResult.setUserData(userData);
			userResult.setErrMsg(e.getMessage());
		}
		return userResult;
	}
	
	@RequestMapping(value = "/sortUser/{order}", method = {RequestMethod.GET})
	public UserResultVO sortUser(Model model,
			@PathVariable("order") String orderByField) {
		UserResultVO resultVO = new UserResultVO();
		
		try {
			List<UserEntity> userDetails = userService.fetchUserDetails(orderByField);
			resultVO.setUserList(userDetails);
		} catch (RuntimeException e) {
			resultVO.setErrMsg(e.getMessage());
		}
		return resultVO;
	}
	
	@RequestMapping(value = "/loadUser/{id}", method = {RequestMethod.GET})
	public UserResultVO loadUser(Model model,
			@PathVariable("id") Integer userId) {
		UserResultVO resultVO = new UserResultVO();
		
		try {
			UserEntity userDetail = userService.loadUserDetail(userId);
			resultVO.setUserData(userDetail);
		} catch (BusinessException e) {
			resultVO.setErrMsg(e.getMessage());
		}
		return resultVO;
	}
	
	@RequestMapping(value = "/deleteUser/{id}", method = {RequestMethod.GET})
	public UserResultVO deleteUser(Model model,
			@PathVariable("id") Integer userId) {
		UserResultVO resultVO = new UserResultVO();
		
		try {
			userService.deleteUser(userId);
		} catch (RuntimeException e) {
			resultVO.setErrMsg(e.getMessage());
		}
		return resultVO;
	}

}
