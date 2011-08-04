package com.skynet.spms.web.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.entity.TaskStatus;
import com.skynet.spms.client.service.WfTaskService;
import com.skynet.spms.jbpm.manager.WfTaskOperManager;

@Controller
@GwtRpcEndPoint
public class WfTaskAction implements WfTaskService{

	@Autowired
	private WfTaskOperManager taskManager;

	
	@Override
	public void changeTaskStatus(String taskID,TaskStatus status) {
		
		taskManager.changeTaskStatus(taskID,status.getStatus());
	}

	@Override
	public void commitTask(String taskID, String outcoming) {
		taskManager.commitTask(taskID, outcoming,null);
	}
	

	@Override
	public void takeTask(String taskID) {
		taskManager.takeTask(taskID, GwtActionHelper.getCurrUser());
		
	}
	
	

	
}
