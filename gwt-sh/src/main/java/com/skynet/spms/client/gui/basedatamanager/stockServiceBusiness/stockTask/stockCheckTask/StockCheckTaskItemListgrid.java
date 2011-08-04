package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.stockCheckTask;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StockCheckTaskItemListgrid extends ListGrid {

	private Logger log = Logger.getLogger("StockCheckTaskItemListgrid");

	public void drawStockCheckTaskItemListgrid() {
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
		// 盘点数量
		ListGridField realityQuantityField = new ListGridField("realityQuantity");
		// 推荐货位
		ListGridField cargoSpaceNumberField = new ListGridField("cargoSpaceNumber");
		// 操作状态(OPN:已新建/WIP:处理中/OVR:已完成/CAN:已取消)
		ListGridField operationStatusField = new ListGridField("operationStatus");

		setFields(taskIDField,
				partNumberField,
				partSerialNumberField,
				partNameField,
				partUnitField,
				quantityField,
				realityQuantityField,
				cargoSpaceNumberField,
				operationStatusField
				);
		setCellHeight(22);
	}

}
