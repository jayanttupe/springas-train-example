package com.skynet.spms.jbpm.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jbpm.api.Execution;
import org.jbpm.api.cmd.Command;
import org.jbpm.api.cmd.Environment;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.history.model.HistoryActivityInstanceImpl;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
import org.jbpm.pvm.internal.history.model.HistoryTaskImpl;
import org.jbpm.pvm.internal.history.model.HistoryVariableImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;

import com.skynet.spms.jbpm.entity.TaskInfoEntity;

public class TaskQueryForGroup implements Command<List<TaskInfoEntity>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7326341357096111190L;
	private String userName;
	
	public void setUserName(String userName){
		this.userName=userName;
	}
	@Override
	public List<TaskInfoEntity> execute(Environment environment)
			throws Exception {
		Session session=environment.get(Session.class);

		String sql = " select  {task.*},{execution.*} "
				+ " from JBPM4_PARTICIPATION participat0_ "
				+ " inner join JBPM4_TASK task on participat0_.TASK_=task.DBID_ "
				+ " inner join jbpm4_execution execution on  execution.id_ = task.execution_id_ "
				+ " where participat0_.TYPE_='candidate' and "
				+ " (participat0_.USERID_= :user or participat0_.GROUPID_ in (:user)) "
				+ " and (task.ASSIGNEE_ is null) ";
		
		SQLQuery query=session.createSQLQuery(sql);
		
		query.addEntity("task",TaskImpl.class)
				.addEntity("execution",ExecutionImpl.class)
				.setParameter("user", userName);
						
		@SuppressWarnings("unchecked")
		List<Object[]> resultList =query.list();
		
		
		List<TaskInfoEntity> taskList=new ArrayList<TaskInfoEntity>();
		for(Object[] objArray:resultList){
			Task task=(Task) objArray[0];
			Execution exec=(Execution) objArray[1];
			TaskInfoEntity taskInfo=new TaskInfoEntity(task,exec);
			taskList.add(taskInfo);
		}
		
		return taskList;
	}
	
	
	 
	
	

}
