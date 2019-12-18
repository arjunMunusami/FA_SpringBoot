package com.fsd.assignment.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.assignment.taskmanager.entity.ProjectEntity;
import com.fsd.assignment.taskmanager.entity.UserEntity;

@EnableJpaRepositories
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	

}
