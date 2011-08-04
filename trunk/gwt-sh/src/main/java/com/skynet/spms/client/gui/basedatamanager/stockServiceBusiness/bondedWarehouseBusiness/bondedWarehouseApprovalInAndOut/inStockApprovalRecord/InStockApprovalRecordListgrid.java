package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.inStockApprovalRecord;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class InStockApprovalRecordListgrid extends ListGrid {

	private Logger log = Logger.getLogger("InStockApprovalRecordListgrid");

	public void drawInStockApprovalRecordListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);
		
		// 检验单号
		ListGridField checkAndAcceptSheetNumberFiled = new ListGridField("checkAndAcceptSheetNumber");
		// 通关电子帐册项号
		ListGridField accountBookItemsNumberFiled = new ListGridField("accountBookItemsNumber");
		// 件号
		ListGridField partNumberFiled = new ListGridField("partNumber");
		// 序号/批号
		ListGridField partSerialNumberFiled = new ListGridField("partSerialNumber");
		// 数量
		ListGridField quantityFiled = new ListGridField("quantity");
		// 入库审批号
		ListGridField inStockApprovalNumberFiled = new ListGridField("inStockApprovalNumber");
		// 入库日期
		ListGridField inStockDateFiled = new ListGridField("inStockDate");
		inStockDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 进口报关编号 
		ListGridField customsNumberFiled = new ListGridField("customsNumber");
		// 入库单号
		ListGridField inStockRecordNumberFiled = new ListGridField("inStockRecordNumber");

		checkAndAcceptSheetNumberFiled.setCanFilter(true);
		accountBookItemsNumberFiled.setCanFilter(true);
		partNumberFiled.setCanFilter(true);
		partSerialNumberFiled.setCanFilter(true);
		quantityFiled.setCanFilter(true);
		inStockApprovalNumberFiled.setCanFilter(true);
		inStockDateFiled.setCanFilter(true);
		customsNumberFiled.setCanFilter(true);
		inStockRecordNumberFiled.setCanFilter(true);
		
		setFields(checkAndAcceptSheetNumberFiled
				,accountBookItemsNumberFiled
				,partNumberFiled
				,partSerialNumberFiled
				,quantityFiled
				,inStockApprovalNumberFiled
				,inStockDateFiled
				,customsNumberFiled
				,inStockRecordNumberFiled
				);
		setCellHeight(22);
	}
}