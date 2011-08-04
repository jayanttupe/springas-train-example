package com.skynet.spms.client.gui.portal;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.EventInfo;
import com.skynet.spms.client.gui.portal.PortalPanel.PortalMember;
import com.skynet.spms.client.service.PersonalCalendarService;
import com.skynet.spms.client.service.PersonalCalendarServiceAsync;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.TimeFormatter;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.calendar.CalendarEvent;
import com.smartgwt.client.widgets.calendar.events.CalendarEventAdded;
import com.smartgwt.client.widgets.calendar.events.CalendarEventChangedEvent;
import com.smartgwt.client.widgets.calendar.events.CalendarEventRemoved;
import com.smartgwt.client.widgets.calendar.events.EventAddedHandler;
import com.smartgwt.client.widgets.calendar.events.EventChangedHandler;
import com.smartgwt.client.widgets.calendar.events.EventRemovedHandler;

public class CalendarMember implements PortalMember{
	
	private PersonalCalendarServiceAsync service=GWT.create(PersonalCalendarService.class);
	
	private Calendar calendar;
	
	public CalendarMember(){
		

			calendar=new Calendar();
			calendar.setWidth100();
			calendar.setHeight100();
				      
			calendar.setShowWeekends(false);
			//use 24 hr based times 
	        calendar.setTimeFormatter(TimeFormatter.TOSHORT24HOURTIME);  
	        calendar.setDateFormatter(DateDisplayFormat.TOEUROPEANSHORTDATE);
	        
	        calendar.addEventAddedHandler(new EventAddedHandler(){

				@Override
				public void onEventAdded(CalendarEventAdded event) {
					
					CalendarEvent inst=event.getEvent();
					EventInfo eventInfo=new EventInfo(inst);
					
					service.saveNewEvent(eventInfo, new AsyncCallback<Void>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
							
						}
						
					});
				}
	        	
	        });
	        
	        calendar.addEventChangedHandler(new EventChangedHandler(){

				@Override
				public void onEventChanged(CalendarEventChangedEvent event) {
					CalendarEvent inst=event.getEvent();
					EventInfo eventInfo=new EventInfo(inst);
					eventInfo.setId(inst.getAttribute("eventID"));
					service.saveNewEvent(eventInfo, new AsyncCallback<Void>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
							
						}
						
					});
					
				}
	        	
	        });
	        
	        calendar.addEventRemovedHandler(new EventRemovedHandler(){
	

				@Override
				public void onEventRemoved(CalendarEventRemoved event) {
					CalendarEvent inst=event.getEvent();
					String eventID=inst.getAttribute("eventID");
					
					service.removeEvent(eventID, new AsyncCallback<Void>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
							
						}
						
					});
				}
	        	
	        });
	}
	

	@Override
	public Canvas getCanvas() {
		
		service.getPersonEventList(new AsyncCallback<List<EventInfo>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<EventInfo> result) {
				
				 CalendarEvent[] array=new CalendarEvent[result.size()];
				 
				 int idx=0;
				 for(EventInfo info:result){
					 CalendarEvent event=info.getCalendarEvent();
					 event.setEventId(idx);
					 array[idx]=event;
					 idx++;
				 }
				 
				 calendar.setData(array);
			}
        	
        });
       
//		calendar.setData(getNewRecords());
		return calendar;
	}

	@Override
	public String getName() {
		return "Calendar";
	}

	@Override
	public String getDescription() {
		return "Calendar";
	}
	
//	public CalendarEvent[] getNewRecords() {  
//
//		Date today = new Date();
//		int year = today.getYear();
//		int month = today.getMonth();
//		int start =  today.getDay();
//		return new CalendarEvent[] {
//				new CalendarEvent(1, "Meeting",
//						"Shareholders meeting: monthly forecast report",
//						new Date(year, month, start + 2, 9, 0, 0), new Date(
//								year, month, start + 2, 14, 0, 0)),
//				new CalendarEvent(2, "Realtor",
//						"Breakfast with realtor to discuss moving plans",
//						new Date(year, month, start + 3, 8, 0, 0), new Date(
//								year, month, start + 3, 10, 0, 0)),
//				new CalendarEvent(3, "Soccer", "Little league soccer finals",
//						new Date(year, month, start + 4, 13, 0, 0), new Date(
//								year, month, start + 4, 16, 0, 0)),
//				new CalendarEvent(4, "Sleep", "Catch up on sleep", new Date(
//						year, month, start + 4, 5, 0, 0), new Date(year, month,
//						start + 4, 9, 0, 0)),
//				new CalendarEvent(5, "Inspection", "Home inspector coming",
//						new Date(year, month, start + 4, 10, 0, 0), new Date(
//								year, month, start + 4, 12, 0, 0), false,
//						"testStyle"),
//				new CalendarEvent(6, "Airport run",
//						"Pick James up from the airport", new Date(year, month,
//								start + 4, 1, 0, 0), new Date(year, month,
//								start + 4, 3, 0, 0)),
//				new CalendarEvent(7, "Dinner Party",
//						"Prepare elaborate meal for friends", new Date(year,
//								month, start + 4, 17, 0, 0), new Date(year,
//								month, start + 4, 20, 0, 0)),
//				new CalendarEvent(8, "Poker", "Poker at Steve's house",
//						new Date(year, month, start + 4, 21, 0, 0), new Date(
//								year, month, start + 4, 23, 0, 0)),
//				new CalendarEvent(
//						9,
//						"Meeting",
//						"Board of directors meeting: discussion of next months strategy",
//						new Date(year, month, start + 5, 11, 0, 0), new Date(
//								year, month, start + 5, 15, 0, 0)) };
//	}  

}
