package com.fsd.assignment.taskmanager.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fsd.assignment.taskmanager.entity.ProjectEntity;
import com.fsd.assignment.taskmanager.entity.TaskEntity;
import com.fsd.assignment.taskmanager.model.TaskSearchVO;

@Repository
public class CustomTaskManagerDAOImpl implements CustomTaskManagerDAO {

	@Autowired
	private EntityManager entityManager;
	 
	@Override
	public List<TaskEntity> fetchTaskDetails(TaskSearchVO taskVo) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();		
		CriteriaQuery cq = cb.createQuery(TaskEntity.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotBlank(taskVo.getTaskName())) {
			Root<TaskEntity> root = cq.from(TaskEntity.class);
			Join<TaskEntity, ProjectEntity> projectJoin = root.join("project", JoinType.INNER);
			predicates.add(cb.like(projectJoin.get("name"), "%"+taskVo.getTaskName()+"%"));
	    }
	   
	    cq.where(predicates.toArray(new Predicate[0]));
		
		return entityManager.createQuery(cq).getResultList();
	}

}
