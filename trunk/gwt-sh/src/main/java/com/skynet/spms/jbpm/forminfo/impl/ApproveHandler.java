package com.skynet.spms.jbpm.forminfo.impl;

import org.springframework.stereotype.Component;

import com.skynet.spms.jbpm.entity.TaskInfoEntity;
import com.skynet.spms.jbpm.forminfo.FormBindHandler;
import com.skynet.spms.persistence.entity.jbpm.WfPersistenceParam;

@Component
public class ApproveHandler implements FormBindHandler{

	@Override
	public WfPersistenceParam getBindByTaskInfo(TaskInfoEntity taskInfo) {
		
		String pk=taskInfo.getBusKey();
		WfPersistenceParam info=new WfPersistenceParam(taskInfo);
		
		info.setRefBusinessKey(pk);
		info.setRefFormName("approval");
		info.getWfParamID().setDefFormName("approvalForm");
		
		return info;
	}

	@Override
	public String getBindForm() {
		return "approvalForm";
	}

}
