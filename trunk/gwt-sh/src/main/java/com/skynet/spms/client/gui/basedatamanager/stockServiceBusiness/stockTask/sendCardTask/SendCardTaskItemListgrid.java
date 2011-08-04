package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.sendCardTask;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class SendCardTaskItemListgrid extends ListGrid {

	private Logger log = Logger.getLogger("SendCardTaskItemListgrid");

	public void drawSendCardTaskItemListgrid() {
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);

		// 任务ID 
		ListGridField taskIDField = new ListGridField("taskID");
		taskIDField.setHidden(true);
		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		// 批次号
		ListGridField partSerialNumberField = new ListGridField("partSerialNumber");
		// 件描述
		ListGridField partNameField = new ListGridField("partName");
		// 计量单位
		ListGridField partUnitField = new ListGridField("partUnit");
		// 数量
		ListGridField quantityField = new ListGridField("quantity");
		// 待入库记录ID
		ListGridField waitInStockRecordIDField = new ListGridField("waitInStockRecordID");
		waitInStockRecordIDField.setHidden(true);
		// 推荐货位
		ListGridField recCargoSpaceNumberField = new ListGridField("recCargoSpaceNumber", "推荐货位");
		// 实际货位
		ListGridField cargoSpaceNumberField = new ListGridField("cargoSpaceNumber", "实际货位");
		// 操作状态(OPN:已新建/WIP:处理中/OVR:已完成/CAN:已取消)
		ListGridField operationStatusField = new ListGridField("operationStatus", "操作状态");

		setFields(taskIDField,
				partNumberField,
				partSerialNumberField,
				partNameField,
				partUnitField,
				quantityField,
				waitInStockRecordIDField,
				recCargoSpaceNumberField,
				cargoSpaceNumberField,
				operationStatusField
				);
		setCellHeight(22);
	}

}
