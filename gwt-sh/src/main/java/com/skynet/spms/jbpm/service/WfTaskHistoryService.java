package com.skynet.spms.jbpm.service;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.api.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.jbpm.entity.FlowHistoryEntity;
import com.skynet.spms.jbpm.entity.WfHistoryTask;
import com.skynet.spms.jbpm.manager.impl.FlowHistoryQueryForProc;

@Component
public class WfTaskHistoryService {


	@Autowired
	private ProcessEngine procEngine;
	
	
	public List<FlowHistoryEntity> getTaskHistoryByFlow(String procInstID){
		
		FlowHistoryQueryForProc query = new FlowHistoryQueryForProc();
		query.setProcInstID(procInstID);

		List<WfHistoryTask> taskList = procEngine.execute(query);
		
		List<FlowHistoryEntity> historyList=new ArrayList<FlowHistoryEntity>();
		
		for(WfHistoryTask task:taskList){
			FlowHistoryEntity entity=new FlowHistoryEntity(task);
			historyList.add(entity);			
		}
		
		return historyList;
		
	}
}
