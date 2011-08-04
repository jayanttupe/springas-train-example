package com.skynet.spms.client.gui.portal;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.skynet.spms.client.gui.portal.PortalPanel.PortalMember;
import com.skynet.spms.client.service.ModuleInfoService;
import com.skynet.spms.client.service.ModuleInfoServiceAsync;
import com.smartgwt.client.util.SC;

public class MemberStore {
	
	private Map<String,PortalMember> portalMap=new HashMap<String,PortalMember>();

	
	public MemberStore(HandlerManager eventBus){
		
		
		PortalMember[] memberArray={
				new StaticInfoMember(),
				new InstMsgMember(eventBus),
				new TaskListMember(eventBus),
				new UserMsgMember(),
				new  BlankMember(),
				new ChartMember(),
				new CalendarMember(),
				new MockMember(),
//				new DsCalendarMember()
			};
		
			for(PortalMember member:memberArray){
				portalMap.put(member.getName(),member);
			}
		
	}
	
	public PortalMember getMemberByName(String name){
		PortalMember member= portalMap.get(name);
		if(member==null){
			return portalMap.get("Blank");
		}
		
		return member;
		
	}

//	public String[] getMemberList(){
//		return portalMap.keySet().toArray(new String[0]);
//	}
}
