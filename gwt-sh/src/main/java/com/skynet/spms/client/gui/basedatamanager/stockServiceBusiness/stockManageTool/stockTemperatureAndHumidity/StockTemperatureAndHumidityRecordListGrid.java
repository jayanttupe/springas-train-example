package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.stockTemperatureAndHumidity;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StockTemperatureAndHumidityRecordListGrid extends ListGrid{
	
	private Logger log = Logger.getLogger("StockTemperatureAndHumidityListGrid");
	
	public void drawTemperatureManageListGridField()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");

		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		
		// 库房编号
		ListGridField txtStockRoomNumber = new ListGridField("stockRoomNumber");
		// 测量点位
		ListGridField txtMeasurePoinTlocation = new ListGridField("measurePoinTlocation");
		// 记录开始日期
		ListGridField txtRecordStartDate = new ListGridField("recordStartDate");
		txtRecordStartDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 记录结束日期
		ListGridField txtRecordEndDate = new ListGridField("recordEndDate");
		txtRecordEndDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 记录仪编号
		ListGridField txtRecorderNumber = new ListGridField("recorderNumber");
		// 仓库温湿度记录编号
		ListGridField txtStockTemperatureAndHumidityRecordNumber = new ListGridField("stockTemperatureAndHumidityRecordNumber");
		// 备注
		ListGridField txtRemark = new ListGridField("remark");
		// 附件项号
		ListGridField txtItemNumber = new ListGridField("itemNumber");
		txtItemNumber.setHidden(true);
		
		setFields(txtStockRoomNumber,
				txtMeasurePoinTlocation
				,txtRecordEndDate
				,txtRecorderNumber
				,txtRecordStartDate
				,txtStockTemperatureAndHumidityRecordNumber				
				,txtRemark
				,txtItemNumber
				);
		setCellHeight(22);
	}
	
}