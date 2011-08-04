package com.skynet.spms.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.EventInfo;

public interface PersonalCalendarServiceAsync {

	void getPersonEventList(AsyncCallback<List<EventInfo>> callback);
	
	void saveNewEvent(EventInfo info,AsyncCallback<Void> callback);

	void removeEvent(String eventID,AsyncCallback<Void> callback);
}
