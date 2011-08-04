package com.skynet.spms.jbpm.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.prop.PropEnum;
import com.skynet.common.prop.PropManager;
import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.jbpm.entity.FlowType;
import com.skynet.spms.jbpm.entity.TaskInfoEntity;
import com.skynet.spms.jbpm.entity.WfTaskInfo;
import com.skynet.spms.jbpm.manager.TaskStatus;
import com.skynet.spms.jbpm.manager.WfTaskListManager;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

@Component
public class WorkListAction implements DataSourceAction<WfTaskInfo> {


	@Autowired
	private PropManager messageProp;

	@Autowired
	private WfTaskListManager taskManager;
	
	@Override
	public String[] getBindDsName() {
		
		return new String[]{"wfTaskSimpInfo"};
	}

	@Override
	public void insert(WfTaskInfo item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public WfTaskInfo update(Map<String, Object> newValues, String itemID) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(String itemID) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public List<WfTaskInfo> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		String priority=(String) values.get("priority");
		String type=(String)values.get("flowType");
		
		List<WfTaskInfo> workList=getTaskList();
		
		
		List<WfTaskInfo> result=new ArrayList<WfTaskInfo>();
		Priority priType=null;
		if(priority!=null&&!"all".equals(priority)){
			priType=Priority.valueOf(priority);
		}
		FlowType flowType=null;
		if(type!=null&&!"all".equals(type)){
			flowType=FlowType.valueOf(type);
		}
		for(WfTaskInfo work:workList){
			if(priType!=null&&priType!=work.getPriority()){
				continue;
			}
			if(flowType!=null&&flowType==work.getFlowType()){
				continue;
			}
			result.add(work);
		}
		
		endRow=Math.min(result.size(), endRow);
		if(endRow<startRow){
			return result;
		}else{
			return result.subList(startRow, endRow);
		}
	}

	@Override
	public List<WfTaskInfo> getList(int startRow, int endRow) {
		List<WfTaskInfo> workList=getTaskList();
				
		if(startRow>workList.size()){
			return new ArrayList<WfTaskInfo>();
		}
		endRow=Math.min(workList.size(), endRow);
		if(endRow<startRow){
			return workList;
		}else{
			return workList.subList(startRow, endRow);
		}
	}
	
	private List<WfTaskInfo> getTaskList() {

		List<TaskInfoEntity> list=new ArrayList<TaskInfoEntity>();

		String userName=GwtActionHelper.getCurrUser();
		
		PropertyEntity wfProp = messageProp.getPropEntity(
		GwtActionHelper.getLocale(), PropEnum.WfTaskInfo);
	
	
		List<TaskInfoEntity> takenList=taskManager.getPreviewTaskList(userName);
		list.addAll(takenList);
		List<TaskInfoEntity> waitList=taskManager.getTakenTaskList(userName);
		list.addAll(waitList);
			
		List<TaskInfoEntity> historyList=taskManager.getAllHistoryTaskListByUser(userName);
		list.addAll(historyList);
			
		List<WfTaskInfo> infoList=new ArrayList<WfTaskInfo>();
		
		for(TaskInfoEntity taskInfo:list){

			infoList.add(taskInfo.getTaskInfo(wfProp));
		}
						
		return infoList;
	}

	
}
