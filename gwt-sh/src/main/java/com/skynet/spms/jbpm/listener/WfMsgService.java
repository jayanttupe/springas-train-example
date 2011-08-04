package com.skynet.spms.jbpm.listener;

import org.jbpm.api.Execution;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("wfMsgService")
public class WfMsgService implements EventListener{
	
	private Logger log=LoggerFactory.getLogger(WfMsgService.class);

	private String transition;
	
	public void setTransition(String trans){
		this.transition=trans;
	}
	@Override
	public void notify(EventListenerExecution execution) throws Exception {

		Activity activity=execution.getActivity();
		if(activity!=null){
			String name=activity.getName();
			log.info("activity name:"+name);
			
			if(activity.hasActivities()){
				log.info("this event bind to activite");
			}
			
			if(activity.hasIncomingTransitions()){
				log.info("this event bind to incoming transition");
			}
			
			if(activity.hasOutgoingTransitions()){
				log.info("this event bind to outgoing transition");
			}
			
			if(activity.hasOutgoingTransition(transition)){
				log.info("this event bind  to outgoing "+transition);
			}
		}else{
			
			for(Execution subExe:execution.getExecutions()){
				log.info("subExe:"+subExe.getName());
			}
			
		}
		
		log.info("trans:"+transition);
	}

}
