package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.inStockRecord;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class InStockRecordListgrid extends ListGrid {

	private Logger log = Logger.getLogger("InStockRecordListgrid");

	public void drawInStockRecordListgrid() {
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 检验单号
		ListGridField checkAndAcceptSheetNumberFiled = new ListGridField("checkAndAcceptSheetNumber");
		checkAndAcceptSheetNumberFiled.setWidth(95);
		// 合同编号
		ListGridField contratNumberFiled = new ListGridField("contratNumber");
		contratNumberFiled.setWidth(90);
		// 件号
		ListGridField partNumberFiled = new ListGridField("partNumber");
		partNumberFiled.setWidth(105);
		// 序号/批号
		ListGridField partSerialNumberFiled = new ListGridField("partSerialNumber");
		partSerialNumberFiled.setWidth(100);
		// 库房编号
		ListGridField stockRoomNumberFiled = new ListGridField("stockRoomNumber");
		// 库房名称
		ListGridField stockRoomChineseNameFiled = new ListGridField("stockRoomChineseName");
		stockRoomChineseNameFiled.setWidth(160);
		// 推荐货位
		ListGridField recCargoSpaceNumFiled = new ListGridField("recCargoSpaceNum");
		recCargoSpaceNumFiled.setWidth(105);
		// 数量
		ListGridField quantityFiled = new ListGridField("quantity");
		quantityFiled.setWidth(50);
		// 寿命件
		ListGridField isLifePartField = new ListGridField("isLifePart");
		// 寿命期
		ListGridField usefulLifePeriodFiled = new ListGridField("usefulLifePeriod");
		// 备件状况
		ListGridField partStatusCodeFiled = new ListGridField("partStatusCode");
		// 下达发卡状态
		ListGridField sendCardStatusFiled = new ListGridField("sendCardStatus");
		// 下达上架状态
		ListGridField shelvingStatusFiled = new ListGridField("shelvingStatus");
		
		checkAndAcceptSheetNumberFiled.setCanFilter(true);
		contratNumberFiled.setCanFilter(true);
		partNumberFiled.setCanFilter(true);
		partSerialNumberFiled.setCanFilter(true);
		stockRoomNumberFiled.setCanFilter(true);
		stockRoomChineseNameFiled.setCanFilter(true);
		recCargoSpaceNumFiled.setCanFilter(true);
		quantityFiled.setCanFilter(true);
		isLifePartField.setCanFilter(true);
		usefulLifePeriodFiled.setCanFilter(true);
		partStatusCodeFiled.setCanFilter(true);
		sendCardStatusFiled.setCanFilter(true);
		shelvingStatusFiled.setCanFilter(true);

		setFields(checkAndAcceptSheetNumberFiled
				,contratNumberFiled
				,partNumberFiled
				,partSerialNumberFiled
				,stockRoomNumberFiled
				,stockRoomChineseNameFiled
				,recCargoSpaceNumFiled
				,quantityFiled
				,isLifePartField
				,usefulLifePeriodFiled
				,partStatusCodeFiled
				,sendCardStatusFiled
				,shelvingStatusFiled);
		setCellHeight(22);
	}
}