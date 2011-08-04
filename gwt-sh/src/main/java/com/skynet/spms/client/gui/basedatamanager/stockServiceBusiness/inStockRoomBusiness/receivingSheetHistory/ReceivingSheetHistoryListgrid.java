package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheetHistory;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.FormItemType;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ReceivingSheetHistoryListgrid extends ListGrid {

	private Logger log = Logger.getLogger("ReceivingSheetHistoryListgrid");

	public void drawReceivingSheetHistoryListgrid() {
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);
		
		// 收料单号
		ListGridField receivingSheetNumber = new ListGridField("receivingSheetNumber");
		// 业务类型
		ListGridField businessType = new ListGridField("m_BusinessType");			 	
		// 合同编号
		ListGridField contratNumber = new ListGridField("contratNumber");
		// 收货日期
		ListGridField receiveDate = new ListGridField("receiveDate");
		receiveDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		receiveDate.setType(ListGridFieldType.DATE);
		// 运单号
		ListGridField mainWayBill = new ListGridField("mainWayBill");
		// 包装
		ListGridField packagingCode = new ListGridField("m_PackagingCode");				
		// 包装外观
		ListGridField packagingAppearance = new ListGridField("m_PackagingAppearance");		
		// 收货人
		ListGridField consignee = new ListGridField("consignee");
		// 交货人
		ListGridField deliveryMan = new ListGridField("deliveryMan");
		// 货运代理
		ListGridField carrierName = new ListGridField("m_CarrierName");				

		receivingSheetNumber.setCanFilter(true);
		businessType.setCanFilter(true);
		contratNumber.setCanFilter(true);
		receiveDate.setCanFilter(true);
		mainWayBill.setCanFilter(true);
		packagingCode.setCanFilter(true);
		packagingAppearance.setCanFilter(true);
		consignee.setCanFilter(true);
		deliveryMan.setCanFilter(true);
		carrierName.setCanFilter(true);
		
		setFields(receivingSheetNumber
				, businessType
				, contratNumber
				, receiveDate
				, mainWayBill
				, packagingCode
				, packagingAppearance
				, consignee
				, deliveryMan
				, carrierName
				);
		setCellHeight(22);
	}
}