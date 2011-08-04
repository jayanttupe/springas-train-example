package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheckResult;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StockCheckResultListgrid extends ListGrid {

	private Logger log = Logger.getLogger("StockCheckResultManageListgrid");

	public void drawStockCheckTrackResultManageListgrid() {
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);
	
		// 盘点编号
		ListGridField checkNumberFiled = new ListGridField("checkNumber");
		// 盘点项目描述
		ListGridField checkDescribeField = new ListGridField("checkDescribe");
		// 盘点开始日期
		ListGridField checkStartDateField = new ListGridField("checkStartDate");
		checkStartDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 盘点结束日期
		ListGridField checkEndDateField = new ListGridField("checkEndDate");
		checkEndDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 起始货位
		ListGridField fromCargoSpaceNumberField = new ListGridField("fromCargoSpaceNumber");
		// 结束货位
		ListGridField toCargoSpaceNumberField = new ListGridField("toCargoSpaceNumber");
		// 盘点结果
		ListGridField stockCheckResultField = new ListGridField("stockCheckResult");
		
		checkNumberFiled.setCanFilter(true);
		checkDescribeField.setCanFilter(true);
		checkStartDateField.setCanFilter(true);
		checkEndDateField.setCanFilter(true);
		fromCargoSpaceNumberField.setCanFilter(true);
		toCargoSpaceNumberField.setCanFilter(true);
		stockCheckResultField.setCanFilter(true);
				
		setFields(checkNumberFiled
				,checkDescribeField
				,checkStartDateField
				,checkEndDateField
				,fromCargoSpaceNumberField
				,toCargoSpaceNumberField
				,stockCheckResultField);
		setCellHeight(22);
	}
}