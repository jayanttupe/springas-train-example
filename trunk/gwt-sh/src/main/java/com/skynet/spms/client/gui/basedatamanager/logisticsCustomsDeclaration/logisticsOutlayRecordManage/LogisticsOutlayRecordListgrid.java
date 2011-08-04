package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.logisticsOutlayRecordManage;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class LogisticsOutlayRecordListgrid extends ListGrid {

	private Logger log = Logger.getLogger("LogisticsOutlayRecordListgrid");

	public void drawLogisticsOutlayRecordListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 物流任务编号
		ListGridField logisticsTaskNumberFiled = new ListGridField("logisticsTaskNumber");
		// 合同编号
		ListGridField contractNumberFiled = new ListGridField("contractNumber");
		// 指令编号
		ListGridField orderNumberFiled = new ListGridField("orderNumber");
		// 总金额
		ListGridField amountFiled = new ListGridField("amount");
		// 状态
		ListGridField statusField = new ListGridField("status");
		
		logisticsTaskNumberFiled.setCanFilter(true);
		contractNumberFiled.setCanFilter(true);
		orderNumberFiled.setCanFilter(true);
		amountFiled.setCanFilter(true);
		statusField.setCanFilter(true);
		
		setFields(logisticsTaskNumberFiled
				,contractNumberFiled
				,orderNumberFiled
				,amountFiled
				,statusField
				);
		 setCellHeight(22);
	}
}