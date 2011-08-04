package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.add;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ViewProcurementOrderItemListGrid extends FilterListGrid {

	public ViewProcurementOrderItemListGrid() {
		this.setCanRemoveRecords(true);
		drawGrid();
	}

	public void drawGrid() {
		// 件号
		ListGridField field2 = new ListGridField("partNumber");
		// 需求日期
		ListGridField field4 = new ListGridField("deliveryDate");
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 采购数量
		ListGridField field5 = new ListGridField("plannedQuantity");
		Utils.formatQuantityField(field5);
		setFields(field2, field4, field5);
	}
}
