package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.packingList;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PackingListPartItemsListgrid extends ListGrid {

	private Logger log = Logger.getLogger("PackingListPartItemsListgrid");

	public void drawPackingListListgrid()
	{
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowRowNumbers(true);

		// 件号
		ListGridField partNumberFiled = new ListGridField("partNumber");
		// 序号/批号
		ListGridField partSerialNumberFiled = new ListGridField("partSerialNumber");
		// 项号
		ListGridField itemNumberFiled = new ListGridField("itemNumber");
		itemNumberFiled.setHidden(true);
		// 件描述
		ListGridField keyWordFiled = new ListGridField("partName");
		// 制造商
		ListGridField manufacturerFiled = new ListGridField("manufacturer");
		// 实发数量
		ListGridField sendQtyFiled = new ListGridField("sendQty");
		// 单位
		ListGridField unitFiled = new ListGridField("unit");
		// 随件证件
		ListGridField certificateTypeFiled = new ListGridField("certificateType");
		// 分箱号
		ListGridField boxIDFiled = new ListGridField("boxID");

		setFields(
				itemNumberFiled
				,partNumberFiled
				,partSerialNumberFiled
				,keyWordFiled
				,sendQtyFiled
				,unitFiled
				,manufacturerFiled
				,certificateTypeFiled
				,boxIDFiled
				);
		
		setCellHeight(22);
	}
}
