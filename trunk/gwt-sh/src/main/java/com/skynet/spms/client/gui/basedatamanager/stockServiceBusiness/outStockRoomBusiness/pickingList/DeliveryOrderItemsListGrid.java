package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList;

import java.util.logging.Logger;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class DeliveryOrderItemsListGrid extends ListGrid {

	private Logger log = Logger.getLogger("DeliveryOrderItemsListGrid");

	public void drawDeliveryOrderItemsListGrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setShowRowNumbers(true);

		// 操作
		ListGridField pickingFiled = new ListGridField("picking","操作");
		pickingFiled.setWidth(50);
		pickingFiled.setAlign(Alignment.CENTER);
		// 件号
		ListGridField partNumberField = new ListGridField("partNumber","件号");
		// 件描述
		ListGridField partNameField = new ListGridField("partName","件描述");
		// 制造商
		ListGridField manufacturerField = new ListGridField("manufacturer","制造商");
		// 应发数量
		ListGridField qtyField = new ListGridField("qty","应发数量");
		// 当前库存数量
		ListGridField inStockQtyFiled = new ListGridField("inStockQty","当前库存数量");
		// 已配发数量
		ListGridField sendQtyFiled = new ListGridField("sendQty","已配发数量");
		// 单位
		ListGridField unitField = new ListGridField("unit","单位");
		// 备件状况
		ListGridField partStatusField = new ListGridField("partStatus","备件状况");
		// 备件类型
		ListGridField partTypeField = new ListGridField("partType","备件类型");
		
		partNumberField.setCanEdit(false);
		partNameField.setCanEdit(false);
		manufacturerField.setCanEdit(false);
		qtyField.setCanEdit(false);
		inStockQtyFiled.setCanEdit(false);
		sendQtyFiled.setCanEdit(false);
		unitField.setCanEdit(false);
		partStatusField.setCanEdit(false);
		partTypeField.setCanEdit(false);

		setFields(pickingFiled
				,partNumberField
				,partNameField
				,manufacturerField  
				,qtyField
				,inStockQtyFiled
				,sendQtyFiled
				,unitField
				,partStatusField
				,partTypeField	
				);

		setCellHeight(22);
	}
}
