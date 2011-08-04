package com.skynet.spms.web.control;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.service.PollService;
import com.skynet.spms.instmsg.UserMsgSign;
import com.skynet.spms.service.GwtMsgSignService;

@Controller("gwtPollAction")
@GwtRpcEndPoint
public class GwtPollAction implements PollService {

	private Logger log = LoggerFactory.getLogger(GwtPollAction.class);

	@Autowired
	private GwtMsgSignService msgSignService;
	
	
	private ConcurrentMap<String,Set<String>> queueMap=new ConcurrentHashMap<String,Set<String>>();
	
	@Override
	public boolean checkIsMsgReceive(){
		
		return msgSignService.checkIsMsgReceive(GwtActionHelper.getCurrUser()); 
	}
	
	
}
