package com.skynet.spms.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.skynet.spms.client.entity.EventInfo;

@RemoteServiceRelativePath("personalCalendarService.form")
public interface PersonalCalendarService extends RemoteService{

	List<EventInfo> getPersonEventList();
	
	void saveNewEvent(EventInfo info);
	
	void removeEvent(String eventID);
}
