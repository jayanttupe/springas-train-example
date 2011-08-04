package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PickingListPartItemsListgrid extends ListGrid {

	private Logger log = Logger.getLogger("PickingListPartItemsListgrid");

	public void drawPickingListPartItemsListgrid()
	{
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(true);
		setShowRowNumbers(true);

		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		// 序号/批号
		ListGridField partSerialNumberField = new ListGridField("partSerialNumber");
		// 件描述
		ListGridField partNameField = new ListGridField("partName");
		// 制造商
		ListGridField manufacturerField = new ListGridField("manufacturer");
		// 实发数量
		ListGridField sendQtyField = new ListGridField("sendQty");
		// 单位
		ListGridField unitField = new ListGridField("unit");
		// 备件状况
		ListGridField partStatusField = new ListGridField("partStatus");
		// 库房编号
		ListGridField stockRoomNumberField = new ListGridField("stockRoomNumber");
		// 货位编号
		ListGridField cargoSpaceNumberField = new ListGridField("cargoSpaceNumber");
		// 剩余寿命
		ListGridField lifeField = new ListGridField("life");
		
		setFields(partNumberField
				,partNameField
				,manufacturerField
				,sendQtyField
				,unitField
				,partSerialNumberField
				,partStatusField
				,stockRoomNumberField
				,cargoSpaceNumberField
				,lifeField
				);
		setCellHeight(22);
	}
}
