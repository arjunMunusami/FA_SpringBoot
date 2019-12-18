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
		
		Root<TaskEntity> root = cq.from(TaskEntity.class);
		
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotBlank(taskVo.getTaskName())) {
	        predicates.add(cb.like(root.get("taskName"), "%"+taskVo.getTaskName()+"%"));
	    }
		
		if(null!=taskVo.getTaskMinPriority()) {
	        predicates.add(cb.ge(root.get("taskPriority"), taskVo.getTaskMinPriority()));
	    }
		
		if(null!=taskVo.getTaskMaxPriority()) {
	        predicates.add(cb.le(root.get("taskPriority"), taskVo.getTaskMaxPriority()));
	    }
		
		if(null!=taskVo.getTaskStartDate()) {
	        predicates.add(cb.greaterThan(root.get("taskStartDt"), taskVo.getTaskStartDate()));
	    }
		
		if(null!=taskVo.getTaskEndDate()) {
	        predicates.add(cb.lessThan(root.get("taskEndDt"), taskVo.getTaskEndDate()));
	    }
		
		if(StringUtils.isNotBlank(taskVo.getParentTask())) {
			Join<TaskEntity, TaskEntity> prtskJoin = root.join("parentTask", JoinType.LEFT);
	        predicates.add(cb.like(prtskJoin.get("taskName"), "%"+taskVo.getParentTask()+"%"));
	    }
	   
	    cq.where(predicates.toArray(new Predicate[0]));
		
		return entityManager.createQuery(cq).getResultList();
	}

}
