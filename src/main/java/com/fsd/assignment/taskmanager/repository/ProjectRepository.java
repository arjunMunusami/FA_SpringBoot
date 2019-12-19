package com.fsd.assignment.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.assignment.taskmanager.entity.ProjectEntity;

@EnableJpaRepositories
@Transactional
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer>{
	
	@Modifying
	@Query("update ProjectEntity set status='IA' where id=:projectId")
	public void suspendProject(Integer projectId);

}
