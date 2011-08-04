package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.add;

import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ViewProcurementPlanItemListGrid extends FilterListGrid {

	public ViewProcurementPlanItemListGrid() {
		drawGrid();
	}

	public void drawGrid() {
		// 件号
		ListGridField field2 = new ListGridField("partNumber");
		// 关键字
		ListGridField field3 = new ListGridField("itemKeyword");
		// 需求日期
		ListGridField field4 = new ListGridField("deliveryDate");
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 计划采购数量
		ListGridField field5 = new ListGridField("plannedQuantity");
		setFields(field2, field3, field4, field5);
	}
}
