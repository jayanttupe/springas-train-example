package com.skynet.spms.jbpm.manager;

import java.util.Map;

import org.jbpm.api.ProcessInstance;

import com.skynet.spms.jbpm.WorkflowEnum;
import com.skynet.spms.jbpm.entity.WfInstParamCols;

public interface WfTaskOperManager {
		
	public void changeTaskStatus(String taskID,int status);
		
	public void commitTask(String taskID, String outcoming,Map<String,?> variables);
	
	void takeTask(String taskID, String user);

	public ProcessInstance startProcessInstanceWithKeyAndVariables(
			WorkflowEnum type, String businessKey, WfInstParamCols paramCols);
	
//	ProcessInstance startProcessInstanceWithKeyAndVariables(WorkflowEnum wf,
//			String businessKey,WfInstParamCols paramCols);

//	List<WfTaskSimpInfo> getPreviewTaskList(String userName);
//
//	List<WfTaskSimpInfo> getTakenTaskList(String userName);
	
}
