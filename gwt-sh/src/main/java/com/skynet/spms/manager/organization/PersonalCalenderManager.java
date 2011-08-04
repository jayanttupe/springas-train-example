package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.client.entity.EventInfo;
import com.skynet.spms.persistence.entity.organization.userInformation.PersonalCalendar;

public interface PersonalCalenderManager {

	void deleteCalendar(String id);

	List<PersonalCalendar> getEventListByUser(String userName);

	void saveCalendar(PersonalCalendar calendar, String userName);

	void updateCalendar(EventInfo info);

	PersonalCalendar updateCalendar(Map<String, Object> newValues, String id);

}
