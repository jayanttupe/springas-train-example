package com.skynet.spms.jbpm.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.jbpm.dao.WfPersistenceParamDao;
import com.skynet.spms.jbpm.entity.FlowHistoryEntity;
import com.skynet.spms.jbpm.entity.TaskInfoEntity;
import com.skynet.spms.jbpm.entity.WfHistoryTask;
import com.skynet.spms.jbpm.forminfo.FormBindHandler;
import com.skynet.spms.jbpm.manager.TaskStatus;
import com.skynet.spms.jbpm.manager.WfTaskListManager;
import com.skynet.spms.jbpm.service.WfTaskHistoryService;
import com.skynet.spms.persistence.entity.jbpm.WfPersistenceParam;

import static com.skynet.spms.jbpm.entity.WfInstParamCols.*;

@Service
@Transactional
public class WfTaskListManagerImpl implements WfTaskListManager {


	Logger log = LoggerFactory.getLogger(WfTaskListManagerImpl.class);

	@Autowired
	private ProcessEngine procEngine;

	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private ExecutionService execService;

	@Autowired
	WfPersistenceParamDao persistenceParamDao;

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private WfTaskHistoryService wfHistoryService;
	


	
	

	@Override
	public List<TaskInfoEntity> getAllHistoryTaskListByUser(String userName) {

		TaskHistoryQueryForUser query = new TaskHistoryQueryForUser();
		query.setUserName(userName);

		return generTaskList(query);
	}

	private List<TaskInfoEntity> generTaskList(TaskHistoryQueryForUser query) {
		Collection<WfHistoryTask> taskList = procEngine.execute(query);

		List<TaskInfoEntity> infoList = new ArrayList<TaskInfoEntity>();

		for (WfHistoryTask task : taskList) {
			TaskInfoEntity info = new TaskInfoEntity(task);
			String procInstID = task.getProcess().getProcessInstanceId();
			
			List<WfPersistenceParam> paramList = persistenceParamDao
			.getParamsByProcInstID(procInstID,task.getTask().getId());

			for (WfPersistenceParam wfParam : paramList) {
				info.addFormInfo(wfParam);
		
			}
		
			Map<String,String> paramMap=task.getParamMap();
			
			info.setBusKey(paramMap.get(BUSINESS_KEY));
			info.setFlowType( paramMap.get(FLOW_TYPE));
			info.setPriorityType(paramMap.get(PRIORITY_TYPE));

			infoList.add(info);
		}

		return infoList;
	}


	@Override
	public List<TaskInfoEntity> getPreviewTaskList(String userName) {

		List<Task> taskList = taskService.findGroupTasks(userName);

		List<TaskInfoEntity> infoList = generTaskInfoEntityList(taskList,TaskStatus.waittake);
		return infoList;
//		TaskQueryForGroup query=new TaskQueryForGroup();
//		query.setUserName(userName);
//		List<TaskInfoEntity> taskList = procEngine.execute(query);
//		
//		for(TaskInfoEntity info:taskList){
//			fillTaskWithParams(info,TaskStatus.waittake);
//		}
//		
//		return taskList;
	}

	private void fillTaskWithParams(TaskInfoEntity info,TaskStatus status) {
		String[] paramArray={ BUSINESS_KEY,FLOW_TYPE,PRIORITY_TYPE};
		
		Map<String,Object> paramMap=execService.getVariables(info.getProcInstID(), new HashSet<String>(Arrays.asList(paramArray)));
		
		String busKey=(String) paramMap.get(BUSINESS_KEY);
		info.setBusKey(busKey);			
		info.setFlowType((String)paramMap.get(FLOW_TYPE));
		info.setPriorityType((String)paramMap.get(PRIORITY_TYPE));
		
		info.setStatus(status.name());
		
		List<WfPersistenceParam> paramList=persistenceParamDao.generWfPersistenParams(info.getFormResourceName(),info);
		for(WfPersistenceParam param:paramList){
			info.addFormInfo(param);
		}
		
		for (String out : taskService.getOutcomes(info.getTaskID())) {
			info.addOutcoming(out);
		}
	}

	@Override
	public List<TaskInfoEntity> getTakenTaskList(String userName) {

		List<Task> taskList = taskService.findPersonalTasks(userName);

		List<TaskInfoEntity> infoList = generTaskInfoEntityList(taskList,TaskStatus.waitexec);
		return infoList;
//		TaskQueryForUser query=new TaskQueryForUser();
//		query.setUserName(userName);
//		List<TaskInfoEntity> taskList = procEngine.execute(query);
//		
//		for(TaskInfoEntity info:taskList){
//			fillTaskWithParams(info,TaskStatus.waitexec);
//		}
//		
//		return taskList;
	}

	private List<TaskInfoEntity> generTaskInfoEntityList(List<Task> taskList,TaskStatus status) {
		List<TaskInfoEntity> infoList = new ArrayList<TaskInfoEntity>();

		for (Task task : taskList) {
			
//			Execution procInst = execService.findProcessInstanceById(task.getExecutionId());
						
//			if (!procInst.getIsProcessInstance()) {
//				procInst = procInst.getProcessInstance();
//			}
			TaskInfoEntity info=new TaskInfoEntity(task);

			String[] paramArray={ BUSINESS_KEY,FLOW_TYPE,PRIORITY_TYPE};
			
			Map<String,Object> paramMap=execService.getVariables(task.getExecutionId(), new HashSet<String>(Arrays.asList(paramArray)));
			
			String busKey=(String) paramMap.get(BUSINESS_KEY);
			info.setBusKey(busKey);			
			info.setFlowType((String)paramMap.get(FLOW_TYPE));
			info.setPriorityType((String)paramMap.get(PRIORITY_TYPE));
			
			info.setStatus(status.name());
			
			List<WfPersistenceParam> paramList=persistenceParamDao.generWfPersistenParams(task.getFormResourceName(),info);
			for(WfPersistenceParam param:paramList){
				info.addFormInfo(param);
			}
			
			for (String out : taskService.getOutcomes(task.getId())) {
				info.addOutcoming(out);
			}			
			infoList.add(info);
		}
		return infoList;
	}

//	private void fillBindFormInfo(TaskInfoEntity taskInfo, Task task) {
//
//		String formNames = task.getFormResourceName();
//
//		if (StringUtils.isBlank(formNames)) {
//			return;
//		}
//
//		String[] formArray = StringUtils.split(formNames, ",");
//		formArray = StringUtils.stripAll(formArray);
//
//		for (String form : formArray) {
//			FormBindHandler handler = bindMap.get(form);
//			WfPersistenceParam wfParam = handler.getBindByTaskInfo(taskInfo);
////			persistenceParamDao.saveOrUpdateParam(wfParam);
//			taskInfo.addFormInfo(wfParam);
//		}
//		return;
//	}

	@Override
	public List<FlowHistoryEntity> getTaskHistoryByFlow(String procInstID){
		
		return wfHistoryService.getTaskHistoryByFlow(procInstID);
	}


}
