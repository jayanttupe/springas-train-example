package com.skynet.spms.client.gui.supplierSupport.procurementOrder.doProcurementOrder;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

public class DoProcurementOrderItemListGrid extends FilterListGrid {

	public DoProcurementOrderItemListGrid() {
		this.setShowRowNumbers(false);
		drawGrid();
	}

	public void drawGrid() {
		this.setCellHeight(22);

		// 件号
		ListGridField field2 = new ListGridField("partNumber");
		field2.setCanFilter(true);

		// 需求日期
		ListGridField field4 = new ListGridField("deliveryDate");
		field4.setCanFilter(true);
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		// 采购数量
		ListGridField field5 = new ListGridField("plannedQuantity");
		field5.setCanFilter(true);
		Utils.formatQuantityField(field5);

		// 计划单价
		ListGridField field7 = new ListGridField("plannedUnitPrice");
		field7.setCanFilter(true);
		Utils.formatPriceField(field7);

		// 金额
		ListGridField field8 = new ListGridField("unitPriceAmount");
		field8.setCanFilter(true);
		field8.setAlign(Alignment.RIGHT);

		// 备注
		ListGridField field10 = new ListGridField("remarkText");
		field10.setCanFilter(true);

		this.setFields(field2, field4, field5, field7, field8, field10);
	}

}
