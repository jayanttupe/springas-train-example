package com.skynet.spms.jbpm.listener;

import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.springframework.stereotype.Component;

import com.skynet.spms.service.LogService;

@Component("wfNotifyService")
public class MockWfNotifyService implements EventListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3396840104295953439L;
	
	private String message;
	
	public void setMsg(String msg){
		this.message=msg;
	}

	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		EnvironmentImpl env=EnvironmentImpl.getCurrent();
		
		LogService logService=(LogService) env.get("logService");
		
		logService.doWfLog("from process "+execution.getName());
		logService.doWfLog(" active:"+execution.getActivity().getName());
		logService.doWfLog("msg:"+message);
			
	}
	
	
}
