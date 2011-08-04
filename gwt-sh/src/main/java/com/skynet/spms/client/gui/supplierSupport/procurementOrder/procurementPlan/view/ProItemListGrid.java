package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.view;

import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ProItemListGrid extends FilterListGrid {

	public ProItemListGrid() {
		this.setCanRemoveRecords(false);
		buildListGrid();
	}

	public void buildListGrid() {
		// 件号
		ListGridField f22 = new ListGridField("partNumber");
		// 计划采购数量
		ListGridField f44 = new ListGridField("plannedQuantity");
		this.setFields(f22, f44);
	}
}
