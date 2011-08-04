package com.skynet.spms.client.gui.portal;

import java.util.logging.Logger;

import com.skynet.spms.client.gui.portal.PortalPanel.PortalMember;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.calendar.Calendar;

public class DsCalendarMember implements PortalMember {

	private Calendar calendar;

	private Logger log = Logger.getLogger("ds calendar");

	public DsCalendarMember() {

		calendar = new Calendar();
		calendar.setShowWeekends(false);

		DataSource dataSource = DataSource.get("portalCalendar");
		calendar.setDataSource(dataSource);

		calendar.setStartDateField("startDate");
		calendar.setEndDateField("endDate");
		calendar.setNameField("event");
		calendar.setDescriptionField("description");
		calendar.setAutoFetchData(true);
	}

	@Override
	public Canvas getCanvas() {

//		calendar.fetchData();

		return calendar;
	}

	@Override
	public String getName() {
		return "DsCalendar";
	}

	@Override
	public String getDescription() {
		return "Calendar";
	}

}
