package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PickingListListgrid extends ListGrid {

	private Logger log = Logger.getLogger("PickingListListgrid");

	public void drawPickingListListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setCanEdit(false);

		// 配料单号
		ListGridField pickingListNumberFiled = new ListGridField("pickingListNumber");
		// 业务类型
		ListGridField businessTypeFiled = new ListGridField("businessType");
		// 指令编号
		ListGridField orderNumberFiled = new ListGridField("orderNumber");
		// 合同编号
		ListGridField contractNumberFiled = new ListGridField("contractNumber");
		// 优先级
		ListGridField priorityFiled = new ListGridField("priority");
		// 交货日期
		ListGridField deliveryDateFiled = new ListGridField("deliveryDate");
		deliveryDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 贸易方式 
		ListGridField tradeMethodsFiled = new ListGridField("tradeMethods");
		// 业务员
		ListGridField operatorFiled = new ListGridField("operator");
		// 是否保税
		ListGridField isBondedFiled = new ListGridField("isBonded");
		// 状态
		ListGridField statusFiled = new ListGridField("status");

		pickingListNumberFiled.setCanFilter(true);
		businessTypeFiled.setCanFilter(true);
		contractNumberFiled.setCanFilter(true);
		orderNumberFiled.setCanFilter(true);
		priorityFiled.setCanFilter(true);
		isBondedFiled.setCanFilter(true);
		deliveryDateFiled.setCanFilter(true);
		operatorFiled.setCanFilter(true);
		tradeMethodsFiled.setCanFilter(true);
		statusFiled.setCanFilter(true);
		
		setFields(pickingListNumberFiled
				,businessTypeFiled
				,contractNumberFiled  
				,orderNumberFiled
				,priorityFiled
				,isBondedFiled
				,deliveryDateFiled
				,operatorFiled
				,tradeMethodsFiled	
				,statusFiled
				);

		setCellHeight(22);
	}
}
