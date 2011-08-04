package com.skynet.spms.jbpm.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.pvm.internal.history.model.HistoryActivityInstanceImpl;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
import org.jbpm.pvm.internal.history.model.HistoryTaskImpl;
import org.jbpm.pvm.internal.history.model.HistoryVariableImpl;

public class WfHistoryTask {
	
	public WfHistoryTask(Object[] objArray) {
		this.task=(HistoryTask) objArray[0];
		this.activity=(HistoryActivityInstance) objArray[1];
		this.process=(HistoryProcessInstance) objArray[2];
		
		flowName= StringUtils.substringBefore(process
				.getProcessDefinitionId(), "-");
		taskName=flowName + "."
				+ activity.getActivityName();
		if(objArray.length>3){
			variableList.add((HistoryVariableImpl) objArray[3]);
		}
	}
	
	public void addVariable(Object obj){
		variableList.add((HistoryVariableImpl) obj);
	}

	private final  HistoryTask task;
	
	private final HistoryActivityInstance activity;
	
	private final HistoryProcessInstance process;
	
	private final List<HistoryVariableImpl> variableList=new ArrayList<HistoryVariableImpl>();
	
	private String flowName;
	
	private String taskName;
	
	public HistoryTask getTask() {
		return task;
	}

	public HistoryActivityInstance getActivity() {
		return activity;
	}

	public HistoryProcessInstance getProcess() {
		return process;
	}
	
	
	public String getFlowName(){
		return flowName;
	}
	
	public String getTaskName(){
		return taskName;
	}

	public Map<String, String> getParamMap() {
		Map<String,String> paramMap=new HashMap<String,String>();
		for(HistoryVariableImpl var:variableList){
			paramMap.put(var.getVariableName(), var.getValue());
		}
		return paramMap;
	}

}
