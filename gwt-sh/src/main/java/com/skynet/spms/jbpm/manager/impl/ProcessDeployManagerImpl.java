package com.skynet.spms.jbpm.manager.impl;

import java.io.File;

import org.jbpm.api.Deployment;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.processengine.ProcessEngineImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.jbpm.manager.WfProcessDeployManager;

@Transactional
@Service
public class ProcessDeployManagerImpl implements WfProcessDeployManager {
	
	private final Logger log = LoggerFactory.getLogger(ProcessDeployManagerImpl.class);

	@Autowired
	private ProcessEngine procEngine;

	@Autowired
	private RepositoryService repositoryService;

	@Override
	public ProcessDefinition deployProcess(String key,File defFile) {
		// init environment with spring.
		EnvironmentImpl env = ((ProcessEngineImpl) procEngine)
				.openEnvironment();
		EnvironmentImpl.pushEnvironment(env);

		ProcessDefinition procDef = generProcDef(key,defFile);

		// close temp environment.
		EnvironmentImpl.popEnvironment();
		env.close();
		return procDef;
	}

	private ProcessDefinition generProcDef(String key,File defFile) {
		
		// get last version define
		ProcessDefinition procDef = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey(key).page(0, 1)
				.orderDesc(ProcessDefinitionQuery.PROPERTY_VERSION)
				.uniqueResult();

		if (procDef == null) {
			procDef = newDeploy(defFile);
			return procDef;
		}

		Deployment deploy = repositoryService.createDeploymentQuery()
				.deploymentId(procDef.getDeploymentId()).uniqueResult();

		// get deploy timestamp
		long timeStamp = deploy.getTimestamp();

		if (defFile.lastModified() > timeStamp) {
			// if file been modify after deploy
			procDef = newDeploy(defFile);
		}
		return procDef;
	}

	

	private ProcessDefinition newDeploy(File defFile) {

		NewDeployment newDeploy = repositoryService.createDeployment();
		newDeploy.addResourceFromFile(defFile);
		newDeploy.setTimestamp(System.currentTimeMillis());

		String newDeployID = newDeploy.deploy();

		return  repositoryService.createProcessDefinitionQuery()
				.deploymentId(newDeployID).uniqueResult();
	}

	@Override
	public ProcessDefinition getProcessDefineByID(String processDefID) {
		ProcessDefinition procDef = repositoryService
		.createProcessDefinitionQuery()
		.processDefinitionId(processDefID)
		.page(0, 1)
		.orderDesc(ProcessDefinitionQuery.PROPERTY_VERSION)
		.uniqueResult();
			
		return procDef;
	}

}
