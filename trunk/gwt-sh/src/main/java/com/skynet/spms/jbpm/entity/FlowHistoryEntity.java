package com.skynet.spms.jbpm.entity;

import java.util.Date;

import org.jbpm.api.history.HistoryTask;

import com.skynet.common.prop.PropertyEntity;

public class FlowHistoryEntity {
	
	public FlowHistoryEntity(WfHistoryTask task) {
		taskID=task.getTask().getId();
		assign=task.getTask().getAssignee();
		outcoming=task.getTask().getOutcome();
		commitDate=task.getTask().getEndTime();
		
		taskName=task.getTaskName();
	}
	
	public void convertWithProp(PropertyEntity prop){
		this.taskName=prop.getProperty(taskName);
	}
	
	
	private String taskID;
	
	private String assign;
	
	private String outcoming;
	
	private String taskName;
	
	private Date commitDate;

	public String getTaskID() {
		return taskID;
	}

	public String getAssign() {
		return assign;
	}

	public String getOutcoming() {
		return outcoming;
	}

	public String getTaskName() {
		return taskName;
	}

	public Date getCommitDate() {
		return commitDate;
	}
	
	@Override
	public String toString(){
		return "taskName:"+taskName+" outcoming:"+
		outcoming+" assign:"+assign+" commitTime:"+commitDate;
	}
	
	
}
