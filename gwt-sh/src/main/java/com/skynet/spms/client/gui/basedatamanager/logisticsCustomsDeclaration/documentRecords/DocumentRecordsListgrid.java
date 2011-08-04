package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.documentRecords;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class DocumentRecordsListgrid extends ListGrid {

	private Logger log = Logger.getLogger("DocumentRecordsListgrid");

	public void drawDocumentRecordsListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 物流任务编号
		ListGridField logisticsTasksNumberFiled = new ListGridField("logisticsTaskNumber");
		// 合同编号
		ListGridField contractNumberFiled = new ListGridField("contractNumber");
		// 指令编号
		ListGridField orderNumberFiled = new ListGridField("orderNumber");
		// 运代商
		ListGridField forwarderFiled = new ListGridField("forwarder");
		// 报关代理商
		ListGridField customsAgentFiled = new ListGridField("customsAgent");
        
		logisticsTasksNumberFiled.setCanFilter(true);
		contractNumberFiled.setCanFilter(true);
		orderNumberFiled.setCanFilter(true);
		forwarderFiled.setCanFilter(true);
		customsAgentFiled.setCanFilter(true);
		
		setFields(logisticsTasksNumberFiled
				,contractNumberFiled
				,orderNumberFiled
				,forwarderFiled
				,customsAgentFiled
				);
		setCellHeight(22);
	}

}
