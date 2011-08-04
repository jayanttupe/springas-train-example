package com.skynet.spms.jbpm.manager.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.jbpm.api.cmd.Command;
import org.jbpm.api.cmd.Environment;
import org.jbpm.pvm.internal.cmd.CommandService;
import org.jbpm.pvm.internal.history.model.HistoryActivityInstanceImpl;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
import org.jbpm.pvm.internal.history.model.HistoryTaskImpl;
import org.jbpm.pvm.internal.history.model.HistoryVariableImpl;

import com.skynet.spms.jbpm.entity.WfHistoryTask;

public class TaskHistoryQueryForUser implements Command<Collection<WfHistoryTask>>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2023788287557471237L;

	
	protected CommandService commandService;
	  
	private String userName;
	
	private String status;
	
	public void setStatus(String status) {
		this.status=status;
	}

	
	public void setUserName(String userName){
		this.userName=userName;
	}
	
	@Override
	public Collection<WfHistoryTask> execute(Environment environment) throws Exception {
		Session session=environment.get(Session.class);
		
		StringBuilder buf=new StringBuilder(
		"select {task.*} ,{act.*} ,{proc.*},{history.*} "+
		
		"from jbpm4_hist_task task "+
		"left join JBPM4_hist_actinst act on task.dbid_=act.htask_ "+
		"left join JBPM4_hist_procinst proc on act.hproci_ = proc.dbid_ " +
		"left join JBPM4_hist_var history on history.procinstid_=proc.id_ "+
		"where task.assignee_ = :assignee and task.state_ is not null " );
		
		if(StringUtils.isNotBlank(status)){
			buf.append(" and task.status = :status");
		}
		
		SQLQuery query=session.createSQLQuery(buf.toString());
				
		query.addEntity("task",HistoryTaskImpl.class)
				.addEntity("act",HistoryActivityInstanceImpl.class)
				.addEntity("proc",HistoryProcessInstanceImpl.class)
				.addEntity("history",HistoryVariableImpl.class)
				.setParameter("assignee", userName);
		
		if(StringUtils.isNotBlank(status)){
			query.setParameter("status", status);
		}
				
		@SuppressWarnings("unchecked")
		List<Object[]> resultList =query.list();
		
//		List<WfHistoryTask> taskList=new ArrayList<WfHistoryTask>();
		Map<String,WfHistoryTask> taskMap=new HashMap<String,WfHistoryTask>();
		for(Object[] objArray:resultList){
			WfHistoryTask task=new WfHistoryTask(objArray);
			String taskID=task.getTask().getId();
			if(taskMap.containsKey(taskID)){
				taskMap.get(taskID).addVariable(objArray[3]);
			}else{
				taskMap.put(taskID, task);
			}
		}
		
		return taskMap.values();
	}

}
