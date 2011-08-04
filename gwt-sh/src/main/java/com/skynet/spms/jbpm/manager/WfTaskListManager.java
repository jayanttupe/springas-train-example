package com.skynet.spms.jbpm.manager;

import java.util.List;

import com.skynet.spms.jbpm.entity.FlowHistoryEntity;
import com.skynet.spms.jbpm.entity.TaskInfoEntity;

public interface WfTaskListManager {
	
	List<TaskInfoEntity> getAllHistoryTaskListByUser(String userName);

	List<TaskInfoEntity> getPreviewTaskList(String userName);

	List<TaskInfoEntity> getTakenTaskList(String userName);

	List<FlowHistoryEntity> getTaskHistoryByFlow(String procInstID);
	

}
