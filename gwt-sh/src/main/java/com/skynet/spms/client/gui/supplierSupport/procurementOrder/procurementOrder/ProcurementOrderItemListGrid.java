package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 采购指令明细项
 * 
 * @author Tony FANG
 * 
 */
public class ProcurementOrderItemListGrid extends FilterListGrid {

	public ProcurementOrderItemListGrid() {
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
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		field4.setCanFilter(true);
		// 采购数量
		ListGridField field5 = new ListGridField("plannedQuantity");
		Utils.formatQuantityField(field5);
		field5.setCanFilter(true);
		// 计划单价
		ListGridField field7 = new ListGridField("plannedUnitPrice");
		Utils.formatPriceField(field7);
		field7.setCanFilter(true);
		// 金额
		ListGridField field8 = new ListGridField("unitPriceAmount");
		Utils.formatPriceField(field8);
		field8.setAlign(Alignment.RIGHT);
		field8.setCanFilter(true);

		// 备注
		ListGridField field10 = new ListGridField("remarkText");
		field10.setCanFilter(true);
		this.setFields(field2, field4, field5, field7, field8, field10);
	}

}
