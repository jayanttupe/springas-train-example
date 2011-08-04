package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet;

import java.util.logging.Logger;

import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ReceivingSheetItemsListgrid extends ListGrid {

	private Logger log = Logger.getLogger("ReceivingSheetItemsListgrid");

	public void drawReceivingSheetItemsListgrid(boolean isCanEdit) {
		setCanEdit(isCanEdit);
		setSelectionType(SelectionStyle.SINGLE);
		setShowRowNumbers(true);

		// 收料单ID
		ListGridField receivingSheetIDField = new ListGridField("receivingSheetID");
		receivingSheetIDField.setHidden(true);
		// 项号
		ListGridField itemNumberField = new ListGridField("itemNumber");
		itemNumberField.setHidden(true);
		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		partNumberField.setWidth(105);
		// 序号/批号
		ListGridField partSerialNumberField = new ListGridField("partSerialNumber");
		// 名称
		ListGridField partNameField = new ListGridField("partName");
		// 数量
		ListGridField quantityField = new ListGridField("quantity");
		// 单位
		ListGridField partUnitField = new ListGridField("partUnit");
		// 备件类型
		ListGridField partTypeField = new ListGridField("partType");
		// 是否序号控制
		ListGridField isSerialField = new ListGridField("isSerial");
		isSerialField.setType(ListGridFieldType.BOOLEAN);
		// 分箱号
		ListGridField boxNOField = new ListGridField("boxNO");
		// 备注
		ListGridField memoField = new ListGridField("memo");
		memoField.setHidden(true);
		// 状态
		ListGridField statusField = new ListGridField("status");
		statusField.setHidden(true);
		
		setFields(receivingSheetIDField
				,itemNumberField
				,partNumberField
				,partSerialNumberField
				,partNameField
				,quantityField
				,partUnitField
				,partTypeField
				,isSerialField
				,boxNOField
				,memoField
				,statusField
				);
		setCellHeight(22);
	}
}
