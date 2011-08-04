package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.reparePartRegister;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ReparePartRegisterListgrid extends ListGrid {

	private Logger log = Logger.getLogger("ReparePartRegisterListgrid");

	public void drawReparePartRegisterListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);
		// 送修登记单号
		ListGridField repairRegisterSheetNumberFiled = new ListGridField("repairRegisterSheetNumber");
		// 收料单编号
		ListGridField receivingSheetNumberFiled = new ListGridField("receivingSheetNumber");
		// 合同编号
		ListGridField contractNumberFiled = new ListGridField("contractNumber");
		// 件号
		ListGridField partNumberFiled = new ListGridField("partNumber");
		// 序号/批号
		ListGridField partSerialNumberFiled = new ListGridField("partSerialNumber");
		// 件描述
		ListGridField partNameField = new ListGridField("partName");
		// 制造商
		ListGridField manufacturerField = new ListGridField("manufacturer");
		// 数量
		ListGridField quantityFiled = new ListGridField("quantity");
		// 单位
		ListGridField unitFiled = new ListGridField("unit");

		repairRegisterSheetNumberFiled.setCanFilter(true);
		receivingSheetNumberFiled.setCanFilter(true);
		contractNumberFiled.setCanFilter(true);
		partNumberFiled.setCanFilter(true);
		partSerialNumberFiled.setCanFilter(true);
		partNameField.setCanFilter(true);
		manufacturerField.setCanFilter(true);
		quantityFiled.setCanFilter(true);
		unitFiled.setCanFilter(true);
		
		setFields(repairRegisterSheetNumberFiled
				,receivingSheetNumberFiled
				,contractNumberFiled
				,partNumberFiled
				,partSerialNumberFiled
				,partNameField
				,manufacturerField
				,quantityFiled
				,unitFiled
				);
		setCellHeight(22);
	}
}