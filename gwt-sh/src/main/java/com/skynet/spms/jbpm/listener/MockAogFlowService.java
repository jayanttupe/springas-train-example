package com.skynet.spms.jbpm.listener;

import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.springframework.stereotype.Component;

import com.skynet.spms.service.LogService;

@Component("aogFlowService")
public class MockAogFlowService implements EventListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3013646280588071813L;
	
	private boolean aogFlow=false;
		

	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		EnvironmentImpl env=EnvironmentImpl.getCurrent();
		LogService logService=(LogService) env.get("logService");

		if(aogFlow){
			logService.doWfLog("in real env,start the aog relation workflow");
		}else{
			logService.doWfLog("no aog flow,exit");
		}
		
	}
	
	
}
