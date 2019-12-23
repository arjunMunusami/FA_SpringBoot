package com.fsd.assignment.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.assignment.taskmanager.entity.TaskEntity;

@EnableJpaRepositories
@Transactional
public interface TaskManagerDAO extends JpaRepository<TaskEntity, Integer> {
	
	
	public TaskEntity findByTaskName(String taskName);
	
	@Modifying
	@Query("update TASK set status=:status where taskId=:taskId")
	public void endTaskDetail(String status,Integer taskId);
	
	

}
