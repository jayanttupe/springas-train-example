package com.skynet.spms.jbpm.manager.impl;

import java.util.Map;

import org.jbpm.api.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.jbpm.WorkflowEnum;
import com.skynet.spms.jbpm.entity.WfInstParamCols;
import com.skynet.spms.jbpm.manager.WfTaskOperManager;
import com.skynet.spms.jbpm.service.WfTaskOperService;

@Service
@Transactional
public class WfTaskOperManagerImpl implements WfTaskOperManager {

	@Autowired
	private WfTaskOperService taskOperService;

	@Override
	public void changeTaskStatus(String taskID, int status) {
		taskOperService.changeTaskStatus(taskID, status);
	}

	@Override
	public void commitTask(String taskID, String outcoming,
			Map<String, ?> variables) {
		taskOperService.commitTask(taskID, outcoming, variables);
		
	}

	@Override
	public void takeTask(String taskID, String user) {
		taskOperService.takeTask(taskID, user);		
	}

	@Override
	public ProcessInstance startProcessInstanceWithKeyAndVariables(
			WorkflowEnum type, String businessKey, WfInstParamCols paramCols) {
		return taskOperService.startProcessInstanceWithKeyAndVariables(type, businessKey, paramCols);
	}
	
	

}
