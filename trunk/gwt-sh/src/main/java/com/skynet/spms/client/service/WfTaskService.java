package com.skynet.spms.client.service;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.skynet.spms.client.entity.TaskStatus;

@RemoteServiceRelativePath("wfTaskService.form")
public interface WfTaskService extends RemoteService {
	
	
//	public List<FlowHistoryEntity> getFlowHistory(String procInstID);
	
	public void takeTask(String taskID);

	public void changeTaskStatus(String taskID,TaskStatus status);
	
	public void commitTask(String taskID,String outcoming);
	
}
