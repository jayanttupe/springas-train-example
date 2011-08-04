package com.skynet.spms.jbpm.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.Execution;
import org.jbpm.api.task.Task;
import org.json.simple.JSONArray;

import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.persistence.entity.jbpm.WfPersistenceParam;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

public class TaskInfoEntity {

	private String flowName;
	
	private String taskName;
	
	private String status;
	
	private Date createTime;
	
	private  Date takenTime;
	
	private Date commitTime;
	
	private String businessKey;
	
	private String procInstID;
	
	private String taskID;
	
	private String historyOutcoming;
	
	private Priority priority;
	
	private FlowType flowType;
		
	public void setFlowType(String flow){
		if(StringUtils.isNotBlank(flow)){
			this.flowType=FlowType.valueOf(flow);
		}else{
			flowType=FlowType.Contract;
		}
	}
	
	public void setPriorityType(String prior){
		if(StringUtils.isNotBlank(prior)){
			this.priority=Priority.valueOf(prior);
		}else{
			priority=Priority.expedite;
		}
	}

	public TaskInfoEntity(WfHistoryTask task) {
		flowName=task.getFlowName();
		taskName=task.getTaskName();
		
		if (StringUtils.isNotBlank(task.getTask().getOutcome())) {
			historyOutcoming=taskName + "."
					+ task.getTask().getOutcome();
		}

		status=task.getTask().getState();
		createTime=task.getTask().getCreateTime();		
		commitTime=task.getTask().getEndTime();
		taskID=task.getTask().getId();
		procInstID=task.getProcess().getProcessInstanceId();

	}
	
	public TaskInfoEntity(Task task,Execution execut){
		procInstID=execut.getId();		

		flowName=StringUtils.substringBefore(
				execut.getProcessDefinitionId(), "-");
		
		taskName=flowName + "." + task.getName();

		taskID=task.getId();
		
		createTime=task.getCreateTime();
		
		formResourceName=task.getFormResourceName();
		
	}
	
	public TaskInfoEntity(Task task) {
		
		procInstID=task.getExecutionId();		

		flowName=StringUtils.substringBefore(
				procInstID, ".");
		
		taskName=flowName + "." + task.getName();

		taskID=task.getId();
		
		createTime=task.getCreateTime();
		
	}
	
	

	public TaskInfoEntity(Task task, String busKey) {
		taskID=task.getId();
		this.businessKey=busKey;
		
	}

	public WfTaskInfo getTaskInfo(PropertyEntity prop) {
		
		WfTaskInfo taskInfo=new WfTaskInfo();
		
		taskInfo.setStatus(status);
		taskInfo.setStatusDesc(prop.getProperty("status."+status));
		taskInfo.setBusKey(businessKey);
		taskInfo.setTaskName(prop.getProperty(taskName));
		taskInfo.setFlowName(prop.getProperty(flowName));
		taskInfo.setOutcoming(prop.getProperty(historyOutcoming));

		for(Map<String,Object> map:jsonFormList){
			String title=prop.getProperty((String)map.get("title"));
			map.put("title",title );
		}
		taskInfo.setFormInfo(JSONArray.toJSONString(jsonFormList));
		
		if(!outcomList.isEmpty()){
			for(Map<String,Object> map:outcomList){
				String desc=prop.getProperty((String)map.get("desc"));
				map.put("desc", desc);
			}
			taskInfo.setOutcoming(JSONArray.toJSONString(outcomList));
		}
		
		taskInfo.setCommitTime(commitTime);
		taskInfo.setCreateTime(createTime);
		taskInfo.setTakenTime(takenTime);
		
		taskInfo.setProcInstID(procInstID);
		taskInfo.setTaskID(taskID);
		
		taskInfo.setFlowType(flowType);
		taskInfo.setPriority(priority);
		
		return taskInfo;
	}

	public String getBusKey(){
		return businessKey;
	}
	public void setBusKey(String variable) {
		businessKey=variable;
	}

	List<Map<String, Object>> jsonFormList = new ArrayList<Map<String, Object>>();

	public void addFormInfo(WfPersistenceParam wfParam){
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("formName", wfParam.getRefFormName());
		map.put("refKey", wfParam.getRefBusinessKey());
		map.put("title", flowName+"."+wfParam.getRefFormName());
		
		jsonFormList.add(map);
	}
	
	
	List<Map<String,Object>> outcomList=new ArrayList<Map<String,Object>>();
	
	public void addOutcoming(String out){
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		jsonMap.put("out",out);
		jsonMap.put("desc",taskName+"."+out);				
		outcomList.add(jsonMap);
	}
	
	public void setStatus(String name) {
		this.status=name;
	}

	public String getProcInstID() {
		return procInstID;
	}

	public String getTaskID() {
		return taskID;
	}

	public String getOutcoming() {
		return historyOutcoming;
	}

	public void setOutcoming(String outcoming) {
		this.historyOutcoming = outcoming;
	}

	private String formResourceName;
	public String getFormResourceName() {
		return formResourceName;
	}
	

}
