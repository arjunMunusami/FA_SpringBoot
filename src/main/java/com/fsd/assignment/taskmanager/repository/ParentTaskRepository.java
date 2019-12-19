package com.fsd.assignment.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.assignment.taskmanager.entity.ParentTaskEntity;

@EnableJpaRepositories
@Transactional
public interface ParentTaskRepository extends JpaRepository<ParentTaskEntity, Integer> , CustomTaskManagerDAO {
	
	
	public ParentTaskEntity findByName(String taskName);
	
}
