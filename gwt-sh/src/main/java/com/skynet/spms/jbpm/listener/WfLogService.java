package com.skynet.spms.jbpm.listener;

import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.Activity;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.springframework.stereotype.Component;

import com.skynet.spms.service.LogService;

@Component("wfLogService")
public class WfLogService implements EventListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3665555269046613430L;
	private String tag;
	
	public void setTag(String tag){
		this.tag=tag;
	}
		

	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		
		LogService logService=EnvironmentImpl.getFromCurrent(LogService.class);
		
		Activity activity=execution.getActivity();
		if(activity!=null){
			logService.doWfLog("active:"+activity.getName());
		}
		logService.doWfLog("tag:"+tag);
	}
	
	

}
