package com.skynet.spms.client.service;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.TaskStatus;

public interface WfTaskServiceAsync {


	void commitTask(String taskID, String outcoming,
			AsyncCallback<Void> callback);

	void changeTaskStatus(String taskID, TaskStatus status,
			AsyncCallback<Void> callback);

//	void startWorkFlow(String wfName, String businessKey,
//			WfParams paramSet, AsyncCallback<Void> callback);

//	public void startApproveFlow(String businessKey,
//			String   businessType,float amount,AsyncCallback<Void> callback);
	
	void takeTask(String taskID,AsyncCallback<Void> callback);

}
