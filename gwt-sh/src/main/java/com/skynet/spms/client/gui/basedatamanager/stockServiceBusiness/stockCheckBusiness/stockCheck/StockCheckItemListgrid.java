package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheck;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StockCheckItemListgrid extends ListGrid {

	private Logger log = Logger.getLogger("StockCheckItemListgrid");

	public void drawStockCheckItemListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
	
		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		// 序号/批号
		ListGridField partSerialNumberField = new ListGridField("partSerialNumber");
		// 货位编号
		ListGridField freightLotNumberField = new ListGridField("cargoSpaceNumber");
		// 件描述
		ListGridField partNameField = new ListGridField("partName");
		// 数量
		ListGridField quantityField = new ListGridField("quantity");
		// 单位
		ListGridField partUnitField = new ListGridField("partUnit");

		setFields(partNumberField
				,partSerialNumberField
				,freightLotNumberField				
				,partNameField
				,quantityField
				,partUnitField
				);
		setCellHeight(22);
	}
}