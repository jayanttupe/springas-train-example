package com.skynet.spms.jbpm.entity;

import java.util.Date;
import java.util.List;


import com.google.gwt.user.client.rpc.IsSerializable;
import com.skynet.spms.persistence.entity.spmsdd.Priority;


public class WfTaskInfo implements IsSerializable{
	private String taskName;
	
	private String flowName;
	
	private String outcoming;
	
	private String status;
	
	private Date commitTime;
	
	private Date takenTime;
	
	private Date createTime;
	
	private String busKey;
	
	private String taskID;
	
	private String procInstID;
	
	private String formInfo;
		
	private int taskStatus;
	
	private String statusDesc;
	
	private Priority priority;
	
	private FlowType flowType;
	
	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getFormInfo() {
		return formInfo;
	}

	public void setFormInfo(String formInfo) {
		this.formInfo = formInfo;
	}


	public String getEntityId(){
		return taskID;
	}
	
	public void setEntityId(String id){
		this.taskID=id;
	}
	

	public String getBusKey() {
		return busKey;
	}

	public void setBusKey(String busKey) {
		this.busKey = busKey;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String procName) {
		this.flowName = procName;
	}

	public String getOutcoming() {
		return outcoming;
	}

	public void setOutcoming(String outcoming) {
		this.outcoming = outcoming;
	}

	public String getStatus() {
		return status;
	}

	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}

	public Date getTakenTime() {
		return takenTime;
	}

	public void setTakenTime(Date takenTime) {
		this.takenTime = takenTime;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProcInstID() {
		return procInstID;
	}

	public void setProcInstID(String procInstID) {
		this.procInstID = procInstID;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public FlowType getFlowType() {
		return flowType;
	}

	public void setFlowType(FlowType flowType) {
		this.flowType = flowType;
	}

	
	
	
	

}

