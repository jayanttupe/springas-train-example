package com.skynet.spms.jbpm.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jbpm.api.cmd.Command;
import org.jbpm.api.cmd.Environment;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.history.HistoryTaskQuery;
import org.jbpm.pvm.internal.cmd.CommandService;
import org.jbpm.pvm.internal.history.model.HistoryActivityInstanceImpl;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
import org.jbpm.pvm.internal.history.model.HistoryTaskImpl;
import org.jbpm.pvm.internal.query.AbstractQuery;
import org.jbpm.pvm.internal.query.HistoryTaskQueryImpl;

import com.skynet.spms.jbpm.entity.WfHistoryTask;

public class FlowHistoryQueryForProc implements Command<List<WfHistoryTask>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2023788287557471237L;

	
	protected CommandService commandService;
	  
//	private String userName;
	
//	private String status;
	
//	public void setStatus(String status) {
//		this.status=status;
//	}

	private String procInstID;
	
	public void setProcInstID(String procID){
		this.procInstID=procID;
	}
	
	@Override
	public List<WfHistoryTask> execute(Environment environment) throws Exception {
		Session session=environment.get(Session.class);
		
		StringBuilder buf=new StringBuilder("select {task.*} ,{act.*} ,{proc.*} "+
		
		"from jbpm4_hist_task task "+
		"left join JBPM4_hist_actinst act on task.dbid_=act.htask_ "+
		"left join JBPM4_hist_procinst proc on act.hproci_ = proc.dbid_ " +
		"where task.execution_ like :procInstID and task.state_ is not null " );
		
//		if(StringUtils.isNotBlank(status)){
//			buf.append(" and task.status = :status");
//		}
		
		SQLQuery query=session.createSQLQuery(buf.toString());
				
		query.addEntity("task",HistoryTaskImpl.class)
				.addEntity("act",HistoryActivityInstanceImpl.class)
				.addEntity("proc",HistoryProcessInstanceImpl.class)
				.setParameter("procInstID", procInstID+"%");
		
//		if(StringUtils.isNotBlank(status)){
//			query.setParameter("status", status);
//		}
				
		@SuppressWarnings("unchecked")
		List<Object[]> resultList =query.list();
		
		List<WfHistoryTask> taskList=new ArrayList<WfHistoryTask>();
		for(Object[] objArray:resultList){
			WfHistoryTask task=new WfHistoryTask(objArray);
			taskList.add(task);
		}
		
		return taskList;
	}

	
	
	


}
