package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.partsInventory.partsInventoryRecord;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PartsInventoryRecordListgrid extends ListGrid {

	private Logger log = Logger.getLogger("PartsInventoryRecordListgrid");

	public void drawSimpleListgrid(){
		setShowFilterEditor(true);
		setCanRemoveRecords(false);
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);

		// 件号
		ListGridField partNumberFiled = new ListGridField("partNumber");
		partNumberFiled.setCanFilter(true);  
		// 序号/批号
		ListGridField partSerialNumberFiled = new ListGridField("partSerialNumber");
		partSerialNumberFiled.setCanFilter(true); 
		// 货位编号
		ListGridField cargoSpaceNumberFiled = new ListGridField("cargoSpaceNumber");
		cargoSpaceNumberFiled.setCanFilter(true); 
		// 入库日期
		ListGridField inStockDateFiled = new ListGridField("inStockDate");
		inStockDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		inStockDateFiled.setCanFilter(true);
		
		setFields(partNumberFiled,partSerialNumberFiled,
				cargoSpaceNumberFiled,inStockDateFiled);
	}
	
	public void drawPartsInventoryRecordListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);

		// 件号
		ListGridField partNumberFiled = new ListGridField("partNumber");
		partNumberFiled.setWidth(105);
		// 序号/批号
		ListGridField partSerialNumberFiled = new ListGridField("partSerialNumber");
		partSerialNumberFiled.setWidth(100);
		// 制造商代码
		ListGridField manufacturerCodeField = new ListGridField("manufacturerCode");
		// 出厂日期
		ListGridField buildDateField = new ListGridField("buildDate");
		buildDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 件描述
		ListGridField keywordField = new ListGridField("partName", "件描述");
		// 库存结存数量
		ListGridField balanceQuantityFiled = new ListGridField("balanceQuantity");
		// 单位
		ListGridField unitFiled = new ListGridField("unit");
		// 库房编号
		ListGridField stockRoomNumberFiled = new ListGridField("stockRoomNumber");
		// 货位编号
		ListGridField cargoSpaceNumberFiled = new ListGridField("cargoSpaceNumber");
		cargoSpaceNumberFiled.setWidth(105);
		// 备件状况
		ListGridField partStatusFiled = new ListGridField("partStatus");
		// 是否冻结
		ListGridField isFreezeFiled = new ListGridField("isFreeze", "是否冻结");
		// 入库员
		ListGridField inStockOperatorFiled = new ListGridField("inStockOperator", "入库员");
		// 入库日期
		ListGridField inStockDateFiled = new ListGridField("inStockDate");
		inStockDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
	
		partNumberFiled.setCanFilter(true);  
		partSerialNumberFiled.setCanFilter(true); 
		manufacturerCodeField.setCanFilter(true); 
		keywordField.setCanFilter(true); 
		balanceQuantityFiled.setCanFilter(true);  
		unitFiled.setCanFilter(true);
		stockRoomNumberFiled.setCanFilter(true); 
		cargoSpaceNumberFiled.setCanFilter(true);  
		partStatusFiled.setCanFilter(true); 
		isFreezeFiled.setCanFilter(true);
		inStockOperatorFiled.setCanFilter(true);
		inStockDateFiled.setCanFilter(true);
		buildDateField.setCanFilter(true); 

		setFields(partNumberFiled 
				,partSerialNumberFiled
				,manufacturerCodeField
				,buildDateField
				,keywordField
				,balanceQuantityFiled 
				,unitFiled
				,stockRoomNumberFiled
				,cargoSpaceNumberFiled 
				,partStatusFiled
				,isFreezeFiled 
				,inStockOperatorFiled
				,inStockDateFiled
				);
		setCellHeight(22);
	}
}