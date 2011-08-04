package com.skynet.spms.jbpm.manager;

import java.io.File;

import org.jbpm.api.ProcessDefinition;

public interface WfProcessDeployManager {
	
	ProcessDefinition deployProcess(String key,File file);

	ProcessDefinition getProcessDefineByID(String processDefID);

}
