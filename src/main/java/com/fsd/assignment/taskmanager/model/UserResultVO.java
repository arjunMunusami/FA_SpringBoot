package com.fsd.assignment.taskmanager.model;

import java.io.Serializable;
import java.util.List;

import com.fsd.assignment.taskmanager.entity.UserEntity;

public class UserResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3989796866404143107L;

	private UserEntity userData;
	
	private List<UserEntity> userList;
	
	private String errMsg;

	public UserEntity getUserData() {
		return userData;
	}

	public void setUserData(UserEntity userData) {
		this.userData = userData;
	}

	public List<UserEntity> getUserList() {
		return userList;
	}

	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	
}
