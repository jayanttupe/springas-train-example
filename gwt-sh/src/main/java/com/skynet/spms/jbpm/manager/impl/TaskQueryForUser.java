package com.skynet.spms.jbpm.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jbpm.api.Execution;
import org.jbpm.api.cmd.Command;
import org.jbpm.api.cmd.Environment;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;

import com.skynet.spms.jbpm.entity.TaskInfoEntity;

public class TaskQueryForUser implements Command<List<TaskInfoEntity>> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7073941822793695108L;
	private String userName;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public List<TaskInfoEntity> execute(Environment environment)
			throws Exception {
		Session session = environment.get(Session.class);

		String sql = " select  {task.*},{execution.*} "
				+ " from JBPM4_TASK task  "
				+ " inner join jbpm4_execution execution on  execution.id_ = task.execution_id_ "
				+ " where task.ASSIGNEE_ = :user ";

		SQLQuery query = session.createSQLQuery(sql);

		query.addEntity("task", TaskImpl.class)
				.addEntity("execution", ExecutionImpl.class)
				.setParameter("user", userName);

		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.list();

		List<TaskInfoEntity> taskList = new ArrayList<TaskInfoEntity>();
		for (Object[] objArray : resultList) {
			Task task = (Task) objArray[0];
			Execution exec = (Execution) objArray[1];
			TaskInfoEntity taskInfo = new TaskInfoEntity(task, exec);
			taskList.add(taskInfo);
		}

		return taskList;
	}

}
