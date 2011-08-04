package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ReceivingSheetListgrid extends ListGrid{

	private Logger log = Logger.getLogger("ReceivingSheetManageListgrid");
	
	public void drawsheetManageListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);

		// 收料单编号
		ListGridField receivingSheetNumberFiled = new ListGridField("receivingSheetNumber");
		receivingSheetNumberFiled.setWidth(95);
		// 是否保税
		ListGridField isBondedFiled = new ListGridField("isBonded");
		isBondedFiled.setType(ListGridFieldType.BOOLEAN);
		// 是否送修登记
		ListGridField isRepairFiled = new ListGridField("isRepair");
		isRepairFiled.setType(ListGridFieldType.BOOLEAN);
		// 提货指令单编号
		ListGridField orderNumberFiled = new ListGridField("orderNumber");
		orderNumberFiled.setWidth(95);
		// 业务类型
		ListGridField businessTypeFiled = new ListGridField("businessType");
		// 合同编号
		ListGridField contractNumberFiled = new ListGridField("contractNumber");
		// 运单号
		ListGridField mainWayBillFiled = new ListGridField("mainWayBill");
		// 包装外观
		ListGridField packagingAppearanceFiled = new ListGridField("packagingAppearance");
		// 箱数
		ListGridField boxQuantityFiled = new ListGridField("boxQuantity");
		// 物流操作人员 
		ListGridField logisticsCreateByFiled = new ListGridField("logisticsCreateBy");
		// 物流操作日期
		ListGridField logisticsCreateDateFiled = new ListGridField("logisticsCreateDate");
		logisticsCreateDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 收料人
		ListGridField receivingUserFiled = new ListGridField("receivingUser");
		// 收料日期
		ListGridField receivingDateFiled = new ListGridField("receivingDate");
		receivingDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		receivingSheetNumberFiled.setCanFilter(true);
		isBondedFiled.setCanFilter(true);
		isRepairFiled.setCanFilter(true);
		orderNumberFiled.setCanFilter(true);
		businessTypeFiled.setCanFilter(true);
		contractNumberFiled.setCanFilter(true);
		mainWayBillFiled.setCanFilter(true);
		packagingAppearanceFiled.setCanFilter(true);
		boxQuantityFiled.setCanFilter(true);
		logisticsCreateByFiled.setCanFilter(true);
		logisticsCreateDateFiled.setCanFilter(true);
		receivingUserFiled.setCanFilter(true);
		receivingDateFiled.setCanFilter(true);

		setFields(receivingSheetNumberFiled
					,isBondedFiled
					,isRepairFiled
					,orderNumberFiled
					,businessTypeFiled
					,contractNumberFiled
					,mainWayBillFiled
					,packagingAppearanceFiled
					,boxQuantityFiled
					,logisticsCreateByFiled
					,logisticsCreateDateFiled
					,receivingUserFiled
					,receivingDateFiled
					);
		setCellHeight(22);
	}
}
