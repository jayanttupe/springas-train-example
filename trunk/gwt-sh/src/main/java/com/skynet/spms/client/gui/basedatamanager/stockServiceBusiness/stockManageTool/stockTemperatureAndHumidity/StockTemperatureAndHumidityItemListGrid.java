package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.stockTemperatureAndHumidity;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StockTemperatureAndHumidityItemListGrid extends ListGrid {

	private Logger log = Logger.getLogger("StockTemperatureAndHumidityItemListGrid");

	public  ListGridField stockFiled;

	public void drawTemperatureManageListGridField() {
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		
		// 记录序号
		ListGridField recordSerialNumber = new ListGridField("recordSerialNumber");
		// 记录日期时间
		ListGridField recordDateTime = new ListGridField("recordDateTime");
		recordDateTime.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 湿度
		ListGridField humidity = new ListGridField("humidity");
		// 湿度警告
		ListGridField humidityAlarm = new ListGridField("humidityAlarm");
		// 温度
		ListGridField temperature = new ListGridField("temperature");
		// 温度警告
		ListGridField temperatureAlarm = new ListGridField("temperatureAlarm");
		// 温度单位
		ListGridField partType = new ListGridField("partType");

		setFields(recordSerialNumber
				, recordDateTime
				, humidity
				, humidityAlarm
				, temperature
				, temperatureAlarm
				, partType);
		setCellHeight(22);
	}

}
