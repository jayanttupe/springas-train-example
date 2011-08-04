package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.repairCodeTask;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class RepairCodeTaskItemListgrid extends ListGrid {

	private Logger log = Logger.getLogger("RepairCodeTaskItemListgrid");

	public void drawRepairCodeTaskItemListgrid() {
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setCellHeight(22);
		
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		fieldList.add(partNumberField);
		// 批次号
		ListGridField partSerialNumberField = new ListGridField("partSerialNumber");
		fieldList.add(partSerialNumberField);
		// 件描述
		ListGridField partNameField = new ListGridField("partName");
		fieldList.add(partNameField);
		// 计量单位
		ListGridField partUnitField = new ListGridField("partUnit");
		fieldList.add(partUnitField);
		// 数量
		ListGridField quantityField = new ListGridField("quantity");
		fieldList.add(quantityField);
		//货位编号
		ListGridField locationNumberField = new ListGridField("locationNumber");
		fieldList.add(locationNumberField);
		// 操作状态(OPN:已新建/WIP:处理中/OVR:已完成/CAN:已取消)
		ListGridField operationStatusField = new ListGridField("operationStatus");
		fieldList.add(operationStatusField);
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
		setFields(fields);
	}
}
