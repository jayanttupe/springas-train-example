package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheckResult;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StockCheckResultItemListgrid extends ListGrid {

	private Logger log = Logger.getLogger("StockCheckResultItemManageListgrid");

	public  ListGridField stockFiled;

	public void drawStockCheckResultItemManageListgrid() {
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
	
		// 货位编号
		ListGridField freightLotNumberField = new ListGridField("cargoSpaceNumber", "货位编号");
		// 件号
		ListGridField partNumberField = new ListGridField("partNumber", "件号");
		// 序号/批号
		ListGridField partSerialNumberField = new ListGridField("partSerialNumber", "序号/批号");
		// 件描述
		ListGridField partNameField = new ListGridField("partName", "件描述");
		// 数量
		ListGridField quantityField = new ListGridField("quantity", "数量");
		// 单位
		ListGridField partUnitField = new ListGridField("partUnit", "单位");
		// 盘点结果
		ListGridField stockCheckResultField = new ListGridField("stockCheckResult", "盘点结果");

		setFields(partNumberField
				,partSerialNumberField
				,freightLotNumberField				
				,partNameField
				,quantityField
				,partUnitField
				,stockCheckResultField
				);
		setCellHeight(22);
	}
}
