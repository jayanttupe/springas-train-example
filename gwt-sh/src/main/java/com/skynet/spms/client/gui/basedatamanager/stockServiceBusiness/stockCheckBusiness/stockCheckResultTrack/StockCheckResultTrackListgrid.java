package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheckResultTrack;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StockCheckResultTrackListgrid extends ListGrid {

	private Logger log = Logger.getLogger("StockCheckResultTrackListgrid");

	public void drawStockCheckTrackListgrid() {
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);
		
		// 盘点编号
		ListGridField checkNumber = new ListGridField("checkNumber");
		// 盘点库房
		ListGridField stockRoomNumbers = new ListGridField("stockRoomNumber");
		// 盘点项目描述
		ListGridField checkDescribe = new ListGridField("checkDescribe");
		// 总项数
		ListGridField totalItemsQuantity = new ListGridField("totalItemsQuantity");
		// 总金额
		ListGridField totalAmount = new ListGridField("totalAmount");
		// 盘盈项数
		ListGridField earningsItem = new ListGridField("earningsItem");
		// 盘盈金额
		ListGridField earningsAmount = new ListGridField("earningsAmount");
		// 盘亏项数
		ListGridField lossItem = new ListGridField("lossItem");
		// 盘亏金额
		ListGridField lossAmount = new ListGridField("lossAmount");
		// 盘点开始日期
		ListGridField checkStartDate = new ListGridField("checkStartDate");
		checkStartDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 盘点结束日期
		ListGridField checkEndDate = new ListGridField("checkEndDate");
		checkEndDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 状态
		ListGridField state = new ListGridField("state");

		checkNumber.setCanFilter(true);
		stockRoomNumbers.setCanFilter(true);
		checkDescribe.setCanFilter(true);
		totalItemsQuantity.setCanFilter(true);
		totalAmount.setCanFilter(true);
		earningsItem.setCanFilter(true);
		earningsAmount.setCanFilter(true);
		lossItem.setCanFilter(true);
		lossAmount.setCanFilter(true);
		checkStartDate.setCanFilter(true);
		checkEndDate.setCanFilter(true);
		state.setCanFilter(true);
		
		setFields(checkNumber
				, stockRoomNumbers
				, checkDescribe
				, totalItemsQuantity
				, totalAmount
				, earningsItem
				, earningsAmount
				, lossItem
				, lossAmount
				, checkStartDate
				, checkEndDate
				, state
			);
		setCellHeight(22);
	}
}