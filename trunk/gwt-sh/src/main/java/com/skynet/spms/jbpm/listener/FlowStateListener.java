package com.skynet.spms.jbpm.listener;

import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.entity.CommonApproveParam;
import com.skynet.spms.manager.approval.ApprovalManager;

@Component("flowStateListener")
public class FlowStateListener implements EventListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7945497113330940215L;

	private Logger log=LoggerFactory.getLogger(FlowStateListener.class);
	
	private String state;
	
	public void setState(String state){
		this.state=state;
	}
	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		EnvironmentImpl env=EnvironmentImpl.getCurrent();
				
		String name=execution.getActivity().getName();
		
		ApprovalManager mang=(ApprovalManager) env.get(ApprovalManager.class);
		
		String pk=(String) execution.getVariable("businessKey");
		CommonApproveParam param=(CommonApproveParam) execution.getVariable("param");
		String type=param.getBusinessType();
		
		log.info("name:"+name+" pk:"+pk+" type:"+type);
		
		mang.updateSheetApprovalStatus(pk, type, name);
	}

	
}
