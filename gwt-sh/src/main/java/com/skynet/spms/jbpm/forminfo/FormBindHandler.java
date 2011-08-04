package com.skynet.spms.jbpm.forminfo;

import com.skynet.spms.jbpm.entity.TaskInfoEntity;
import com.skynet.spms.persistence.entity.jbpm.WfPersistenceParam;

public interface FormBindHandler {
	
	WfPersistenceParam getBindByTaskInfo(TaskInfoEntity taskInfo);

	String getBindForm();

}
