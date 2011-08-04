package com.skynet.spms.web.control;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.entity.EventInfo;
import com.skynet.spms.client.service.PersonalCalendarService;
import com.skynet.spms.manager.organization.PersonalCalenderManager;
import com.skynet.spms.persistence.entity.organization.userInformation.PersonalCalendar;

@Controller
@GwtRpcEndPoint
public class CalendarAction implements PersonalCalendarService {

	@Autowired
	private PersonalCalenderManager calMang;
	
	@Override
	public List<EventInfo> getPersonEventList() {
		List<PersonalCalendar> calList=calMang.getEventListByUser(GwtActionHelper.getCurrUser());
		
		List<EventInfo> infoList=new ArrayList<EventInfo>();
		for(PersonalCalendar cal:calList){
			infoList.add(cal.getEventInfo());
		}
		return infoList;
	}

	@Override
	public void saveNewEvent(EventInfo info) {
		if(StringUtils.isNotBlank(info.getId())){
			calMang.updateCalendar(info);
		}else{
			PersonalCalendar cal=new PersonalCalendar();
			cal.updateByEvent(info);
			calMang.saveCalendar(cal, GwtActionHelper.getCurrUser());			
		}
	}
	

	@Override
	public void removeEvent(String eventID) {
		
		calMang.deleteCalendar(eventID);
	}

}
