package com.skynet.spms.client.entity;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.smartgwt.client.widgets.calendar.CalendarEvent;

public class EventInfo implements IsSerializable{

	private Date dateStart;
	
	private Date dateEnd;
	
	private String title;
	
	private String context;
	
	private boolean canEdit=true;
	
	private String id;
	
	public EventInfo(){
		
	}
	
	public EventInfo(CalendarEvent event){
		dateStart=event.getStartDate();
		dateEnd=event.getEndDate();
		title=event.getName();
		context=event.getDescription();
		canEdit=event.getCanEdit();
		
	}
	
	public CalendarEvent getCalendarEvent(){
		CalendarEvent event=new CalendarEvent();
		event.setStartDate(dateStart);
		event.setEndDate(dateEnd);
		event.setName(title);
		event.setDescription(context);
		event.setCanEdit(canEdit);
		event.setAttribute("eventID", id);
		return event;
	}
	
	

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
