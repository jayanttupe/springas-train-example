package com.skynet.spms.action.portal;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.datasource.GetEntityCls;
import com.skynet.spms.manager.organization.PersonalCalenderManager;
import com.skynet.spms.persistence.entity.organization.userInformation.PersonalCalendar;

@Component
public class CalendarAction implements DataSourceAction<PersonalCalendar>,GetEntityCls {


	@Autowired
	private PersonalCalenderManager calMang;
	
	@Override
	public String[] getBindDsName() {
		
		return new String[]{"portalCalendar"};
	}

	@Override
	public void insert(PersonalCalendar item) {
		calMang.saveCalendar(item, GwtActionHelper.getCurrUser());
	}

	@Override
	public PersonalCalendar update(Map<String, Object> newValues, String itemID) {
				
		return calMang.updateCalendar(newValues,itemID);
	}

	@Override
	public void delete(String itemID) {
		calMang.deleteCalendar(itemID);
	}

	@Override
	public List<PersonalCalendar> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<PersonalCalendar> getList(int startRow, int endRow) {
	
		return calMang.getEventListByUser(GwtActionHelper.getCurrUser());
	}


	@Override
	public Class getEntityClass() {
		return PersonalCalendar.class;
	}

}
